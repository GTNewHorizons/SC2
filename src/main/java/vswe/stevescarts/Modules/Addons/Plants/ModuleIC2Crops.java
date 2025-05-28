package vswe.stevescarts.Modules.Addons.Plants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import ic2.core.crop.TileEntityCrop;
import ic2.core.item.ItemCropSeed;
import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Helpers.Localization;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Interfaces.GuiMinecart;
import vswe.stevescarts.Modules.Addons.ModuleAddon;
import vswe.stevescarts.Modules.ICropModule;
import vswe.stevescarts.Slots.SlotBase;
import vswe.stevescarts.Slots.SlotCropStick;

public class ModuleIC2Crops extends ModuleAddon implements ICropModule {

    private boolean harvestOptimal = true;

    public ModuleIC2Crops(MinecartModular cart) {
        super(cart);
    }

    @Override
    public boolean isSeedValid(ItemStack seed) {

        UniqueIdentifier uniqueIdentifier = GameRegistry.findUniqueIdentifierFor(seed.getItem());

        return uniqueIdentifier.modId.equals("IC2") && uniqueIdentifier.name.equals("itemCropSeed");
    }

    @Override
    public Block getCropFromSeed(ItemStack seed) {
        TileEntityCrop te = new TileEntityCrop();
        return te.blockType;
    }

    @Override
    public boolean isReadyToHarvest(int x, int y, int z) {
        TileEntity te = getCart().worldObj.getTileEntity(x, y, z);
        if (!(te instanceof ICropTile)) {
            return false;
        }
        if (((ICropTile) te).getCrop() == null) {
            return false;
        }
        if (!harvestOptimal) {
            return ((ICropTile) te).getCrop().canBeHarvested((ICropTile) te);
        }
        return ((ICropTile) te).getSize() >= ((ICropTile) te).getCrop().getOptimalHavestSize((ICropTile) te);
    }

    @Override
    public List<ItemStack> harvestCrop(int x, int y, int z, int fortune) {
        TileEntity te = getCart().worldObj.getTileEntity(x, y, z);
        if (!(te instanceof ICropTile)) {
            return null;
        }
        ItemStack[] items = ((ICropTile) te).harvest_automated(harvestOptimal);
        return items != null ? Arrays.asList(items) : new ArrayList<ItemStack>();
    }

    @Override
    public int guiHeight() {
        return 60;
    }

    @Override
    public int guiWidth() {
        return 100;
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public void mouseClicked(GuiMinecart gui, int x, int y, int button) {
        if (inRect(x, y, buttonRect)) {
            sendPacket(0, (byte) (harvestOptimal ? 0 : 1));
        }
    }

    @Override
    public int getInventorySize() {
        return 3;
    }

    @Override
    public int numberOfGuiData() {
        return 1;
    }

    @Override
    protected void checkGuiData(Object[] info) {
        updateGuiData(info, 0, (short) (harvestOptimal ? 1 : 0));
    }

    @Override
    public int numberOfPackets() {
        return 1;
    }

    @Override
    public void receiveGuiData(int id, short data) {
        if (id == 0) {
            harvestOptimal = data != 0;
        }
    }

    @Override
    protected void receivePacket(int id, byte[] data, EntityPlayer player) {
        if (id == 0) {
            harvestOptimal = data[0] != 0;
        }
    }

    int[] buttonRect = { 8, 34, 20, 20 };

    @Override
    public void drawBackground(GuiMinecart gui, int x, int y) {
        ResourceHelper.bindResource("/gui/checkbox.png");
        drawImage(gui, buttonRect, 20, 0);
        if (harvestOptimal) {
            drawImage(gui, buttonRect, 40, 0);
        } else {
            drawImage(gui, buttonRect, 60, 0);
        }
        if (inRect(x, y, buttonRect)) {
            drawImage(gui, buttonRect, 0, 0);
        }

    }

    @Override
    public void drawMouseOver(GuiMinecart gui, int x, int y) {
        drawStringOnMouseOver(gui, Localization.MODULES.ADDONS.TOGGLE_HARVESTMODE.translate(), x, y, buttonRect);
    }

    @Override
    public void drawForeground(GuiMinecart gui) {
        drawString(gui, getHarvestModeLine(0), 34, 36, 0x404040);
        drawString(gui, getHarvestModeLine(1), 34, 46, 0x404040);
    }

    private String getHarvestModeLine(int line) {
        return Localization.MODULES.ADDONS.HARVESTMODE.translate(String.valueOf(harvestOptimal ? 1 : 0))
                .split(" ")[line];
    }

    @Override
    protected int getInventoryHeight() {
        return 1;
    }

    @Override
    protected int getInventoryWidth() {
        return 3;
    }

    @Override
    protected SlotBase getSlot(int slotId, int x, int y) {
        return new SlotCropStick(getCart(), slotId, 8 + x * 18, 8 + y * 18);
    }

    @Override
    protected void Load(NBTTagCompound tagCompound, int id) {
        harvestOptimal = tagCompound.getByte(generateNBTName("harvestOptimal", id)) == 1;
        super.Load(tagCompound, id);
    }

    @Override
    protected void Save(NBTTagCompound tagCompound, int id) {
        tagCompound.setBoolean(generateNBTName("harvestOptimal", id), harvestOptimal);
        super.Save(tagCompound, id);
    }

    @Override
    public void placeCrop(int x, int y, int z, ItemStack seed) {
        CropCard cc = Crops.instance.getCropCard(seed);
        TileEntity te = getCart().worldObj.getTileEntity(x, y, z);
        if (!(te instanceof TileEntityCrop)) {
            return;
        }
        if (!(seed.getItem() instanceof ItemCropSeed)) {
            return;
        }
        ((TileEntityCrop) te).tryPlantIn(
                cc,
                1,
                ItemCropSeed.getGrowthFromStack(seed),
                ItemCropSeed.getGainFromStack(seed),
                ItemCropSeed.getResistanceFromStack(seed),
                ItemCropSeed.getScannedFromStack(seed));
    }

    @Override
    public boolean isSeedPlaceable(int x, int y, int z, ItemStack seed) {
        int hasCropSticks = -1;
        for (int i = 0; i < getInventorySize(); i++) {
            if (getStack(i) != null) {
                hasCropSticks = i;
            }
        }
        if (hasCropSticks == -1) {
            return false;
        }
        if (!(seed.getItem() instanceof ItemCropSeed)) {
            return false;
        }

        if (getCart().worldObj.isAirBlock(x, y, z) && getCart().worldObj.getBlock(x, y - 1, z) == Blocks.farmland) {
            getCart().worldObj.setBlock(x, y, z, Block.getBlockFromItem(getStack(hasCropSticks).getItem()));
            getStack(hasCropSticks).stackSize--;
            if (getStack(hasCropSticks).stackSize <= 0) {
                setStack(hasCropSticks, null);
            }
        }

        CropCard cc = Crops.instance.getCropCard(seed);
        TileEntity te = getCart().worldObj.getTileEntity(x, y, z);
        if (!(te instanceof TileEntityCrop)) {
            return false;
        }
        return cc.canGrow((ICropTile) te) && ((ICropTile) te).getCrop() == null;
    }

}
