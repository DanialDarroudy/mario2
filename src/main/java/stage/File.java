package stage;

import game.GUIGame;
import game.LGame;
import main.User;

import javax.swing.*;
import java.awt.*;

public class File implements Cloneable{
    private Level levelObject[];
    private int hearts;
    private int state;
    private boolean whichLevel[];

    public void paint(Graphics g, LGame lGame, int show, User user, Timer timer, GUIGame game) {
        for (int i = 0; i < whichLevel.length; i++) {
            if (whichLevel[i]) {
                levelObject[i].paint(g, lGame, show, user, timer, game);
            }
        }
    }

    //-------getters--------//


    public boolean[] getWhichLevel() {
        return whichLevel;
    }

    public Level[] getLevelObject() {
        return levelObject;
    }

    public int getHearts() {
        return hearts;
    }

    public int getState() {
        return state;
    }
    //----------setters--------------//

    public void setWhichLevel(boolean[] whichLevel) {
        this.whichLevel = whichLevel;
    }

    @Override
    public File clone() {
        try {
            return (File) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
