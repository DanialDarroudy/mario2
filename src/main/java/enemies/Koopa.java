package enemies;

import characters.Character;
import game.LGame;
import items.Shield;
import items.Shot;
import items.Sword;

import javax.swing.*;
import java.awt.*;

public class Koopa extends Enemy {
    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Koopa.png").getImage();
    private final static Image sleepImage = new ImageIcon("src\\main\\java\\pictures\\SleepKoopa.png").getImage();
    private boolean sleep = false;

    public void paint(Graphics g) {
        if (live) {
            if (!sleep) {
                g.drawImage(image, x, y, null);
            } else {
                g.drawImage(sleepImage, x, y, null);
            }
        }
    }

    //----------------methods------------//
    public void move(LGame game) {
        if (live) {
            if (!sleep) {
                time += 5;
                x += direction;
                if (time >= 2000) {
                    direction *= -1;
                    time = 0;
                }
                if (game.gravity(x, y, height)) {
                    y += 5;
                }
            } else {
                time += 5;
                if (time >= 3000) {
                    time = 0;
                    sleep = false;
                }
            }
        }
    }

    public void sleep() {
        x += 100;
        sleep = true;
        time = 0;
    }

    @Override
    public void kill(LGame game) {
        super.kill(game);
        game.setScore(game.getScore() + 2);
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
            if (!sleep) {
                sleep();
            } else {
                kill(game);
            }
        }
    }

    @Override
    public void collision(Character character, LGame game) {
        if (theEnemy().intersects(character.getCharacter()) && live) {
            if (character.getY() + character.getHeight() <= y + 5) {
                if (!sleep) {
                    sleep();
                } else {
                    kill(game);
                }
            } else {
                if (!sleep) {
                    character.killed();
                }
            }
            character.setVy(0);
            character.setVx(0);
        }
    }

    @Override
    public void shotCollision(Shot shot, LGame game) {
        if (theEnemy().intersects(shot.getShot()) && live && shot.isLive()) {
            shot.setLive(false);
            if (!sleep) {
                sleep();
            } else {
                kill(game);
            }
        }
    }

    public Rectangle theEnemy() {
        return new Rectangle(x, y, width, height);
    }
}
