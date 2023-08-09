package items;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Mushroom extends Item {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Mushroom.png").getImage();


    //------------methods--------------//
    public void paint(Graphics g) {
        if (show) {
            g.drawImage(image, x, y, null);
        }
    }

    public void move(LGame game) {
        if (show) {
            if (time > 9000) {
                time = 0;
            }
            time += 5;
            if (time > 3000 && time < 6000) {
                x += 1;
            } else if (time > 6000) {
                x -= 1;
            }
            if (game.gravity(x, y, height)) {
                y += 5;
            }
        }
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

    @Override
    public void get(LGame game) {
        super.get(game);
        show = false;
        game.setScore(game.getScore() + 30);
        game.levelUp();

    }

}
