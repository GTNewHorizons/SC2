package vswe.stevescarts.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import vswe.stevescarts.StevesCarts;
import vswe.stevescarts.TileEntities.TileEntityCargo;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCargoManager extends BlockContainerBase {

    public BlockCargoManager() {
        super(Material.rock);
        setCreativeTab(StevesCarts.tabsSC2Blocks);
    }

    private IIcon topIcon;
    private IIcon botIcon;
    private IIcon redIcon;
    private IIcon blueIcon;
    private IIcon greenIcon;
    private IIcon yellowIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0:
                return botIcon;
            case 1:
                return topIcon;
            case 2:
                return yellowIcon;
            case 3:
                return blueIcon;
            case 4:
                return greenIcon;
            default:
                return redIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        topIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":cargo_manager_top");
        botIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":cargo_manager_bot");
        redIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":cargo_manager_red");
        blueIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":cargo_manager_blue");
        greenIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":cargo_manager_green");
        yellowIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":cargo_manager_yellow");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntityCargo tileEntity = (TileEntityCargo) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            for (int slot = 0; slot < tileEntity.getSizeInventory(); ++slot) {
                ItemStack stack = tileEntity.getStackInSlot(slot);

                if (stack != null) {
                    float var10 = world.rand.nextFloat() * 0.8F + 0.1F;
                    float var11 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem droppedStack;

                    for (float var12 = world.rand.nextFloat() * 0.8F + 0.1F; stack.stackSize > 0; world
                            .spawnEntityInWorld(droppedStack)) {
                        int var13 = world.rand.nextInt(21) + 10;

                        if (var13 > stack.stackSize) {
                            var13 = stack.stackSize;
                        }

                        stack.stackSize -= var13;
                        droppedStack = new EntityItem(
                                world,
                                (double) ((float) x + var10),
                                (double) ((float) y + var11),
                                (double) ((float) z + var12),
                                new ItemStack(stack.getItem(), var13, stack.getItemDamage()));
                        float var15 = 0.05F;
                        droppedStack.motionX = (double) ((float) world.rand.nextGaussian() * var15);
                        droppedStack.motionY = (double) ((float) world.rand.nextGaussian() * var15 + 0.2F);
                        droppedStack.motionZ = (double) ((float) world.rand.nextGaussian() * var15);

                        if (stack.hasTagCompound()) {
                            droppedStack.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                        }
                    }
                }
            }
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7,
            float par8, float par9) {
        if (entityplayer.isSneaking()) {
            return false;
        }

        if (world.isRemote) {
            return true;
        }

        FMLNetworkHandler.openGui(entityplayer, StevesCarts.instance, 1, world, x, y, z);

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityCargo();
    }

}
