package vswe.stevescarts.Modules.Engines;

import vswe.stevescarts.Carts.MinecartModular;

public class ModuleElectricHV extends ModuleElectricBase {

    public ModuleElectricHV(MinecartModular cart) {
        super(cart);
    }

    @Override
    public int getEngineTier() {
        return EngineTier.HV;
    }

    @Override
    protected int getMaxCharge() {
        return 60000;
    }

    @Override
    public void consumeFuel(int comsumption) {
        super.consumeFuel(comsumption * 4);
    }

    @Override
    public int getFuelLevel() {
        return super.getFuelLevel() / 4;
    }

    @Override
    protected void checkGuiData(Object[] info) {
        updateGuiData(info, 0, (short) (getFuelLevel() * 4));
    }
}
