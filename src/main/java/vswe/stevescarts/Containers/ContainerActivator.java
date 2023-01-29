package vswe.stevescarts.Containers;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;

import vswe.stevescarts.Helpers.ActivatorOption;
import vswe.stevescarts.TileEntities.TileEntityActivator;
import vswe.stevescarts.TileEntities.TileEntityBase;

public class ContainerActivator extends ContainerBase {

    public IInventory getMyInventory() {
        return null;
    }

    public TileEntityBase getTileEntity() {
        return activator;
    }

    private TileEntityActivator activator;

    public ContainerActivator(IInventory invPlayer, TileEntityActivator activator) {
        this.activator = activator;

        lastOptions = new ArrayList<Integer>();
        for (ActivatorOption option : activator.getOptions()) {
            lastOptions.add(option.getOption());
        }
    }

    public ArrayList<Integer> lastOptions;

}
