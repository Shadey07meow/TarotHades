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
    private Player player;

    // Object list MUST include player
    private ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    private Player worldPlayer = null;
    private Vector2 centerPosition = new Vector2(950, 420);
    private int distanceFromCenter = 20 * 4 ;

    private boolean debugMode = true;

    // Constructor
    public WorldRenderer(Player player)
    {
        this.player = player;
        this.setPlayer(this.player);
    }

    public WorldRenderer()
    {
        this.player = null;
    }

    // Add game object to the game world
    public void addObject(GameObject obj)
    {
        this.objectList.add(0, obj);
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
        this.player.update();
        
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

        if (debugMode)
        {
            ///System.out.println(checkPlayerDistanceFromCenter());
            ///System.out.println(Vector2.distance(centerPosition, player.getPosition()));
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
                Vector2 inpVec = Vector2.normalized(player.getVelocity()); 
                obj.move(-player.getVelocity().x, -player.getVelocity().y);

            }
        }
    }



    
    

    // Setters getters
    public ArrayList<GameObject> getObjectList()
    {
        return  this.objectList;
    }

    private void setPlayer(Player p)
    {
        this.player = p;

        if(objectList.size() - 1 > 0)
        {
            objectList.add(objectList.size() - 1, p);    
        } else
        {
            objectList.add(0, p);
        }
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
