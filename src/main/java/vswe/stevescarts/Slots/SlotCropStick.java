package vswe.stevescarts.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public class SlotCropStick extends SlotBase {

    public SlotCropStick(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public boolean isItemValid(ItemStack itemstack) {
        UniqueIdentifier uniqueIdentifier = GameRegistry.findUniqueIdentifierFor(itemstack.getItem());
        return uniqueIdentifier.modId.equals("IC2") && uniqueIdentifier.name.equals("blockCrop");
    }
}
