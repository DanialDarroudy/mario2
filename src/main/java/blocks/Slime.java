package blocks;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Slime extends Block {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Slime.png").getImage();

    //-----------methods--------------//
    public void jump(Character character) {
        character.setSlime(true);
    }

    @Override
    public void collision(Character character, LGame lGame) {
        if (theBlock().intersects(character.getCharacter())) {
            if (character.getY() <= y + 5) {
                jump(character);
            }
            character.setJump(100);
            character.setVy(0);
            character.setVx(0);
        }
    }

    @Override
    public Rectangle theBlock() {
        return new Rectangle(x, y, width, height);
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
