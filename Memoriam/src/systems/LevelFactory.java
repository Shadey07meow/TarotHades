package systems;

import images.ImageLibrary;
import java.awt.Color;
import javax.swing.Timer;
import object.*;
import scenes.*;
import  java.util.ArrayList;

public class LevelFactory {

    public static int maxLevels;
    public static GameFrame gFrame;
    public static ArrayList<PlayableScreen> levels = new ArrayList<>();


    public static GameFrame getFrame()
    {
        return gFrame;
    }


    public static void loadLevel(int id, WorldRenderer world, Player player, PlayableScreen scene) {


        if(id > maxLevels) 
        {
         id = maxLevels;
         System.out.println("You are above levells");   
        }
        Map map = new Map(ImageLibrary.get().map, new Vector2(100, 500), 1, scene);

        world.resetWorld(map, player);
        scene.closeChestUI();

        player.setUIOpen(false);

        searchLevel(id);


        //spawnChest(world, player, scene);

        showLevelMessage(scene, "LEVEL " + id);
    }

    private static void searchLevel(int id) 
    {
        

        gFrame.showPanel(levels.get(id).getScreenName());
        
    }


    private static void setSpawn(Player player, int x, int y) {
        player.setPosition(x, y);
    }

    private static void addObject(WorldRenderer world, GameObject obj) {
        world.addObject(obj);
    }

    private static void addEnemy(WorldRenderer world, GameObject obj) {
        world.addObject(obj);
    }



    private static void showLevelMessage(PlayableScreen scene, String text) {

        scene.setLevelText(text);

        Timer t = new Timer(1200, e -> scene.clearLevelText());
        t.setRepeats(false);
        t.start();
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