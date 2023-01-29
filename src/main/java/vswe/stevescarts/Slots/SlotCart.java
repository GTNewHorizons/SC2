package vswe.stevescarts.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import vswe.stevescarts.Items.ModItems;

public class SlotCart extends Slot {

    public SlotCart(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == ModItems.carts
                && itemstack.getTagCompound() != null
                && !itemstack.getTagCompound().hasKey("maxTime");
    }
}
