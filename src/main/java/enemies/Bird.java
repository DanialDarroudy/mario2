package enemies;

import characters.Character;
import game.LGame;
import items.Shield;
import items.Shot;
import items.Sword;

import javax.swing.*;
import java.awt.*;

public class Bird extends Enemy {
    private static final Image image = new ImageIcon("src\\main\\java\\pictures\\Bird.png").getImage();

    //------------methods--------------//
    @Override
    public void paint(Graphics g) {
        if (live) {
            g.drawImage(image, x, y, null);
            if (nuke != null && nuke.isLive()) {
                nuke.paint(g);
            }
        }
    }

    @Override
    public void move(LGame game) {
        if (live) {
            time += 10;
            x += direction;
            if (time > 3000) {
                time = 0;
                direction *= -1;
                nuke = new Nuke(x, y);
            }
        }
        if (nuke != null && nuke.isLive()) {
            nuke.gravity(game);
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
            character.setVy(0);
            character.setVx(0);
            character.killed();
        }
    }

    @Override
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

    @Override
    public void swordCollision(Sword sword, LGame game) {
        if (theEnemy().intersects(sword.getSword()) && live && game.getSelectedCharacter().isUseSword()) {
            game.getSelectedCharacter().setUseSword(false);
            kill(game);
        }
    }

    public Rectangle theEnemy() {
        return new Rectangle(x, y, width, height);
    }
}
