package vswe.stevescarts.Arcade;

import java.util.EnumSet;

import vswe.stevescarts.Arcade.Place.PLACE_STATE;
import vswe.stevescarts.Interfaces.GuiMinecart;

public class Station extends Property {

    private String name;
    private int stationId;

    public Station(ArcadeMonopoly game, PropertyGroup group, int stationId, String name) {
        super(game, group, name, 200);
        this.stationId = stationId;
        this.name = name;
    }

    @Override
    protected int getTextureId() {
        return 1 + stationId;
    }

    @Override
    public void draw(GuiMinecart gui, EnumSet<PLACE_STATE> states) {
        super.draw(gui, states);
        drawValue(gui);
    }

    @Override
    protected int getTextY() {
        return 10;
    }

    public int getRentCost(int ownedStations) {
        return 25 * (int) Math.pow(2, (ownedStations - 1));
    }

    @Override
    public int getRentCost() {
        return getRentCost(getOwnedInGroup());
    }

}
