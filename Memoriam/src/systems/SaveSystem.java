package systems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.SwingUtilities;
import object.*;
import scenes.PlayableScreen;

public class SaveSystem {

    // When a thing is thinged, it saves the thing
    // Does not save everthing, when a new level is entered, calls a method that overwrites a text file in the autoSave folder 
    PlayableScreen playableScreen;

    public static void saveProgress(int levelNumber, int playerHealth, Map<PlayerAbility, Integer> list, int kills)
    {
        File saveFile = new File("autosave/saveFile.4t");
        try
        {
            saveFile.createNewFile();
        } catch (IOException e){}
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile)))
        {
            // Save level
            writer.write("L:" + String.valueOf((levelNumber)) + "\n");

            // Save health
            writer.write("H:" + String.valueOf((playerHealth)) + "\n");
            
            // Save kills
            writer.write("K:" + String.valueOf((kills)) + "\n");

            // Relics
            Relic chosen = RelicManager.get().getChosenRelic();
            if (chosen != null) {
                writer.write("R:" + chosen.ordinal() + "\n");
            }

            int count = 0;
    
            
            // Save powerups 
            for(Map.Entry<PlayerAbility, Integer> entry : list.entrySet())
            {
                // Save level
                PlayerAbility ab = entry.getKey();
                writer.write("P"+  String.valueOf((count)) + ":" + ab.ordinal() + ";" + entry.getValue() + "\n");
                count++;
            }

        } catch (IOException e)
        {

        }
    }   

 public static void loadLastSave() {

    File saveFile = new File("autosave/saveFile.4t");
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

            System.out.println("Reading file");

            if (line.startsWith("L:")) {
                level = Integer.parseInt(line.substring(2));
                System.out.println("Loaded level: " + level);
            }

            else if (line.startsWith("H:")) {
                hp = Integer.parseInt(line.substring(2));
                System.out.println("HP: " + hp);
            }

            else if (line.startsWith("K:")) {
                kills = Integer.parseInt(line.substring(2));
                System.out.println("Kills: " + kills);
            }

            else if (line.startsWith("R:")) {
                relic = Integer.parseInt(line.substring(2));
                System.out.println("Relic: " + relic);
            }

            else if (line.startsWith("P")) {

                String[] parts = line.substring(line.indexOf(":") + 1)
                                     .split(";");

                int skillNum = Integer.parseInt(parts[0]);
                int stackNum = Integer.parseInt(parts[1]);

                abilities.put(PlayerAbility.values()[skillNum], stackNum);

                System.out.println("Skill: " + skillNum + " Stack: " + stackNum);
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

        // ✔ STEP 1: LOAD LEVEL FIRST
        LevelManager.loadLevel(savedLevel);

        PlayableScreen screen = LevelManager.getPlayableScreen(savedLevel);
        if (screen == null) return;

        WorldRenderer world = screen.getWorldRenderer();
        if (world == null) return;

        Player player = world.getPlayer();
        if (player == null) return;
        if (savedRelic != -1) {

        RelicManager.reset();

        Relic relicObj = Relic.values()[savedRelic];
        RelicManager.get().applyRelic(relicObj, player);

}

        // ✔ STEP 2: APPLY STATE AFTER WORLD EXISTS
        for (Map.Entry<PlayerAbility, Integer> entry : savedAbilities.entrySet()) {
    PlayerAbility ability = entry.getKey();
    int stack = entry.getValue();

    for (int i = 0; i < stack; i++) {
        player.applyAbility(ability);
    }
}
        player.getStats().setCurrentHP(savedHP);
        GameStats.get().setKills(savedKills);

        System.out.println("Loaded HP: " + savedHP);
        System.out.println("Loaded kills: " + savedKills);
        System.out.println("Loaded relic: " + savedRelic);
        System.out.println("Loaded abilities: " + savedAbilities);
    });
}

    public static int getKills()
    {
        File saveFile = new File("autosave/saveFile.4t");

        int kills = 0;
        if(!saveFile.exists()) return kills;

        try(BufferedReader reader = new BufferedReader(new FileReader(saveFile)))
        {
            String currentLine = "";
 

            while((currentLine = reader.readLine()) != null)
            {
                System.out.println("Reading file");
                // Decoding .4t file here


                if(currentLine.charAt(0) == 'K')
                {
                    String killCount = currentLine.substring(2);
                    kills = Integer.parseInt(killCount);
                    System.out.println("Amount of kills :" + kills);
                }
            }
        } catch (IOException e)
        {

        }
        return kills;
    }

    
    public static int getHP()
    {
        File saveFile = new File("autosave/saveFile.4t");

        int hp = 0;
        if(!saveFile.exists()) return hp;

        try(BufferedReader reader = new BufferedReader(new FileReader(saveFile)))
        {
            String currentLine = "";
 

            while((currentLine = reader.readLine()) != null)
            {
                System.out.println("Reading file");
                // Decoding .4t file here


                if(currentLine.charAt(0) == 'H')
                {
                    String health = currentLine.substring(2);
                    hp = Integer.parseInt(health);
   
                }
            }
        } catch (IOException e)
        {

        }
        return hp;
        
    }

    
    public static String getRelic()
    {
        File saveFile = new File("autosave/saveFile.4t");

        String relicString = "";
        if(!saveFile.exists()) return relicString;

        try(BufferedReader reader = new BufferedReader(new FileReader(saveFile)))
        {
            String currentLine = "";
 

            while((currentLine = reader.readLine()) != null)
            {
                System.out.println("Reading file");
                // Decoding .4t file here


                if(currentLine.charAt(0) == 'R')
                {
                    String rString = currentLine.substring(2);
                    int i = Integer.parseInt(rString);
                    relicString = Relic.values()[i].toString();
                    break;
                }
            }
        } catch (IOException e)
        {

        }
        return relicString;
    }

    
    public static ArrayList<String> getArcanas()
    {
        
        ArrayList<String> abs = new ArrayList<>();
        File saveFile = new File("autosave/saveFile.4t");
        // System.out.println("Hello therse");

        //Map<PlayerAbility, Integer> outAbs = new Map<PlayerAbility, Integer>();  

        if(!saveFile.exists()) return abs;

        try(BufferedReader reader = new BufferedReader(new FileReader(saveFile)))
        {
            String currentLine = "";
            Map<PlayerAbility, Integer> abilities = new EnumMap<>(PlayerAbility.class);

            while((currentLine = reader.readLine()) != null)
            {
                System.out.println("Reading file");
                if(currentLine.charAt(0) == 'P')
                {
                    char skill = currentLine.charAt(3);
                    char stack = currentLine.charAt(5);
                    int skillNum = Character.getNumericValue(skill);
                    int stackNum = Character.getNumericValue(stack);
                    System.out.println("Skill : " + PlayerAbility.values()[skillNum] + ", At stack :" + stackNum);
                    
                    abilities.put(PlayerAbility.values()[skillNum], stackNum);
                }

            }

            for (Map.Entry<PlayerAbility, Integer> item : abilities.entrySet()) 
            {
                abs.add(item.getKey().toString()+ ":" + String.valueOf(item.getValue()));
            }



        } catch (IOException e)
        {

        }
         return abs;
             
    }   

    public static void resetToNewRun() {

        File saveFile = new File("autosave/saveFile.4t");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {

            // basic default state
            writer.write("L:0\n");   // level
            writer.write("H:10\n");  // default HP
            writer.write("K:0\n");   // kills

            // NO relics
            // NO abilities

            System.out.println("[SaveSystem] Save reset to new run state");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
