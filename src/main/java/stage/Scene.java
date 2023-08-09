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

import javax.swing.*;
import java.awt.*;

public class Scene {
    public static Scene hide;
    public final static int width = 1800;
    public final static int height = 1000;
    public final static Image backGround = new ImageIcon("src\\main\\java\\pictures\\BackGround.png").getImage();
    private Block[] blockObject;
    private Enemy[] enemyObject;
    private Pipe[] pipeObject;
    private Spawn spawnPipe;
    private Hollow hollow;
    private Item[] itemObject;

    public void paint(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        if (blockObject != null) {
            for (Block block : blockObject) {
                block.paint(g);
            }
        }
        if (enemyObject != null) {
            for (Enemy enemy : enemyObject) {
                enemy.paint(g);
            }
        }
        if (pipeObject != null) {
            for (Pipe pipe : pipeObject) {
                pipe.paint(g);
            }
        }
        if (itemObject != null) {
            for (Item item : itemObject) {
                item.paint(g);
            }
        }
        if (spawnPipe != null) {
            spawnPipe.paint(g);
        }
        if (hollow != null) {
            hollow.paint(g);
        }
    }


    //------------setters--------------//

    public void setHollow(Hollow hollow) {
        this.hollow = hollow;
    }

    public void setItemObject(Item[] itemObject) {
        this.itemObject = itemObject;
    }

    public void setBlockObject(Block[] blockObject) {
        this.blockObject = blockObject;
    }

    public void setEnemyObject(Enemy[] enemyObject) {
        this.enemyObject = enemyObject;
    }

    public void setPipeObject(Pipe[] pipeObject) {
        this.pipeObject = pipeObject;
    }

    public void setSpawnPipe(Spawn spawnPipe) {
        this.spawnPipe = spawnPipe;
    }


    //-----------getters------------------//

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

    public Hollow getHollow() {
        return hollow;
    }

    public Item[] getItemObject() {
        return itemObject;
    }
}
