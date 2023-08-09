package game;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Audio {
    Clip main;
    //--------------for singleton---------------//
    private final static Audio instance = new Audio();

    private Audio() {

    }

    public static Audio getInstance() {
        return instance;
    }

    //---------------methods--------------------//
    public void initMain(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("src\\main\\java\\audios\\" + "main" + ".wav").getAbsoluteFile());
            main = AudioSystem.getClip();
            main.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }
    public Clip getSound(String name) {
        Clip clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("src\\main\\java\\audios\\" + name + ".wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        if (name.equals("main")){
            return main;
        }
        else {
            return clip;
        }
    }


}
