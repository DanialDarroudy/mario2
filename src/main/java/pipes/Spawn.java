package pipes;

import characters.Character;
import game.LGame;
import items.Coin;
import items.Item;
import stage.Scene;

import javax.swing.*;
import java.awt.*;

public class Spawn {
    private int x;
    private int y;
    private String type = "spawn";
    private final static int width = 100;

    private final static Image image = new ImageIcon("src\\main\\java\\pictures\\Spawn.png").getImage();
    private final static int height = 200;
    private boolean enter;

    public void paint(Graphics g) {
        g.drawImage(image, x, y + 20, null);
    }

    public void collision(Character character, LGame game) {
        if (thePipe().intersects(character.getCharacter())) {
            if (character.getY() >= y - character.getHeight() && character.getX() >= x && character.getX() <= x + 50) {
                spawn(game);
            } else {
                character.setVy(0);
                character.setVx(0);
            }
        }
    }

    public Rectangle thePipe() {
        return new Rectangle(x, y + 20, width, height);
    }

    public void spawn(LGame game) {
        if (enter) {
            Scene scene = new Scene();
            Spawn spawn = new Spawn();
            spawn.enter = false;
            spawn.x = 1500;
            spawn.y = 600;
            spawn.type = "spawn";
            scene.setSpawnPipe(spawn);
            Item[] items = new Item[10];
            for (int i = 0; i < 10; i++) {
                Coin coin = new Coin();
                coin.setX((i + 1) * 100);
                coin.setY(750);
                coin.setShow(true);
                coin.setType("coin");
                items[i] = coin;
            }
            scene.setItemObject(items);
            for (int i = 0; i < game.getFile().getWhichLevel().length; i++) {
                if (game.getFile().getWhichLevel()[i]) {
                    for (int j = 0; j < game.getFile().getLevelObject()[i].getWhichSection().length; j++) {
                        if (game.getFile().getLevelObject()[i].getWhichSection()[j]) {
                            Scene.hide = game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0];
                            game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0] = scene;
                            game.getSelectedCharacter().setY(200);
                            game.getSelectedCharacter().setX(900);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < game.getFile().getWhichLevel().length; i++) {
                if (game.getFile().getWhichLevel()[i]) {
                    for (int j = 0; j < game.getFile().getLevelObject()[i].getWhichSection().length; j++) {
                        if (game.getFile().getLevelObject()[i].getWhichSection()[j]) {
                            game.getFile().getLevelObject()[i].getSectionObject()[j].getSceneObject()[0] = Scene.hide;
                            game.getSelectedCharacter().setY(800 - game.getSelectedCharacter().getHeight());
                            game.getSelectedCharacter().setX(0);
                        }
                    }
                }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
