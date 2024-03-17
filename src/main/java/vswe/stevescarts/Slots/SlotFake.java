package vswe.stevescarts.Slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import vswe.stevescarts.Helpers.TransferHandler.TRANSFER_TYPE;

public abstract class SlotFake extends SlotBase implements ISpecialItemTransferValidator, ISpecialSlotValidator {

    public SlotFake(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    @Override
    public int getSlotStackLimit() {
        return 0;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
        if (par2ItemStack != null && par1EntityPlayer != null && par1EntityPlayer.inventory != null) {
            par1EntityPlayer.inventory.setItemStack(null);
        }
    }

    @Override
    public boolean isItemValidForTransfer(ItemStack item, TRANSFER_TYPE type) {
        return false;
    }

    @Override
    public boolean isSlotValid() {
        return false;
    }

}
