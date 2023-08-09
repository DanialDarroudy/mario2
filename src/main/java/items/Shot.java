package items;

import game.Audio;

import javax.swing.*;
import java.awt.*;

public class Shot {
    private static final Image image = new ImageIcon("src\\main\\java\\pictures\\Shot.png").getImage();
    private int x;
    private int y;
    private boolean live = true;
    private int initX;

    public Shot(int x, int y) {
        Audio.getInstance().getSound("shot").start();
        this.x = x;
        this.y = y;
        this.initX = x;
    }

    public Rectangle getShot() {
        return new Rectangle(x, y, 10, 10);
    }

    public void paint(Graphics g) {
        if (live) {
            g.drawImage(image, x, y, null);
        }
    }

    //-------------setters--------------//
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    //------------------getters-----------//

    public static Image getImage() {
        return image;
    }

    public int getInitX() {
        return initX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return live;
    }
}
