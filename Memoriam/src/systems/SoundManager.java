package systems;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {

    private static Clip currentMusic;

    // loops bg music
    public static void playMusic(String path) {
        stopMusic(); // stop previous track

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(SoundManager.class.getClassLoader().getResource("music/TempMainMenu.wav"));
            currentMusic = AudioSystem.getClip();
            currentMusic.open(audio);
            currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // stops music
    public static void stopMusic() {
        if (currentMusic != null && currentMusic.isRunning()) {
            currentMusic.stop();
            currentMusic.close();
        }
    }

    // play sfx
    public static void playSFX(String path) {
        //to be added
    }
}

