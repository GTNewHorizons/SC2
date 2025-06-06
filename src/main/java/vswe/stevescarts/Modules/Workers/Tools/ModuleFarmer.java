package vswe.stevescarts.Modules.Workers.Tools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Helpers.Localization;
import vswe.stevescarts.Interfaces.GuiMinecart;
import vswe.stevescarts.Modules.ICropModule;
import vswe.stevescarts.Modules.ISuppliesModule;
import vswe.stevescarts.Modules.ModuleBase;
import vswe.stevescarts.Slots.SlotBase;
import vswe.stevescarts.Slots.SlotSeed;

public abstract class ModuleFarmer extends ModuleTool implements ISuppliesModule, ICropModule {

    public ModuleFarmer(MinecartModular cart) {
        super(cart);
    }

    protected abstract int getRange();

    public int getExternalRange() {
        return getRange();
    }

    private ArrayList<ICropModule> plantModules;

    @Override
    public void init() {
        super.init();
        plantModules = new ArrayList<ICropModule>();

        for (ModuleBase module : getCart().getModules()) {
            if (module instanceof ICropModule) {
                plantModules.add((ICropModule) module);
            }
        }
    }

    // lower numbers are prioritised
    public byte getWorkPriority() {
        return 80;
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public void drawForeground(GuiMinecart gui) {
        drawString(gui, Localization.MODULES.TOOLS.FARMER.translate(), 8, 6, 0x404040);
    }

    @Override
    protected int getInventoryWidth() {
        return super.getInventoryWidth() + 3;
    }

    @Override
    protected SlotBase getSlot(int slotId, int x, int y) {
        if (x == 0) {
            return super.getSlot(slotId, x, y);
        } else {
            x--;
            return new SlotSeed(getCart(), this, slotId, 8 + x * 18, 28 + y * 18);
        }
    }

    // return true when the work is done, false allow other modules to continue the work
    public boolean work() {
        // get the next block so the cart knows where to mine
        Vec3 next = getNextblock();
        // save thee coordinates for easy access
        int x = (int) next.xCoord;
        int y = (int) next.yCoord;
        int z = (int) next.zCoord;

        // loop through the blocks in the "hole" in front of the cart

        for (int i = -getRange(); i <= getRange(); i++) {
            for (int j = -getRange(); j <= getRange(); j++) {
                // calculate the coordinates of this "hole"
                int coordX = x + i;
                int coordY = y - 1;
                int coordZ = z + j;

                if (farm(coordX, coordY, coordZ)) {
                    return true;
                } else if (till(coordX, coordY, coordZ)) {
                    return true;
                } else if (plant(coordX, coordY, coordZ)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean till(int x, int y, int z) {
        Block b = getCart().worldObj.getBlock(x, y, z);

        if (getCart().worldObj.isAirBlock(x, y + 1, z) && (b == Blocks.grass || b == Blocks.dirt)) {
            if (doPreWork()) {
                startWorking(10);
                return true;
            } else {
                stopWorking();
                getCart().worldObj.setBlock(x, y, z, Blocks.farmland);
            }
        }

        return false;
    }

    protected boolean plant(int x, int y, int z) {
        int hasSeeds = -1;

        Block soilblock = getCart().worldObj.getBlock(x, y, z);

        if (soilblock == null) {
            return false;
        }
        ICropModule workingModule = null;
        // check if there's any seeds to place
        for (int i = 0; i < getInventorySize(); i++) {
            // check if the slot contains seeds
            if (getStack(i) == null) {
                continue;
            }
            workingModule = getModuleFromSeed(getStack(i));
            if (workingModule == null) {
                return false;
            }

            if (workingModule.isSeedPlaceable(x, y + 1, z, getStack(i))) {
                hasSeeds = i;
                break;
            }
        }

        if (hasSeeds == -1) {
            return false;
        }

        if (doPreWork()) {
            startWorking(25);
            return true;
        }
        stopWorking();

        workingModule.placeCrop(x, y + 1, z, getStack(hasSeeds));

        getStack(hasSeeds).stackSize--;

        if (getStack(hasSeeds).stackSize <= 0) {
            setStack(hasSeeds, null);
        }
        return false;
    }

    protected boolean farm(int x, int y, int z) {
        if (isBroken()) {
            return false;
        }
        ICropModule workingCropModule = getHarvestingModule(x, y + 1, z);

        if (workingCropModule == null) {
            return false;
        }
        if (doPreWork()) {
            int efficiency = enchanter != null ? enchanter.getEfficiencyLevel() : 0;
            int workingtime = (int) (getBaseFarmingTime() / Math.pow(1.3F, efficiency));
            setFarming(workingtime * 4);
            startWorking(workingtime);
            return true;
        }

        stopWorking();

        int fortune = enchanter != null ? enchanter.getFortuneLevel() : 0;
        List<ItemStack> stuff = workingCropModule.harvestCrop(x, y + 1, z, fortune);

        for (ItemStack iStack : stuff) {

            // Extra drop rates same as Crop managers of same tier
            if (getCart().rand.nextFloat() <= 0.05 * (getCart().getCurrentEngine().getEngineTier() + 1)) {
                iStack.stackSize *= 2;
            }

            getCart().addItemToChest(iStack);

            if (iStack.stackSize != 0) {
                EntityItem entityitem = new EntityItem(
                        getCart().worldObj,
                        getCart().posX,
                        getCart().posY,
                        getCart().posZ,
                        iStack);
                entityitem.motionX = (float) (x - getCart().x()) / 10;
                entityitem.motionY = 0.15F;
                entityitem.motionZ = (float) (z - getCart().z()) / 10;
                getCart().worldObj.spawnEntityInWorld(entityitem);
            }
        }

        damageTool(3);
        return false;
    }

    protected int getBaseFarmingTime() {
        return 25;
    }

    public ICropModule getModuleFromSeed(ItemStack seed) {
        for (ICropModule module : plantModules) {
            if (module.isSeedValid(seed)) {
                return module;
            }
        }

        return null;
    }

    protected ICropModule getHarvestingModule(int x, int y, int z) {
        for (ICropModule module : plantModules) {
            if (module.isReadyToHarvest(x, y, z)) {
                return module;
            }
        }

        return null;
    }

    @Override
    public boolean isSeedValid(ItemStack seed) {
        return seed.getItem() == Items.wheat_seeds || seed.getItem() == Items.potato || seed.getItem() == Items.carrot;
    }

    @Override
    public Block getCropFromSeed(ItemStack seed) {
        if (seed.getItem() == Items.carrot) {
            return Blocks.carrots;
        } else if (seed.getItem() == Items.potato) {
            return Blocks.potatoes;
        } else if (seed.getItem() == Items.wheat_seeds) {
            return Blocks.wheat;
        }

        return null;
    }

    @Override
    public boolean isReadyToHarvest(int x, int y, int z) {
        Block block = getCart().worldObj.getBlock(x, y, z);
        int m = getCart().worldObj.getBlockMetadata(x, y, z);

        if (block instanceof BlockCrops && m == 7) {
            return true;
        }

        return false;
    }

    private int farming;
    private float farmAngle;
    private float rigAngle = -(float) Math.PI * 5 / 4;

    public float getFarmAngle() {
        return farmAngle;
    }

    public float getRigAngle() {
        return rigAngle;
    }

    @Override
    public void initDw() {
        addDw(0, 0);
    }

    @Override
    public int numberOfDataWatchers() {
        return 1;
    }

    private void setFarming(int val) {
        farming = val;
        updateDw(0, (byte) (val > 0 ? 1 : 0));
    }

    protected boolean isFarming() {
        if (isPlaceholder()) {
            return getSimInfo().getIsFarming();
        } else {
            return getCart().isEngineBurning() && getDw(0) != 0;
        }
    }

    /**
     * Called every tick, here the necessary actions should be taken
     **/
    public void update() {
        // call the method from the super class, this will do all ordinary things first
        super.update();

        if (!getCart().worldObj.isRemote) {
            setFarming(farming - 1);
        } else {
            float up = -(float) Math.PI * 5 / 4;
            float down = -(float) Math.PI;
            boolean flag = isFarming();
            if (flag) {
                if (rigAngle < down) {
                    rigAngle += 0.1F;
                    if (rigAngle > down) {
                        rigAngle = down;
                    }
                } else {
                    farmAngle = (float) ((farmAngle + 0.15F) % (Math.PI * 2));
                }
            } else {
                if (rigAngle > up) {
                    rigAngle -= 0.075F;
                    if (rigAngle < up) {
                        rigAngle = up;
                    }
                }
            }
        }
    }

    @Override
    public boolean haveSupplies() {
        for (int i = 0; i < getInventorySize(); i++) {
            ItemStack item = getStack(i);
            if (item != null && getModuleFromSeed(item) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public World getWorld() {
        return getCart().worldObj;
    }
}
