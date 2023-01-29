package vswe.stevescarts.Fancy;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ServerFancy {

    private List<FancyPancy> fancies = new ArrayList<FancyPancy>();

    public void add(FancyPancy fancyPancy) {
        fancies.add(fancyPancy);
    }

    public List<FancyPancy> getFancies() {
        return fancies;
    }
}
