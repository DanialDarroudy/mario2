package items;

import game.Audio;

import javax.swing.*;
import java.awt.*;

public class Sword {
    public final static Image image = new ImageIcon("src\\main\\java\\pictures\\Sword.png").getImage();
    private int x;
    private int y;
    private int initX;
    private boolean back = false;

    public Sword() {
        Audio.getInstance().getSound("sword").start();
    }

    //--------------methods-------------------//
    public Rectangle getSword() {
        return new Rectangle(x, y, 30, 30);
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    //---------------getters------------------//

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getInitX() {
        return initX;
    }

    public boolean isBack() {
        return back;
    }
    //--------------------setters--------------------//


    public void setInitX(int initX) {
        this.initX = initX;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBack(boolean back) {
        this.back = back;
    }
}
