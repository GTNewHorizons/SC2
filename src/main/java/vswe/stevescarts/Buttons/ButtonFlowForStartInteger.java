package vswe.stevescarts.Buttons;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowForStartInteger extends ButtonFlowForInteger {

    public ButtonFlowForStartInteger(ModuleComputer module, LOCATION loc, int dif) {
        super(module, loc, dif);
    }

    @Override
    protected String getName() {
        return "start";
    }

    @Override
    protected boolean isVarVisible(ComputerTask task) {
        return task.getFlowForUseStartVar();
    }

    @Override
    protected int getInteger(ComputerTask task) {
        return task.getFlowForStartInteger();
    }

    @Override
    protected void setInteger(ComputerTask task, int val) {
        task.setFlowForStartInteger(val);
    }

}
