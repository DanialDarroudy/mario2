package enemies;

import characters.Character;
import game.LGame;
import items.Shield;
import items.Shot;
import items.Sword;

import javax.swing.*;
import java.awt.*;

public class Goomba extends Enemy {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Goomba.png").getImage();

    //----------------methods------------//
    @Override
    public void paint(Graphics g) {
        if (live) {
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public void move(LGame game) {
        if (live) {
            time += 5;
            x += direction;
            if (time >= 2000) {
                direction *= -1;
                time = 0;
            }
            if (game.gravity(x, y, height)) {
                y += 5;
            }
        }
    }

    @Override
    public void kill(LGame game) {
        super.kill(game);
        game.setScore(game.getScore() + 1);
    }

    @Override
    public void collision(Character character, LGame game) {
        if (theEnemy().intersects(character.getCharacter()) && live) {
            if (character.getY() + character.getHeight() <= y + 5) {
                kill(game);
            } else {
                character.killed();
            }
            character.setVy(0);
            character.setVx(0);
        }
    }

    @Override
    public void swordCollision(Sword sword, LGame game) {
        if (theEnemy().intersects(sword.getSword()) && live && game.getSelectedCharacter().isUseSword()) {
            game.getSelectedCharacter().setUseSword(false);
            kill(game);
        }
    }

    public void shieldCollision(Shield shield, LGame game) {
        if (theEnemy().intersects(shield.theShield()) && live) {
            super.shieldCollision(shield, game);
            kill(game);
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
