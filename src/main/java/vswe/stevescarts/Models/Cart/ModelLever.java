package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import vswe.stevescarts.Modules.ILeverModule;
import vswe.stevescarts.Modules.ModuleBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLever extends ModelCartbase {

    @Override
    public ResourceLocation getResource(ModuleBase module) {
        return resource;
    }

    protected int getTextureWidth() {
        return 32;
    }

    protected int getTextureHeight() {
        return 32;
    }

    ModelRenderer lever;
    ResourceLocation resource;

    public ModelLever(ResourceLocation resource) {
        this.resource = resource;

        ModelRenderer base = new ModelRenderer(this, 0, 0);
        AddRenderer(base);

        base.addBox(
                -2.5F, // X
                -1.5F, // Y
                -0.5F, // Z
                5, // Size X
                3, // Size Y
                1, // Size Z
                0.0F // Size Increasement
        );
        base.setRotationPoint(
                0, // X
                2F, // Y
                8.5F // Z
        );

        lever = new ModelRenderer(this, 0, 4);
        base.addChild(lever);
        fixSize(lever);

        lever.addBox(
                -0.5F, // X
                -12F, // Y
                -0.5F, // Z
                1, // Size X
                11, // Size Y
                1, // Size Z
                0.0F // Size Increasement
        );
        lever.setRotationPoint(
                0, // X
                0F, // Y
                0F // Z
        );

        ModelRenderer handle = new ModelRenderer(this, 4, 4);
        lever.addChild(handle);
        fixSize(handle);

        handle.addBox(
                -1F, // X
                -13F, // Y
                -1F, // Z
                2, // Size X
                2, // Size Y
                2, // Size Z
                0.0F // Size Increasement
        );
        handle.setRotationPoint(
                0, // X
                0F, // Y
                0F // Z
        );
    }

    public void applyEffects(ModuleBase module, float yaw, float pitch, float roll) {
        lever.rotateAngleZ = module == null ? 0
                : (float) Math.PI / 8 - ((ILeverModule) module).getLeverState() * (float) Math.PI / 4;
    }
}
