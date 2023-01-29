package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonVarUseSecondVar extends ButtonVarUseVar {

    public ButtonVarUseSecondVar(ModuleComputer module, LOCATION loc, boolean use) {
        super(module, loc, use);
    }

    @Override
    protected boolean getUseVar(ComputerTask task) {
        return task.getVarUseSecondVar();
    }

    @Override
    protected void setUseVar(ComputerTask task, boolean val) {
        task.setVarUseSecondVar(val);
    }

    @Override
    protected String getName() {
        return "second";
    }

    @Override
    protected boolean isSecondValue() {
        return true;
    }
}
