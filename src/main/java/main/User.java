package main;

import database.Files;
import game.LGame;

import java.util.ArrayList;

public class User {
    // this class for users
    private String userName;
    private String passWord;
    private LGame game1;
    private LGame game2;
    private LGame game3;
    private boolean newButton1 = false;
    private boolean newButton2 = false;
    private boolean newButton3 = false;
    private boolean continueButton1 = false;
    private boolean continueButton2 = false;
    private boolean continueButton3 = false;
    private String selectedCharacter = "Mario";
    private int coin = 0;
    private final ArrayList<Integer> scores = new ArrayList<>();
    private final ArrayList<String> characters = new ArrayList<>();

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        Files.users.put(userName, this);
        Files.arrayUsers.add(this);
        this.characters.add("Mario");
    }
    //--------------------------------------------------------methods-------------------------------------------------//


    //--------------------------------------------------------getters-------------------------------------------------//
    public String getPassWord() {
        return passWord;
    }

    public int getCoin() {
        return coin;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public LGame getGame1() {
        return game1;
    }

    public LGame getGame2() {
        return game2;
    }

    public LGame getGame3() {
        return game3;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public boolean isNewButton1() {
        return newButton1;
    }

    public boolean isNewButton2() {
        return newButton2;
    }

    public boolean isNewButton3() {
        return newButton3;
    }

    public boolean isContinueButton1() {
        return continueButton1;
    }

    public boolean isContinueButton2() {
        return continueButton2;
    }

    public boolean isContinueButton3() {
        return continueButton3;
    }

    //--------------------------------------------------------setters-------------------------------------------------//
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setGame1(LGame game1) {
        this.game1 = game1;
    }

    public void setGame2(LGame game2) {
        this.game2 = game2;
    }

    public void setGame3(LGame game3) {
        this.game3 = game3;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public void setNewButton1(boolean newButton1) {
        this.newButton1 = newButton1;
    }

    public void setNewButton2(boolean newButton2) {
        this.newButton2 = newButton2;
    }

    public void setNewButton3(boolean newButton3) {
        this.newButton3 = newButton3;
    }

    public void setContinueButton1(boolean continueButton1) {
        this.continueButton1 = continueButton1;
    }

    public void setContinueButton2(boolean continueButton2) {
        this.continueButton2 = continueButton2;
    }

    public void setContinueButton3(boolean continueButton3) {
        this.continueButton3 = continueButton3;
    }
}
