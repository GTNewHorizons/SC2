package vswe.stevescarts.Modules.Storages.Chests;

import vswe.stevescarts.Carts.MinecartModular;

public class ModuleSideChests extends ModuleChest {

    public ModuleSideChests(MinecartModular cart) {
        super(cart);
    }

    @Override
    protected int getInventoryWidth() {
        return 5;
    }

    @Override
    protected int getInventoryHeight() {
        return 3;
    }

}
