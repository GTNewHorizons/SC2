package vswe.stevescarts.Models.Cart;

import vswe.stevescarts.Modules.ModuleBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLiquidDrainer extends ModelCleaner {

    public String modelTexture(ModuleBase module) {
        return "/models/cleanerModelLiquid.png";
    }

    public ModelLiquidDrainer() {
        super();
    }

}
