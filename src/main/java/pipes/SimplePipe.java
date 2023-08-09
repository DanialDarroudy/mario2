package pipes;

import characters.Character;

import javax.swing.*;
import java.awt.*;

public class SimplePipe extends Pipe {
    final static Image image = new ImageIcon("src\\main\\java\\pictures\\SimplePipe.png").getImage();
    private final static int height = 200;

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public void collision(Character character) {
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
