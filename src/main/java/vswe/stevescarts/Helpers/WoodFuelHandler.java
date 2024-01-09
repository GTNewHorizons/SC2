package vswe.stevescarts.Helpers;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import vswe.stevescarts.Items.ItemCartComponent;
import vswe.stevescarts.Items.ModItems;

public class WoodFuelHandler implements IFuelHandler {

    public WoodFuelHandler() {
        GameRegistry.registerFuelHandler(this);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel != null && fuel.getItem() != null && fuel.getItem() == ModItems.component) {
            if (ItemCartComponent.isWoodLog(fuel)) {
                return 150;
            } else if (ItemCartComponent.isWoodTwig(fuel)) {
                return 50;
            }
        }

        return 0;
    }

}
