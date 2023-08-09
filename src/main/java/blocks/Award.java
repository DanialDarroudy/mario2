package blocks;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Award extends Block {
    private static final Image image = new ImageIcon("src\\main\\java\\pictures\\Award.png").getImage();


    //-----------methods------------//
    public void get(LGame game) {
        if (knock < 1) {
            game.setScore(game.getScore() + 1);
            showAward(game);
            toEmpty(game);
        }
        knock++;
    }

    public void showAward(LGame game) {
        for (int i = 0; i < game.getFile().getLevelObject().length; i++) {
            for (int j = 0; j < game.getFile().getLevelObject()[i].getSectionObject().length; j++) {
                for (int k = 0; k < game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getItemObject().length; k++) {
                    if (game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getItemObject()[k].getX() == x &&
                            game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getItemObject()[k].getY() + 50 == y) {
                        game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0].getItemObject()[k].setShow(true);
                    }
                }
            }
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

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
