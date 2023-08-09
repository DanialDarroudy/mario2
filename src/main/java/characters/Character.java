package characters;

import game.Audio;
import items.Shield;
import items.Shot;
import items.Sword;
import stage.File;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Character {
    //this class should be abstract but because json it isn't
    int state;
    int width = 50;
    int height;
    int x = 0;
    int y = 800 - height;
    int vx = 0;
    int vy = 0;
    int heart;
    boolean decreaseHeart = false;
    boolean antiKnock = false;
    boolean slime = false;
    boolean sit = false;
    Shot shot;
    int shotColdDown = 0;
    int swordColdDown = 0;
    int sword = 0;
    boolean useSword = false;
    int jump = 100;
    Shield shield;
    Sword mainSword;
    String type;

    //------------------------------------------------methods---------------------------------------------------------//

    public static ArrayList<String> characters() {
        ArrayList<String> characters = new ArrayList<>();
        characters.add("Mario");
        characters.add("Wario");
        characters.add("Toad");
        characters.add("Boo");
        characters.add("Yoshi");
        return characters;
    }

    public static HashMap<String, ImageIcon> pictureOfCharacters() {
        HashMap<String, ImageIcon> pictureOfCharacters = new HashMap<>();
        pictureOfCharacters.put("Mario", Mario.getImageIcon());
        pictureOfCharacters.put("Wario", Wario.getImageIcon());
        pictureOfCharacters.put("Toad", Toad.getImageIcon());
        pictureOfCharacters.put("Boo", Boo.getImageIcon());
        pictureOfCharacters.put("Yoshi", Yoshi.getImageIcon());
        return pictureOfCharacters;
    }

    public void paint(Graphics g) {
        if (antiKnock && shield != null) {
            shield.paint(g);
        }
        if (shot != null && shot.isLive()) {
            shot.paint(g);
        }
        if (mainSword != null && useSword) {
            mainSword.paint(g);
        }
    }

    public void checkMario() {
        if (state == 0 || sit) {
            height = 50;
        } else if (state == 1) {
            height = 75;
        } else {
            height = 100;
        }
    }

    public void move() {
        // for jump
        jump();
        //for times
        shotColdDown += 5;
        swordColdDown += 5;
        //for power up
        checkMario();
        //for move
        y += vy;
        vy = 0;
        x += vx;
        //for shot
        moveShot();
        //for sword
        moveSword();
        //for shield
        shield();
    }


    public Rectangle getCharacter() {
        return new Rectangle(x, y, width, height);
    }

    public void jump() {
        if (slime) {
            if (jump <= 75) {
                vy = -10;
                jump++;
                if (jump == 76) {
                    slime = false;
                }
            }
        } else {
            if (jump <= 50) {
                vy = -10;
                jump++;
            }
        }
    }

    public void killed() {
        if (state == 0) {
            heart--;
            this.setDecreaseHeart(true);
            Audio.getInstance().getSound("killed").start();
        } else {
            state -= 1;
        }
    }

    public void moveSword() {
        if (useSword) {
            if (mainSword.getX() - mainSword.getInitX() >= 400) {
                mainSword.setBack(true);
            }
            if (mainSword.isBack()) {
                mainSword.setX(mainSword.getX() - 5);
                if (mainSword.getX() == mainSword.getInitX()) {
                    useSword = false;
                }
            } else {
                mainSword.setX(mainSword.getX() + 5);
            }
        }
    }

    public void moveShot() {
        if (shot != null && shot.isLive()) {
            shot.setX(shot.getX() + 5);
            if (shot.getX() - shot.getInitX() >= 400) {
                shot.setLive(false);
            }
        }
    }

    public void fire() {
        if (state == 2 && shotColdDown >= 3000) {
            shotColdDown = 0;
            shot = new Shot(x + 50, y + 70);
        }
    }

    public void shield() {
        if (shield != null && antiKnock) {
            shield.setTime(shield.getTime() + 5);
            if (shield.getTime() > 15000) {
                antiKnock = false;
                shield.setShow(false);
            }
            shield.setX(x - 25);
            shield.setY(y + (2 - state) * 25);
        }
    }

    //------------------------------------------------getters---------------------------------------------------------//

    public int getVx() {
        return vx;
    }

    public boolean isAntiKnock() {
        return antiKnock;
    }

    public boolean isUseSword() {
        return useSword;
    }

    public boolean isSlime() {
        return slime;
    }

    public boolean isSit() {
        return sit;
    }

    public int getShotColdDown() {
        return shotColdDown;
    }

    public Sword getMainSword() {
        return mainSword;
    }

    public int getSword() {
        return sword;
    }

    public int getJump() {
        return jump;
    }

    public int getState() {
        return state;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }

    public boolean isDecreaseHeart() {
        return decreaseHeart;
    }

    public int getVy() {
        return vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Shield getShield() {
        return shield;
    }

    public int getHeart() {
        return heart;
    }


    public int getSwordColdDown() {
        return swordColdDown;
    }

    public Shot getShot() {
        return shot;
    }
    //------------------------------------------------setters---------------------------------------------------------//

    public void setSwordColdDown(int swordColdDown) {
        this.swordColdDown = swordColdDown;
    }

    public void setShotColdDown(int shotColdDown) {
        this.shotColdDown = shotColdDown;
    }

    public void setMainSword(Sword mainSword) {
        this.mainSword = mainSword;
    }

    public void setSword(int sword) {
        this.sword = sword;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }

    public void setDecreaseHeart(boolean decreaseHeart) {
        this.decreaseHeart = decreaseHeart;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }


    public void setVy(int vy) {
        this.vy = vy;
    }

    public void setAntiKnock(boolean antiKnock) {
        this.antiKnock = antiKnock;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setUseSword(boolean useSword) {
        this.useSword = useSword;
    }

    public void setSlime(boolean slime) {
        this.slime = slime;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }
}
