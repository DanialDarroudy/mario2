package items;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Shield {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Shield.png").getImage();
    private static final int width = 100;
    private static final int height = 100;
    private int x;
    private int y;
    private int time = 0;
    private boolean show = true;

    public Shield(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        if (show) {
            g.drawImage(image, x, y, null);
        }
    }

    public Rectangle theShield() {
        return new Rectangle(x, y, width, height);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTime() {
        return time;
    }

    public boolean isShow() {
        return show;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
