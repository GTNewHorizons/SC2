package vswe.stevescarts.Containers;

import net.minecraft.inventory.IInventory;

import vswe.stevescarts.Helpers.LogicObject;
import vswe.stevescarts.TileEntities.TileEntityBase;
import vswe.stevescarts.TileEntities.TileEntityDetector;

public class ContainerDetector extends ContainerBase {

    public IInventory getMyInventory() {
        return null;
    }

    public TileEntityBase getTileEntity() {
        return detector;
    }

    private TileEntityDetector detector;
    public LogicObject mainObj;

    public ContainerDetector(IInventory invPlayer, TileEntityDetector detector) {
        this.detector = detector;

        mainObj = new LogicObject((byte) 1, (byte) 0);
    }

}
