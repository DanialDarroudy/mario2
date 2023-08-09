package stage;

import game.GUIGame;
import game.LGame;
import items.CheckPoint;
import main.User;

import javax.swing.*;
import java.awt.*;

public class Level {
    private Section[] sectionObject;
    private boolean[] whichSection;
    private CheckPoint checkPointObject;

    public void paint(Graphics g, LGame lGame, int show, User user, Timer timer, GUIGame game) {
        int init = -1;
        for (int i = 0; i < whichSection.length; i++) {
            if (whichSection[i]) {
                sectionObject[i].paint(g);
                init = i;
            }
        }
        if (checkPointObject.getWhichSection() == init) {
            checkPointObject.paint(g);
            checkPointObject.save(lGame, show, user, timer, game);
        }
    }

    //----------getters----------------//

    public CheckPoint getCheckPointObject() {
        return checkPointObject;
    }

    public boolean[] getWhichSection() {
        return whichSection;
    }

    public Section[] getSectionObject() {
        return sectionObject;
    }
    //----------setters----------------//

    public void setWhichSection(boolean[] whichSection) {
        this.whichSection = whichSection;
    }
}
