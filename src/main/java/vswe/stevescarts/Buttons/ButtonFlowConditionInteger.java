package vswe.stevescarts.Buttons;

import net.minecraft.entity.player.EntityPlayer;

import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Modules.Workers.ModuleComputer;

public class ButtonFlowConditionInteger extends ButtonFlowCondition {

    private int dif;

    public ButtonFlowConditionInteger(ModuleComputer module, LOCATION loc, int dif) {
        super(module, loc);
        this.dif = dif;
    }

    @Override
    public String toString() {
        if (dif < 0) {
            return "Decrease by " + (-1 * dif);
        } else {
            return "Increase by " + dif;
        }
    }

    @Override
    public int texture() {
        if (dif == 1) {
            return 40;
        } else if (dif == -1) {
            return 41;
        } else if (dif == 10) {
            return 42;
        } else if (dif == -10) {
            return 43;
        }

        return super.texture();
    }

    @Override
    public boolean isVisible() {
        if (((ModuleComputer) module).getSelectedTasks() != null) {
            for (ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
                if (task.getFlowConditionUseSecondVar()) {
                    return false;
                }
            }
        }

        return super.isVisible();
    }

    @Override
    public boolean isEnabled() {
        for (ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
            if (-128 <= task.getFlowConditionInteger() + dif && task.getFlowConditionInteger() + dif <= 127) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onServerClick(EntityPlayer player, int mousebutton, boolean ctrlKey, boolean shiftKey) {
        for (ComputerTask task : ((ModuleComputer) module).getSelectedTasks()) {
            task.setFlowConditionInteger(task.getFlowConditionInteger() + dif);
        }
    }

}
