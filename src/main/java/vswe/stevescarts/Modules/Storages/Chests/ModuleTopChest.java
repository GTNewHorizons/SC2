package vswe.stevescarts.Modules.Storages.Chests;

import vswe.stevescarts.Carts.MinecartModular;

public class ModuleTopChest extends ModuleChest {

    public ModuleTopChest(MinecartModular cart) {
        super(cart);
    }

    @Override
    protected int getInventoryWidth() {
        return 6;
    }

    @Override
    protected int getInventoryHeight() {
        return 3;
    }

}
