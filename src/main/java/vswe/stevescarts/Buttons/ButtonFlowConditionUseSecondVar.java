package vswe.stevescarts.Buttons;

import net.minecraft.entity.player.EntityPlayer;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowConditionUseSecondVar extends ButtonFlowCondition {

    private boolean use;

    public ButtonFlowConditionUseSecondVar(ModuleComputer module, LOCATION loc, boolean use) {
        super(module, loc);
        this.use = use;
    }

    @Override
    public String toString() {
        return use ? "Use second variable" : "Use integer";
    }

    @Override
    public int texture() {
        return use ? 38 : 39;
    }

    @Override
    public boolean isEnabled() {
        for (ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
            if (use != task.getFlowConditionUseSecondVar()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onServerClick(EntityPlayer player, int mousebutton, boolean ctrlKey, boolean shiftKey) {
        for (ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
            task.setFlowConditionUseSecondVar(use);
        }
    }

}
