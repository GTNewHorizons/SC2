package vswe.stevescarts.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import vswe.stevescarts.Modules.Workers.Tools.ModuleWoodcutter;

public class SlotSapling extends SlotBase {

    private ModuleWoodcutter module;

    public SlotSapling(IInventory iinventory, ModuleWoodcutter module, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.module = module;
    }

    public boolean isItemValid(ItemStack itemstack) {
        return module.isSaplingHandler(itemstack);
    }
}
