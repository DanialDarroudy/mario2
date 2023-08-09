package main;


import characters.*;
import characters.Character;
import characters.Mario;
import database.*;
import game.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Menu {
    //this class for menu of game
    private final GUI singleton;

    public GUI getSingleton() {
        return singleton;
    }

    //--------------for singleton---------------//
    private final static Menu instance = new Menu();

    private Menu() {
        singleton = GUI.getInstance();
    }

    public static Menu getInstance() {
        return instance;
    }

    //----------------methods--------------------//

    public void enter() {
        JFrame frame = singleton.getFrames().get("enter");
        initializeFrames(frame);
        initializeButtons(singleton.getButtons().get("sign up"), frame, 750, 250, 200, 100, e -> {
            frame.setVisible(false);
            if (singleton.isSignUpFrame()) {
                singleton.getFrames().get("sign up").setVisible(true);
            } else {
                signUp();
            }
        });
        initializeButtons(singleton.getButtons().get("log in"), frame, 750, 400, 200, 100, e -> {
            frame.setVisible(false);
            if (singleton.isLogInFrame()) {
                singleton.getFrames().get("log in").setVisible(true);
            } else {
                logIn();
            }

        });
        initializeButtons(singleton.getButtons().get("exit"), frame, 750, 550, 200, 100, e -> {
            Files.saveUser(Files.arrayUsers);
            System.exit(0);
        });
    }

    public void signUp() {
        JFrame frame = singleton.getFrames().get("sign up");
        initializeFrames(frame);
        initializeTextFields(singleton.getTextFields().get("sign up username"), frame, 650, 250, 400, 50);
        initializeTextFields(singleton.getTextFields().get("sign up password"), frame, 650, 350, 400, 50);
        initializeButtons(singleton.getButtons().get("sign up next"), frame, 750, 500, 200, 100, e -> initializeUser());
        initializeButtons(singleton.getButtons().get("back to enter"), frame, 650, 700, 400, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("enter").setVisible(true);
        });
        singleton.setSignUpFrame(true);
    }

    public void logIn() {
        JFrame frame = singleton.getFrames().get("log in");
        initializeFrames(frame);
        initializeTextFields(singleton.getTextFields().get("log in username"), frame, 650, 250, 400, 50);
        initializeTextFields(singleton.getTextFields().get("log in password"), frame, 650, 350, 400, 50);
        initializeButtons(singleton.getButtons().get("log in next"), frame, 750, 500, 200, 100, e -> checkForExistUser());
        initializeButtons(singleton.getButtons().get("back to enter"), frame, 650, 700, 400, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("enter").setVisible(true);
        });
        singleton.setLogInFrame(true);
    }

    public void mainPanel(User user) {
        JFrame frame = singleton.getFrames().get("main panel");
        initializeFrames(frame);
        initializeButtons(singleton.getButtons().get("new game"), frame, 200, 200, 400, 100, e -> {
            frame.setVisible(false);
            if (singleton.isNewGameFrame()) {
                singleton.getFrames().get("new game").setVisible(true);
            } else {
                enterWithNewGame(user);
            }
        });
        initializeButtons(singleton.getButtons().get("continue game"), frame, 700, 200, 400, 100, e -> {
            frame.setVisible(false);
            if (singleton.isContinueGameFrame()) {
                singleton.getFrames().get("continue game").setVisible(true);
            } else {
                enterWithContinueGames(user);
            }
        });
        initializeButtons(singleton.getButtons().get("highest results"), frame, 1200, 200, 400, 100, e -> {
            frame.setVisible(false);
            if (singleton.isHighestResultsFrame()) {
                singleton.getFrames().get("highest results").setVisible(true);
            } else {
                highestResults();
            }
        });
        initializeButtons(singleton.getButtons().get("coins"), frame, 200, 400, 400, 100, e -> showCoins(user));
        initializeButtons(singleton.getButtons().get("store"), frame, 700, 400, 400, 100, e -> {
            frame.setVisible(false);
            if (singleton.isStoreFrame()) {
                singleton.getFrames().get("store").setVisible(true);
            } else {
                store(user);
            }
        });
        initializeButtons(singleton.getButtons().get("profile"), frame, 1200, 400, 400, 100, e -> {
            frame.setVisible(false);
            if (singleton.isProfileFrame()) {
                singleton.getFrames().get("profile").setVisible(true);
            } else {
                showProfile(user);
            }
        });
        initializeButtons(singleton.getButtons().get("load stage"), frame, 700, 600, 400, 100, e -> loadStage());
        initializeButtons(singleton.getButtons().get("back to enter"), frame, 650, 800, 500, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("enter").setVisible(true);
        });
        singleton.setMainFrame(true);
    }

    public void enterWithNewGame(User user) {
        JFrame frame = singleton.getFrames().get("new game");
        initializeFrames(frame);
        if (user.isNewButton1()) {
            singleton.getButtons().get("new button 1").setText("Game1");
        }
        if (user.isNewButton2()) {
            singleton.getButtons().get("new button 2").setText("Game2");
        }
        if (user.isNewButton3()) {
            singleton.getButtons().get("new button 3").setText("Game3");
        }
        initializeButtons(singleton.getButtons().get("new button 1"), frame, 450, 250, 200, 100, e -> {
            initNewGame1(user);
        });
        initializeButtons(singleton.getButtons().get("new button 2"), frame, 450, 400, 200, 100, e -> {
            initNewGame2(user);
        });
        initializeButtons(singleton.getButtons().get("new button 3"), frame, 450, 550, 200, 100, e -> {
            initNewGame3(user);
        });
        initDeleteGame(user);
        initializeButtons(singleton.getButtons().get("back to menu"), frame, 450, 700, 200, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("main panel").setVisible(true);
        });
        singleton.setNewGameFrame(true);
    }

    public void enterWithContinueGames(User user) {
        JFrame frame = singleton.getFrames().get("continue game");
        initializeFrames(frame);
        if (user.isContinueButton1()) {
            singleton.getButtons().get("continue button 1").setText("Game1");
        }
        if (user.isContinueButton2()) {
            singleton.getButtons().get("continue button 2").setText("Game2");
        }
        if (user.isContinueButton3()) {
            singleton.getButtons().get("continue button 3").setText("Game3");
        }
        initializeButtons(singleton.getButtons().get("continue button 1"), frame, 450, 250, 200, 100, e -> {
            initContinueGame1(user);
        });
        initializeButtons(singleton.getButtons().get("continue button 2"), frame, 450, 400, 200, 100, e -> {
            initContinueGame2(user);
        });
        initializeButtons(singleton.getButtons().get("continue button 3"), frame, 450, 550, 200, 100, e -> {
            initContinueGame3(user);
        });
        initializeButtons(singleton.getButtons().get("back to menu"), frame, 450, 700, 200, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("main panel").setVisible(true);
        });
        singleton.setContinueGameFrame(true);
    }

    public void highestResults() {
        JFrame frame = singleton.getFrames().get("highest results");
        initializeFrames(frame);
        frame.add(initHighestResults());
        initializeButtons(singleton.getButtons().get("back to menu"), frame, 50, 50, 200, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("main panel").setVisible(true);
        });
        singleton.setHighestResultsFrame(true);
    }

    public void showCoins(User user) {
        JOptionPane.showMessageDialog(singleton.getFrames().get("main panel"),
                "Your number of coins : " + user.getCoin(), "Coin", JOptionPane.WARNING_MESSAGE);
    }

    public void loadStage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src/main/resources"));
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        int init = fileChooser.showDialog(null, "Load");
        if (init == JFileChooser.APPROVE_OPTION) {
            Config.getInstance().setStage(Config.getInstance().getStage() + 1);
        }
        Config.getInstance().setFileChooser(fileChooser);
        Config.getInstance().setStage();
    }

    public void store(User user) {
        JFrame frame = singleton.getFrames().get("store");
        initializeFrames(frame);
        initStore(user);
        initializeButtons(singleton.getButtons().get("back to menu"), frame, 50, 50, 200, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("main panel").setVisible(true);
        });
        singleton.setStoreFrame(true);
    }

    public void showProfile(User user) {
        JFrame frame = singleton.getFrames().get("profile");
        initializeFrames(frame);
        informationOfUser(user);
        chooseCharacter(user, frame);
        initializeButtons(singleton.getButtons().get("back to menu"), frame, 50, 850, 200, 100, e -> {
            frame.setVisible(false);
            singleton.getFrames().get("main panel").setVisible(true);
        });
        singleton.setProfileFrame(true);
    }

    public void selectDifficulty(User user, int show) {
        JFrame frame = singleton.getFrames().get("select");
        initializeFrames(frame);
        initSelectDifficulty();
        chooseCharacter(user, frame);
        initializeButtons(singleton.getButtons().get("start game"), frame, 50, 500, 100, 50, e -> {
            frame.setVisible(false);
            newGame(user, show);
        });
        singleton.setDifficultyFrame(true);
    }

    public void newGame(User user, int show) {
        nullAllFrames();
        if (show == 1) {
            JFrame frame = new JFrame("NewGame1");
            singleton.getFrames().put("new game 1", frame);
            initializeFrames(frame);
            LGame lGame = new LGame(initGame(user));
            GUIGame guiGame = new GUIGame(lGame, frame, singleton.getTimers().get("timer 1"),
                    singleton.getActionListeners().get("game 1"), user, show);
            initializePanel(guiGame, frame);
            helpGame1(user, lGame, guiGame);
        } else if (show == 2) {
            JFrame frame = new JFrame("NewGame2");
            singleton.getFrames().put("new game 2", frame);
            initializeFrames(frame);
            LGame lGame = new LGame(initGame(user));
            GUIGame guiGame = new GUIGame(lGame, frame, singleton.getTimers().get("timer 2"),
                    singleton.getActionListeners().get("game 2"), user, show);
            initializePanel(guiGame, frame);
            helpGame2(user, lGame, guiGame);
        } else if (show == 3) {
            JFrame frame = new JFrame("NewGame3");
            singleton.getFrames().put("new game 3", frame);
            initializeFrames(frame);
            LGame lGame = new LGame(initGame(user));
            GUIGame guiGame = new GUIGame(lGame, frame, singleton.getTimers().get("timer 3"),
                    singleton.getActionListeners().get("game 3"), user, show);
            initializePanel(guiGame, frame);
            helpGame3(user, lGame, guiGame);
        }
    }

    public void continueGame(User user, int show) {
        nullAllFrames();
        if (show == 1) {
            JFrame frame = new JFrame("ContinueGame1");
            singleton.getFrames().replace("continue game 1", frame);
            initializeFrames(frame);
            GUIGame guiGame = new GUIGame(user.getGame1(), frame, singleton.getTimers().get("timer 1"),
                    singleton.getActionListeners().get("game 1"), user, show);
            initializePanel(guiGame, frame);
            helpGame1(user, user.getGame1(), guiGame);
        } else if (show == 2) {
            JFrame frame = new JFrame("ContinueGame2");
            singleton.getFrames().replace("continue game 2", frame);
            initializeFrames(frame);
            GUIGame guiGame = new GUIGame(user.getGame2(), frame, singleton.getTimers().get("timer 2"),
                    singleton.getActionListeners().get("game 2"), user, show);
            initializePanel(guiGame, frame);
            helpGame2(user, user.getGame2(), guiGame);
        } else if (show == 3) {
            JFrame frame = new JFrame("ContinueGame3");
            singleton.getFrames().replace("continue game 3", frame);
            initializeFrames(frame);
            GUIGame guiGame = new GUIGame(user.getGame3(), frame, singleton.getTimers().get("timer 3"),
                    singleton.getActionListeners().get("game 3"), user, show);
            initializePanel(guiGame, frame);
            helpGame3(user, user.getGame3(), guiGame);
        }
    }

    //----------------------------------------------------initialization----------------------------------------------//
    public void initializeFrames(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Icon.png").getImage());
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(1800, 1000);
    }

    public void initializePanel(JPanel panel, JFrame frame) {
        panel.setBounds(0, 0, 1800, 1000);
        panel.setVisible(true);
        frame.add(panel);
    }

    public void initializeRadioButtons(JRadioButton radioButton, JFrame frame
            , int x, int y, int width, int height) {
        frame.add(radioButton);
        radioButton.setBounds(x, y, width, height);

    }

    public void initializeButtonGroup(ButtonGroup buttonGroup, JRadioButton... radioButtons) {
        for (JRadioButton radioButton : radioButtons) {
            buttonGroup.add(radioButton);
        }
    }

    public void initializeButtons(JButton button, JFrame frame
            , int x, int y, int width, int height
            , ActionListener actionListener) {
        frame.add(button);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
    }

    public void initializeTextFields(JTextField textField, JFrame frame
            , int x, int y, int width, int height) {
        frame.add(textField);
        textField.setBounds(x, y, width, height);
    }

    public void initializeLabel(JLabel label, JFrame frame, int x, int y, int width, int height) {
        frame.add(label);
        label.setBounds(x, y, width, height);
    }

    public void initializeUser() {
        User user = new User(singleton.getTextFields().get("sign up username").getText(),
                singleton.getTextFields().get("sign up password").getText());
        singleton.getFrames().get("sign up").setVisible(false);
        mainPanel(user);
    }

    //----------------------------------------------------other methods---------------------------------------------------//
    public void checkForExistUser() {
        if (Files.users.containsKey(singleton.getTextFields().get("log in username").getText())) {
            User user = Files.users.get(singleton.getTextFields().get("log in username").getText());
            if (user.getPassWord().equals(singleton.getTextFields().get("log in password").getText())) {
                singleton.getFrames().get("log in").setVisible(false);
                mainPanel(user);
            } else {
                JOptionPane.showMessageDialog(singleton.getFrames().get("log in"), "Your username is found but password is wrong!"
                        , "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("log in"), "Your username not found!"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void nullAllFrames() {
        singleton.getFrames().put("new game 1", null);
        singleton.getFrames().put("new game 2", null);
        singleton.getFrames().put("new game 3", null);
        singleton.getFrames().put("continue game 1", null);
        singleton.getFrames().put("continue game 2", null);
        singleton.getFrames().put("continue game 3", null);
    }

    public void initNewGame1(User user) {
        singleton.getButtons().get("new button 1").setText("Game1");
        user.setNewButton1(true);
        singleton.getFrames().get("new game").setVisible(false);
        if (singleton.isDifficultyFrame()) {
            singleton.getFrames().get("select").setVisible(true);
        } else {
            selectDifficulty(user, 1);
        }
    }

    public void initNewGame2(User user) {
        singleton.getButtons().get("new button 2").setText("Game2");
        user.setNewButton2(true);
        singleton.getFrames().get("new game").setVisible(false);
        if (singleton.isDifficultyFrame()) {
            singleton.getFrames().get("select").setVisible(true);
        } else {
            selectDifficulty(user, 2);
        }
    }

    public void initNewGame3(User user) {
        singleton.getButtons().get("new button 3").setText("Game3");
        user.setNewButton3(true);
        singleton.getFrames().get("new game").setVisible(false);
        if (singleton.isDifficultyFrame()) {
            singleton.getFrames().get("select").setVisible(true);
        } else {
            selectDifficulty(user, 3);
        }
    }

    public void initContinueGame1(User user) {
        if (singleton.getButtons().get("continue button 1").getText().equals("Game1")) {
            singleton.getFrames().get("continue game").setVisible(false);
            continueGame(user, 1);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("continue game 1"), "this button is empty"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void initContinueGame2(User user) {
        if (singleton.getButtons().get("continue game 2").getText().equals("Game2")) {
            singleton.getFrames().get("continue game").setVisible(false);
            continueGame(user, 2);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("continue game 2"), "this button is empty"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void initContinueGame3(User user) {
        if (singleton.getButtons().get("continue game 3").getText().equals("Game3")) {
            singleton.getFrames().get("continue game").setVisible(false);
            continueGame(user, 3);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("continue game 3"), "this button is empty"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public JScrollPane initHighestResults() {
        String[] column = {"Name of user", "Highest Score"};
        String[][] row = new String[Files.arrayUsers.size()][2];
        for (int i = 0; i < Files.arrayUsers.size(); i++) {
            row[i][0] = Files.arrayUsers.get(i).getUserName();
            int highestScore = getHighestScore(Files.arrayUsers.get(i));
            row[i][1] = String.valueOf(highestScore);
        }
        JTable table = new JTable(row, column);
        singleton.getTables().put("highest results", table);
        table.setBounds(600, 200, 800, 600);
        table.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        scrollPane.setBounds(600, 200, 800, 600);
        return scrollPane;
    }

    public void buyBoo(User user) {
        if (user.getCoin() >= 20) {
            user.getCharacters().add("Boo");
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You buy Boo", "Buy", JOptionPane.WARNING_MESSAGE);
            user.setCoin(user.getCoin() - 20);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You do not have required coins"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void buyWario(User user) {
        if (user.getCoin() >= 30) {
            user.getCharacters().add("Wario");
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You buy Wario", "Buy", JOptionPane.WARNING_MESSAGE);
            user.setCoin(user.getCoin() - 30);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You do not have required coins"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void buyToad(User user) {
        if (user.getCoin() >= 20) {
            user.getCharacters().add("Toad");
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You buy Toad", "Buy", JOptionPane.WARNING_MESSAGE);
            user.setCoin(user.getCoin() - 20);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You do not have required coins"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void buyYoshi(User user) {
        if (user.getCoin() >= 10) {
            user.getCharacters().add("Yoshi");
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You buy Yoshi", "Buy", JOptionPane.WARNING_MESSAGE);
            user.setCoin(user.getCoin() - 10);
        } else {
            JOptionPane.showMessageDialog(singleton.getFrames().get("store"), "You do not have required coins"
                    , "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void initStore(User user) {
        JFrame frame = singleton.getFrames().get("store");
        initializeLabel(singleton.getLabels().get("image boo"), frame, 150, 300, 230, 230);
        initializeLabel(singleton.getLabels().get("image wario"), frame, 500, 300, 400, 250);
        initializeLabel(singleton.getLabels().get("image toad"), frame, 1000, 300, 200, 300);
        initializeLabel(singleton.getLabels().get("image yoshi"), frame, 1300, 300, 240, 230);
        initializeLabel(singleton.getLabels().get("coin boo"), frame, 150, 200, 350, 50);
        initializeLabel(singleton.getLabels().get("coin wario"), frame, 550, 200, 350, 50);
        initializeLabel(singleton.getLabels().get("coin toad"), frame, 975, 200, 350, 50);
        initializeLabel(singleton.getLabels().get("coin yoshi"), frame, 1300, 200, 350, 50);
        initializeButtons(singleton.getButtons().get("buy boo"), frame, 160, 700, 200, 100, e -> buyBoo(user));
        initializeButtons(singleton.getButtons().get("buy wario"), frame, 600, 700, 200, 100, e -> buyWario(user));
        initializeButtons(singleton.getButtons().get("buy toad"), frame, 1000, 700, 200, 100, e -> buyToad(user));
        initializeButtons(singleton.getButtons().get("buy yoshi"), frame, 1310, 700, 200, 100, e -> buyYoshi(user));
    }

    public void informationOfUser(User user) {
        JFrame frame = singleton.getFrames().get("profile");
        initializeLabel(singleton.getLabels().get("selected"), frame, 50, 50, 300, 50);
        singleton.getLabels().get("selected").setText("Your current character is " + user.getSelectedCharacter());
        initializeLabel(singleton.getLabels().get("image selected"), frame, 50, 100, 400, 400);
        singleton.getLabels().get("image selected").setIcon(Character.pictureOfCharacters().get(user.getSelectedCharacter()));
        initializeLabel(singleton.getLabels().get("name user"), frame, 50, 500, 200, 50);
        singleton.getLabels().get("name user").setText("Your name is " + user.getUserName());
        initializeLabel(singleton.getLabels().get("highest score"), frame, 50, 700, 200, 50);
        singleton.getLabels().get("highest score").setText("Your highest score is : " + getHighestScore(user));
    }

    public int getHighestScore(User user) {
        int highestScore = 0;
        for (int i = 0; i < user.getScores().size(); i++) {
            if (user.getScores().get(i) > highestScore) {
                highestScore = user.getScores().get(i);
            }
        }
        return highestScore;
    }

    public void chooseCharacter(User user, JFrame frame) {
        if (user.getCharacters().contains("Boo")) {
            initializeLabel(singleton.getLabels().get("image boo"), frame, 600, 50, 230, 230);
            initializeButtons(singleton.getButtons().get("select boo"), frame, 620, 400, 200, 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Boo is selected");
                    user.setSelectedCharacter("Boo");
                }
            });
        }
        if (user.getCharacters().contains("Wario")) {
            initializeLabel(singleton.getLabels().get("image wario"), frame, 900, 50, 400, 250);
            initializeButtons(singleton.getButtons().get("select wario"), frame, 1000, 400, 200, 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Wario is selected");
                    user.setSelectedCharacter("Wario");
                }
            });
        }
        if (user.getCharacters().contains("Toad")) {
            initializeLabel(singleton.getLabels().get("image toad"), frame, 1380, 50, 200, 300);
            initializeButtons(singleton.getButtons().get("select toad"), frame, 1380, 400, 200, 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Toad is selected");
                    user.setSelectedCharacter("Toad");
                }
            });
        }
        if (user.getCharacters().contains("Yoshi")) {
            initializeLabel(singleton.getLabels().get("image yoshi"), frame, 800, 500, 240, 230);
            initializeButtons(singleton.getButtons().get("select yoshi"), frame, 820, 800, 200, 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Yoshi is selected");
                    user.setSelectedCharacter("Yoshi");
                }
            });
        }
        if (user.getCharacters().contains("Mario")) {
            initializeLabel(singleton.getLabels().get("image mario"), frame, 1300, 500, 200, 200);
            initializeButtons(singleton.getButtons().get("select mario"), frame, 1300, 800, 200, 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Mario is selected");
                    user.setSelectedCharacter("Mario");
                }
            });
        }
    }

    public void initSelectDifficulty() {
        JFrame frame = singleton.getFrames().get("select");
        initializeLabel(singleton.getLabels().get("difficulty"), frame, 50, 50, 100, 50);
        initializeRadioButtons(singleton.getRadioButtons().get("hard"), frame, 50, 150, 100, 50);
        initializeRadioButtons(singleton.getRadioButtons().get("medium"), frame, 50, 250, 100, 50);
        initializeRadioButtons(singleton.getRadioButtons().get("easy"), frame, 50, 350, 100, 50);
        initializeButtonGroup(singleton.getButtonGroups().get("difficulty"), singleton.getRadioButtons().get("hard"),
                singleton.getRadioButtons().get("medium"), singleton.getRadioButtons().get("easy"));
    }

    public Character initGame(User user) {
        switch (user.getSelectedCharacter()) {
            case "Mario":
                return new Mario();
            case "Boo":
                return new Boo();
            case "Wario":
                return new Wario();
            case "Toad":
                return new Toad();
            case "Yoshi":
                return new Yoshi();
            default:
                return null;
        }
    }

    public void helpGame1(User user, LGame lGame, GUIGame guiGame) {
        singleton.getActionListeners().replace("game 1", e -> {
            lGame.move(user);
            guiGame.repaint();
        });
        singleton.getTimers().get("timer 1").addActionListener(singleton.getActionListeners().get("game 1"));
        singleton.getTimers().get("timer 1").start();
    }

    public void helpGame2(User user, LGame lGame, GUIGame guiGame) {
        singleton.getActionListeners().replace("game 2", e -> {
            lGame.move(user);
            guiGame.repaint();
        });
        singleton.getTimers().get("timer 2").addActionListener(singleton.getActionListeners().get("game 2"));
        singleton.getTimers().get("timer 2").start();
    }

    public void helpGame3(User user, LGame lGame, GUIGame guiGame) {
        singleton.getActionListeners().replace("game 3", e -> {
            lGame.move(user);
            guiGame.repaint();
        });
        singleton.getTimers().get("timer 3").addActionListener(singleton.getActionListeners().get("game 3"));
        singleton.getTimers().get("timer 3").start();
    }

    public void initDeleteGame(User user) {
        JFrame frame = singleton.getFrames().get("new game");
        initializeLabel(singleton.getLabels().get("delete"), frame, 800, 150, 200, 100);
        initializeRadioButtons(singleton.getRadioButtons().get("delete 1"), frame, 800, 250, 200, 100);
        initializeRadioButtons(singleton.getRadioButtons().get("delete 2"), frame, 800, 400, 200, 100);
        initializeRadioButtons(singleton.getRadioButtons().get("delete 3"), frame, 800, 550, 200, 100);
        initializeButtons(singleton.getButtons().get("delete games"), frame, 800, 700, 200, 100, e -> {
            if (singleton.getRadioButtons().get("delete 1").isSelected() &&
                    singleton.getButtons().get("new button 1").getText().equals("Game1")) {
                user.setNewButton1(false);
                singleton.getButtons().get("new button 1").setText("Null");
                singleton.getButtons().get("continue button 1").setText("Null");
                user.setContinueButton1(false);
                user.setGame1(null);
            }
            if (singleton.getRadioButtons().get("delete 2").isSelected() &&
                    singleton.getButtons().get("new button 2").getText().equals("Game2")) {
                user.setNewButton2(false);
                singleton.getButtons().get("new button 2").setText("Null");
                singleton.getButtons().get("continue button 2").setText("Null");
                user.setContinueButton2(false);
                user.setGame2(null);
            }
            if (singleton.getRadioButtons().get("delete 3").isSelected() &&
                    singleton.getButtons().get("new button 3").getText().equals("Game3")) {
                user.setNewButton3(false);
                singleton.getButtons().get("new button 3").setText("Null");
                singleton.getButtons().get("continue button 3").setText("Null");
                user.setContinueButton3(false);
                user.setGame3(null);
            }
        });
    }
}
