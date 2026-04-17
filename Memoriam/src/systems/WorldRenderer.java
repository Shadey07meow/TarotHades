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

    // Object list MUST include player
    private ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    private Player worldPlayer = null;
    private Vector2 centerPosition = new Vector2(950, 420);
    private int distanceFromCenter = 20 * 4 ;

    private boolean debugMode = false;

    // Constructors
    public WorldRenderer(Player player)
    {
        this.player = player;
        this.map = new Map(null, player.getPosition(), 1);

        this.setMap(this.map);
        this.setPlayer(this.player);
    }

    public WorldRenderer(Player player, Map  m)
    {
        this.player = player;
        this.map = m;
        this.setMap(this.map);
        this.setPlayer(this.player);
    }

    public WorldRenderer()
    {
        this.player = null;
        this.map = null;
    }

    // Add game object to the game world
    public void addObject(GameObject obj)
    {
        // Checks if there is a map
        if(this.objectList.get(0) == null)
        {
            System.out.println("There is no map yet, cannot add objects");
            return;
        }

        if(obj == (GameObject)this.player || obj == (GameObject)this.map )
        {
            System.out.println("There can only be one instance of this object in this class");
            return;    
        }

        this.objectList.add(1, obj);
        System.out.println("Added an object to the scene");
    }


    // Needs a center point, is already set in the beginning of the java file
    // If distance of the player from the center point is greater than some amount, then move the world instead of the player
    public boolean checkPlayerDistanceFromCenter()
    {
        return (Vector2.distance(centerPosition, player.getPosition()) > distanceFromCenter);
    }

    // Update world position
    public void updateWorld()
    {

        // Update player first
        if(this.player != null)
        {
            for (int x = 0; x < this.objectList.size(); x++)
            {  
                // Updates everything but the player
                if(this.objectList.get(x) != (GameObject)this.player)
                {
                    this.objectList.get(x).update();
                } else
                {
                    ///// Updates the player
                    this.player.update();
                    if (debugMode)
                    {
                        ///System.out.println(checkPlayerDistanceFromCenter());
                        ///System.out.println(Vector2.distance(centerPosition, player.getPosition()));
                    }
                }
            } 

            if(checkPlayerDistanceFromCenter())
                    {
                        // moving world logic
                        player.setMovable(false);
                        moveObjectsWithWorld();
                    }
                    else
                    {
                        // Not moving world logic
                        player.setMovable(true);
                }

                
        }
    }

    public void moveObjectsWithWorld()
    {
        boolean p = false;
        int count = 0;
        for (GameObject obj : objectList)
        {   
            if(count == objectList.size() - 1) p = true;
            
            if(p == false)
            {
                // Move objects
                obj.move(-player.getVelocity().x, -player.getVelocity().y);
                obj.interpolate(1);
                count++;
                
            } else
            {
                // Move player
                obj.move(-player.getVelocity().x, -player.getVelocity().y);

            }
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
    
    
}
