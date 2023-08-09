package enemies;

import characters.Character;
import game.LGame;
import items.Shield;
import items.Shot;
import items.Sword;

import javax.swing.*;
import java.awt.*;

public class Plant extends Enemy {
    public final static Image image = new ImageIcon("src\\main\\java\\pictures\\Plant.png").getImage();
    private boolean show = false;

    //----------methods-------------//
    @Override
    public void paint(Graphics g) {
        if (show && live) {
            g.drawImage(image, x, y, null);
        }
    }

    public void collision(Character character) {
        if (theEnemy().intersects(character.getCharacter()) && live && show) {
            character.setVy(0);
            character.setVx(0);
            character.killed();
        }
    }

    public Rectangle theEnemy() {
        return new Rectangle(x, y, width, height);
    }

    public void shieldCollision(Shield shield, LGame game) {
        if (theEnemy().intersects(shield.theShield()) && live) {
            super.shieldCollision(shield, game);
            kill(game);
        }
    }

    @Override
    public void swordCollision(Sword sword, LGame game) {
        if (theEnemy().intersects(sword.getSword()) && live && game.getSelectedCharacter().isUseSword() && show) {
            game.getSelectedCharacter().setUseSword(false);
            kill(game);
        }
    }

    @Override
    public void kill(LGame game) {
        super.kill(game);
        game.setScore(game.getScore() + 1);
    }

    public void shotCollision(Shot shot, LGame game) {
        if (theEnemy().intersects(shot.getShot()) && live && shot.isLive() && show) {
            shot.setLive(false);
            kill(game);
        }
    }


    //-------------getters-----------//

    public boolean isShow() {
        return show;
    }

    //----------------setters------------//

    public void setShow(boolean show) {
        this.show = show;
    }

}
