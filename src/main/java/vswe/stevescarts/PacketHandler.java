package vswe.stevescarts;

import static vswe.stevescarts.StevesCarts.CHANNEL;
import static vswe.stevescarts.StevesCarts.packetHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import vswe.stevescarts.Blocks.BlockCartAssembler;
import vswe.stevescarts.Blocks.ModBlocks;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Containers.ContainerBase;
import vswe.stevescarts.Containers.ContainerMinecart;
import vswe.stevescarts.Modules.ModuleBase;
import vswe.stevescarts.TileEntities.TileEntityBase;

public class PacketHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
        int idForCrash = -1;
        try {
            byte[] bytes = event.packet.payload().array();
            ByteArrayDataInput reader = ByteStreams.newDataInput(bytes);

            int id = reader.readByte();
            idForCrash = id;

            if (id == -1) {
                int x = reader.readInt();
                int y = reader.readInt();
                int z = reader.readInt();

                int len = bytes.length - 13;
                byte[] data = new byte[len];
                for (int i = 0; i < len; i++) {
                    data[i] = reader.readByte();
                }

                World world = player.worldObj;

                ((BlockCartAssembler) ModBlocks.CART_ASSEMBLER.getBlock()).updateMultiBlock(world, x, y, z);
            } else {
                int entityid = reader.readInt();
                int len = bytes.length - 5;
                byte[] data = new byte[len];
                for (int i = 0; i < len; i++) {
                    data[i] = reader.readByte();
                }

                World world = player.worldObj;
                MinecartModular cart = getCart(entityid, world);
                if (cart != null) {
                    receivePacketAtCart(cart, id, data, player);
                }
            }

        } catch (Exception ex) {
            System.out.println(
                    "The client failed to process a packet with "
                            + (idForCrash == -1 ? "unknown id" : "id " + idForCrash));
        }

    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) {
        EntityPlayer player = ((NetHandlerPlayServer) event.handler).playerEntity;
        int idForCrash = -1;
        try {
            byte[] bytes = event.packet.payload().array();
            ByteArrayDataInput reader = ByteStreams.newDataInput(bytes);

            int id = reader.readByte();
            idForCrash = id;

            World world = player.worldObj;

            if (player.openContainer instanceof ContainerPlayer) {
                int entityid = reader.readInt();
                int len = bytes.length - 5;
                byte[] data = new byte[len];
                for (int i = 0; i < len; i++) {
                    data[i] = reader.readByte();
                }
                MinecartModular cart = getCart(entityid, world);
                if (cart != null) {
                    receivePacketAtCart(cart, id, data, player);
                }
            } else {

                int len = bytes.length - 1;
                byte[] data = new byte[len];
                for (int i = 0; i < len; i++) {
                    data[i] = reader.readByte();
                }

                Container con = player.openContainer;

                if (con instanceof ContainerMinecart) {
                    ContainerMinecart conMC = (ContainerMinecart) con;
                    MinecartModular cart = conMC.cart;

                    receivePacketAtCart(cart, id, data, player);
                } else if (con instanceof ContainerBase) {
                    ContainerBase conBase = (ContainerBase) con;
                    TileEntityBase base = conBase.getTileEntity();
                    if (base != null) {
                        base.receivePacket(id, data, player);
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(
                    "The server failed to process a packet with "
                            + (idForCrash == -1 ? "unknown id" : "id " + idForCrash));
        }

    }

    private void receivePacketAtCart(MinecartModular cart, int id, byte[] data, EntityPlayer player) {
        for (ModuleBase module : cart.getModules()) {
            if (id >= module.getPacketStart() && id < module.getPacketStart() + module.totalNumberOfPackets()) {
                module.delegateReceivedPacket(id - module.getPacketStart(), data, player);
                break;
            }
        }
    }

    private MinecartModular getCart(int ID, World world) {
        for (Object e : world.loadedEntityList) {
            if (e instanceof Entity && ((Entity) e).getEntityId() == ID && e instanceof MinecartModular) {
                return (MinecartModular) e;
            }
        }
        return null;
    }

    public static void sendPacket(int id, byte[] extraData) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bs);

        try {
            ds.writeByte((byte) id);

            for (byte b : extraData) {
                ds.writeByte(b);
            }

        } catch (IOException e) {

        }

        packetHandler.sendToServer(createPacket(bs.toByteArray()));
    }

    private static FMLProxyPacket createPacket(byte[] bytes) {
        ByteBuf buf = Unpooled.copiedBuffer(bytes);
        return new FMLProxyPacket(buf, CHANNEL);
    }

    public static void sendPacket(MinecartModular cart, int id, byte[] extraData) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bs);

        try {
            ds.writeByte((byte) id);

            ds.writeInt(cart.getEntityId());

            for (byte b : extraData) {
                ds.writeByte(b);
            }

        } catch (IOException e) {

        }

        packetHandler.sendToServer(createPacket(bs.toByteArray()));
    }

    public static void sendPacketToPlayer(int id, byte[] data, EntityPlayer player, MinecartModular cart) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bs);

        try {
            ds.writeByte((byte) id);

            ds.writeInt(cart.getEntityId());

            for (byte b : data) {
                ds.writeByte(b);
            }

        } catch (IOException e) {

        }

        packetHandler.sendTo(createPacket(bs.toByteArray()), (EntityPlayerMP) player);
    }

    public static void sendBlockInfoToClients(World world, byte[] data, int x, int y, int z) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(bs);

        try {
            ds.writeByte((byte) -1);

            ds.writeInt(x);
            ds.writeInt(y);
            ds.writeInt(z);

            for (byte b : data) {
                ds.writeByte(b);
            }

        } catch (IOException e) {

        }

        packetHandler.sendToAllAround(
                createPacket(bs.toByteArray()),
                new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, 64));
    }

}
