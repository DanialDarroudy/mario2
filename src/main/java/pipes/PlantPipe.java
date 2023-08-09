package pipes;

import characters.Character;
import enemies.Plant;

import javax.swing.*;
import java.awt.*;

public class PlantPipe extends Pipe {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\PlantPipe.png").getImage();
    private final static int height = 100;

    //----------methods---------------//
    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
        plant();
        plant.paint(g);
    }

    public void plant() {
        if (plant.isLive()) {
            time += 5;
            if (time > 3000 && time < 6000) {
                plant.setShow(true);
            } else if (time > 6000) {
                time = 0;
                plant.setShow(false);
            }
        }
    }

    @Override
    public void collision(Character character) {
        plant.collision(character);
        if (thePipe().intersects(character.getCharacter())) {
            character.setVy(0);
            character.setVx(0);
        }
    }

    @Override
    public Rectangle thePipe() {
        return new Rectangle(x, y, width, height);
    }

}
