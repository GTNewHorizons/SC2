package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Modules.ModuleBase;
import vswe.stevescarts.Modules.Workers.ModuleTorch;

@SideOnly(Side.CLIENT)
public class ModelTorchplacer extends ModelCartbase {

    private static ResourceLocation texture = ResourceHelper.getResource("/models/torchModel.png");

    @Override
    public ResourceLocation getResource(ModuleBase module) {
        return texture;
    }

    protected int getTextureWidth() {
        return 32;
    }

    protected int getTextureHeight() {
        return 32;
    }

    ModelRenderer[] torches1;
    ModelRenderer[] torches2;

    public ModelTorchplacer() {
        torches1 = createSide(false);
        torches2 = createSide(true);
    }

    private ModelRenderer[] createSide(boolean opposite) {
        ModelRenderer anchor = new ModelRenderer(this, 0, 0);
        AddRenderer(anchor);

        if (opposite) {
            anchor.rotateAngleY = (float) Math.PI;
        }

        ModelRenderer base = new ModelRenderer(this, 0, 0);
        anchor.addChild(base);
        fixSize(base);

        base.addBox(
                -7, // X
                -2F, // Y
                -1, // Z
                14, // Size X
                4, // Size Y
                2, // Size Z
                0.0F // Size Increasement
        );
        base.setRotationPoint(
                0, // X
                -2F, // Y
                -9F // Z
        );

        ModelRenderer[] torches = new ModelRenderer[3];
        for (int i = -1; i <= 1; i++) {
            ModelRenderer torchHolder = new ModelRenderer(this, 0, 6);
            base.addChild(torchHolder);
            fixSize(torchHolder);

            torchHolder.addBox(
                    -1, // X
                    -1F, // Y
                    -0.5F, // Z
                    2, // Size X
                    2, // Size Y
                    1, // Size Z
                    0.0F // Size Increasement
            );
            torchHolder.setRotationPoint(
                    i * 4, // X
                    0F, // Y
                    -1.5F // Z
            );

            ModelRenderer torch = new ModelRenderer(this, 0, 9);
            torches[i + 1] = torch;
            torchHolder.addChild(torch);
            fixSize(torch);

            torch.addBox(
                    -1, // X
                    -5F, // Y
                    -1F, // Z
                    2, // Size X
                    10, // Size Y
                    2, // Size Z
                    0.0F // Size Increasement
            );
            torch.setRotationPoint(
                    0, // X
                    0F, // Y
                    -1.5F // Z
            );
        }
        return torches;
    }

    public void applyEffects(ModuleBase module, float yaw, float pitch, float roll) {

        int torches = module == null ? 7 : ((ModuleTorch) module).getTorches();
        for (int i = 0; i < 3; i++) {
            boolean isTorch = (torches & (1 << i)) != 0;
            torches1[i].isHidden = !isTorch;
            torches2[2 - i].isHidden = !isTorch;
        }
    }
}
