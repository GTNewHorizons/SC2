package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonVarUseFirstVar extends ButtonVarUseVar {

    public ButtonVarUseFirstVar(ModuleComputer module, LOCATION loc, boolean use) {
        super(module, loc, use);
    }

    @Override
    protected boolean getUseVar(ComputerTask task) {
        return task.getVarUseFirstVar();
    }

    @Override
    protected void setUseVar(ComputerTask task, boolean val) {
        task.setVarUseFirstVar(val);
    }

    @Override
    protected String getName() {
        return "first";
    }

}
