package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonVarSecondInteger extends ButtonVarInteger {

    public ButtonVarSecondInteger(ModuleComputer module, LOCATION loc, int dif) {
        super(module, loc, dif);
    }

    @Override
    protected String getName() {
        return "second";
    }

    @Override
    protected boolean isVarVisible(ComputerTask task) {
        return task.getVarUseSecondVar();
    }

    @Override
    protected int getInteger(ComputerTask task) {
        return task.getVarSecondInteger();
    }

    @Override
    protected void setInteger(ComputerTask task, int val) {
        task.setVarSecondInteger(val);
    }

    @Override
    protected boolean isSecondValue() {
        return true;
    }
}
