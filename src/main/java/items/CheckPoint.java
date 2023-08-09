package items;


import game.GUIGame;
import game.LGame;
import main.User;

import javax.swing.*;
import java.awt.*;

public class CheckPoint {
    public final static Image image = new ImageIcon("src\\main\\java\\pictures\\CheckPoint.png").getImage();
    private int x;
    private int y;
    public static int width = 50;
    public static int height = 100;
    private int whichSection;
    private boolean live = true;
    private boolean isSave = false;
    private int whichGame;

    //------------------methods-------------------//
    public void save(LGame lGame, int show, User user, Timer timer, GUIGame game) {
        if (theCheckPoint().intersects(lGame.getSelectedCharacter().getCharacter()) && live) {
            timer.stop();
            int init = JOptionPane.showConfirmDialog(game, "you can by pay PR coin save game"
                    , "Save", JOptionPane.YES_NO_OPTION);
            if (init == JOptionPane.YES_OPTION) {
                lGame.setCoin((int) (lGame.getCoin() - lGame.getProgressRisk()));
                if (show == 1) {
                    user.setGame1(lGame.clone());
                } else if (show == 2) {
                    user.setGame2(lGame.clone());
                } else {
                    user.setGame3(lGame.clone());
                }
                isSave = true;
                whichGame = show;
            } else if (init == JOptionPane.NO_OPTION) {
                lGame.setCoin((int) (lGame.getCoin() + (lGame.getProgressRisk() / 4)));
            }
            live = false;
        }
    }

    public Rectangle theCheckPoint() {
        return new Rectangle(x, y, width, height);
    }

    public void paint(Graphics g) {
        if (live) {
            g.drawImage(image, x, y, null);
        }
    }
    //------------------setters---------------------//

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public void setWhichSection(int whichSection) {
        this.whichSection = whichSection;
    }

    //------------------getters---------------------//

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWhichGame() {
        return whichGame;
    }

    public boolean isSave() {
        return isSave;
    }

    public boolean isLive() {
        return live;
    }

    public int getWhichSection() {
        return whichSection;
    }
}
