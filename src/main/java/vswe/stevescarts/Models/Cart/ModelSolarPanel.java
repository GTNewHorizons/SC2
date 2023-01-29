package vswe.stevescarts.Models.Cart;

import net.minecraft.client.model.ModelRenderer;

import vswe.stevescarts.Modules.Engines.ModuleSolarTop;
import vswe.stevescarts.Modules.ModuleBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ModelSolarPanel extends ModelCartbase {

    public ModelSolarPanel() {
        super();
    }

    protected ModelRenderer createMovingHolder(int x, int y) {
        ModelRenderer moving = new ModelRenderer(this, x, y);

        this.moving = moving;
        AddRenderer(moving);
        return moving;
    }

    ModelRenderer moving;

    public void applyEffects(ModuleBase module, float yaw, float pitch, float roll) {
        moving.rotationPointY = module == null ? -4 : ((ModuleSolarTop) module).getMovingLevel();
    }
}
