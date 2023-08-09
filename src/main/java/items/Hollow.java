package items;

import javax.swing.*;
import java.awt.*;

public class Hollow {
    private int x;
    private int y;
    public static int width = 200;
    public static int height = 200;
    public static final Image image = new ImageIcon("F:\\Code\\ProjectAP\\src\\main\\java\\pictures\\Hollow.png").getImage();

    //------------------------------------------------methods---------------------------------------------------------//
    public void paint(Graphics g) {
        g.drawImage(image, x, y - 1, null);
    }
    //------------------------------------------------getters---------------------------------------------------------//

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //------------------------------------------------setters---------------------------------------------------------//

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}

