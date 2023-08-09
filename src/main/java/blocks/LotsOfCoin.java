package blocks;

import characters.Character;
import enemies.Enemy;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class LotsOfCoin extends Block {
    private static final Image image = new ImageIcon("src\\main\\java\\pictures\\LotsOfCoin.png").getImage();

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    //-----------methods------//
    @Override
    public void get(LGame game) {
        if (knock < 5) {
            game.setCoin(game.getCoin() + 1);
        } else if (knock == 5) {
            toEmpty(game);
        }
        knock++;
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

    public void toEmpty(LGame game) {
        for (int i = 0; i < game.getFile().getLevelObject().length; i++) {
            for (int j = 0; j < game.getFile().getLevelObject()[i].getSectionObject().length; j++) {
                for (int k = 0; k < game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getBlockObject().length; k++) {
                    if (game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getBlockObject()[k] == this) {
                        Empty empty = new Empty();
                        empty.setX(x);
                        empty.setY(y);
                        empty.setType("empty");
                        empty.setShow(true);
                        empty.setKnock(0);
                        game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getBlockObject()[k] = empty;
                    }
                }
            }
        }
    }
}
