package main;

import characters.*;
import game.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI {
    // Hello TA , this class for graphics of menu
    private final HashMap<String, JFrame> frames = new HashMap<>();
    private final HashMap<String, JButton> buttons = new HashMap<>();
    private final HashMap<String, JTextField> textFields = new HashMap<>();
    private final HashMap<String, JLabel> labels = new HashMap<>();
    private final HashMap<String, JRadioButton> radioButtons = new HashMap<>();
    private final HashMap<String, ButtonGroup> buttonGroups = new HashMap<>();
    private final HashMap<String, JTable> tables = new HashMap<>();
    private final HashMap<String, ActionListener> actionListeners = new HashMap<>();
    private final HashMap<String, Timer> timers = new HashMap<>();
    private boolean signUpFrame = false;
    private boolean logInFrame = false;
    private boolean mainFrame = false;
    private boolean newGameFrame = false;
    private boolean continueGameFrame = false;
    private boolean highestResultsFrame = false;
    private boolean storeFrame = false;
    private boolean profileFrame = false;
    private boolean difficultyFrame = false;
    //--------------for singleton---------------//
    private final static GUI instance = new GUI();

    private GUI() {
        initializeFrames();
        initializeButtons();
        initializeTextFields();
        initializeLabels();
        initializeRadios();
        initializeGroups();
        initializeTables();
        initializeActionsAndTimers();
    }

    public static GUI getInstance() {
        return instance;
    }

    //----------methods-------------//
    private void initializeFrames() {
        JFrame frameEnter = new JFrame("Enter");
        JFrame frameSignUp = new JFrame("Sign Up");
        JFrame frameLogIn = new JFrame("Log In");
        JFrame frameMain = new JFrame("Main Panel");
        JFrame frameNewGame = new JFrame("New Game");
        JFrame frameContinueGame = new JFrame("Continue Game");
        JFrame frameHighestResults = new JFrame("Highest Results");
        JFrame frameStore = new JFrame("Store");
        JFrame frameProfile = new JFrame("Profile");
        JFrame frameDifficulty = new JFrame("Select Difficulty and Character");
        JFrame frameNewGame1 = new JFrame("NewGame1");
        JFrame frameNewGame2 = new JFrame("NewGame2");
        JFrame frameNewGame3 = new JFrame("New Game3");
        JFrame frameContinueGame1 = new JFrame("ContinueGame1");
        JFrame frameContinueGame2 = new JFrame("ContinueGame2");
        JFrame frameContinueGame3 = new JFrame("ContinueGame3");
        frames.put("enter", frameEnter);
        frames.put("sign up", frameSignUp);
        frames.put("log in", frameLogIn);
        frames.put("main panel", frameMain);
        frames.put("new game", frameNewGame);
        frames.put("continue game", frameContinueGame);
        frames.put("highest results", frameHighestResults);
        frames.put("store", frameStore);
        frames.put("profile", frameProfile);
        frames.put("select", frameDifficulty);
        frames.put("new game 1", frameNewGame1);
        frames.put("new game 2", frameNewGame2);
        frames.put("new game 3", frameNewGame3);
        frames.put("continue game 1", frameContinueGame1);
        frames.put("continue game 2", frameContinueGame2);
        frames.put("continue game 3", frameContinueGame3);
    }

    private void initializeButtons() {
        JButton backToMenu = new JButton("Back to menu");
        JButton backToEnter = new JButton("Back and enter with new account");
        JButton signUp = new JButton("Sign Up");
        JButton logIn = new JButton("Log In");
        JButton exit = new JButton("Exit");
        JButton signUpNext = new JButton("Next");
        JButton logInNext = new JButton("Next");
        JButton newGame = new JButton("New Game");
        JButton continueGames = new JButton("Continue Games");
        JButton highestResults = new JButton("Highest Results");
        JButton coins = new JButton("My Coins");
        JButton store = new JButton("Store");
        JButton profile = new JButton("My Profile");
        JButton buyBoo = new JButton("Buy Boo");
        JButton buyWario = new JButton("Buy Wario");
        JButton buyToad = new JButton("Buy Toad");
        JButton buyYoshi = new JButton("Buy Yoshi");
        JButton selectBoo = new JButton("Select Boo");
        JButton selectWario = new JButton("Select Wario");
        JButton selectToad = new JButton("Select Toad");
        JButton selectYoshi = new JButton("Select Yoshi");
        JButton selectMario = new JButton("Select Mario");
        JButton startGame = new JButton("Start Game");
        JButton deleteSelectedGames = new JButton("Delete Selected Games");
        JButton newButton1 = new JButton("Null");
        JButton newButton2 = new JButton("Null");
        JButton newButton3 = new JButton("Null");
        JButton continueButton1 = new JButton("Null");
        JButton continueButton2 = new JButton("Null");
        JButton continueButton3 = new JButton("Null");
        JButton loadStage = new JButton("Load your favorite stage");
        buttons.put("load stage", loadStage);
        buttons.put("back to menu", backToMenu);
        buttons.put("back to enter", backToEnter);
        buttons.put("sign up", signUp);
        buttons.put("log in", logIn);
        buttons.put("exit", exit);
        buttons.put("sign up next", signUpNext);
        buttons.put("log in next", logInNext);
        buttons.put("new game", newGame);
        buttons.put("continue game", continueGames);
        buttons.put("highest results", highestResults);
        buttons.put("coins", coins);
        buttons.put("store", store);
        buttons.put("profile", profile);
        buttons.put("buy boo", buyBoo);
        buttons.put("buy wario", buyWario);
        buttons.put("buy toad", buyToad);
        buttons.put("buy yoshi", buyYoshi);
        buttons.put("select boo", selectBoo);
        buttons.put("select wario", selectWario);
        buttons.put("select toad", selectToad);
        buttons.put("select yoshi", selectYoshi);
        buttons.put("select mario", selectMario);
        buttons.put("start game", startGame);
        buttons.put("delete games", deleteSelectedGames);
        buttons.put("new button 1", newButton1);
        buttons.put("new button 2", newButton2);
        buttons.put("new button 3", newButton3);
        buttons.put("continue button 1", continueButton1);
        buttons.put("continue button 2", continueButton2);
        buttons.put("continue button 3", continueButton3);
    }

    private void initializeTextFields() {
        JTextField signUpUserName = new JTextField("Enter your username");
        JTextField signUpPassWord = new JTextField("Enter your password");
        JTextField logInUserName = new JTextField("Enter your username");
        JTextField logInPassWord = new JTextField("Enter your password");
        textFields.put("sign up username", signUpUserName);
        textFields.put("sign up password", signUpPassWord);
        textFields.put("log in username", logInUserName);
        textFields.put("log in password", logInPassWord);
    }

    private void initializeLabels() {
        JLabel labelOfBoo = new JLabel(Boo.getImageIcon());
        JLabel labelOfWario = new JLabel(Wario.getImageIcon());
        JLabel labelOfToad = new JLabel(Toad.getImageIcon());
        JLabel labelOfYoshi = new JLabel(Yoshi.getImageIcon());
        JLabel labelOfMario = new JLabel(Mario.getImageIcon());
        JLabel coinOfBoo = new JLabel("For buy Boo (move faster) , you need to 20 coins");
        JLabel coinOfWario = new JLabel("For buy Wario (higher jump) , you need to 30 coins");
        JLabel coinOfToad = new JLabel("For buy Toad (attract coins) , you need to 20 coins");
        JLabel coinOfYoshi = new JLabel("For buy Yoshi (Throw arrows faster) , you need to 10 coins");
        JLabel selectedCharacter = new JLabel();
        JLabel labelOfSelectedCharacter = new JLabel();
        JLabel nameOfUser = new JLabel();
        JLabel highestScore = new JLabel();
        JLabel selectDifficulty = new JLabel("Select Difficulty :");
        JLabel deleteGame = new JLabel("Select For Delete Game :");
        labels.put("image boo", labelOfBoo);
        labels.put("image wario", labelOfWario);
        labels.put("image toad", labelOfToad);
        labels.put("image yoshi", labelOfYoshi);
        labels.put("image mario", labelOfMario);
        labels.put("coin boo", coinOfBoo);
        labels.put("coin wario", coinOfWario);
        labels.put("coin toad", coinOfToad);
        labels.put("coin yoshi", coinOfYoshi);
        labels.put("selected", selectedCharacter);
        labels.put("image selected", labelOfSelectedCharacter);
        labels.put("name user", nameOfUser);
        labels.put("highest score", highestScore);
        labels.put("difficulty", selectDifficulty);
        labels.put("delete", deleteGame);
    }

    private void initializeRadios() {
        JRadioButton hard = new JRadioButton("Hard");
        JRadioButton medium = new JRadioButton("Medium");
        JRadioButton easy = new JRadioButton("Easy");
        JRadioButton deleteGame1 = new JRadioButton("Delete Game 1");
        JRadioButton deleteGame2 = new JRadioButton("Delete Game 2");
        JRadioButton deleteGame3 = new JRadioButton("Delete Game 3");
        radioButtons.put("hard", hard);
        radioButtons.put("medium", medium);
        radioButtons.put("easy", easy);
        radioButtons.put("delete 1", deleteGame1);
        radioButtons.put("delete 2", deleteGame2);
        radioButtons.put("delete 3", deleteGame3);
    }

    private void initializeGroups() {
        ButtonGroup difficulty = new ButtonGroup();
        buttonGroups.put("difficulty", difficulty);
    }

    private void initializeTables() {
        JTable tableOfHighestResults = new JTable();
        tables.put("highest results", tableOfHighestResults);
    }

    private void initializeActionsAndTimers() {
        ActionListener actionOfGame1 = e -> {

        };
        ActionListener actionOfGame2 = e -> {

        };
        ActionListener actionOfGame3 = e -> {

        };
        Timer timer1 = new Timer(5, actionOfGame1);
        Timer timer2 = new Timer(5, actionOfGame2);
        Timer timer3 = new Timer(5, actionOfGame3);
        actionListeners.put("game 1", actionOfGame1);
        actionListeners.put("game 2", actionOfGame2);
        actionListeners.put("game 3", actionOfGame3);
        timers.put("timer 1", timer1);
        timers.put("timer 2", timer2);
        timers.put("timer 3", timer3);
    }

    //---------getters--------------//

    public HashMap<String, JFrame> getFrames() {
        return frames;
    }

    public HashMap<String, JButton> getButtons() {
        return buttons;
    }

    public HashMap<String, JTextField> getTextFields() {
        return textFields;
    }

    public HashMap<String, JLabel> getLabels() {
        return labels;
    }

    public HashMap<String, JRadioButton> getRadioButtons() {
        return radioButtons;
    }

    public HashMap<String, ButtonGroup> getButtonGroups() {
        return buttonGroups;
    }

    public HashMap<String, JTable> getTables() {
        return tables;
    }

    public HashMap<String, ActionListener> getActionListeners() {
        return actionListeners;
    }

    public HashMap<String, Timer> getTimers() {
        return timers;
    }


    public boolean isSignUpFrame() {
        return signUpFrame;
    }

    public boolean isLogInFrame() {
        return logInFrame;
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

    public boolean isNewGameFrame() {
        return newGameFrame;
    }

    public boolean isContinueGameFrame() {
        return continueGameFrame;
    }

    public boolean isHighestResultsFrame() {
        return highestResultsFrame;
    }

    public boolean isStoreFrame() {
        return storeFrame;
    }

    public boolean isProfileFrame() {
        return profileFrame;
    }

    public boolean isDifficultyFrame() {
        return difficultyFrame;
    }
    //-----------------setters----------------------/

    public void setSignUpFrame(boolean signUpFrame) {
        this.signUpFrame = signUpFrame;
    }

    public void setLogInFrame(boolean logInFrame) {
        this.logInFrame = logInFrame;
    }

    public void setMainFrame(boolean mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setNewGameFrame(boolean newGameFrame) {
        this.newGameFrame = newGameFrame;
    }

    public void setContinueGameFrame(boolean continueGameFrame) {
        this.continueGameFrame = continueGameFrame;
    }

    public void setHighestResultsFrame(boolean highestResultsFrame) {
        this.highestResultsFrame = highestResultsFrame;
    }

    public void setStoreFrame(boolean storeFrame) {
        this.storeFrame = storeFrame;
    }

    public void setProfileFrame(boolean profileFrame) {
        this.profileFrame = profileFrame;
    }

    public void setDifficultyFrame(boolean difficultyFrame) {
        this.difficultyFrame = difficultyFrame;
    }
}
