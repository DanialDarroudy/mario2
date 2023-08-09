package items;

import characters.Character;
import game.LGame;

import javax.swing.*;
import java.awt.*;

public class Star extends Item {

    private static final Image image = new ImageIcon("src\\main\\java\\pictures\\Mushroom.png").getImage();
    private boolean jump = false;

    //------------methods--------------//

    public void paint(Graphics g) {
        if (show) {
            g.drawImage(image, x, y, null);
        }
    }

    public void move(LGame game) {
        if (show) {
            if (moveTime > 9000) {
                moveTime = 0;
            }
            moveTime += 5;
            if (moveTime > 3000 && moveTime < 6000) {
                x += 1;
            } else if (moveTime > 6000) {
                x -= 1;
            }
            if (game.gravity(x, y, height)) {
                y += 5;
            }
            jump(game);
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

    public void jump(LGame game) {
        jumpTime += 5;
        if (!game.gravity(x, y, height) && jumpTime > 1000) {
            jumpTime = 0;
            jump = true;
        }
        if (jumpTime > 50) {
            jump = false;
        }
        if (jump) {
            y -= 10;
        }
    }

    public void get(LGame game) {
        super.get(game);
        show = false;
        game.setScore(game.getScore() + 40);
        game.levelUp();
        shield(game);
    }

    public void shield(LGame game) {
        game.getSelectedCharacter().setAntiKnock(true);
        Shield shield = new Shield(game.getSelectedCharacter().getX() - 25,
                game.getSelectedCharacter().getY() - (2 - game.getSelectedCharacter().getState()) * 25);
        game.getSelectedCharacter().setShield(shield);
    }
}
