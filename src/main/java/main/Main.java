package main;

import database.Config;
import database.Files;
import game.Audio;

public class Main {
    public static void main(String[] args) {
        Files.loadUsers();
        Audio.getInstance().initMain();
        Config.getInstance().setStage();
        Menu.getInstance().enter();
    }
}
