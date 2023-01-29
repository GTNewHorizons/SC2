package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowForEndVar extends ButtonFlowForVar {

    public ButtonFlowForEndVar(ModuleComputer module, LOCATION loc, boolean increase) {
        super(module, loc, increase);
    }

    protected int getIndex(ComputerTask task) {
        return task.getFlowForEndVarIndex();
    }

    protected void setIndex(ComputerTask task, int val) {
        task.setFlowForEndVar(val);
    }

    protected String getName() {
        return "end";
    }

    protected boolean isVarVisible(ComputerTask task) {
        return task.getFlowForUseEndVar();
    }

}
