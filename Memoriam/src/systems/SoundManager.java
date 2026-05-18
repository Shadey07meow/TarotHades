package systems;

import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.*;

public class SoundManager {

    private static SoundManager instance;

    public static SoundManager get() {

        if(instance == null) {
            instance = new SoundManager();
        }

        return instance;
    }

    private  Clip currentMusic;
    private  HashMap<String, String> sounds = new HashMap<>();

    private SoundManager() {

        // MUSIC
        sounds.put("menuMusic", "Memoriam/assets/audio/music/mainMenu.wav");
        sounds.put("lobbyMusic", "Memoriam/assets/audio/music/lobby.wav");
        sounds.put("gameMusic", "Memoriam/assets/audio/music/gameplay.wav");
        sounds.put("bossMusic", "Memoriam/assets/audio/music/boss.wav");
        sounds.put("storyMusic", "Memoriam/assets/audio/music/story.wav");

        // SFX
        sounds.put("button", "Memoriam/assets/audio/sfx/buttonClick.wav");
        sounds.put("shoot", "Memoriam/assets/audio/sfx/shoot.wav");
        sounds.put("enemyHit", "Memoriam/assets/audio/sfx/enemyHit.wav");
        sounds.put("playerHit", "Memoriam/assets/audio/sfx/playerHit.wav");
        sounds.put("enemyDeath", "Memoriam/assets/audio/sfx/enemyDeath.wav");
        sounds.put("pickup", "Memoriam/assets/audio/sfx/pickup.wav");
        sounds.put("chestOpen", "Memoriam/assets/audio/sfx/chestOpen.wav");
        sounds.put("gameOver", "Memoriam/assets/audio/sfx/gameOver.wav");
    }

    // PLAY MUSIC
public void playMusic(String key) {
    try {
        stopMusic();

        File file = new File(sounds.get(key));

        if (!file.exists()) {
            System.out.println("Missing music file: " + file.getAbsolutePath());
            return;
        }

        AudioInputStream audio = AudioSystem.getAudioInputStream(file);

        currentMusic = AudioSystem.getClip();
        currentMusic.open(audio);
        currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
        System.out.println(System.getProperty("user.dir"));
        currentMusic.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // STOP MUSIC
    public  void stopMusic() {

        if(currentMusic != null) {

            currentMusic.stop();

            currentMusic.close();
        }
    }

    // PLAY SOUND EFFECT
public void playSFX(String key) {
    try {
        File file = new File(sounds.get(key));

        if (!file.exists()) {
            System.out.println("Missing SFX file: " + file.getAbsolutePath());
            return;
        }

        AudioInputStream audio = AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
