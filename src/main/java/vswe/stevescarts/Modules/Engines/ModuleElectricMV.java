package vswe.stevescarts.Modules.Engines;

import vswe.stevescarts.Carts.MinecartModular;

public class ModuleElectricMV extends ModuleElectricBase {

    public ModuleElectricMV(MinecartModular cart) {
        super(cart);
    }

    @Override
    public int getEngineTier() {
        return EngineTier.MV;
    }

    @Override
    protected int getMaxCharge() {
        return 15000;
    }

    @Override
    public void consumeFuel(int comsumption) {
        super.consumeFuel(comsumption * 2);
    }

    @Override
    public int getFuelLevel() {
        return super.getFuelLevel() / 2;
    }

    @Override
    protected void checkGuiData(Object[] info) {
        updateGuiData(info, 0, (short) (getFuelLevel() * 2));
    }
}
