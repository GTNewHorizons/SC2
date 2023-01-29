package vswe.stevescarts.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import vswe.stevescarts.Items.ModItems;

public class SlotCakeDynamite extends SlotCake implements ISlotExplosions {

    public SlotCakeDynamite(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public boolean isItemValid(ItemStack itemstack) {
        return super.isItemValid(itemstack)
                || (itemstack != null && itemstack.getItem() == ModItems.component && itemstack.getItemDamage() == 6);
    }

}
