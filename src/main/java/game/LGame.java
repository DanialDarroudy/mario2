package game;

import blocks.Block;
import characters.Character;
import database.Config;
import enemies.Enemy;
import enemies.Koopa;
import items.Hollow;
import items.Sword;
import main.User;
import pipes.Pipe;
import pipes.PlantPipe;
import stage.File;
import stage.Level;
import stage.Scene;

import javax.sound.sampled.Clip;
import java.util.Arrays;

public class LGame implements Cloneable {
    //this class for logic of game
    private File file;
    private int time;
    private int coin = 0;
    private int score = 0;
    private Character selectedCharacter;
    private double progressRate = 0;
    private double progressRisk = 0;
    private double Dn = 0;
    private boolean gameOver = false;
    private boolean finishGame = false;

    //-----------methods--------------//
    public LGame(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
        //for set last file
        file = Config.files.get(Config.files.size() - 1);
        Config.files.remove(Config.files.size() - 1);
        //for set time
        time = file.getLevelObject()[0].getSectionObject()[0].getTime() * 1000;
        //for play sound
        Audio.getInstance().getSound("main").start();
        Audio.getInstance().getSound("main").loop(Clip.LOOP_CONTINUOUSLY);
        //for init mario
        this.selectedCharacter.setState(file.getState());
        this.selectedCharacter.setHeart(file.getHearts());
        this.selectedCharacter.setHeight(50 + this.selectedCharacter.getState() * 25);
        this.selectedCharacter.setY(800 - selectedCharacter.getHeight());
        //for build scenes
        for (int i = 0; i < file.getLevelObject().length; i++) {
            for (int j = 0; j < file.getLevelObject()[i].getSectionObject().length; j++) {
                file.getLevelObject()[i].getSectionObject()[j].buildScene();
            }
        }

    }

    public void move(User user) {
        // for progress rate and risk and Dn
        initProgress();
        //for time
        time -= 5;
        // for sword
        initSword();
        //for mario move
        selectedCharacter.move();
        //for mario killed
        if (selectedCharacter.isDecreaseHeart()) {
            selectedCharacter.setDecreaseHeart(false);
            coin -= Dn;
            killedMario(user);
        }
        //for hollow
        checkMove(user);
        //for gravity
        if (gravity(selectedCharacter.getX(), selectedCharacter.getY(), selectedCharacter.getHeight())) {
            selectedCharacter.setY(selectedCharacter.getY() + 5);
        }
        //for collisions
        collision();
        //for gate
        if (finishSection()) {
            nextGame();
            selectedCharacter.setY(800 - selectedCharacter.getHeight());
            selectedCharacter.setX(0);
        }
        //for move enemies
        moveEnemy();
        //for limit time
        if (time < 0) {
            selectedCharacter.setHeart(selectedCharacter.getHeart() - 1);
            selectedCharacter.setX(0);
            selectedCharacter.setY(725);
            for (int i = 0; i < file.getLevelObject().length; i++) {
                if (file.getWhichLevel()[i]) {
                    for (int j = 0; j < file.getLevelObject()[i].getSectionObject().length; j++) {
                        if (file.getLevelObject()[i].getWhichSection()[j]) {
                            time = file.getLevelObject()[i].getSectionObject()[j].getTime() * 1000;
                        }
                    }
                }
            }
        }
        //for limit hearts
        if (selectedCharacter.getHeart() < 0) {
            for (int i = 0; i < file.getLevelObject().length; i++) {
                if (file.getWhichLevel()[i]) {
                    resetLevel(file.getLevelObject()[i]);
                }
            }
            selectedCharacter.setHeart(file.getHearts());
            gameOver = true;
        }
    }

    public void killedMario(User user) {
        for (int i = 0; i < file.getWhichLevel().length; i++) {
            if (file.getWhichLevel()[i]) {
                if (file.getLevelObject()[i].getCheckPointObject().isSave()) {
                    if (file.getLevelObject()[i].getCheckPointObject().getWhichGame() == 1) {
                        selectedCharacter.setX(user.getGame1().getSelectedCharacter().getX());
                        selectedCharacter.setY(user.getGame1().getSelectedCharacter().getY());
                        file = user.getGame1().getFile();
                    } else if (file.getLevelObject()[i].getCheckPointObject().getWhichGame() == 2) {
                        selectedCharacter.setX(user.getGame2().getSelectedCharacter().getX());
                        selectedCharacter.setY(user.getGame2().getSelectedCharacter().getY());
                        file = user.getGame2().getFile();
                    } else {
                        selectedCharacter.setX(user.getGame3().getSelectedCharacter().getX());
                        selectedCharacter.setY(user.getGame3().getSelectedCharacter().getY());
                        file = user.getGame3().getFile();

                    }
                } else {
                    selectedCharacter.setX(0);
                    selectedCharacter.setY(750);
                }
            }
        }
        if (score >= 20) {
            score -= 20;
        } else {
            score = 0;
        }
    }

    public void moveEnemy() {
        for (int i = 0; i < file.getWhichLevel().length; i++) {
            if (file.getWhichLevel()[i]) {
                for (int j = 0; j < file.getLevelObject()[i].getWhichSection().length; j++) {
                    if (file.getLevelObject()[i].getWhichSection()[j]) {
                        Scene scene = file.getLevelObject()[i].getSectionObject()[j].getSceneObject()[0];
                        if (scene.getEnemyObject() != null) {
                            for (int k = 0; k < scene.getEnemyObject().length; k++) {
                                scene.getEnemyObject()[k].move(this);
                            }
                        }
                    }
                }
            }
        }
    }

    public void resetLevel(Level level) {
        Arrays.fill(level.getWhichSection(), false);
        level.getWhichSection()[0] = true;
        time = level.getSectionObject()[0].getTime() * 1000;
        selectedCharacter.setX(0);
        selectedCharacter.setY(750);
    }

    public void collision() {
        for (int i = 0; i < file.getWhichLevel().length; i++) {
            if (file.getWhichLevel()[i]) {
                for (int j = 0; j < file.getLevelObject()[i].getWhichSection().length; j++) {
                    if (file.getLevelObject()[i].getWhichSection()[j]) {
                        Scene scene = file.getLevelObject()[i].getSectionObject()[j].getSceneObject()[0];
                        if (scene.getBlockObject() != null) {
                            for (int k = 0; k < scene.getBlockObject().length; k++) {
                                scene.getBlockObject()[k].collision(selectedCharacter, this);
                            }
                        }
                        if (scene.getEnemyObject() != null) {
                            for (int k = 0; k < scene.getEnemyObject().length; k++) {
                                scene.getEnemyObject()[k].collision(selectedCharacter, this);
                                if (selectedCharacter.getShot() != null && selectedCharacter.getShot().isLive()) {
                                    scene.getEnemyObject()[k].shotCollision(selectedCharacter.getShot(), this);
                                }
                                if (selectedCharacter.getShield() != null && selectedCharacter.isAntiKnock()) {
                                    scene.getEnemyObject()[k].shieldCollision(selectedCharacter.getShield(), this);
                                }
                                if (selectedCharacter.getMainSword() != null && selectedCharacter.isUseSword()) {
                                    scene.getEnemyObject()[k].swordCollision(selectedCharacter.getMainSword(), this);
                                }
                            }
                        }
                        if (scene.getPipeObject() != null) {
                            for (int k = 0; k < scene.getPipeObject().length; k++) {
                                scene.getPipeObject()[k].collision(selectedCharacter);
                                if (scene.getPipeObject()[k] instanceof PlantPipe) {
                                    Pipe pipe = scene.getPipeObject()[k];
                                    if (pipe.getPlant().isLive() && pipe.getPlant().isShow()) {
                                        if (selectedCharacter.getShot() != null && selectedCharacter.getShot().isLive()) {
                                            pipe.getPlant().shotCollision(selectedCharacter.getShot(), this);
                                        }
                                        if (selectedCharacter.getShield() != null && selectedCharacter.isAntiKnock()) {
                                            pipe.getPlant().shieldCollision(selectedCharacter.getShield(), this);
                                        }
                                        if (selectedCharacter.isUseSword() && selectedCharacter.getMainSword() != null) {
                                            pipe.getPlant().swordCollision(selectedCharacter.getMainSword(), this);
                                        }
                                    }
                                }
                            }
                        }
                        for (int k = 0; k < scene.getItemObject().length; k++) {
                            scene.getItemObject()[k].collision(selectedCharacter, this);
                        }
                        if (scene.getSpawnPipe() != null) {
                            scene.getSpawnPipe().collision(selectedCharacter, this);
                        }
                    }

                }
            }
        }
    }

    public boolean gravity(int x, int y, int height) {
        for (int i = 0; i < file.getWhichLevel().length; i++) {
            if (file.getWhichLevel()[i]) {
                for (int j = 0; j < file.getLevelObject()[i].getWhichSection().length; j++) {
                    if (file.getLevelObject()[i].getWhichSection()[j]) {
                        Scene scene = file.getLevelObject()[i].getSectionObject()[j].getSceneObject()[0];
                        if (scene.getHollow() == null) {
                            if (y >= 800 - height) {
                                return false;
                            }
                        } else {
                            if (y >= 800 - height && !(x >= scene.getHollow().getX() &&
                                    x <= scene.getHollow().getX() + Hollow.width - 50)) {
                                return false;
                            }
                        }
                        if (scene.getEnemyObject() != null) {
                            for (int k = 0; k < scene.getEnemyObject().length; k++) {
                                if (scene.getEnemyObject()[k] instanceof Koopa) {
                                    if (scene.getEnemyObject()[k].isLive() && y == scene.getEnemyObject()[k].getY() - height + 5 &&
                                            x >= scene.getEnemyObject()[k].getX() - Enemy.getWidth() &&
                                            x <= scene.getEnemyObject()[k].getX() + Enemy.getWidth()) {
                                        return false;
                                    }
                                }
                            }
                        }
                        if (scene.getBlockObject() != null) {
                            for (int k = 0; k < scene.getBlockObject().length; k++) {
                                if (scene.getBlockObject()[k].isShow() && y == scene.getBlockObject()[k].getY() - height &&
                                        x >= scene.getBlockObject()[k].getX() - Block.getWidth() &&
                                        x <= scene.getBlockObject()[k].getX() + Block.getWidth()) {
                                    return false;
                                }
                            }
                        }
                        if (scene.getPipeObject() != null) {
                            for (int k = 0; k < scene.getPipeObject().length; k++) {
                                if (y == scene.getPipeObject()[k].getY() - height &&
                                        x >= scene.getPipeObject()[k].getX() - 50 &&
                                        x <= scene.getPipeObject()[k].getX() + 100) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void initProgress() {
        for (int i = 0; i < file.getWhichLevel().length; i++) {
            if (file.getWhichLevel()[i]) {
                for (int j = 0; j < file.getLevelObject()[i].getWhichSection().length; j++) {
                    if (file.getLevelObject()[i].getWhichSection()[j]) {
                        progressRate = (double) (selectedCharacter.getX() + 1800 * j) /
                                (double) (file.getLevelObject()[i].getSectionObject().length * 1800);
                        progressRisk = progressRate * coin;
                    }
                }
                if (file.getLevelObject()[i].getCheckPointObject().isSave()) {
                    Dn = (2 * coin + progressRisk) / 5;
                } else {
                    Dn = (coin + progressRisk) / 4;
                }
            }
        }
    }

    public void initSword() {
        if (selectedCharacter.getSword() >= 2000) {
            if (coin >= 3) {
                Sword sword = new Sword();
                selectedCharacter.setSwordColdDown(0);
                selectedCharacter.setUseSword(true);
                selectedCharacter.setSword(0);
                coin -= 3;
                sword.setX(selectedCharacter.getX() + 50);
                sword.setInitX(sword.getX() + 50);
                sword.setY(selectedCharacter.getY());
                selectedCharacter.setMainSword(sword);
            }
        }
    }

    public void checkMove(User user) {
        if (selectedCharacter.getX() <= 0) {
            selectedCharacter.setX(selectedCharacter.getX() + 5);
        }
        if (selectedCharacter.getX() >= 1750 && selectedCharacter.getY() <= 600) {
            selectedCharacter.setX(selectedCharacter.getX() - 5);
        }
        if (selectedCharacter.getY() <= 0) {
            selectedCharacter.setY(selectedCharacter.getY() + 10);
        }
        if (selectedCharacter.getY() >= 900) {
            for (int i = 0; i < file.getWhichLevel().length; i++) {
                if (file.getWhichLevel()[i]) {
                    if (file.getLevelObject()[i].getCheckPointObject().isSave()) {
                        if (file.getLevelObject()[i].getCheckPointObject().getWhichGame() == 1) {
                            selectedCharacter.setX(user.getGame1().getSelectedCharacter().getX());
                            selectedCharacter.setY(user.getGame1().getSelectedCharacter().getY());
                            time = user.getGame1().getTime() * 1000;
                            file = user.getGame1().getFile().clone();
                        } else if (file.getLevelObject()[i].getCheckPointObject().getWhichGame() == 2) {
                            selectedCharacter.setX(user.getGame2().getSelectedCharacter().getX());
                            selectedCharacter.setY(user.getGame2().getSelectedCharacter().getY());
                            time = user.getGame2().getTime() * 1000;
                            file = user.getGame2().getFile().clone();
                        } else {
                            selectedCharacter.setX(user.getGame3().getSelectedCharacter().getX());
                            selectedCharacter.setY(user.getGame3().getSelectedCharacter().getY());
                            time = user.getGame3().getTime() * 1000;
                            file = user.getGame3().getFile().clone();
                        }
                    } else {
                        resetLevel(file.getLevelObject()[i]);
                    }
                    break;
                }
            }
            selectedCharacter.setHeart(selectedCharacter.getHeart() - 1);
            selectedCharacter.setState(0);
            coin -= Dn;
            Audio.getInstance().getSound("killed").start();
            if (score >= 30) {
                score -= 30;
            } else {
                score = 0;
            }
        }
    }

    public void nextGame() {
        out:
        for (int i = 0; i < file.getLevelObject().length; i++) {
            if (file.getWhichLevel()[i]) {
                for (int j = 0; j < file.getLevelObject()[i].getSectionObject().length; j++) {
                    if (file.getLevelObject()[i].getWhichSection()[j]) {
                        if (j + 1 < file.getLevelObject()[i].getSectionObject().length) {
                            file.getLevelObject()[i].getWhichSection()[j + 1] = true;
                            time = file.getLevelObject()[i].getSectionObject()[j + 1].getTime() * 1000;
                        } else {
                            finishLevel();
                            if (i + 1 < file.getLevelObject().length) {
                                file.getWhichLevel()[i + 1] = true;
                                file.getLevelObject()[i + 1].getWhichSection()[0] = true;
                                time = file.getLevelObject()[i + 1].getSectionObject()[0].getTime() * 1000;
                            } else {
                                finishGame();
                            }
                            file.getWhichLevel()[i] = false;
                        }
                        file.getLevelObject()[i].getWhichSection()[j] = false;
                        break out;
                    }
                }
            }
        }
    }

    public boolean finishSection() {
        return selectedCharacter.getX() >= 1750 && selectedCharacter.getY() >= 600;
    }

    public void finishLevel() {
        Audio.getInstance().getSound("finish level").start();
    }

    public void finishGame() {
        Audio.getInstance().getSound("main").stop();
        Audio.getInstance().getSound("finish game").start();
        finishGame = true;
    }

    public void levelUp() {
        int state = selectedCharacter.getState();
        if (state < 2) {
            selectedCharacter.setState(state + 1);
        }
    }

    @Override
    public LGame clone() {
        try {
            return (LGame) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    //-----------getters-----------//

    public File getFile() {
        return file;
    }

    public double getProgressRate() {
        return progressRate;
    }

    public int getTime() {
        return time;
    }

    public int getCoin() {
        return coin;
    }

    public int getScore() {
        return score;
    }

    public boolean isFinishGame() {
        return finishGame;
    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public double getProgressRisk() {
        return progressRisk;
    }

    public double getDn() {
        return Dn;
    }
    //----------setters------------//

    public void setFile(File file) {
        this.file = file;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setProgressRate(double progressRate) {
        this.progressRate = progressRate;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setProgressRisk(double progressRisk) {
        this.progressRisk = progressRisk;
    }

    public void setDn(double dn) {
        Dn = dn;
    }

    public void setFinishGame(boolean finishGame) {
        this.finishGame = finishGame;
    }

    public void setCoin(int Coin) {
        this.coin = Coin;
    }

    public void setScore(int Score) {
        this.score = Score;
    }

    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }


}
