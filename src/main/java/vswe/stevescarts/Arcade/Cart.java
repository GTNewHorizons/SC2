package vswe.stevescarts.Arcade;

import vswe.stevescarts.Arcade.TrackOrientation.DIRECTION;
import vswe.stevescarts.Interfaces.GuiMinecart;

public class Cart {

    private int x;
    private int y;
    private DIRECTION dir;
    private int imageIndex;
    private boolean enabled;

    public Cart(int imageIndex) {
        this.imageIndex = imageIndex;
        enabled = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public DIRECTION getDireciotn() {
        return dir;
    }

    public void setX(int val) {
        x = val;
    }

    public void setY(int val) {
        y = val;
    }

    public void setDirection(DIRECTION val) {
        dir = val;
    }

    public void setAlive(boolean val) {
        enabled = val;
    }

    public void move(ArcadeTracks game) {
        if (!enabled) {
            return;
        }

        x += dir.getX();
        y += dir.getY();

        if (x < 0 || y < 0
                || x >= game.getTrackMap().length
                || y >= game.getTrackMap()[0].length
                || game.getTrackMap()[x][y] == null) {
            if (dir != DIRECTION.STILL) {
                onCrash();
            }
            dir = DIRECTION.STILL;
        } else {
            game.getTrackMap()[x][y].travel(game, this);
            dir = game.getTrackMap()[x][y].getOrientation().travel(dir.getOpposite());
        }

        if (game.isItemOnGround() && x == game.getItemX() && y == game.getItemY()) {
            onItemPickUp();
            game.pickItemUp();
        }
    }

    public void onItemPickUp() {}

    public void onCrash() {};

    public void render(ArcadeTracks game, GuiMinecart gui, int tick) {
        if (!enabled) {
            return;
        }

        int x = ArcadeTracks.LEFT_MARGIN + 2 + (int) (16 * (this.x + dir.getX() * (tick / 4F)));
        int y = ArcadeTracks.TOP_MARGIN + 2 + (int) (16 * (this.y + dir.getY() * (tick / 4F)));
        int u = 256 - 12 * (imageIndex + 1);
        int v = 256 - 12;
        int w = 12;
        int h = 12;

        game.drawImageInArea(gui, x, y, u, v, w, h);
    }

    public boolean isAlive() {
        return enabled;
    }

}
