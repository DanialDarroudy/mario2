package stage;

import blocks.Award;
import blocks.Block;
import enemies.Enemy;
import enemies.Goomba;
import items.Hollow;
import items.Item;
import pipes.Pipe;
import pipes.SimplePipe;
import pipes.Spawn;

import java.awt.*;
import java.util.ArrayList;

public class Section {
    private int length;
    private int time;
    private Block blockObject[];
    private Enemy enemyObject[];
    private Pipe pipeObject[];
    private Spawn spawnPipe;
    private Hollow hollow;
    private Item itemObject[];
    private Scene sceneObject[];

    public void buildScene() {
        //add hollow and items in scene 2
        length *= 50;
        int scene = length / 1800;
        if (scene == 0) {
            sceneObject = new Scene[1];
            sceneObject[0] = new Scene();
            sceneObject[0].setBlockObject(blockObject);
            sceneObject[0].setEnemyObject(enemyObject);
            sceneObject[0].setPipeObject(pipeObject);
            sceneObject[0].setSpawnPipe(spawnPipe);
            sceneObject[0].setHollow(hollow);
            sceneObject[0].setItemObject(itemObject);
        } else if (scene == 1) {
            sceneObject = new Scene[2];
            sceneObject[0] = new Scene();
            ArrayList<Block> blocks0 = new ArrayList<>();
            for (Block block : blockObject) {
                if (block.getX() < 1800) {
                    blocks0.add(block);
                }
            }
            sceneObject[0].setBlockObject((Block[]) blocks0.toArray());
            ArrayList<Enemy> enemies0 = new ArrayList<>();
            for (Enemy enemy : enemyObject) {
                if (enemy.getX() < 1800) {
                    enemies0.add(enemy);
                }
            }
            sceneObject[0].setEnemyObject((Enemy[]) enemies0.toArray());
            ArrayList<Pipe> pipes0 = new ArrayList<>();
            for (Pipe pipe : pipeObject) {
                if (pipe.getX() < 1800) {
                    pipes0.add(pipe);
                }
            }
            sceneObject[0].setPipeObject((Pipe[]) pipes0.toArray());
            if (spawnPipe.getX() < 1800) {
                sceneObject[0].setSpawnPipe(spawnPipe);
            }
            sceneObject[1] = new Scene();
            ArrayList<Block> blocks1 = new ArrayList<>();
            for (Block block : blockObject) {
                if (block.getX() > 1800) {
                    blocks1.add(block);
                }
            }
            sceneObject[1].setBlockObject((Block[]) blocks1.toArray());
            ArrayList<Enemy> enemies1 = new ArrayList<>();
            for (Enemy enemy : enemyObject) {
                if (enemy.getX() > 1800) {
                    enemies1.add(enemy);
                }
            }
            sceneObject[1].setEnemyObject((Enemy[]) enemies1.toArray());
            ArrayList<Pipe> pipes1 = new ArrayList<>();
            for (Pipe pipe : pipeObject) {
                if (pipe.getX() > 1800) {
                    pipes1.add(pipe);
                }
            }
            sceneObject[1].setPipeObject((Pipe[]) pipes1.toArray());
            if (spawnPipe.getX() > 1800) {
                sceneObject[1].setSpawnPipe(spawnPipe);
            }
        }
    }

    public void paint(Graphics g) {
        sceneObject[0].paint(g);
    }

    //------------getters-----------------//
    public int getLength() {
        return length;
    }

    public int getTime() {
        return time;
    }

    public Hollow getHollow() {
        return hollow;
    }

    public Item[] getItemObject() {
        return itemObject;
    }

    public Block[] getBlockObject() {
        return blockObject;
    }

    public Enemy[] getEnemyObject() {
        return enemyObject;
    }

    public Pipe[] getPipeObject() {
        return pipeObject;
    }

    public Spawn getSpawnPipe() {
        return spawnPipe;
    }

    public Scene[] getSceneObject() {
        return sceneObject;
    }
}
