package vswe.stevescarts.Helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Interfaces.GuiBase;

public interface ITankHolder {

    public ItemStack getInputContainer(int tankid);

    public void clearInputContainer(int tankid);

    public void addToOutputContainer(int tankid, ItemStack item);

    public void onFluidUpdated(int tankid);

    @SideOnly(Side.CLIENT)
    public void drawImage(int tankid, GuiBase gui, IIcon icon, int targetX, int targetY, int srcX, int srcY, int sizeX,
            int sizeY);
}
