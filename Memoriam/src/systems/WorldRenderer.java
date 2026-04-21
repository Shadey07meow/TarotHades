package systems;

import object.*;
import scenes.*;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

public class WorldRenderer {

    // Class containing all the information about the objects in it
    // Needs player class in order to determine position changes
    // Also needs a class in order to render the map first always, ensuring it'll always appear last
    private Player player;
    private Map map;
    private PlayableScreen gamePanel;

    // Object list MUST include player
    private ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    private Player worldPlayer = null;
    private Vector2 centerPosition = new Vector2(950, 420);
    private int distanceFromCenter = 20 * 4 ;
    private final double xThresholdd = 150; 
    private final double yThresholdd = 150; 

    private boolean debugMode = false;

    // Constructors
    public WorldRenderer(Player player, PlayableScreen s)
    {
        this.player = player;
        this.map = new Map(null, player.getPosition(), 1);
        this.gamePanel = s;

        this.setMap(this.map);
        this.setPlayer(this.player);
    }

    public WorldRenderer(Player player, Map  m,  PlayableScreen s)
    {
        this.player = player;
        this.map = m;
        this.gamePanel = s;

        this.setMap(this.map);
        this.setPlayer(this.player);
    }

    public WorldRenderer(PlayableScreen s)
    {
        this.gamePanel = s;
        this.player = null;
        this.map = null;
    }

    // Add game object to the game world
    public void addObject(GameObject obj)
    {
        // Checks if there is a map
        if(this.objectList.get(0) == null)
        {
            //System.out.println("There is no map yet, cannot add objects");
            return;
        }

        if(obj == (GameObject)this.player || obj == (GameObject)this.map )
        {
            System.out.println("There can only be one instance of this object in this class");
            return;    
        }

        this.objectList.add(1, obj);
        //System.out.println("Added an object to the scene");
    }


    // Needs a center point, is already set in the beginning of the java file
    // If distance of the player from the center point is greater than some amount, then move the world instead of the player
    public boolean checkPlayerDistanceFromCenter()
    {
        return (Vector2.distance(this.centerPosition, this.player.getPosition()) > distanceFromCenter);
    }

    // Update world position
    public void updateWorld()
    {

        // Update player first
        if(this.player != null)
        {
            for (int x = 0; x < this.objectList.size(); x++)
            {  
                this.objectList.get(x).update();
            } 

            // This is the world clamping logic
            

            boolean willMoveRight = (this.player.getPosition().x > this.centerPosition.x + xThresholdd) &&  (this.player.getVelocity().x > 0);
            boolean willMoveLeft = (this.player.getPosition().x < this.centerPosition.x - xThresholdd) &&  (this.player.getVelocity().x < 0);
            
            boolean willMoveUp = (this.player.getPosition().y > this.centerPosition.y + yThresholdd) &&  (this.player.getVelocity().y < 0);
            boolean willMoveDown = (this.player.getPosition().y < this.centerPosition.y - yThresholdd) &&  (this.player.getVelocity().y > 0);
            if(willMoveRight|| willMoveLeft)
            {
                // Runs if the player is outside the bounding box of left and right 
                ///System.out.println("I am outside the x threshold");
                moveObjectsWithWorldX();
            }

            if( willMoveDown || willMoveUp)
            {
                // Runs if the player is outside the bounding box of left and right 
                //System.out.println("I am outside the y threshold");
                moveObjectsWithWorldY();
            }

            /*
            
            if(checkPlayerDistanceFromCenter())
            {
                // moving world logic
                // player.setMovable(false);
                moveObjectsWithWorld();
            }
            */

                
        }
    }

    public void moveObjectsWithWorld()
    {
        for (GameObject obj : objectList)
        {   
        
            // Move objects
            // Move everything horizontally
            if(!atMapBorderY())
            {
                obj.move(0, -player.getVelocity().y);    
            } 
            if(!atMapBorderX())
            {
                obj.move(-player.getVelocity().x, 0);    
            } 
            obj.interpolate(1);
        }
    }

    public void moveObjectsWithWorldY()
    {
        for (GameObject obj : objectList)
        {   
        
            // Move objects
            // Move everything horizontally
            if(!atMapBorderY())
            {
                obj.move(0, -player.getVelocity().y);    
            } 
            obj.interpolate(1);
        }
    }


    public void moveObjectsWithWorldX()
    {
        for (GameObject obj : objectList)
        {   
            if(!atMapBorderX())
            {
                obj.move(-player.getVelocity().x, 0);    
            } 
            obj.interpolate(1);
        }
    }

    // Method to auto close the worldrenderer
    public void closeWorld()
    {
        for(GameObject obj : this.objectList)
        {
            this.objectList.remove(obj);
        }

        this.player = null;
        this.map = null;

    }

    // Setters getters
    public ArrayList<GameObject> getObjectList()
    {
        return  this.objectList;
    }

    // Sets the last object of the list to be the player
    public void setPlayer(Player p)
    {
        this.player = p;
        p.setWorldRenderer(this);

        // Checks if there is a map
        if(this.objectList.get(0) == null)
        {
            System.out.println("There is no map yet, cannot add objects");
            return;
        }


        if(objectList.size() == 1)
        {
            objectList.add(1, p);    
        }  else if (objectList.size() > 1)
        {
            objectList.add(objectList.size() - 1, p);
        }
    }

    // Sets the first object of the world into a map
    public void setMap(Map m)
    {
        this.map = m;
        this.objectList.add(0, m);
        System.out.println("Set map scene");
    }

    public boolean getDebug()
    {
        return this.debugMode;
    }
    
    public Vector2 getCenterPosition()
    {
        return this.centerPosition;
    }    

    public void setCenterPosition(Vector2 v)
    {
        this.centerPosition = v;
    }    

    public Player getPlayer()
    {
        return this.worldPlayer;
    }

    public int getDistanceFromCenter()
    {
        return this.distanceFromCenter;
    }
    
    public boolean atMapBorderY()
    {
        boolean b = false;
        
        // Checks whether the top of the map is at the edge of the top of the screen
        if(player.getVelocity().y < 0)
        {
            if((int)map.getPosition().y + (int)(map.getScaledHeight() / 2) < gamePanel.getHeight()) 
            {
                b = true;
            }
        } else
        if(player.getVelocity().y > 0)
        {
            if((int)map.getPosition().y - (int)(map.getScaledHeight() / 2) > 0) 
            {
                b = true;
            }
        }
 


        return b;
    }

    public boolean atMapBorderX()
    {
        boolean b = false;
        
        if(player.getVelocity().x < 0)
        {
            if((int)map.getPosition().x - (int)(map.getScaledWidth() / 2) > 0) 
            {
                b = true;
            }
        }
        else if(player.getVelocity().x > 0)
        {
            if((int)map.getPosition().x + (int)(map.getScaledWidth() / 2) <  gamePanel.getWidth()) 
            {
                b = true;

            }
        }
 

        return b;
    }

    public void resetWorld(Map map, Player player)
{
    objectList.clear();

    this.map = map;


    this.player = player;


    this.setMap(map);
    this.setPlayer(player);

    player.setWorldRenderer(this);
}
}
