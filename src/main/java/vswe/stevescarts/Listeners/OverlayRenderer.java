package vswe.stevescarts.Listeners;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Carts.MinecartModular;

public class OverlayRenderer {

    public OverlayRenderer() {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            renderOverlay();
        }
    }

    @SideOnly(Side.CLIENT)
    private void renderOverlay() {
        net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getMinecraft();
        EntityPlayer player = minecraft.thePlayer;

        if (minecraft.currentScreen == null && player.ridingEntity != null
                && player.ridingEntity instanceof MinecartModular) {
            ((MinecartModular) player.ridingEntity).renderOverlay(minecraft);
        }
    }

}
