package vswe.stevescarts.Modules.Storages.Chests;

import net.minecraft.item.ItemStack;

import mods.railcraft.api.carts.CartTools;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Modules.Storages.ModuleStorage;

public class ModuleTrainStorage extends ModuleStorage {

    public ModuleTrainStorage(MinecartModular cart) {
        super(cart);
    }

    public void pushItemStack(ItemStack iStack) {
        ItemStack ret = CartTools.transferHelper.pushStack(this.getCart(), iStack);
        if (ret == null) {
            iStack.stackSize = 0;
        } else {
            iStack.stackSize = ret.stackSize;
        }
    }
}
