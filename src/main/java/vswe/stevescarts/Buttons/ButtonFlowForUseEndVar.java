package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowForUseEndVar extends ButtonFlowForUseVar {

    public ButtonFlowForUseEndVar(ModuleComputer module, LOCATION loc, boolean use) {
        super(module, loc, use);
    }

    protected boolean getUseVar(ComputerTask task) {
        return task.getFlowForUseEndVar();
    }

    protected void setUseVar(ComputerTask task, boolean val) {
        task.setFlowForUseEndVar(val);
    }

    protected String getName() {
        return "end";
    }

}
