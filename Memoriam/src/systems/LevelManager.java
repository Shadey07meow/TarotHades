package systems;

import java.util.ArrayList;
import object.*;
import scenes.*;

public class LevelManager {

    public static int maxLevels;
    public static GameFrame gFrame;
    public static ArrayList<PlayableScreen> levels = new ArrayList<>();
    private static java.util.Set<object.PlayerAbility> savedAbilities =
            new java.util.HashSet<>();

     private static java.util.Map<PlayerAbility, Integer> savedAbilityLevels =
            new java.util.EnumMap<>(PlayerAbility.class);


    public static GameFrame getFrame(){ return gFrame; }

    public static void savePlayerAbilities(Player player) {

        savedAbilities.clear();
        savedAbilityLevels.clear();

        for (object.PlayerAbility ability : object.PlayerAbility.values()) {
            int level = player.getAbilityLevel(ability);
            if (player.hasAbility(ability)) {
                savedAbilities.add(ability);
                savedAbilityLevels.put(ability, level);
            }
        }
        System.out.println("Saved abilities: " + savedAbilities);
    }


    public static void restorePlayerAbilities(Player player) {
        
        for (PlayerAbility ability : savedAbilities) {
            int targetLevel = savedAbilityLevels.getOrDefault(ability, 1);
            int currentLevel = player.getAbilityLevel(ability);

            // Apply missing stacks so the player ends up at the saved level
            for (int i = currentLevel; i < targetLevel; i++) {
                player.applyAbility(ability);
            }
        }
        System.out.println("Restored abilities: " + savedAbilities);
    }

    public static void loadLevel(int id, PlayableScreen currentLevel) {
        System.out.println("You are loading a level, ID: " + String.valueOf(id) );

        if(id >= levels.size())
        {
            gFrame.showPanel("menu");
            return;
        }

        Player player = (currentLevel.getWorldRenderer() != null)
                ? currentLevel.getWorldRenderer().getPlayer()
                : null;

        if (player != null) {
            // Fire "level completed" event so timed effects decrement 
            StatusEffectManager.get().onLevelCompleted(player);

            // Save abilities for the next level 
            savePlayerAbilities(player);
        }
        
        // When loading a level, transition logic should also be handled here

        // Transition Logic
        gFrame.cutsceneScreen.loadCutsceneForLevel(id);
        gFrame.showPanel("cutscene");

        while(gFrame.cutsceneScreen.isFinishedLoading() == false){
        }

        System.out.println("Finished cutscenes"); 
        currentLevel.closeGameLoop();
        searchLevel(id);
    }

    private static void searchLevel(int id) {
        gFrame.showPanel(levels.get(id).getScreenName());
    }

     public static void startNewRun() {
        savedAbilities.clear();
        savedAbilityLevels.clear();
        StatusEffectManager.reset();
        RelicManager.reset();
        System.out.println("[LevelManager] New run started — singletons reset.");
    }

    // NEVER CALL AGAIN
    public static void setFrame(GameFrame thing){ gFrame = thing; }

    public static void addLevel(PlayableScreen a){ levels.add(a);}
}