package vswe.stevescarts.Modules;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public interface ICropModule {

    public boolean isSeedValid(ItemStack seed);

    public Block getCropFromSeed(ItemStack seed);

    public boolean isReadyToHarvest(int x, int y, int z);

    public default List<ItemStack> harvestCrop(int x, int y, int z, int fortune) {
        Block block = getWorld().getBlock(x, y, z);
        int m = getWorld().getBlockMetadata(x, y, z);

        getWorld().setBlockMetadataWithNotify(x, y, z, 0, 3);

        return block.getDrops(getWorld(), x, y, z, m, fortune);
    }

    public default void placeCrop(int x, int y, int z, ItemStack seed) {
        Block cropblock = getCropFromSeed(seed);
        getWorld().setBlock(x, y, z, cropblock);
    }

    public default boolean isSeedPlaceable(int x, int y, int z, ItemStack seed) {
        Block soilblock = getWorld().getBlock(x, y - 1, z);
        Block cropblock = getCropFromSeed(seed);
        return cropblock instanceof IPlantable && getWorld().isAirBlock(x, y, z)
                && soilblock.canSustainPlant(getWorld(), x, y, z, ForgeDirection.UP, ((IPlantable) cropblock));
    }

    public World getWorld();

}
