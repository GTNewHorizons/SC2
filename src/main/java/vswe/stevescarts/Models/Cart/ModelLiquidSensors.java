package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.stevescarts.Helpers.ResourceHelper;
import vswe.stevescarts.Modules.Addons.ModuleLiquidSensors;
import vswe.stevescarts.Modules.ModuleBase;

@SideOnly(Side.CLIENT)
public class ModelLiquidSensors extends ModelCartbase {

    private static ResourceLocation texture = ResourceHelper.getResource("/models/sensorModel.png");

    @Override
    public ResourceLocation getResource(ModuleBase module) {
        return texture;
    }

    protected int getTextureWidth() {
        return 32;
    }

    protected int getTextureHeight() {
        return 16;
    }

    ModelRenderer[] sensor1;
    ModelRenderer[] sensor2;

    public ModelLiquidSensors() {
        sensor1 = createSensor(false);
        sensor2 = createSensor(true);
    }

    private ModelRenderer[] createSensor(boolean right) {
        ModelRenderer base = new ModelRenderer(this, 0, 0);
        AddRenderer(base);

        base.addBox(
                0.5F, // X
                2, // Y
                0.5F, // Z
                1, // Size X
                4, // Size Y
                1, // Size Z
                0.0F // Size Increasement
        );
        if (right) {
            base.setRotationPoint(
                    -10.0F, // X
                    -11F, // Y
                    6F // Z
            );
        } else {
            base.setRotationPoint(
                    -10.0F, // X
                    -11F, // Y
                    -8.0F // Z
            );
        }

        ModelRenderer head = new ModelRenderer(this, 4, 0);
        fixSize(head);
        base.addChild(head);

        head.addBox(
                -2F, // X
                -2F, // Y
                -2F, // Z
                4, // Size X
                4, // Size Y
                4, // Size Z
                0.0F // Size Increasement
        );
        head.setRotationPoint(
                1.0F, // X
                0F, // Y
                1.0F // Z
        );

        ModelRenderer face = new ModelRenderer(this, 20, 0);
        fixSize(face);
        head.addChild(face);

        face.addBox(
                -0.5F, // X
                -1F, // Y
                -1F, // Z
                1, // Size X
                2, // Size Y
                2, // Size Z
                0.0F // Size Increasement
        );
        face.setRotationPoint(
                -2.5F, // X
                0F, // Y
                0.0F // Z
        );

        ModelRenderer[] dynamic = new ModelRenderer[4];
        dynamic[0] = head;
        for (int i = 1; i < 4; i++) {
            ModelRenderer light = new ModelRenderer(this, 20, 1 + i * 3);
            fixSize(light);
            head.addChild(light);

            light.addBox(
                    -1F, // X
                    -0.5F, // Y
                    -1F, // Z
                    2, // Size X
                    1, // Size Y
                    2, // Size Z
                    0.0F // Size Increasement
            );
            light.setRotationPoint(
                    0F, // X
                    -2.5F, // Y
                    0.0F // Z
            );

            dynamic[i] = light;
        }

        return dynamic;
    }

    public void applyEffects(ModuleBase module, float yaw, float pitch, float roll) {

        sensor1[0].rotateAngleY = module == null ? 0 : -((ModuleLiquidSensors) module).getSensorRotation();
        sensor2[0].rotateAngleY = module == null ? 0 : ((ModuleLiquidSensors) module).getSensorRotation();

        int active = module == null ? 2 : ((ModuleLiquidSensors) module).getLight();

        for (int i = 1; i < 4; i++) {
            sensor1[i].isHidden = i != active;
            sensor2[i].isHidden = i != active;
        }
    }
}
