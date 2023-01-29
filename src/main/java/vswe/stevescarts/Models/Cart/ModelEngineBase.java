package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ModelEngineBase extends ModelCartbase {

    protected ModelRenderer anchor;

    public ModelEngineBase() {
        anchor = new ModelRenderer(this, 0, 0);
        AddRenderer(anchor);

        anchor.setRotationPoint(
                10.5F, // X
                0.5F, // Y
                -0F // Z
        );

        anchor.rotateAngleY = -(float) Math.PI / 2;
    }

}
