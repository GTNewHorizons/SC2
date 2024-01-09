package vswe.stevescarts.Blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.StevesCarts;

public class BlockRailJunction extends BlockSpecialRailBase {

    private IIcon normalIcon;
    private IIcon cornerIcon;

    public BlockRailJunction() {
        super(false);
        setCreativeTab(StevesCarts.tabsSC2Blocks);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return meta >= 6 ? cornerIcon : normalIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        normalIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":" + "junction_rail");
        cornerIcon = register.registerIcon(StevesCarts.instance.textureHeader + ":" + "junction_rail" + "_corner");
    }

    /**
     * Return true if the rail can go up and down slopes
     */
    @Override
    public boolean canMakeSlopes(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    /*
     * Return the rails metadata
     */
    @Override
    public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z) {
        if (cart instanceof MinecartModular) {
            MinecartModular modularCart = (MinecartModular) cart;

            int meta = modularCart.getRailMeta(x, y, z);

            if (meta != -1) {
                return meta;
            }
        }
        return world.getBlockMetadata(x, y, z);
    }

}
