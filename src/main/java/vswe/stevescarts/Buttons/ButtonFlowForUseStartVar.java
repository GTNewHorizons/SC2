package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowForUseStartVar extends ButtonFlowForUseVar {

    public ButtonFlowForUseStartVar(ModuleComputer module, LOCATION loc, boolean use) {
        super(module, loc, use);
    }

    protected boolean getUseVar(ComputerTask task) {
        return task.getFlowForUseStartVar();
    }

    protected void setUseVar(ComputerTask task, boolean val) {
        task.setFlowForUseStartVar(val);
    }

    protected String getName() {
        return "start";
    }

}
