package vswe.stevescarts.Modules.Realtimers;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;

import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Helpers.EnchantmentInfo.ENCHANTMENT_TYPE;
import vswe.stevescarts.Helpers.Localization;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Interfaces.GuiMinecart;
import vswe.stevescarts.Modules.Addons.ModuleEnchants;
import vswe.stevescarts.Modules.Addons.Projectiles.ModuleProjectile;
import vswe.stevescarts.Modules.ISuppliesModule;
import vswe.stevescarts.Modules.ModuleBase;
import vswe.stevescarts.Slots.SlotArrow;
import vswe.stevescarts.Slots.SlotBase;

public class ModuleShooter extends ModuleBase implements ISuppliesModule {

    public ModuleShooter(MinecartModular cart) {
        super(cart);
        pipes = new ArrayList<Integer>();
        generatePipes(pipes);
        pipeRotations = new float[pipes.size()];
        // rotatePipes(true);
        generateInterfaceRegions();
    }

    private ArrayList<ModuleProjectile> projectiles;
    private ModuleEnchants enchanter;

    @Override
    public void init() {
        super.init();
        projectiles = new ArrayList<ModuleProjectile>();

        for (ModuleBase module : getCart().getModules()) {
            if (module instanceof ModuleProjectile) {
                projectiles.add((ModuleProjectile) module);
            } else if (module instanceof ModuleEnchants) {
                enchanter = (ModuleEnchants) module;
                enchanter.addType(ENCHANTMENT_TYPE.SHOOTER);
            }
        }
    }

    @Override
    protected int getInventoryHeight() {
        return 2;
    }

    @Override
    protected SlotBase getSlot(int slotId, int x, int y) {
        return new SlotArrow(getCart(), this, slotId, 8 + x * 18, 23 + y * 18);
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public void drawForeground(GuiMinecart gui) {
        drawString(gui, Localization.MODULES.ATTACHMENTS.SHOOTER.translate(), 8, 6, 0x404040);

        int delay = AInterval[arrowInterval];
        double freq = 20D / (delay + 1);

        String s = String.valueOf((((int) (freq * 1000)) / 1000D));
        drawString(
                gui,
                Localization.MODULES.ATTACHMENTS.FREQUENCY.translate() + ":",
                intervalDragArea[0] + intervalDragArea[2] + 5,
                15,
                0x404040);
        drawString(gui, s, intervalDragArea[0] + intervalDragArea[2] + 5, 23, 0x404040);
        s = String.valueOf((delay / 20D) + Localization.MODULES.ATTACHMENTS.SECONDS.translate());
        drawString(
                gui,
                Localization.MODULES.ATTACHMENTS.DELAY.translate() + ":",
                intervalDragArea[0] + intervalDragArea[2] + 5,
                35,
                0x404040);
        drawString(gui, s, intervalDragArea[0] + intervalDragArea[2] + 5, 43, 0x404040);
    }

    @Override
    public int guiWidth() {
        return super.guiWidth() + guiExtraWidth();
    }

    protected int guiExtraWidth() {
        return 112;
    }

    @Override
    public int guiHeight() {
        return Math.max(super.guiHeight(), guiRequiredHeight());
    }

    protected int guiRequiredHeight() {
        return 67;
    }

    protected void generateInterfaceRegions() {
        pipeSelectionX = guiWidth() - 110;
        pipeSelectionY = ((guiHeight() - 12)/* the area used for drawing */ - 26 /* its own height */) / 2
                + 12/* the area used for drawing starts here */;

        intervalSelectionX = pipeSelectionX + 26 + 8;
        intervalSelectionY = 10;

        intervalSelection = new int[] { intervalSelectionX, intervalSelectionY, 14, 53 };
        intervalDragArea = new int[] { intervalSelectionX - 4, intervalSelectionY, 40, 53 };
    }

    private int pipeSelectionX;
    private int pipeSelectionY;

    private int intervalSelectionX;
    private int intervalSelectionY;

    private int[] intervalSelection;
    private int[] intervalDragArea;

    @Override
    public void drawBackground(GuiMinecart gui, int x, int y) {
        ResourceHelper.bindResource("/gui/shooter.png");

        drawImage(gui, pipeSelectionX + (26 - 8) / 2, pipeSelectionY + (26 - 8) / 2 - 1, 0, 104, 8, 9);

        for (int i = 0; i < pipes.size(); i++) {
            int pipe = pipes.get(i);
            int pipeX = pipe % 3;
            int pipeY = pipe / 3;

            boolean active = isPipeActive(i);
            boolean selected = inRect(x, y, getRectForPipe(pipe)) || (currentCooldownState == 0 && active);

            int srcX = pipeX * 9;
            if (!active) {
                srcX += 26;
            }
            int srcY = pipeY * 9;
            if (selected) {
                srcY += 26;
            }

            drawImage(gui, getRectForPipe(pipe), srcX, srcY);
        }

        drawImage(gui, intervalSelection, 42, 52);

        int size = (int) ((arrowInterval / (float) AInterval.length) * 4);

        int targetX = intervalSelectionX + 7;
        int targetY = intervalSelectionY + arrowInterval * 2;
        int srcX = 0;
        int srcY = 52 + size * 13;

        drawImage(gui, targetX, targetY, srcX, srcY, 25, 13);

        srcX += 25;
        // srcY += 1;
        targetX += 7;

        drawImage(gui, targetX, targetY + 1, srcX, srcY + 1, 1, 11);
        drawImage(gui, targetX + 1, targetY + 2, srcX + 1, srcY + 2, 1, 9);
        drawImage(gui, targetX + 1, targetY + 1, srcX + 1, srcY + 1, Math.min(currentCooldownState, 15), 2);
        drawImage(
                gui,
                targetX + 15,
                targetY + 1,
                srcX + 15,
                srcY + 1,
                2,
                Math.max(Math.min(currentCooldownState, 25) - 15, 0));
        int len = Math.max(Math.min(currentCooldownState, 41) - 25, 0);
        drawImage(gui, targetX + 1 + (16 - len), targetY + 10, srcX + 1 + (16 - len), srcY + 10, len, 2);
    }

    private int currentCooldownState;

    private int getCurrentCooldownState() {
        double perc = arrowTick / (double) AInterval[arrowInterval];
        currentCooldownState = (int) (41 * perc);
        return currentCooldownState;
    }

    private int[] getRectForPipe(int pipe) {
        return new int[] { pipeSelectionX + (pipe % 3) * 9, pipeSelectionY + (pipe / 3) * 9, 8, 8 };
    }

    @Override
    public void mouseClicked(GuiMinecart gui, int x, int y, int button) {
        if (button == 0) {
            if (inRect(x, y, intervalDragArea)) {
                dragState = y - (intervalSelectionY + arrowInterval * 2);
            } else {
                for (int i = 0; i < pipes.size(); i++) {
                    if (inRect(x, y, getRectForPipe(pipes.get(i)))) {
                        sendPacket(0, (byte) i);
                        break;
                    }
                }
            }
        }
    }

    private int dragState = -1;

    @Override
    public void mouseMovedOrUp(GuiMinecart gui, int x, int y, int button) {
        if (button != -1) {
            dragState = -1;
        } else if (dragState != -1 /* && inRect(x,y,intervalDragArea) */) {
            int interval = ((y + getCart().getRealScrollY()) - intervalSelectionY - dragState) / 2;

            if (interval != arrowInterval) {
                if (interval >= 0 && interval < AInterval.length) {
                    sendPacket(1, (byte) interval);
                }
            }
        }
    }

    @Override
    protected void receivePacket(int id, byte[] data, EntityPlayer player) {
        if (id == 0) {
            byte info = getActivePipes();
            info ^= 1 << data[0];
            setActivePipes(info);
        } else if (id == 1) {
            byte info = data[0];
            if (info < 0) {
                info = 0;
            } else if (info >= AInterval.length) {
                info = (byte) (AInterval.length - 1);
            }

            arrowInterval = info;
            arrowTick = AInterval[info];
        }
    }

    @Override
    public int numberOfPackets() {
        return 2;
    }

    @Override
    public int numberOfGuiData() {
        return 2;
    }

    @Override
    protected void checkGuiData(Object[] info) {
        updateGuiData(info, 0, (short) currentCooldownState);
        updateGuiData(info, 1, (short) arrowInterval);
    }

    @Override
    public void receiveGuiData(int id, short data) {
        if (id == 0) {
            currentCooldownState = data;
        } else if (id == 1) {
            arrowInterval = data;
        }
    }

    // called to update the module's actions. Called by the cart's update code.
    @Override
    public void update() {
        super.update();

        if (!getCart().worldObj.isRemote) {
            if (arrowTick > 0) {
                arrowTick--;
            } else {
                Shoot();
            }
        } else {
            rotatePipes(false);
        }
    }

    // pipes that this module have
    // 0 (Forward Left) , 1 (Forward) , 2 (Forward Right)
    // 3 (Left), , 4 (Invalid) , 5 (Right)
    // 6 (Back Left) , 7 (Back) , 8 (Back Right)
    private final ArrayList<Integer> pipes;
    private final float[] pipeRotations;

    protected void generatePipes(ArrayList<Integer> list) {
        for (int i = 0; i < 9; i++) {
            if (i == 4) continue;
            /*
             * if (i == 3) continue; if (i == 5) continue;
             */
            list.add(i);
        }
    }

    protected boolean hasProjectileItem() {
        return getProjectileItem(false) != null;
    }

    // if flag is true, consume one projectile if found
    protected ItemStack getProjectileItem(boolean flag) {

        if (flag && enchanter != null && enchanter.useInfinity()) {
            flag = false;
        }

        for (int i = 0; i < getInventorySize(); i++) {
            if (getStack(i) != null) {
                if (isValidProjectileItem(getStack(i))) {
                    ItemStack projectile = getStack(i).copy();
                    projectile.stackSize = 1;

                    if (flag) {
                        if (!getCart().hasCreativeSupplies()) {
                            getStack(i).stackSize--;

                            if (getStack(i).stackSize == 0) {
                                setStack(i, null);
                            }
                        }
                    }

                    return projectile;
                }
            }
        }

        return null;
    }

    protected void Shoot() {
        setTimeToNext(AInterval[arrowInterval]);
        if ((getCart().pushX != 0 && getCart().pushZ != 0) || (getCart().pushX == 0 && getCart().pushZ == 0)
                || !getCart().hasFuel()) {
            return;
        }

        boolean hasShot = false;

        for (int i = 0; i < pipes.size(); i++) {
            if (!isPipeActive(i)) {
                continue;
            }
            int pipe = pipes.get(i);

            if (!hasProjectileItem()) {
                break;
            }

            int x = pipe % 3 - 1;
            int y = pipe / 3 - 1;

            if (getCart().pushZ > 0) {
                y *= -1;
                x *= -1;
            } else if (getCart().pushZ < 0) {} else if (getCart().pushX < 0) {
                int temp = -x;
                x = y;
                y = temp;
            } else if (getCart().pushX > 0) {
                int temp = x;
                x = -y;
                y = temp;
            }

            Entity projectile = getProjectile(null, getProjectileItem(true));

            projectile.setPosition(getCart().posX + x * 1.5, getCart().posY + 0.75F, getCart().posZ + y * 1.5);
            setHeading(projectile, x, 0.10000000149011612D, y, 1.6F, 12F);
            // readd if the EntityMob file beaves again
            // entityarrow.shootingEntity = getCart();
            setProjectileDamage(projectile);
            setProjectileOnFire(projectile);
            setProjectileKnockback(projectile);
            getCart().worldObj.spawnEntityInWorld(projectile);
            hasShot = true;
            damageEnchant();
        }

        if (hasShot) {
            getCart().worldObj.playAuxSFX(1002, (int) getCart().posX, (int) getCart().posY, (int) getCart().posZ, 0);
        }
    }

    protected void damageEnchant() {
        if (enchanter != null) {
            enchanter.damageEnchant(ENCHANTMENT_TYPE.SHOOTER, 1);
        }
    }

    protected void setProjectileOnFire(Entity projectile) {
        if (enchanter != null && enchanter.useFlame()) {
            projectile.setFire(100);
        }
    }

    protected void setProjectileDamage(Entity projectile) {
        if (enchanter != null && projectile instanceof EntityArrow) {
            int power = enchanter.getPowerLevel();
            if (power > 0) {
                EntityArrow arrow = (EntityArrow) projectile;
                arrow.setDamage((arrow).getDamage() + (double) power * 0.5D + 0.5D);
            }
        }
    }

    protected void setProjectileKnockback(Entity projectile) {
        if (enchanter != null && projectile instanceof EntityArrow) {
            int punch = enchanter.getPunchLevel();
            if (punch > 0) {
                EntityArrow arrow = (EntityArrow) projectile;
                arrow.setKnockbackStrength(punch);
            }
        }
    }

    protected void setHeading(Entity projectile, double motionX, double motionY, double motionZ, float motionMult,
            float motionNoise) {
        if (projectile instanceof IProjectile) {
            ((IProjectile) projectile).setThrowableHeading(motionX, motionY, motionZ, motionMult, motionNoise);
        } else if (projectile instanceof EntityFireball) {
            // Not a projective :S
            EntityFireball fireball = (EntityFireball) projectile;

            double totalMotion = (double) MathHelper
                    .sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
            fireball.accelerationX = motionX / totalMotion * 0.1D;
            fireball.accelerationY = motionY / totalMotion * 0.1D;
            fireball.accelerationZ = motionZ / totalMotion * 0.1D;
        }
    }

    protected Entity getProjectile(Entity target, ItemStack item) {
        for (ModuleProjectile module : projectiles) {
            if (module.isValidProjectile(item)) {
                return module.createProjectile(target, item);
            }
        }
        return new EntityArrow(getCart().worldObj);
    }

    public boolean isValidProjectileItem(ItemStack item) {
        for (ModuleProjectile module : projectiles) {
            if (module.isValidProjectile(item)) {
                return true;
            }
        }
        return item.getItem() == Items.arrow;
    }

    private final int[] AInterval = { 1, 3, 5, 7, 10, 13, 17, 21, 27, 35, 44, 55, 70, 95, 130, 175, 220, 275, 340, 420,
            520, 650 };
    private int arrowTick;
    private int arrowInterval = 5;

    protected void setTimeToNext(int val) {
        arrowTick = val;
    }

    private void rotatePipes(boolean isNew) {
        float minRotation = 0F;
        float maxRotation = (float) Math.PI / 4;
        float speed = 0.15F;

        for (int i = 0; i < pipes.size(); i++) {
            boolean isActive = isPipeActive(i);
            if (isNew && isActive) {
                pipeRotations[i] = minRotation;
            } else if (isNew && !isActive) {
                pipeRotations[i] = maxRotation;
            } else if (isActive && pipeRotations[i] > minRotation) {
                pipeRotations[i] -= speed;
                if (pipeRotations[i] < minRotation) {
                    pipeRotations[i] = minRotation;
                }
            } else if (!isActive && pipeRotations[i] < maxRotation) {
                pipeRotations[i] += speed;
                if (pipeRotations[i] > maxRotation) {
                    pipeRotations[i] = maxRotation;
                }
            }
        }
    }

    @Override
    public int numberOfDataWatchers() {
        return 1;
    }

    @Override
    public void initDw() {
        addDw(0, 0);
    }

    public void setActivePipes(byte val) {
        updateDw(0, val);
    }

    public byte getActivePipes() {
        if (isPlaceholder()) {
            return getSimInfo().getActivePipes();
        } else {
            return getDw(0);
        }
    }

    protected boolean isPipeActive(int id) {
        return (getActivePipes() & (1 << id)) != 0;
    }

    public int getPipeCount() {
        return pipes.size();
    }

    public float getPipeRotation(int id) {
        return pipeRotations[id];
    }

    @Override
    protected void Save(NBTTagCompound tagCompound, int id) {
        tagCompound.setByte(generateNBTName("Pipes", id), getActivePipes());
        tagCompound.setByte(generateNBTName("Interval", id), (byte) arrowInterval);
        saveTick(tagCompound, id);
    }

    @Override
    protected void Load(NBTTagCompound tagCompound, int id) {
        setActivePipes(tagCompound.getByte(generateNBTName("Pipes", id)));
        arrowInterval = tagCompound.getByte(generateNBTName("Interval", id));
        loadTick(tagCompound, id);
    }

    protected void saveTick(NBTTagCompound tagCompound, int id) {
        tagCompound.setByte(generateNBTName("Tick", id), (byte) arrowTick);
    }

    protected void loadTick(NBTTagCompound tagCompound, int id) {
        arrowTick = tagCompound.getByte(generateNBTName("Tick", id));
    }

    @Override
    public boolean haveSupplies() {
        return hasProjectileItem();
    }

}
