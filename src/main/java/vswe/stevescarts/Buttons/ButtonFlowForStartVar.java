package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowForStartVar extends ButtonFlowForVar {

    public ButtonFlowForStartVar(ModuleComputer module, LOCATION loc, boolean increase) {
        super(module, loc, increase);
    }

    protected int getIndex(ComputerTask task) {
        return task.getFlowForStartVarIndex();
    }

    protected void setIndex(ComputerTask task, int val) {
        task.setFlowForStartVar(val);
    }

    protected String getName() {
        return "start";
    }

    protected boolean isVarVisible(ComputerTask task) {
        return task.getFlowForUseStartVar();
    }

}
