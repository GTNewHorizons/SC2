package vswe.stevescarts.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Items.ModItems;
import vswe.stevescarts.StevesCarts;

public class BlockMetalStorage extends Block implements IBlockBase {

    public BlockMetalStorage() {
        super(Material.iron);
        this.setCreativeTab(StevesCarts.tabsSC2Blocks);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        meta %= ModItems.storages.icons.length;

        return ModItems.storages.icons[meta];
    }

    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        // do nothing here
    }

    private String unlocalizedName;

    @Override
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public void setUnlocalizedName(String name) {
        this.unlocalizedName = name;
    }

}
