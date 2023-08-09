package characters;

import javax.swing.*;
import java.awt.*;

public class Wario extends Character {
    public static ImageIcon imageIcon = new ImageIcon("F:\\Code\\MainProjectOfAP\\src\\main\\java\\pictures\\Wario.png");
    public static Image image0 = new ImageIcon("F:\\Code\\MainProjectOfAP\\src\\main\\java\\pictures\\WarioInGame0.png").getImage();
    public static Image image1 = new ImageIcon("F:\\Code\\MainProjectOfAP\\src\\main\\java\\pictures\\WarioInGame1.png").getImage();
    public static Image image2 = new ImageIcon("F:\\Code\\MainProjectOfAP\\src\\main\\java\\pictures\\WarioInGame2.png").getImage();

    //------------------------------------------------methods---------------------------------------------------------//
    public void paint(Graphics g) {
        if (state == 0) {
            g.drawImage(image0, x, y, null);
        } else if (state == 1) {
            g.drawImage(image1, x, y, null);
        } else {
            g.drawImage(image2, x, y, null);
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
