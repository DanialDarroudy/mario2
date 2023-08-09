package blocks;


import characters.Character;
import game.Audio;
import game.LGame;

import java.awt.*;

public class Block {
    int knock = 0;
    boolean show = true;
    int x;
    int y;
    String type;
    static int width = 50;
    static int height = 50;

    public Block() {
    }

    public void paint(Graphics g) {
    }

    public void get(LGame Game) {
        show = false;
        Audio.getInstance().getSound("wall").start();
    }

    public void collision(Character character, LGame lGame) {

    }

    public Rectangle theBlock() {
        return null;
    }

    //--------getters----------//

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getKnock() {
        return knock;
    }

    public boolean isShow() {
        return show;
    }

    public String getType() {
        return type;
    }

    public static int getWidth() {
        return width;
    }

    //------------------setters-------------------//

    public static void setWidth(int width) {
        Block.width = width;
    }

    public static void setHeight(int height) {
        Block.height = height;
    }

    public void setKnock(int knock) {
        this.knock = knock;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }
}
