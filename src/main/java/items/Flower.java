package items;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Flower extends Item {
    private static Image image = new ImageIcon("src\\main\\java\\pictures\\Flower.png").getImage();

    //---------methods---------//
    @Override
    public void paint(Graphics g) {
        if (show) {
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public void get(LGame game) {
        super.get(game);
        show = false;
        game.setScore(game.getScore() + 20);
        game.levelUp();
    }

    @Override
    public void collision(Character character, LGame lGame) {
        if (theItem().intersects(character.getCharacter()) && show) {
            get(lGame);
        }
    }

    @Override
    public Rectangle theItem() {
        return new Rectangle(x, y, width, height);
    }
}
