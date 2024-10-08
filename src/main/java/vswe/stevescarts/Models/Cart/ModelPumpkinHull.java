package vswe.stevescarts.Models.Cart;

import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Modules.ModuleBase;

@SideOnly(Side.CLIENT)
public class ModelPumpkinHull extends ModelHull {

    @Override
    public ResourceLocation getResource(ModuleBase module) {
        return (module == null || isActive(module)) ? resourceactive : resourceidle;
    }

    private ResourceLocation resourceactive;
    private ResourceLocation resourceidle;

    public ModelPumpkinHull(ResourceLocation resourceactive, ResourceLocation resourceidle) {
        super(resourceactive);
        this.resourceactive = resourceactive;
        this.resourceidle = resourceidle;

    }

    private boolean isActive(ModuleBase module) {
        long time = module.getCart().worldObj.getWorldInfo().getWorldTime() % 24000;
        return time >= 12000 && time <= 18000;
    }

}
