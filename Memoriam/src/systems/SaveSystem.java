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
import object.*;

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
                writer.write("R:" + relic.getRelic().ordinal() + "\n");

            }

            int count = 0;
    
            
            // Save powerups 
            for(Map.Entry<PlayerAbility, Integer> entry : list.entrySet())
            {
                // Save level
                PlayerAbility ab = entry.getKey();
                writer.write("P"+  String.valueOf((count)) + ":" + ab + ";" + entry.getValue() + "\n");
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

        //Map<PlayerAbility, Integer> outAbs = new Map<PlayerAbility, Integer>();  

        if(!saveFile.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(saveFile)))
        {
            String currentLine = "";
            int level = 0;
            Map<PlayerAbility, Integer> abilities = new EnumMap<>(PlayerAbility.class);

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

                if(currentLine.charAt(0) == 'R')
                {
                    String relicThing = currentLine.substring(2);
                    int relicNum = Integer.parseInt(relicThing);
                    System.out.println("Relic :" + relicNum);
                    ArrayList<RelicStatusEffect> thingy = new ArrayList<>();
                    thingy.add(new RelicStatusEffect(Relic.values()[relicNum]));
                    StatusEffectManager.get().setActiveRelics(thingy);
                }

                
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

            LevelManager.getPlayableScreen(level).getWorldRenderer().getPlayer().setAbilityMap(abilities);

        } catch (IOException e)
        {

        }
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

}
