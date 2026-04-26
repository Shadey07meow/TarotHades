package systems;

import images.ImageLibrary;
import java.awt.Color;
import javax.swing.Timer;
import object.*;
import scenes.*;

public class LevelFactory {

    public static int maxLevels;

    public static void loadLevel(int id, WorldRenderer world, Player player, PlayableScreen scene) {

        Map map = new Map(ImageLibrary.get().map, new Vector2(100, 500), 1, scene);

        world.resetWorld(map, player);
        scene.closeChestUI();

        player.setUIOpen(false);

        buildLevel(id, world, player, scene);

        //spawnChest(world, player, scene);

        showLevelMessage(scene, "LEVEL " + id);
    }

    private static void buildLevel(int id, WorldRenderer world, Player player, PlayableScreen scene) {

        switch (id) {

            case 1 -> {
                setSpawn(player, 300, 300);

                addEnemy(world, new BlueWisp(player.getPosition(), 2, scene));
            }

            case 2 -> {
                setSpawn(player, 400, 300);

                addObject(world, new GameObject(500, 400, 50, scene) );
            }

            case 3 -> {
                setSpawn(player, 500, 500);

                addObject(world, new GameObject(350, 350, 80, scene));
            }

            case 4 -> {
                setSpawn(player, 600, 300);

                addObject(world, new GameObject(700, 200, 60, scene));
            }

            case 5 -> {
                setSpawn(player, 500, 500);

                GameObject boss = new GameObject(700, 500, 120, scene);
                boss.setColor(Color.RED);

                addEnemy(world, boss);
            }

            default -> {
                System.out.println("Invalid level: " + id);
                setSpawn(player, 300, 300);
            }
        }
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
}