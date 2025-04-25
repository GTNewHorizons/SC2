package vswe.stevescarts.Modules.Engines;

import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.api.electricity.IElectricMinecart;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Helpers.Localization;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Interfaces.GuiMinecart;

public class ModuleElectricBase extends ModuleEngine implements IElectricMinecart {

    protected int getMaxCharge() {
        return 7500;
    }

    private final ChargeHandler chargeHandler = new ChargeHandler(
            this.getCart(),
            ChargeHandler.Type.USER,
            getMaxCharge());

    private int clientFuelLevel = 0;

    public ModuleElectricBase(MinecartModular cart) {
        super(cart);
    }

    @Override
    public int getFuelLevel() {
        return (int) getChargeHandler().getCharge();
    }

    @Override
    protected void loadFuel() {}

    public void consumeFuel(int comsumption) {
        getChargeHandler().removeCharge(comsumption);
    }

    public void setFuelLevel(int val) {
        getChargeHandler().setCharge(val);
    }

    @Override
    public int getTotalFuel() {
        return (int) getChargeHandler().getCapacity();
    }

    @Override
    public float[] getGuiBarColor() {
        return new float[] { 0F, 0F, 0F };
    }

    @Override
    public void drawForeground(GuiMinecart gui) {
        drawString(gui, Localization.MODULES.ENGINES.ELECTRIC.translate(), 8, 6, 0x404040);
        String strfuel = Localization.MODULES.ENGINES.NO_CHARGE.translate();

        if (clientFuelLevel > 0) {
            strfuel = Localization.MODULES.ENGINES.CHARGE.translate(String.valueOf(clientFuelLevel));
        }

        drawString(gui, strfuel, 8, 34, 0x404040);
        // drawString(gui,"Consumption: " + getCart().getConsumption(), 8, 50, 0x404040);
    }

    @Override
    protected void initPriorityButton() {
        priorityButton = new int[] { 88, 7, 16, 16 };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawBackground(GuiMinecart gui, int x, int y) {
        super.drawBackground(gui, x, y);

        ResourceHelper.bindResource("/gui/energy.png");
        drawBox(gui, 0, 0, 1F);
        drawBox(gui, 0, 15, (float) (clientFuelLevel / getChargeHandler().getCapacity()));
    }

    private final int[] chargeRect = new int[] { 10, 15, 52, 15 };

    private void drawBox(GuiMinecart gui, int u, int v, float mult) {
        int w = (int) (chargeRect[2] * mult);
        if (w > 0) {
            drawImage(gui, chargeRect[0], chargeRect[1], u, v, w, chargeRect[3]);
        }
    }

    @Override
    public int guiHeight() {
        return 45;
    }

    @Override
    public int guiWidth() {
        return 110;
    }

    @Override
    public int numberOfGuiData() {
        return 1;
    }

    @Override
    protected void checkGuiData(Object[] info) {
        updateGuiData(info, 0, (short) getFuelLevel());
    }

    @Override
    public void receiveGuiData(int id, short data) {
        if (id == 0) {
            clientFuelLevel = data;
            if (clientFuelLevel < 0) {
                clientFuelLevel = clientFuelLevel + 65536;
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if (!getCart().worldObj.isRemote) {
            getChargeHandler().tick();
        }
    }

    @Override
    protected void Save(NBTTagCompound tagCompound, int id) {
        super.Save(tagCompound, id);
        chargeHandler.writeToNBT(tagCompound);
    }

    @Override
    protected void Load(NBTTagCompound tagCompound, int id) {
        super.Load(tagCompound, id);
        chargeHandler.readFromNBT(tagCompound);
    }

    @Override
    public boolean hasSlots() {
        return false;
    }

    public ChargeHandler getChargeHandler() {
        return chargeHandler;
    }

}
