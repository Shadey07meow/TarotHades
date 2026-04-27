package systems;

import images.ImageLibrary;
import java.awt.Color;
import javax.swing.Timer;
import object.*;
import scenes.*;
import  java.util.ArrayList;

public class LevelManager {

    public static int maxLevels;
    public static GameFrame gFrame;
    public static ArrayList<PlayableScreen> levels = new ArrayList<>();


    public static GameFrame getFrame()
    {
        return gFrame;
    }


    public static void loadLevel(int id, PlayableScreen currentLevel) {

        System.out.println("You are loading a level");

        // When loading a level, transition logic should also be handled here

        // Transition Logic
        gFrame.cutsceneScreen.loadCutsceneForLevel(id);
        gFrame.showPanel("cutscene");

        while(gFrame.cutsceneScreen.isFinishedLoading() == false){
        }

    
        
        //  
        currentLevel.closeGameLoop();
        searchLevel(id);

    }

    private static void searchLevel(int id) 
    {
        

        gFrame.showPanel(levels.get(id).getScreenName());
        
    }


    // NEVER CALL AGAIN
    public static void setFrame(GameFrame thing)
    {
        gFrame = thing;
    }

    public static void addLevel(PlayableScreen a)
    {
        levels.add(a);
    }

}