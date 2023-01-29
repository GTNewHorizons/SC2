package vswe.stevescarts.Fancy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.ThreadDownloadImageData;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FancyPancyLoader implements Runnable {

    public static boolean isImageReady(ThreadDownloadImageData image) {
        if (image == null) {
            return false;
        }

        image.getGlTextureId();
        return ReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, image, 7);
    }

    private Map<String, FancyPancyHandler> fancyTypes;

    public FancyPancyLoader() {
        fancyTypes = new HashMap<String, FancyPancyHandler>();
        // add(new CapeHandler());
        // add(new SkinHandler());
        add(new OverheadHandler());

        new Thread(this).start();
    }

    private void add(FancyPancyHandler fancyPancyHandler) {
        fancyTypes.put(fancyPancyHandler.getCode(), fancyPancyHandler);
    }

    @Override
    public void run() {
        for (FancyPancyHandler fancyPancyHandler : fancyTypes.values()) {
            fancyPancyHandler.setReady(true);
        }
    }

}
