package pipes;

import characters.Character;
import enemies.Plant;

import java.awt.*;

public class Pipe {
    int time = 0;
    Plant plant;
    int x;
    int y;
    String type;
    static final int width = 100;
    static int height;

    public void paint(Graphics g) {
    }

    public void collision(Character character) {
    }

    public Rectangle thePipe() {
        return null;
    }


    //-----------getters------------//

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTime() {
        return time;
    }

    public Plant getPlant() {
        return plant;
    }

    public String getType() {
        return type;
    }

    public static int getHeight() {
        return height;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
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
