package vswe.stevescarts.Modules.Workers;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

import vswe.stevescarts.Buttons.ButtonBase;
import vswe.stevescarts.Buttons.ButtonControlInteger;
import vswe.stevescarts.Buttons.ButtonControlType;
import vswe.stevescarts.Buttons.ButtonControlUseVar;
import vswe.stevescarts.Buttons.ButtonControlVar;
import vswe.stevescarts.Buttons.ButtonFlowConditionInteger;
import vswe.stevescarts.Buttons.ButtonFlowConditionOperator;
import vswe.stevescarts.Buttons.ButtonFlowConditionSecondVar;
import vswe.stevescarts.Buttons.ButtonFlowConditionUseSecondVar;
import vswe.stevescarts.Buttons.ButtonFlowConditionVar;
import vswe.stevescarts.Buttons.ButtonFlowEndType;
import vswe.stevescarts.Buttons.ButtonFlowForEndInteger;
import vswe.stevescarts.Buttons.ButtonFlowForEndVar;
import vswe.stevescarts.Buttons.ButtonFlowForStartInteger;
import vswe.stevescarts.Buttons.ButtonFlowForStartVar;
import vswe.stevescarts.Buttons.ButtonFlowForStep;
import vswe.stevescarts.Buttons.ButtonFlowForUseEndVar;
import vswe.stevescarts.Buttons.ButtonFlowForUseStartVar;
import vswe.stevescarts.Buttons.ButtonFlowForVar;
import vswe.stevescarts.Buttons.ButtonFlowType;
import vswe.stevescarts.Buttons.ButtonInfoType;
import vswe.stevescarts.Buttons.ButtonInfoVar;
import vswe.stevescarts.Buttons.ButtonKeyboard;
import vswe.stevescarts.Buttons.ButtonLabelId;
import vswe.stevescarts.Buttons.ButtonProgramAdd;
import vswe.stevescarts.Buttons.ButtonProgramStart;
import vswe.stevescarts.Buttons.ButtonTask;
import vswe.stevescarts.Buttons.ButtonTaskType;
import vswe.stevescarts.Buttons.ButtonVarAdd;
import vswe.stevescarts.Buttons.ButtonVarFirstInteger;
import vswe.stevescarts.Buttons.ButtonVarFirstVar;
import vswe.stevescarts.Buttons.ButtonVarSecondInteger;
import vswe.stevescarts.Buttons.ButtonVarSecondVar;
import vswe.stevescarts.Buttons.ButtonVarType;
import vswe.stevescarts.Buttons.ButtonVarUseFirstVar;
import vswe.stevescarts.Buttons.ButtonVarUseSecondVar;
import vswe.stevescarts.Buttons.ButtonVarVar;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Computer.ComputerControl;
import vswe.stevescarts.Computer.ComputerInfo;
import vswe.stevescarts.Computer.ComputerProg;
import vswe.stevescarts.Computer.ComputerTask;
import vswe.stevescarts.Computer.ComputerVar;
import vswe.stevescarts.Computer.IWriting;
import vswe.stevescarts.Interfaces.GuiMinecart;

public class ModuleComputer extends ModuleWorker {

    public ModuleComputer(MinecartModular cart) {
        super(cart);
    }

    // lower numbers are prioritized
    public byte getWorkPriority() {
        return 5;
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public boolean hasSlots() {
        return false;
    }

    @Override
    public int guiWidth() {
        return 443;
    }

    @Override
    public int guiHeight() {
        return 250;
    }

    @Override
    public void drawForeground(GuiMinecart gui) {
        // drawString(gui,"Assembly", 8, 6, 0x404040);

        if (isWriting()) {
            drawString(gui, getWriting().getText(), 100, 6, 0x404040);
            drawString(gui, "Max Length: " + getWriting().getMaxLength(), 100, 18, 0x404040);
        }
    }

    @Override
    protected void loadButtons() {
        new ButtonProgramAdd(this, ButtonBase.LOCATION.OVERVIEW);
        new ButtonProgramStart(this, ButtonBase.LOCATION.OVERVIEW);
        for (int i = 0; i < 7; i++) {
            new ButtonTaskType(this, ButtonBase.LOCATION.PROGRAM, i);
        }

        new ButtonVarAdd(this, ButtonBase.LOCATION.PROGRAM);

        for (int i = 0; i < 11; i++) {
            new ButtonFlowType(this, ButtonBase.LOCATION.TASK, i);
        }

        new ButtonLabelId(this, ButtonBase.LOCATION.TASK, true);
        new ButtonLabelId(this, ButtonBase.LOCATION.TASK, false);

        // ======= CONDITION ========= //
        new ButtonFlowConditionVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowConditionVar(this, ButtonBase.LOCATION.TASK, true);
        for (int i = 0; i < 6; i++) {
            new ButtonFlowConditionOperator(this, ButtonBase.LOCATION.TASK, i);
        }
        new ButtonFlowConditionUseSecondVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowConditionUseSecondVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonFlowConditionInteger(this, ButtonBase.LOCATION.TASK, 1);
        new ButtonFlowConditionInteger(this, ButtonBase.LOCATION.TASK, -1);
        new ButtonFlowConditionInteger(this, ButtonBase.LOCATION.TASK, 10);
        new ButtonFlowConditionInteger(this, ButtonBase.LOCATION.TASK, -10);

        new ButtonFlowConditionSecondVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowConditionSecondVar(this, ButtonBase.LOCATION.TASK, true);

        // ======= FOR ========= //
        new ButtonFlowForVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowForVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonFlowForUseStartVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowForUseStartVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonFlowForStartInteger(this, ButtonBase.LOCATION.TASK, 1);
        new ButtonFlowForStartInteger(this, ButtonBase.LOCATION.TASK, -1);
        new ButtonFlowForStartInteger(this, ButtonBase.LOCATION.TASK, 10);
        new ButtonFlowForStartInteger(this, ButtonBase.LOCATION.TASK, -10);

        new ButtonFlowForStartVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowForStartVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonFlowForUseEndVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowForUseEndVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonFlowForEndInteger(this, ButtonBase.LOCATION.TASK, 1);
        new ButtonFlowForEndInteger(this, ButtonBase.LOCATION.TASK, -1);
        new ButtonFlowForEndInteger(this, ButtonBase.LOCATION.TASK, 10);
        new ButtonFlowForEndInteger(this, ButtonBase.LOCATION.TASK, -10);

        new ButtonFlowForEndVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowForEndVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonFlowForStep(this, ButtonBase.LOCATION.TASK, false);
        new ButtonFlowForStep(this, ButtonBase.LOCATION.TASK, true);

        // ======= END ========= //
        for (int i = 0; i < 4; i++) {
            new ButtonFlowEndType(this, ButtonBase.LOCATION.TASK, i);
        }

        // ======= VAR ========= //
        for (int i = 0; i < 18; i++) {
            new ButtonVarType(this, ButtonBase.LOCATION.TASK, i);
        }

        new ButtonVarVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonVarVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonVarUseFirstVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonVarUseFirstVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonVarFirstInteger(this, ButtonBase.LOCATION.TASK, 1);
        new ButtonVarFirstInteger(this, ButtonBase.LOCATION.TASK, -1);
        new ButtonVarFirstInteger(this, ButtonBase.LOCATION.TASK, 10);
        new ButtonVarFirstInteger(this, ButtonBase.LOCATION.TASK, -10);

        new ButtonVarFirstVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonVarFirstVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonVarUseSecondVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonVarUseSecondVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonVarSecondInteger(this, ButtonBase.LOCATION.TASK, 1);
        new ButtonVarSecondInteger(this, ButtonBase.LOCATION.TASK, -1);
        new ButtonVarSecondInteger(this, ButtonBase.LOCATION.TASK, 10);
        new ButtonVarSecondInteger(this, ButtonBase.LOCATION.TASK, -10);

        new ButtonVarSecondVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonVarSecondVar(this, ButtonBase.LOCATION.TASK, true);

        // ======= CONTROL ========= //
        new ButtonControlType(this, ButtonBase.LOCATION.TASK, (byte) 0);
        ComputerControl.createButtons(getCart(), this);

        new ButtonControlUseVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonControlUseVar(this, ButtonBase.LOCATION.TASK, true);

        new ButtonControlInteger(this, ButtonBase.LOCATION.TASK, 1);
        new ButtonControlInteger(this, ButtonBase.LOCATION.TASK, -1);
        new ButtonControlInteger(this, ButtonBase.LOCATION.TASK, 10);
        new ButtonControlInteger(this, ButtonBase.LOCATION.TASK, -10);

        new ButtonControlVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonControlVar(this, ButtonBase.LOCATION.TASK, true);

        // ======= INFO ========= //
        new ButtonInfoType(this, ButtonBase.LOCATION.TASK, (byte) 0);
        ComputerInfo.createButtons(getCart(), this);

        new ButtonInfoVar(this, ButtonBase.LOCATION.TASK, false);
        new ButtonInfoVar(this, ButtonBase.LOCATION.TASK, true);

        // ======= TASKS ========= //
        for (int i = 0; i < 21; i++) {
            new ButtonTask(this, ButtonBase.LOCATION.FLOATING, i);
        }

        // ======= KEYBOARD ========= //
        ButtonKeyboard.generateKeyboard(this);

    }

    @Override
    public boolean useButtons() {
        return true;
    }

    private IWriting writing;

    public boolean isWriting() {
        return writing != null;
    }

    public IWriting getWriting() {
        return writing;
    }

    public void setWriting(IWriting val) {
        writing = val;
    }

    private short info;

    /**
     * b0 - shift b1 - caps
     * 
     * b2-b15 - *unused*
     **/

    public void flipShift() {
        info ^= 1;
    }

    public void flipCaps() {
        info ^= 2;
    }

    public boolean getShift() {
        return (info & 1) != 0;
    }

    public boolean getCaps() {
        return (info & 2) != 0;
    }

    public boolean isLower() {
        return getShift() == getCaps();
    }

    public void disableShift() {
        info &= ~1;
    }

    private ArrayList<ComputerProg> programs = new ArrayList<ComputerProg>();
    private ComputerProg editProg;
    private ArrayList<ComputerTask> editTasks = new ArrayList<ComputerTask>();

    public ComputerProg getCurrentProg() {
        return editProg;
    }

    public ArrayList<ComputerTask> getSelectedTasks() {
        return editTasks;
    }

    public void setCurrentProg(ComputerProg prog) {
        editProg = prog;
    }

    private ComputerProg activeProg;

    public void setActiveProgram(ComputerProg prog) {
        activeProg = prog;
    }

    public ComputerProg getActiveProgram() {
        return activeProg;
    }

    // return true when the work is done, false allow other modules to continue the work
    public boolean work() {

        if (activeProg != null) {
            if (doPreWork()) {
                startWorking(activeProg.getRunTime());
            } else {
                if (!activeProg.run()) {
                    activeProg = null;
                }
                stopWorking();
            }
        }

        return true;
    }

    /**
     * Called every tick, here the necessary actions should be taken
     **/
    public void update() {
        // call the method from the super class, this will do all ordinary things first
        super.update();

    }

    protected void receivePacket(int id, byte[] data, EntityPlayer player) {

    }

    public int numberOfPackets() {
        return 0;
    }

    public int numberOfGuiData() {
        return headerSize + programHeaderSize + taskMaxCount * taskSize + varMaxCount * varSize;
    }

    public void activationChanged() {
        editTasks.clear();

        if (editProg != null) {
            for (ComputerTask task : editProg.getTasks()) {
                if (task.getIsActivated()) {
                    editTasks.add(task);
                }
            }
        }
    }

    private static final int headerSize = 1;
    private static final int programHeaderSize = 3;
    private static final int taskMaxCount = 256;
    private static final int varMaxCount = 63;
    private static final int taskSize = 2;
    private static final int varSize = 5;

    /**
     * 0 - global info
     * 
     * Program 0 - program data (its info short) 1 - number of tasks << 8, number of variables 2 - active task
     * 
     * For every task (0-255): 0-1 - task data (its info int)
     * 
     * For every variable(0-30): 0 - variable data (its info short) 1 - its short val 2-4 - its 6 char name
     * 
     * 
     **/
    protected void checkGuiData(Object[] info) {
        updateGuiData(info, 0, this.info);

        if (editProg != null) {
            updateGuiData(info, headerSize + 0, editProg.getInfo());

            int tasks = editProg.getTasks().size();
            int vars = editProg.getVars().size();

            updateGuiData(info, headerSize + 1, (short) (tasks << 8 | vars));

            if (editProg == activeProg) {
                updateGuiData(info, headerSize + 2, (short) activeProg.getActiveId());
            } else {
                updateGuiData(info, headerSize + 2, (short) 256);
            }

            for (int taskId = 0; taskId < tasks; taskId++) {
                ComputerTask theTask = editProg.getTasks().get(taskId);
                for (int internalId = 0; internalId < taskSize; internalId++) {
                    updateGuiData(
                            info,
                            headerSize + programHeaderSize + taskId * taskSize + internalId,
                            theTask.getInfo(internalId));
                }
            }

            for (int varId = 0; varId < vars; varId++) {
                ComputerVar theVar = editProg.getVars().get(varId);
                for (int internalId = 0; internalId < varSize; internalId++) {
                    updateGuiData(
                            info,
                            headerSize + programHeaderSize + taskMaxCount * taskSize + varId * varSize + internalId,
                            theVar.getInfo(internalId));
                }
            }
        } else {
            updateGuiData(info, headerSize + 0, (short) 0);
        }
    }

    public void receiveGuiData(int id, short data) {
        System.out.println("ID " + id + " Data " + data);
        if (id == 0) {
            info = data;
        } else if (id == headerSize + 0) {
            if (data == 0) {
                editProg = null;
            } else {
                if (editProg == null) {
                    editProg = new ComputerProg(this);
                }

                editProg.setInfo(data);
            }
        } else if (editProg != null) {

            if (id == headerSize + 1) {
                int tasks = (data >> 8) & 255;
                int vars = data & 255;

                editProg.setTaskCount(tasks);
                editProg.setVarCount(vars);
            } else if (id == headerSize + 2) {
                if (data >= 0 && data < 256) {
                    activeProg = editProg;
                    editProg.setActiveId(data);
                } else {
                    activeProg = null;
                    editProg.setActiveId(0);
                }
            } else {
                int taskId = id - headerSize - programHeaderSize;
                if (taskId < taskMaxCount * taskSize) {
                    int task = taskId / taskSize;
                    int taskInternalPos = taskId % taskSize;

                    if (task >= 0 && task < editProg.getTasks().size()) {
                        ComputerTask theTask = editProg.getTasks().get(task);
                        theTask.setInfo(taskInternalPos, data);
                    }

                } else {
                    int varId = taskId - taskMaxCount * taskSize;

                    int var = varId / varSize;
                    int varInternalPos = varId % varSize;

                    if (var >= 0 && var < editProg.getVars().size()) {
                        ComputerVar theVar = editProg.getVars().get(var);
                        theVar.setInfo(varInternalPos, data);
                    }
                }

            }
        }
    }

}
