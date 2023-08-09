package enemies;

import characters.Character;
import game.Audio;
import game.LGame;
import stage.Scene;

import javax.swing.*;
import java.awt.*;

public class Nuke {
    private static final Image image = new ImageIcon("src\\main\\java\\pictures\\Nuke.png").getImage();
    private int x;
    private int y;
    private static final int width = 50;
    private static final int height = 50;
    private boolean live = true;

    public Nuke(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        if (live) {
            g.drawImage(image, x, y, null);
        }
    }

    public void gravity(LGame game) {
        if (live) {
            y += 1;
            collision(game.getSelectedCharacter(), game);
            if (y == 800) {
                live = false;
                Audio.getInstance().getSound("bomb").start();
            }
        }
    }

    public void collision(Character character, LGame game) {
        if (theNuke().intersects(character.getCharacter()) && live) {
            character.setVy(0);
            character.setVx(0);
            character.killed();
            live = false;
            Audio.getInstance().getSound("bomb").start();
        }
        for (int i = 0; i < game.getFile().getLevelObject().length; i++) {
            if (game.getFile().getWhichLevel()[i]) {
                for (int j = 0; j < game.getFile().getLevelObject()[i].getSectionObject().length; j++) {
                    if (game.getFile().getLevelObject()[i].getWhichSection()[j]) {
                        Scene scene = game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0];
                        for (int k = 0; k < scene.getEnemyObject().length; k++) {
                            if (scene.getEnemyObject()[k].live && !(scene.getEnemyObject()[k] instanceof Bird)) {
                                if (theNuke().intersects(scene.getEnemyObject()[k].theEnemy())) {
                                    scene.getEnemyObject()[k].live = false;
                                    live = false;
                                    Audio.getInstance().getSound("bomb").start();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Rectangle theNuke() {
        return new Rectangle(x - 50, y, width + 100, height + 50);
    }


    public boolean isLive() {
        return live;
    }
}
