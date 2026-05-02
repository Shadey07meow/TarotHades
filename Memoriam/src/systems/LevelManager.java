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


    public static GameFrame getFrame()
    {

        return gFrame;
    }

    public static void savePlayerAbilities(Player player) {
        savedAbilities.clear();
        for (object.PlayerAbility ability : object.PlayerAbility.values()) {
            if (player.hasAbility(ability)) {
                savedAbilities.add(ability);
            }
        }
        System.out.println("Saved abilities: " + savedAbilities);
    }

    public static void restorePlayerAbilities(Player player) {
        for (object.PlayerAbility ability : savedAbilities) {
            player.applyAbility(ability);
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

        if (currentLevel.getWorldRenderer() != null &&
            currentLevel.getWorldRenderer().getPlayer() != null) {
            savePlayerAbilities(currentLevel.getWorldRenderer().getPlayer());
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

    // NEVER CALL AGAIN
    public static void setFrame(GameFrame thing){ gFrame = thing; }

    public static void addLevel(PlayableScreen a){ levels.add(a);}
}