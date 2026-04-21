package systems;

import images.ImageLibrary;
import java.awt.Color;
import javax.swing.Timer;
import object.*;
import scenes.*;

public class LevelFactory {

    public static void loadLevel(int id, WorldRenderer world, Player player, GameStart scene) {

        Map map = new Map(ImageLibrary.get().map, new Vector2(100, 500), 1);

        // RESET WORLD FIRST (IMPORTANT)
        world.resetWorld(map, player);

        // ALWAYS close UI on level change
        scene.closeChestUI();

        switch (id) {

            case 1 -> {
                System.out.println("Level 1: Awakening");
                showLevelMessage(scene, "LEVEL 1: AWAKENING");
                
                player.setPosition(300, 300);
                
                world.addObject(new TreasureChest(200, 200, player, 2, scene));
            }

            case 2 -> {
                System.out.println("Level 2: Hallways");

                showLevelMessage(scene, "LEVEL 2: HALLWAYS");

                player.setPosition(400, 300);
                

                world.addObject(new GameObject(500, 400, 50));
            }

            case 3 -> {
                System.out.println("Level 3: Ruins");

                showLevelMessage(scene, "LEVEL 3: RUINS");

                player.setPosition(500, 500);
            }

            case 4 -> {
                System.out.println("Level 4: Trials");

                showLevelMessage(scene, "LEVEL 4: TRIALS");

                player.setPosition(600, 300);
            }

            case 5 -> {
                System.out.println("FINAL BOSS: The Fool");

                showLevelMessage(scene, "FINAL BOSS");

                player.setPosition(500, 500);

                GameObject boss = new GameObject(700, 500, 120);
                boss.setColor(Color.RED);

                world.addObject(boss);
            }
        }
    }

    private static void showLevelMessage(GameStart scene, String text) {

        scene.setLevelText(text);

        Timer t = new Timer(1200, e -> scene.clearLevelText());
        t.setRepeats(false);
        t.start();
    }
}