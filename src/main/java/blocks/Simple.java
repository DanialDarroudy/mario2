package blocks;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Simple extends Block {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Simple.png").getImage();

    //---------------------------methods------------------------//
    @Override
    public void get(LGame game) {
        super.get(game);
        game.setScore(game.getScore() + 1);
    }

    @Override
    public void collision(Character character, LGame lGame) {
        if (theBlock().intersects(character.getCharacter()) && show) {
            if (character.getY() <= y + height + 5) {
                get(lGame);
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

    @Override
    public void paint(Graphics g) {
        if (show) {
            g.drawImage(image, x, y, null);
        }
    }
}
