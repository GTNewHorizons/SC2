package vswe.stevescarts;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.world.World;

import vswe.stevescarts.Carts.MinecartModular;
import vswe.stevescarts.Fancy.FancyPancyLoader;
import vswe.stevescarts.Helpers.*;
import vswe.stevescarts.Items.ModItems;
import vswe.stevescarts.ModuleData.ModuleData;
import vswe.stevescarts.Renders.RendererMinecart;
import vswe.stevescarts.Renders.RendererMinecartItem;
import vswe.stevescarts.Renders.RendererUpgrade;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        new FancyPancyLoader();
    }

    @Override
    public void renderInit() {
        RenderingRegistry.registerEntityRenderingHandler(MinecartModular.class, new RendererMinecart());
        RenderingRegistry.registerEntityRenderingHandler(
                EntityEasterEgg.class,
                new RenderSnowball(ModItems.component, ComponentTypes.PAINTED_EASTER_EGG.getId()));
        StevesCarts.instance.blockRenderer = new RendererUpgrade();
        new RendererMinecartItem();
        RenderingRegistry.registerEntityRenderingHandler(EntityCake.class, new RenderSnowball(Items.cake));
        ModuleData.initModels();
        if (StevesCarts.instance.tradeHandler != null) {
            StevesCarts.instance.tradeHandler.registerSkin();
        }
    }

    @Override
    public void soundInit() {
        new SoundHandler();
        new MinecartSoundMuter();
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

}
