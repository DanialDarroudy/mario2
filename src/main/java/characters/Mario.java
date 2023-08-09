package characters;

import items.Sword;

import javax.swing.*;
import java.awt.*;

public class Mario extends Character {
    public final static ImageIcon imageIcon = new ImageIcon("src\\main\\java\\pictures\\Mario.png");
    public final static Image image0 = new ImageIcon("src\\main\\java\\pictures\\MarioInGame0.png").getImage();
    public final static Image image1 = new ImageIcon("src\\main\\java\\pictures\\MarioInGame1.png").getImage();
    public final static Image image2 = new ImageIcon("src\\main\\java\\pictures\\MarioInGame2.png").getImage();
    public final static Image sitImage1 = new ImageIcon("src\\main\\java\\pictures\\MarioSit1.png").getImage();
    public final static Image sitImage2 = new ImageIcon("src\\main\\java\\pictures\\MarioSit2.png").getImage();

    //------------------------------------------------methods---------------------------------------------------------//
    public void paint(Graphics g) {
        type = "Mario";
        //for shots
        super.paint(g);
        //for paint mario
        if (state == 0) {
            g.drawImage(image0, x, y, null);
        } else if (state == 1) {
            if (sit) {
                g.drawImage(sitImage1, x, y, null);
            } else {
                g.drawImage(image1, x, y, null);
            }
        } else {
            if (sit) {
                g.drawImage(sitImage2, x, y, null);
            } else {
                g.drawImage(image2, x, y, null);
            }
        }
    }

    public void move() {
        super.move();
    }

    //------------------------------------------------getters---------------------------------------------------------//
    public static ImageIcon getImageIcon() {
        return imageIcon;
    }

    //------------------------------------------------setters---------------------------------------------------------//

}