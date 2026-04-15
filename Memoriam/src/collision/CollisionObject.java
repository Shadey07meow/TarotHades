package collision;

import object.*;
import systems.*;

import java.util.ArrayList;

public abstract class CollisionObject {
  

    protected boolean isMovable;
    protected Vector2 position;
    protected GameObject connectedGameObject;

    protected ArrayList<CollisionObject> collidingWith = new ArrayList<>();
    
    public CollisionObject(GameObject object, boolean movable) 
    {
        // Separate GameObject
        this.position = object.getPosition();        
        this.connectedGameObject = object;
    }

    // Getters
    public boolean getIsMovable() 
    {return isMovable;}   
    
    public GameObject getGameObject() 
    {return this.connectedGameObject;}

    public ArrayList<CollisionObject> getCollidingWith() 
    {return collidingWith;}

    


    // Collision check
    public abstract boolean isColliding(CollisionObject comparedObject);
}
