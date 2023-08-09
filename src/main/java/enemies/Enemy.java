package enemies;

import characters.Character;
import game.Audio;
import game.LGame;
import items.Shield;
import items.Shot;
import items.Sword;

import java.awt.*;

public class Enemy {
    Nuke nuke;
    int direction = 1;
    int time = 0;
    int acceleration = 1;
    double vx = 0;
    int x;
    int y;
    String type;
    static int width = 50;
    static int height = 50;
    boolean live = true;

    //---------methods----------//
    public void kill(LGame game) {
        live = false;
        game.setCoin(game.getCoin() + 3);
        Audio.getInstance().getSound("kill").start();
    }

    public void collision(Character character, LGame game) {
    }

    public void shotCollision(Shot shot, LGame game) {
    }

    public Rectangle theEnemy() {
        return null;
    }

    public void shieldCollision(Shield shield, LGame game) {
        Audio.getInstance().getSound("bomb").start();
    }

    public void swordCollision(Sword sword, LGame game) {
    }

    public void paint(Graphics g) {

    }

    public void move(LGame game) {
    }
    //---------getters----------//


    public int getDirection() {
        return direction;
    }

    public int getTime() {
        return time;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public double getVx() {
        return vx;
    }

    public boolean isLive() {
        return live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
    //----------setters------------//

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void setVx(double vx) {
        this.vx = vx;
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
