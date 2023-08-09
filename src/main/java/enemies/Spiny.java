package enemies;

import characters.Character;
import game.LGame;
import items.Shield;
import items.Shot;
import items.Sword;

import javax.swing.*;
import java.awt.*;

public class Spiny extends Enemy {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Spiny.png").getImage();

    //----------------methods------------//
    public void paint(Graphics g) {
        if (live) {
            g.drawImage(image, x, y, null);
        }
    }

    public void move(LGame game) {
        if (live) {
            if ((game.getSelectedCharacter().getX() - x <= 400 && game.getSelectedCharacter().getX() - x >= 0) ||
                    (x - game.getSelectedCharacter().getX() <= 400 || x - game.getSelectedCharacter().getX() >= 0)
                            && game.getSelectedCharacter().getY() == y) {
                vx += acceleration;
                if (game.getSelectedCharacter().getX() - x >= 0) {
                    x += vx / 20;
                } else {
                    x -= vx / 20;
                }
            } else {
                vx = 0;
                x += direction;
                time += 5;
                if (game.gravity(x, y, height)) {
                    y += 5;
                }
                if (time >= 2000) {
                    direction *= -1;
                    time = 0;
                }
            }
        }
    }

    public void shieldCollision(Shield shield, LGame game) {
        if (theEnemy().intersects(shield.theShield()) && live) {
            super.shieldCollision(shield, game);
            kill(game);
        }
    }

    @Override
    public void swordCollision(Sword sword, LGame game) {
        if (theEnemy().intersects(sword.getSword()) && live && game.getSelectedCharacter().isUseSword()) {
            game.getSelectedCharacter().setUseSword(false);
            kill(game);
        }
    }

    @Override
    public void kill(LGame game) {
        super.kill(game);
        game.setScore(game.getScore() + 3);
    }

    @Override
    public void collision(Character character, LGame game) {
        if (theEnemy().intersects(character.getCharacter()) && live) {
            character.killed();
            character.setVy(0);
            character.setVx(0);
        }
    }

    @Override
    public void shotCollision(Shot shot, LGame game) {
        if (theEnemy().intersects(shot.getShot()) && live && shot.isLive()) {
            shot.setLive(false);
            kill(game);
        }
    }

    public Rectangle theEnemy() {
        return new Rectangle(x, y, width, height);
    }
}
