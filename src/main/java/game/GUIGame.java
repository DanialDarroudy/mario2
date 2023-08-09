package game;

import items.CheckPoint;
import items.Shot;
import main.GUI;
import main.Main;
import main.Menu;
import main.User;
import stage.File;
import stage.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIGame extends JPanel {
    //this class for graphic of game
    private LGame lGame;
    private JFrame frame;
    private Timer timer;
    private ActionListener actionListener;
    private User user;
    private int show;


    //----------methods-----------------//
    public GUIGame(LGame lGame, JFrame frame, Timer timer, ActionListener actionListener, User user, int show) {
        this.lGame = lGame;
        this.show = show;
        this.timer = timer;
        this.user = user;
        this.actionListener = actionListener;
        this.frame = frame;
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        lGame.getSelectedCharacter().setVx(-5);
                        break;
                    case KeyEvent.VK_RIGHT:
                        lGame.getSelectedCharacter().setVx(5);
                        break;
                    case KeyEvent.VK_DOWN:
                        lGame.getSelectedCharacter().setSit(true);
                    case KeyEvent.VK_UP:
                        if (lGame.getSelectedCharacter().getSwordColdDown() >= 5000) {
                            lGame.getSelectedCharacter().setSword(lGame.getSelectedCharacter().getSword() + 5);
                        }
                        break;
                    default:
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        lGame.getSelectedCharacter().setVx(0);
                        break;
                    case KeyEvent.VK_DOWN:
                        lGame.getSelectedCharacter().setSit(false);
                        lGame.getSelectedCharacter().setY(lGame.getSelectedCharacter().getY() - lGame.getSelectedCharacter().getState() * 25);
                        lGame.getSelectedCharacter().setSword(0);
                        break;
                    case KeyEvent.VK_UP:
                        if (!lGame.gravity(lGame.getSelectedCharacter().getX(), lGame.getSelectedCharacter().getY(), lGame.getSelectedCharacter().getHeight())) {
                            lGame.getSelectedCharacter().setJump(0);
                        }
                        lGame.getSelectedCharacter().setSword(0);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        stop();
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!lGame.gravity(lGame.getSelectedCharacter().getX(), lGame.getSelectedCharacter().getY(), lGame.getSelectedCharacter().getHeight())) {
                            lGame.getSelectedCharacter().fire();
                        }
                        break;
                    default:
                        break;
                }
            }

            public void keyTyped(KeyEvent e) {
            }
        });
    }

    public void stop() {
        Audio.getInstance().getSound("main").stop();
        timer.stop();
        int init = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the game?"
                , "Stop", JOptionPane.YES_NO_OPTION);
        if (init == JOptionPane.YES_OPTION) {
            Audio.getInstance().getSound("main").close();
            Menu.getInstance().getSingleton().getButtons().get("continue button " + show).setText("Game" + show);
            if (show == 1) {
                user.setContinueButton1(true);
                user.setGame1(lGame);
            } else if (show == 2) {
                user.setContinueButton2(true);
                user.setGame1(lGame);
            } else {
                user.setContinueButton3(true);
                user.setGame1(lGame);
            }
            this.setVisible(false);
            frame.dispose();
            Menu.getInstance().getSingleton().getFrames().get("main panel").setVisible(true);
            timer.removeActionListener(actionListener);
        } else if (init == JOptionPane.NO_OPTION) {
            timer.start();
            Audio.getInstance().getSound("main").start();
        }
    }

    public void paintInformation(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Coin : " + lGame.getCoin(), 50, 50);
        g.drawString("Heart : " + lGame.getSelectedCharacter().getHeart(), 350, 50);
        g.drawString("Time : " + lGame.getTime() / 1000, 650, 50);
        g.drawString("Score : " + lGame.getScore(), 950, 50);
        int level = 1;
        int section = 1;
        out:
        for (int i = 0; i < lGame.getFile().getWhichLevel().length; i++) {
            if (lGame.getFile().getWhichLevel()[i]) {
                level = i + 1;
                for (int j = 0; j < lGame.getFile().getLevelObject()[i].getWhichSection().length; j++) {
                    if (lGame.getFile().getLevelObject()[i].getWhichSection()[j]) {
                        section = j + 1;
                        break out;
                    }
                }
            }
        }
        g.drawString("World " + level + "-" + section, 1450, 50);
    }

    public void gate(Graphics g) {
        g.fillRect(1750, 600, 50, 200);
    }

    @Override
    public void paint(Graphics g) {
        //for paint game
        lGame.getFile().paint(g, lGame, show, user, timer, this);
        //for paint mario
        lGame.getSelectedCharacter().paint(g);
        //for paint information
        paintInformation(g);
        //for paint gate
        gate(g);
        //for game over
        gameOver();
        // for finish game
        finishGame();
    }

    public void gameOver() {
        if (lGame.isGameOver()) {
            lGame.setGameOver(false);
            JOptionPane.showMessageDialog(frame, "You are loser", "Game Over", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void finishGame() {
        if (lGame.isFinishGame()) {
            user.getScores().add(lGame.getScore());
            user.setCoin(lGame.getCoin());
            if (show == 1) {
                GUI.getInstance().getTimers().get("timer 1").stop();
                frame.setVisible(false);
                user.setNewButton1(false);
                user.setContinueButton1(false);
                GUI.getInstance().getButtons().get("new button 1").setText("Null");
                GUI.getInstance().getButtons().get("continue button 1").setText("Null");
                user.setGame1(null);
            } else if (show == 2) {
                GUI.getInstance().getTimers().get("timer 2").stop();
                frame.setVisible(false);
                user.setNewButton2(false);
                user.setContinueButton2(false);
                GUI.getInstance().getButtons().get("new button 2").setText("Null");
                GUI.getInstance().getButtons().get("continue button 2").setText("Null");
                user.setGame2(null);
            } else {
                GUI.getInstance().getTimers().get("timer 3").stop();
                frame.setVisible(false);
                user.setNewButton3(false);
                user.setContinueButton3(false);
                GUI.getInstance().getButtons().get("new button 3").setText("Null");
                GUI.getInstance().getButtons().get("continue button 3").setText("Null");
                user.setGame3(null);
            }
            GUI.getInstance().getFrames().get("main panel").setVisible(true);
        }
    }
}