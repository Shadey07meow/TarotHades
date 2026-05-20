package systems;

import java.io.*;
import java.util.HashMap;
import javax.sound.sampled.*;

public class SoundManager {

    private static SoundManager instance;

    public static SoundManager get() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private Clip currentMusic;
    private final HashMap<String, String> sounds = new HashMap<>();

    private SoundManager() {

        // MUSIC
        sounds.put("menuMusic", "assets/audio/music/mainMenu.wav");
        sounds.put("lobbyMusic", "assets/audio/music/lobby.wav");
        sounds.put("gameMusic", "assets/audio/music/gameplay.wav");
        sounds.put("bossMusic", "assets/audio/music/boss.wav");
        sounds.put("storyMusic", "assets/audio/music/story.wav");

        // SFX
        sounds.put("button", "assets/audio/sfx/buttonClick.wav");
        sounds.put("shoot", "assets/audio/sfx/shoot.wav");
        sounds.put("enemyHit", "assets/audio/sfx/enemyHit.wav");
        sounds.put("playerHit", "assets/audio/sfx/playerHit.wav");
        sounds.put("enemyDeath", "assets/audio/sfx/enemyDeath.wav");
        sounds.put("pickup", "assets/audio/sfx/pickup.wav");
        sounds.put("chestOpen", "assets/audio/sfx/chestOpen.wav");
        sounds.put("gameOver", "assets/audio/sfx/gameOver.wav");
    }



    public void playMusic(String key) {

        try {
            // AudioInputStream audio = AudioSystem.getAudioInputStream(new File("music/TempMainMenu.wav"));

            //System.out.println("Does (" + key +") exist:  " + new File(sounds.get(key)).exists());
            stopMusic();

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                            new File(sounds.get(key)));

            currentMusic = AudioSystem.getClip();

            currentMusic.open(audio);

            currentMusic.loop(Clip.LOOP_CONTINUOUSLY);

            currentMusic.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playSFX(String key) {

        try {

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                            new File(sounds.get(key)));

            Clip clip = AudioSystem.getClip();

            clip.open(audio);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {

        if(currentMusic != null) {

            currentMusic.stop();

            currentMusic.close();
        }
    }

}