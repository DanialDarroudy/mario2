package blocks;

import characters.Character;
import game.LGame;
import items.Coin;

import javax.swing.*;
import java.awt.*;

public class HaveCoin extends Block {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\HaveCoin.png").getImage();

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    //-----------methods------//
    @Override
    public void get(LGame game) {
        if (knock == 0) {
            knock++;
            game.setScore(game.getScore() + 1);
            game.setCoin(game.getCoin() + 1);
        }
    }

    @Override
    public void collision(Character character, LGame lGame) {
        if (theBlock().intersects(character.getCharacter())) {
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
}
