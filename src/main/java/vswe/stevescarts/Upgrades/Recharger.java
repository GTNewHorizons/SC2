package vswe.stevescarts.Upgrades;

import vswe.stevescarts.Helpers.Localization;
import vswe.stevescarts.TileEntities.TileEntityUpgrade;

public class Recharger extends RechargerBase {

    protected int amount;

    public Recharger(int amount) {
        this.amount = amount;
    }

    @Override
    protected int getAmount(TileEntityUpgrade upgrade) {
        return amount;
    }

    @Override
    protected boolean canGenerate(TileEntityUpgrade upgrade) {
        return true;
    }

    @Override
    public String getName() {
        return Localization.UPGRADES.GENERATOR.translate(String.valueOf(amount), String.valueOf(amount));
    }

}
