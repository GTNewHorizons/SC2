package vswe.stevescarts.Slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import vswe.stevescarts.Helpers.TransferHandler.TRANSFER_TYPE;

public class SlotCartCrafterResult extends SlotBase implements ISpecialItemTransferValidator {

    public SlotCartCrafterResult(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
        return false;
    }

    @Override
    public boolean isItemValidForTransfer(ItemStack item, TRANSFER_TYPE type) {
        return false;
    }

    @Override
    public int getSlotStackLimit() {
        return 0;
    }

}
