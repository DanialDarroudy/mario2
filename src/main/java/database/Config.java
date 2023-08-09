package database;


import blocks.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enemies.*;
import items.*;
import pipes.Pipe;
import pipes.PlantPipe;
import pipes.SimplePipe;
import stage.File;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    private int stage = 0;
    private JFileChooser fileChooser;
    public static final ArrayList<File> files = new ArrayList<>();
    //--------------for singleton---------------//
    private final static Config instance = new Config();

    private Config() {

    }

    public static Config getInstance() {
        return instance;
    }

    //-------------methods-------------//
    public void setStage() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader;
            if (stage == 0) {
                reader = java.nio.file.Files.newBufferedReader(Paths.get("src/main/resources/Stage.json"));
            } else {
                reader = java.nio.file.Files.newBufferedReader(Paths.get(fileChooser.getSelectedFile().getPath()));
            }
            File file = gson.fromJson(reader, File.class);
            files.add(file);
            setAbstract(file);
            reader.close();
        } catch (IOException ignored) {
        }
    }

    public void setAbstract(File file) {
        for (int i = 0; i < file.getLevelObject().length; i++) {
            for (int j = 0; j < file.getLevelObject()[i].getSectionObject().length; j++) {
                //for blocks
                if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject() != null) {
                    for (int k = 0; k < file.getLevelObject()[i].getSectionObject()[j].getBlockObject().length; k++) {
                        if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k].getType().equals("award")) {
                            Block block = file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k];
                            Award award = new Award();
                            award.setShow(block.isShow());
                            award.setKnock(block.getKnock());
                            award.setX(block.getX());
                            award.setY(block.getY());
                            award.setType(block.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k] = award;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k].getType().equals("empty")) {
                            Block block = file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k];
                            Empty empty = new Empty();
                            empty.setShow(block.isShow());
                            empty.setKnock(block.getKnock());
                            empty.setX(block.getX());
                            empty.setY(block.getY());
                            empty.setType(block.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k] = empty;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k].getType().equals("have coin")) {
                            Block block = file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k];
                            HaveCoin haveCoin = new HaveCoin();
                            haveCoin.setShow(block.isShow());
                            haveCoin.setKnock(block.getKnock());
                            haveCoin.setX(block.getX());
                            haveCoin.setY(block.getY());
                            haveCoin.setType(block.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k] = haveCoin;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k].getType().equals("lot coin")) {
                            Block block = file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k];
                            LotsOfCoin lotsOfCoin = new LotsOfCoin();
                            lotsOfCoin.setShow(block.isShow());
                            lotsOfCoin.setKnock(block.getKnock());
                            lotsOfCoin.setX(block.getX());
                            lotsOfCoin.setY(block.getY());
                            lotsOfCoin.setType(block.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k] = lotsOfCoin;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k].getType().equals("simple")) {
                            Block block = file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k];
                            Simple simple = new Simple();
                            simple.setShow(block.isShow());
                            simple.setKnock(block.getKnock());
                            simple.setX(block.getX());
                            simple.setY(block.getY());
                            simple.setType(block.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k] = simple;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k].getType().equals("slime")) {
                            Block block = file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k];
                            Slime slime = new Slime();
                            slime.setShow(block.isShow());
                            slime.setKnock(block.getKnock());
                            slime.setX(block.getX());
                            slime.setY(block.getY());
                            slime.setType(block.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getBlockObject()[k] = slime;
                        }
                    }
                }
                //for enemies
                if (file.getLevelObject()[i].getSectionObject()[j].getEnemyObject() != null) {
                    for (int k = 0; k < file.getLevelObject()[i].getSectionObject()[j].getEnemyObject().length; k++) {
                        if (file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k].getType().equals("bird")) {
                            Enemy enemy = file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k];
                            Bird bird = new Bird();
                            bird.setAcceleration(enemy.getAcceleration());
                            bird.setX(enemy.getX());
                            bird.setY(enemy.getY());
                            bird.setType(enemy.getType());
                            bird.setLive(enemy.isLive());
                            bird.setDirection(enemy.getDirection());
                            bird.setTime(enemy.getTime());
                            bird.setVx(enemy.getVx());
                            file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k] = bird;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k].getType().equals("goomba")) {
                            Enemy enemy = file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k];
                            Goomba goomba = new Goomba();
                            goomba.setAcceleration(enemy.getAcceleration());
                            goomba.setX(enemy.getX());
                            goomba.setY(enemy.getY());
                            goomba.setType(enemy.getType());
                            goomba.setLive(enemy.isLive());
                            goomba.setDirection(enemy.getDirection());
                            goomba.setTime(enemy.getTime());
                            goomba.setVx(enemy.getVx());
                            file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k] = goomba;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k].getType().equals("koopa")) {
                            Enemy enemy = file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k];
                            Koopa koopa = new Koopa();
                            koopa.setAcceleration(enemy.getAcceleration());
                            koopa.setX(enemy.getX());
                            koopa.setY(enemy.getY());
                            koopa.setType(enemy.getType());
                            koopa.setLive(enemy.isLive());
                            koopa.setDirection(enemy.getDirection());
                            koopa.setTime(enemy.getTime());
                            koopa.setVx(enemy.getVx());
                            file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k] = koopa;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k].getType().equals("spiny")) {
                            Enemy enemy = file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k];
                            Spiny spiny = new Spiny();
                            spiny.setAcceleration(enemy.getAcceleration());
                            spiny.setX(enemy.getX());
                            spiny.setY(enemy.getY());
                            spiny.setType(enemy.getType());
                            spiny.setLive(enemy.isLive());
                            spiny.setDirection(enemy.getDirection());
                            spiny.setTime(enemy.getTime());
                            spiny.setVx(enemy.getVx());
                            file.getLevelObject()[i].getSectionObject()[j].getEnemyObject()[k] = spiny;
                        }
                    }
                }
                //for items
                if (file.getLevelObject()[i].getSectionObject()[j].getItemObject() != null) {
                    for (int k = 0; k < file.getLevelObject()[i].getSectionObject()[j].getItemObject().length; k++) {
                        if (file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k].getType().equals("coin")) {
                            Item item = file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k];
                            Coin coin = new Coin();
                            coin.setTime(item.getTime());
                            coin.setX(item.getX());
                            coin.setY(item.getY());
                            coin.setType(item.getType());
                            coin.setShow(item.isShow());
                            coin.setJumpTime(item.getJumpTime());
                            coin.setMoveTime(item.getMoveTime());
                            file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k] = coin;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k].getType().equals("flower")) {
                            Item item = file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k];
                            Flower flower = new Flower();
                            flower.setTime(item.getTime());
                            flower.setX(item.getX());
                            flower.setY(item.getY());
                            flower.setType(item.getType());
                            flower.setShow(item.isShow());
                            flower.setJumpTime(item.getJumpTime());
                            flower.setMoveTime(item.getMoveTime());
                            file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k] = flower;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k].getType().equals("mushroom")) {
                            Item item = file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k];
                            Mushroom mushroom = new Mushroom();
                            mushroom.setTime(item.getTime());
                            mushroom.setX(item.getX());
                            mushroom.setY(item.getY());
                            mushroom.setType(item.getType());
                            mushroom.setShow(item.isShow());
                            mushroom.setJumpTime(item.getJumpTime());
                            mushroom.setMoveTime(item.getMoveTime());
                            file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k] = mushroom;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k].getType().equals("star")) {
                            Item item = file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k];
                            Star star = new Star();
                            star.setTime(item.getTime());
                            star.setX(item.getX());
                            star.setY(item.getY());
                            star.setType(item.getType());
                            star.setShow(item.isShow());
                            star.setJumpTime(item.getJumpTime());
                            star.setMoveTime(item.getMoveTime());
                            file.getLevelObject()[i].getSectionObject()[j].getItemObject()[k] = star;
                        }
                    }
                }
                //for pipes
                if (file.getLevelObject()[i].getSectionObject()[j].getPipeObject() != null) {
                    for (int k = 0; k < file.getLevelObject()[i].getSectionObject()[j].getPipeObject().length; k++) {
                        if (file.getLevelObject()[i].getSectionObject()[j].getPipeObject()[k].getType().equals("plant")) {
                            Pipe pipe = file.getLevelObject()[i].getSectionObject()[j].getPipeObject()[k];
                            PlantPipe plantPipe = new PlantPipe();
                            plantPipe.setPlant(pipe.getPlant());
                            plantPipe.setTime(pipe.getTime());
                            plantPipe.setX(pipe.getX());
                            plantPipe.setY(pipe.getY());
                            plantPipe.setType(pipe.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getPipeObject()[k] = plantPipe;
                        } else if (file.getLevelObject()[i].getSectionObject()[j].getPipeObject()[k].getType().equals("simple")) {
                            Pipe pipe = file.getLevelObject()[i].getSectionObject()[j].getPipeObject()[k];
                            SimplePipe simplePipe = new SimplePipe();
                            simplePipe.setPlant(pipe.getPlant());
                            simplePipe.setTime(pipe.getTime());
                            simplePipe.setX(pipe.getX());
                            simplePipe.setY(pipe.getY());
                            simplePipe.setType(pipe.getType());
                            file.getLevelObject()[i].getSectionObject()[j].getPipeObject()[k] = simplePipe;
                        }
                    }
                }
            }
        }
    }
    //--------------getters----------------//

    public int getStage() {
        return stage;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
    //---------------setters-----------------//

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
}
