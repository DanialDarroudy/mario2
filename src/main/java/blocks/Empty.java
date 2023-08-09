package blocks;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Empty extends Block {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Empty.png").getImage();

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public void collision(Character character, LGame lGame) {
        if (theBlock().intersects(character.getCharacter())) {
            character.setJump(100);
            character.setVy(0);
            character.setVx(0);
        }
    }

    @Override
    public Rectangle theBlock() {
        return new Rectangle(x, y, width, height);
    }
}
