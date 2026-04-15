package collision;

import object.*;
import systems.*;

public abstract class CollisionObject {
  

    protected boolean isMovable;
    protected Vector2 position;
    protected GameObject connectedGameObject;
    
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

    


    // Collision check
    public abstract boolean isColliding(CollisionObject comparedObject);
}
