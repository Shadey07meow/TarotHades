package collision;

import java.awt.Color;

import object.*;
import scenes.*;
import systems.*;

public class CollisionObject {
  
    public class Bounds
    {
        // Made bounds class for easy access of 4 points
        public Vector2 NW, NE, SW, SE;
        public Bounds (Vector2 point1, Vector2 point2, Vector2 point3, Vector2 point4){
            this.NW = point1;
            this.NE = point2;
            this.SW = point3;
            this.SE = point4;
        }
    }
    
    private boolean isMovable;
    private Vector2 position;
    private Bounds localBounds;
    private Bounds globalBounds;
    private GameObject connectedGameObject;
    
    public CollisionObject(GameObject object, boolean movable) 
    {
        // Separate GameObject
        this.position = object.getPosition();
        Vector2 halfScales = new Vector2(object.getScaledHeight() / 2, object.getScaledWidth() / 2);
        
        // Points for each one  
        Vector2 point1 = new Vector2(-halfScales.x, halfScales.y);
        Vector2 point2 = new Vector2(halfScales.x, halfScales.y);
        Vector2 point3 = new Vector2(-halfScales.x, -halfScales.y);
        Vector2 point4 = new Vector2(halfScales.x, -halfScales.y);

        this.localBounds = new Bounds(point1, point2, point3, point4);
        
        this.connectedGameObject = object;
    }

    // Getters
    public boolean getIsMovable() 
    {return isMovable;}

    public Bounds getBounds() 
    {return this.globalBounds;}    
    
    public GameObject getGameObject() 
    {return this.connectedGameObject;}


    // Basic AABB collision check
    public boolean isColliding(CollisionObject comparedObject) 
    {   
        /*
        Is basically the Bounds class, is already created
        
        int thisLeft   = getX() - getScaledWidth() / 2;
        int thisRight  = getX() + getScaledWidth() / 2;
        int thisTop    = getY() - getScaledHeight() / 2;
        int thisBottom = getY() + getScaledHeight() / 2;
        
        

        int otherLeft   = other.getX() - other.getScaledWidth() / 2;
        int otherRight  = other.getX() + other.getScaledWidth() / 2;
        int otherTop    = other.getY() - other.getScaledHeight() / 2;
        int otherBottom = other.getY() + other.getScaledHeight() / 2;
        
        */

        // Method compares this specific globalBounds for collision object to another globalBounds collision object
        boolean isColliding = false;
        
        
        
        
        return false;
    }
}
