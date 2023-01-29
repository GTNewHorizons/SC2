package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;

import vswe.stevescarts.Modules.ModuleBase;
import vswe.stevescarts.Modules.Realtimers.ModuleShooterAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSniperRifle extends ModelGun {

    ModelRenderer anchor;
    ModelRenderer gun;

    public ModelSniperRifle() {
        anchor = new ModelRenderer(this);
        AddRenderer(anchor);

        gun = createGun(anchor);
    }

    public void applyEffects(ModuleBase module, float yaw, float pitch, float roll) {
        gun.rotateAngleZ = module == null ? 0 : ((ModuleShooterAdv) module).getPipeRotation(0);
        anchor.rotateAngleY = module == null ? 0
                : (float) Math.PI + ((ModuleShooterAdv) module).getRifleDirection() + yaw;
    }
}
