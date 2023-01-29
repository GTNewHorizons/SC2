package vswe.stevescarts.Listeners;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;

import vswe.stevescarts.Carts.MinecartModular;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChunkListener {

    public ChunkListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void invoke(EntityEvent.EnteringChunk event) {
        if (!event.entity.isDead && event.entity instanceof MinecartModular) {
            ((MinecartModular) event.entity).loadChunks(event.newChunkX, event.newChunkZ);
        }

    }
}
