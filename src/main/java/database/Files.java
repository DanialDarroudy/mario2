package database;

import characters.Character;
import characters.Mario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Files {
    public final static HashMap<String, User> users = new HashMap<>();
    public final static List<User> arrayUsers = new ArrayList<>();

    public static void saveUser(List<User> arrayOfUsers) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/Users.json");
            writer.write(gson.toJson(arrayOfUsers));
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public static void loadUsers() {
        try {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            Reader r = java.nio.file.Files.newBufferedReader(Paths.get("src/main/resources/Users.json"));
            List<User> arrayOfUsers = g.fromJson(r, new TypeToken<List<User>>() {
            }.getType());
            r.close();
            arrayUsers.addAll(arrayOfUsers);
            for (User arrayUser : arrayUsers) {
                users.put(arrayUser.getUserName(), arrayUser);
            }
            initializeCharacter();
        } catch (IOException ignored) {
        }
    }
    public static void initializeCharacter(){
        Character helpCharacter;
        for (User arrayUser : arrayUsers) {
            if (arrayUser.getGame1() != null) {
                if (arrayUser.getGame1().getSelectedCharacter().getType().equals("Mario")) {
                    helpCharacter = new Mario();
                    helpCharacter.setX(arrayUser.getGame1().getSelectedCharacter().getX());
                    helpCharacter.setY(arrayUser.getGame1().getSelectedCharacter().getY());
                    helpCharacter.setVx(arrayUser.getGame1().getSelectedCharacter().getVx());
                    helpCharacter.setVy(arrayUser.getGame1().getSelectedCharacter().getVy());
                    helpCharacter.setHeart(arrayUser.getGame1().getSelectedCharacter().getHeart());
                    helpCharacter.setDecreaseHeart(arrayUser.getGame1().getSelectedCharacter().isDecreaseHeart());
                    helpCharacter.setType(arrayUser.getGame1().getSelectedCharacter().getType());
                    helpCharacter.setShield(arrayUser.getGame1().getSelectedCharacter().getShield());
                    helpCharacter.setJump(arrayUser.getGame1().getSelectedCharacter().getJump());
                    helpCharacter.setUseSword(arrayUser.getGame1().getSelectedCharacter().isUseSword());
                    helpCharacter.setMainSword(arrayUser.getGame1().getSelectedCharacter().getMainSword());
                    helpCharacter.setSword(arrayUser.getGame1().getSelectedCharacter().getSword());
                    helpCharacter.setSwordColdDown(arrayUser.getGame1().getSelectedCharacter().getSwordColdDown());
                    helpCharacter.setSit(arrayUser.getGame1().getSelectedCharacter().isSit());
                    helpCharacter.setSlime(arrayUser.getGame1().getSelectedCharacter().isSlime());
                    helpCharacter.setShot(arrayUser.getGame1().getSelectedCharacter().getShot());
                    helpCharacter.setState(arrayUser.getGame1().getSelectedCharacter().getState());
                    helpCharacter.setWidth(arrayUser.getGame1().getSelectedCharacter().getWidth());
                    helpCharacter.setHeight(arrayUser.getGame1().getSelectedCharacter().getHeight());
                    helpCharacter.setAntiKnock(arrayUser.getGame1().getSelectedCharacter().isAntiKnock());
                    helpCharacter.setShotColdDown(arrayUser.getGame1().getSelectedCharacter().getShotColdDown());
                    arrayUser.getGame1().setSelectedCharacter(helpCharacter);
                }
            }
            if (arrayUser.getGame2() != null) {
                if (arrayUser.getGame2().getSelectedCharacter().getType().equals("Mario")) {
                    helpCharacter = new Mario();
                    helpCharacter.setX(arrayUser.getGame2().getSelectedCharacter().getX());
                    helpCharacter.setY(arrayUser.getGame2().getSelectedCharacter().getY());
                    helpCharacter.setVx(arrayUser.getGame2().getSelectedCharacter().getVx());
                    helpCharacter.setVy(arrayUser.getGame2().getSelectedCharacter().getVy());
                    helpCharacter.setHeart(arrayUser.getGame2().getSelectedCharacter().getHeart());
                    helpCharacter.setDecreaseHeart(arrayUser.getGame2().getSelectedCharacter().isDecreaseHeart());
                    helpCharacter.setType(arrayUser.getGame2().getSelectedCharacter().getType());
                    helpCharacter.setShield(arrayUser.getGame2().getSelectedCharacter().getShield());
                    helpCharacter.setJump(arrayUser.getGame2().getSelectedCharacter().getJump());
                    helpCharacter.setUseSword(arrayUser.getGame2().getSelectedCharacter().isUseSword());
                    helpCharacter.setMainSword(arrayUser.getGame2().getSelectedCharacter().getMainSword());
                    helpCharacter.setSword(arrayUser.getGame2().getSelectedCharacter().getSword());
                    helpCharacter.setSwordColdDown(arrayUser.getGame2().getSelectedCharacter().getSwordColdDown());
                    helpCharacter.setSit(arrayUser.getGame2().getSelectedCharacter().isSit());
                    helpCharacter.setSlime(arrayUser.getGame2().getSelectedCharacter().isSlime());
                    helpCharacter.setShot(arrayUser.getGame2().getSelectedCharacter().getShot());
                    helpCharacter.setState(arrayUser.getGame2().getSelectedCharacter().getState());
                    helpCharacter.setWidth(arrayUser.getGame2().getSelectedCharacter().getWidth());
                    helpCharacter.setHeight(arrayUser.getGame2().getSelectedCharacter().getHeight());
                    helpCharacter.setAntiKnock(arrayUser.getGame2().getSelectedCharacter().isAntiKnock());
                    helpCharacter.setShotColdDown(arrayUser.getGame2().getSelectedCharacter().getShotColdDown());
                    arrayUser.getGame2().setSelectedCharacter(helpCharacter);
                }
            }
            if (arrayUser.getGame3() != null) {
                if (arrayUser.getGame3().getSelectedCharacter().getType().equals("Mario")) {
                    helpCharacter = new Mario();
                    helpCharacter.setX(arrayUser.getGame3().getSelectedCharacter().getX());
                    helpCharacter.setY(arrayUser.getGame3().getSelectedCharacter().getY());
                    helpCharacter.setVx(arrayUser.getGame3().getSelectedCharacter().getVx());
                    helpCharacter.setVy(arrayUser.getGame3().getSelectedCharacter().getVy());
                    helpCharacter.setHeart(arrayUser.getGame3().getSelectedCharacter().getHeart());
                    helpCharacter.setDecreaseHeart(arrayUser.getGame3().getSelectedCharacter().isDecreaseHeart());
                    helpCharacter.setType(arrayUser.getGame3().getSelectedCharacter().getType());
                    helpCharacter.setShield(arrayUser.getGame3().getSelectedCharacter().getShield());
                    helpCharacter.setJump(arrayUser.getGame3().getSelectedCharacter().getJump());
                    helpCharacter.setUseSword(arrayUser.getGame3().getSelectedCharacter().isUseSword());
                    helpCharacter.setMainSword(arrayUser.getGame3().getSelectedCharacter().getMainSword());
                    helpCharacter.setSword(arrayUser.getGame3().getSelectedCharacter().getSword());
                    helpCharacter.setSwordColdDown(arrayUser.getGame3().getSelectedCharacter().getSwordColdDown());
                    helpCharacter.setSit(arrayUser.getGame3().getSelectedCharacter().isSit());
                    helpCharacter.setSlime(arrayUser.getGame3().getSelectedCharacter().isSlime());
                    helpCharacter.setShot(arrayUser.getGame3().getSelectedCharacter().getShot());
                    helpCharacter.setState(arrayUser.getGame3().getSelectedCharacter().getState());
                    helpCharacter.setWidth(arrayUser.getGame3().getSelectedCharacter().getWidth());
                    helpCharacter.setHeight(arrayUser.getGame3().getSelectedCharacter().getHeight());
                    helpCharacter.setAntiKnock(arrayUser.getGame3().getSelectedCharacter().isAntiKnock());
                    helpCharacter.setShotColdDown(arrayUser.getGame3().getSelectedCharacter().getShotColdDown());
                    arrayUser.getGame3().setSelectedCharacter(helpCharacter);
                }
            }
        }
    }
}
