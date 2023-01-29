package vswe.stevescarts.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import vswe.stevescarts.StevesCarts;
import vswe.stevescarts.TileEntities.TileEntityActivator;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockActivator extends BlockContainerBase {

    public BlockActivator() {
        super(Material.rock);
        setCreativeTab(StevesCarts.tabsSC2Blocks);
    }

    private IIcon topIcon;
    private IIcon botIcon;
    private IIcon sideIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0:
                return botIcon;
            case 1:
                return topIcon;
            default:
                return sideIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        topIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":module_toggler_top");
        botIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":module_toggler_bot");
        sideIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":module_toggler_side");
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

        FMLNetworkHandler.openGui(entityplayer, StevesCarts.instance, 4, world, x, y, z);

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityActivator();
    }

}
