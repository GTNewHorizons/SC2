package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Modules.ModuleBase;

@SideOnly(Side.CLIENT)
public class ModelNote extends ModelCartbase {

    private static ResourceLocation texture = ResourceHelper.getResource("/models/noteModel.png");

    @Override
    public ResourceLocation getResource(ModuleBase module) {
        return texture;
    }

    protected int getTextureHeight() {
        return 32;
    }

    public ModelNote() {

        AddSpeaker(false);
        AddSpeaker(true);
    }

    private void AddSpeaker(boolean opposite) {
        ModelRenderer noteAnchor = new ModelRenderer(this);
        AddRenderer(noteAnchor);

        /*
         * if (opposite) { noteAnchor.rotateAngleY = (float)Math.PI; }
         */

        ModelRenderer base = new ModelRenderer(this, 0, 0);
        fixSize(base);
        noteAnchor.addChild(base);

        base.addBox(
                8, // X
                6, // Y
                6F, // Z
                16, // Size X
                12, // Size Y
                12, // Size Z
                0.0F // Size Increasement
        );
        base.setRotationPoint(
                -16.0F, // X
                -13.5F, // Y
                -12 + 14.0F * (opposite ? 1 : -1) // Z
        );

    }

}
