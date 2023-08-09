package items;

import characters.Character;
import game.Audio;
import game.LGame;

import java.awt.*;

public class Item {
    boolean show = false;
    int moveTime = 0;
    int jumpTime = 0;
    int time = 0;

    int x;
    int y;
    static int width = 50;
    static int height = 50;
    String type;

    public void paint(Graphics g) {
    }

    public void get(LGame game) {
        Audio.getInstance().getSound("item").start();
    }

    public void collision(Character character, LGame lGame) {
    }

    public Rectangle theItem() {
        return new Rectangle(x, y, width, height);
    }

    public String getType() {
        return type;
    }

    public boolean isShow() {
        return show;
    }

    public int getMoveTime() {
        return moveTime;
    }

    public int getJumpTime() {
        return jumpTime;
    }

    public int getTime() {
        return time;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setShow(boolean show) {
        this.show = show;
    }

    public void setMoveTime(int moveTime) {
        this.moveTime = moveTime;
    }

    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public void setTime(int time) {
        this.time = time;
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
