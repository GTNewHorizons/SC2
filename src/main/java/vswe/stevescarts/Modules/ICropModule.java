package vswe.stevescarts.Modules;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface ICropModule {

    public boolean isSeedValid(ItemStack seed);

    public Block getCropFromSeed(ItemStack seed);

    public boolean isReadyToHarvest(int x, int y, int z);

    public List<ItemStack> harvestCrop(int x, int y, int z, int fortune);

    public void placeCrop(int x, int y, int z, ItemStack seed);

    public boolean isSeedPlaceable(int x, int y, int z, ItemStack seed);
}
