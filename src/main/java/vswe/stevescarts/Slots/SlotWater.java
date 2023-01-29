package vswe.stevescarts.Slots;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotWater extends SlotBase {

    public SlotWater(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public int getSlotStackLimit() {
        return 1;
    }

    public boolean isItemValid(ItemStack itemstack) {
        return itemstack.getItem() == Items.water_bucket;
    }
}
