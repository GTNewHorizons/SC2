package vswe.stevescarts.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockContainerBase extends BlockContainer implements IBlockBase {
    private String unlocalizedName;
    protected BlockContainerBase(Material material) {
        super(material);
    }

    @Override
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public void setUnlocalizedName(String name) {
        this.unlocalizedName = name;
    }

}
