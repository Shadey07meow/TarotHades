package systems;


import object.*;

import java.util.Map;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class SaveSystem {

    // When a thing is thinged, it saves the thing
    // Does not save everthing, when a new level is entered, calls a method that overwrites a text file in the autoSave folder 
    
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
            for(RelicStatusEffect relic : StatusEffectManager.get().getActiveRelics())
            {

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

    public static void loadLastSave()
    {

        File saveFile = new File("autosave/saveFile.4t");
        // System.out.println("Hello therse");

        if(!saveFile.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(saveFile)))
        {
            String currentLine = "";
            int level = 0;

            while((currentLine = reader.readLine()) != null)
            {
                System.out.println("Reading file");
                // Decoding .4t file here
                
                // Level Loading here
                if(currentLine.charAt(0) == 'L')
                {
                    // Load level things here
                    level = Character.getNumericValue(currentLine.charAt(2));
                    System.out.println("Loading level :" + level);
                    LevelManager.loadLevel(level);
                }
                
                if(currentLine.charAt(0) == 'H')
                {
                    String healthPoints = currentLine.substring(2);
                    int hp = Integer.parseInt(healthPoints);
                    System.out.println("Amount of Health : " + hp);
                    LevelManager.getPlayableScreen(level).getWorldRenderer().getPlayer().getStats().setCurrentHP(hp);
                }

                    if(currentLine.charAt(0) == 'K')
                {
                    String killCount = currentLine.substring(2);
                    int kills = Integer.parseInt(killCount);
                    System.out.println("Amount of kills :" + kills);
                    GameStats.get().setKills(kills);
                }

            }

        } catch (IOException e)
        {

        }
    }

}
