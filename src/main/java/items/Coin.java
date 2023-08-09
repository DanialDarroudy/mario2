package items;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Coin extends Item {
    private static Image image = new ImageIcon("src\\main\\java\\pictures\\Coin.png").getImage();


    //-----------methods-------------//
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
        game.setCoin(game.getCoin() + 1);
        game.setScore(game.getScore() + 10);
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
