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
        sounds.put("menuMusic", "/assets/audio/music/mainMenu.wav");
        sounds.put("lobbyMusic", "/assets/audio/music/lobby.wav");
        sounds.put("gameMusic", "/assets/audio/music/gameplay.wav");
        sounds.put("bossMusic", "/assets/audio/music/boss.wav");
        sounds.put("storyMusic", "/assets/audio/music/story.wav");

        // SFX
        sounds.put("button", "/assets/audio/sfx/buttonClick.wav");
        sounds.put("shoot", "/assets/audio/sfx/shoot.wav");
        sounds.put("enemyHit", "/assets/audio/sfx/enemyHit.wav");
        sounds.put("playerHit", "/assets/audio/sfx/playerHit.wav");
        sounds.put("enemyDeath", "/assets/audio/sfx/enemyDeath.wav");
        sounds.put("pickup", "/assets/audio/sfx/pickup.wav");
        sounds.put("chestOpen", "/assets/audio/sfx/chestOpen.wav");
        sounds.put("gameOver", "/assets/audio/sfx/gameOver.wav");
    }


    public void playMusic(String key) {
        try {
            stopMusic();

            System.out.println("▶ Playing music: " + key);

            AudioInputStream audio = getAudioStream(sounds.get(key));

            if (audio == null) {
                System.out.println("❌ Missing music: " + key);
                return;
            }

            currentMusic = AudioSystem.getClip();
            currentMusic.open(audio);

            currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
            currentMusic.start();

        } catch (Exception e) {
            System.out.println("❌ Failed to play music: " + key);
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        try {
            if (currentMusic != null) {
                currentMusic.stop();
                currentMusic.close();
                currentMusic = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void playSFX(String key) {
        try {
            AudioInputStream audio = getAudioStream(sounds.get(key));

            if (audio == null) {
                System.out.println("❌ Missing SFX: " + key);
                return;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            System.out.println("❌ Failed SFX: " + key);
            e.printStackTrace();
        }
    }



    private AudioInputStream getAudioStream(String path) {
        try {

            if (path == null) return null;

            // 1. FILESYSTEM MODE (IDE / DEV)
            File file = new File(
                    System.getProperty("user.dir")
                            + File.separator + "Memoriam"
                            + path.replace("/", File.separator)
            );

            if (file.exists()) {
                return AudioSystem.getAudioInputStream(file);
            }

            // 2. JAR MODE (fallback)
            InputStream is = getClass().getResourceAsStream(path);

            if (is != null) {
                return AudioSystem.getAudioInputStream(
                        new BufferedInputStream(is)
                );
            }

            System.out.println("❌ Audio not found: " + path);
            return null;

        } catch (Exception e) {
            System.out.println("❌ Audio load error: " + path);
            e.printStackTrace();
            return null;
        }
    }
}