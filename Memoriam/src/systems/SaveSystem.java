package systems;

import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;
import object.Entities.Player;
import object.Entities.PlayerAbility;
import object.statics.Relic;
import scenes.templates.PlayableScreen;

public class SaveSystem {

    private static final String SAVE_PATH = "/autosave/saveFile.4t";

    public static void saveProgress(int levelNumber, int playerHealth,
                                    Map<PlayerAbility, Integer> list,
                                    int kills) {

        File saveFile = new File(getAssetPath(SAVE_PATH));

        try {
            File parent = saveFile.getParentFile();
            if (parent != null) parent.mkdirs(); // IMPORTANT FIX
            saveFile.createNewFile();
        } catch (IOException ignored) {}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {

            writer.write("L:" + levelNumber + "\n");
            writer.write("H:" + playerHealth + "\n");
            writer.write("K:" + kills + "\n");

            // relic
            Relic chosen = RelicManager.get().getChosenRelic();
            if (chosen != null) {
                writer.write("R:" + chosen.ordinal() + "\n");
            }

            int count = 0;

            for (Map.Entry<PlayerAbility, Integer> entry : list.entrySet()) {
                writer.write("P" + count + ":" +
                        entry.getKey().ordinal() + ";" +
                        entry.getValue() + "\n");
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadLastSave() {

        File saveFile = new File(getAssetPath(SAVE_PATH));
        if (!saveFile.exists()) return;

        int level = 0;
        int hp = 0;
        int kills = 0;
        int relic = -1;

        Map<PlayerAbility, Integer> abilities =
                new EnumMap<>(PlayerAbility.class);

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("L:")) {
                    level = Integer.parseInt(line.substring(2));
                }
                else if (line.startsWith("H:")) {
                    hp = Integer.parseInt(line.substring(2));
                }
                else if (line.startsWith("K:")) {
                    kills = Integer.parseInt(line.substring(2));
                }
                else if (line.startsWith("R:")) {
                    relic = Integer.parseInt(line.substring(2));
                }
                else if (line.startsWith("P")) {

                    String[] parts = line.substring(line.indexOf(":") + 1)
                            .split(";");

                    int skillNum = Integer.parseInt(parts[0]);
                    int stackNum = Integer.parseInt(parts[1]);

                    abilities.put(PlayerAbility.values()[skillNum], stackNum);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        final int savedLevel = level;
        final int savedHP = hp;
        final int savedKills = kills;
        final int savedRelic = relic;
        final Map<PlayerAbility, Integer> savedAbilities = abilities;

        SwingUtilities.invokeLater(() -> {

            LevelManager.loadLevel(savedLevel);

            PlayableScreen screen = LevelManager.getPlayableScreen(savedLevel);
            if (screen == null) return;

            WorldRenderer world = screen.getWorldRenderer();
            if (world == null) return;

            Player player = world.getPlayer();
            if (player == null) return;

            // relic
            if (savedRelic != -1) {
                RelicManager.reset();
                RelicManager.get().applyRelic(
                        Relic.values()[savedRelic],
                        player
                );
            }

            // abilities
            for (Map.Entry<PlayerAbility, Integer> entry : savedAbilities.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    player.applyAbility(entry.getKey());
                }
            }

            player.getStats().setCurrentHP(savedHP);
            GameStats.get().setKills(savedKills);
        });
    }

    public static ArrayList<String> getArcanas() {

    ArrayList<String> abs = new ArrayList<>();
    File saveFile = new File(getAssetPath("autosave/saveFile.4t"));

    if (!saveFile.exists()) return abs;

    try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {

        String line;
        Map<PlayerAbility, Integer> abilities = new EnumMap<>(PlayerAbility.class);

        while ((line = reader.readLine()) != null) {

            if (line.startsWith("P")) {

                String[] parts = line.substring(line.indexOf(":") + 1)
                        .split(";");

                int skillNum = Integer.parseInt(parts[0]);
                int stackNum = Integer.parseInt(parts[1]);

                abilities.put(PlayerAbility.values()[skillNum], stackNum);
            }
        }

        for (Map.Entry<PlayerAbility, Integer> item : abilities.entrySet()) {
            abs.add(item.getKey().toString() + ":" + item.getValue());
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return abs;
}

  
    public static int getLevel() {
        return readInt("L");
    }

    public static int getKills() {
        return readInt("K");
    }

    public static int getHP() {
        return readInt("H");
    }

    private static int readInt(String key) {

        File saveFile = new File(getAssetPath(SAVE_PATH));
        if (!saveFile.exists()) return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(key + ":")) {
                    return Integer.parseInt(line.substring(2));
                }
            }

        } catch (IOException ignored) {}

        return 0;
    }

    public static String getRelic() {

        File saveFile = new File(getAssetPath(SAVE_PATH));
        if (!saveFile.exists()) return "";

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("R:")) {
                    int i = Integer.parseInt(line.substring(2));
                    return Relic.values()[i].toString();
                }
            }

        } catch (IOException ignored) {}

        return "";
    }


    public static void resetToNewRun() {

        File saveFile = new File(getAssetPath(SAVE_PATH));

        try {
            File parent = saveFile.getParentFile();
            if (parent != null) parent.mkdirs();
            saveFile.createNewFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
                writer.write("L:0\n");
                writer.write("H:10\n");
                writer.write("K:0\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getAssetPath(String path) {

        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        String basePath = System.getProperty("user.dir");

        return basePath
                + File.separator
                + "Memoriam"
                + File.separator
                + path.replace("/", File.separator);
    }
}