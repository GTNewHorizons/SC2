package vswe.stevescarts.Modules.Addons.Plants;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Modules.Addons.ModuleAddon;
import vswe.stevescarts.Modules.ICropModule;

public class ModuleModCrops extends ModuleAddon implements ICropModule {

    public ModuleModCrops(MinecartModular cart) {
        super(cart);
    }

    @Override
    public boolean isSeedValid(ItemStack seed) {

        UniqueIdentifier uniqueIdentifier = GameRegistry.findUniqueIdentifierFor(seed.getItem());

        if (seed.getItem() == Items.melon_seeds || seed.getItem() == Items.pumpkin_seeds
                || seed.getItem() == Items.nether_wart) {
            return false;
        }

        // tb seeds need more work
        if (uniqueIdentifier.modId.equals("thaumicbases")) {
            return false;
        }

        // Natura crops don't work well with the SC2 approach
        if (uniqueIdentifier.modId.equals("Natura")) {
            return false;
        }
        return seed.getItem() instanceof IPlantable;
    }

    @Override
    public Block getCropFromSeed(ItemStack seed) {
        return ((IPlantable) seed.getItem()).getPlant(getCart().worldObj, 0, 0, 0);
    }

    @Override
    public boolean isReadyToHarvest(int x, int y, int z) {
        Block block = getCart().worldObj.getBlock(x, y, z);
        int m = getCart().worldObj.getBlockMetadata(x, y, z);
        UniqueIdentifier uniqueIdentifier = GameRegistry.findUniqueIdentifierFor(block);

        if (!(block instanceof IGrowable)) {
            return false;
        }

        // tb seeds need more work
        if (uniqueIdentifier.modId.equals("thaumicbases")) {
            return false;
        }

        // Natura crops don't work well with the SC2 approach
        if (uniqueIdentifier.modId.equals("Natura")) {
            return false;
        }

        // witchery compatibility
        if (uniqueIdentifier.modId.equals("witchery")) {
            if (uniqueIdentifier.name.equals("garlicplant") && m == 5) {
                return true;
            } else if (m == 4) {
                return true;
            }
        }

        // default for crops
        if (m == 7) {
            return true;
        }

        return false;
    }

    @Override
    public World getWorld() {
        return getCart().worldObj;
    }
}
