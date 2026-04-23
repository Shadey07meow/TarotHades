package collision;

import object.*;
import systems.*;

import java.util.ArrayList;
import java.awt.Color;

public abstract class CollisionObject {
  
    protected boolean isMovable = true;
    protected Vector2 position;
    protected GameObject connectedGameObject;
    
    protected boolean isColliding = false;
    public Color curColor = Color.black;
    public Color activeColor = Color.green;
    public Color inactiveColor = Color.gray;

    protected ArrayList<CollisionObject> collidingWith = new ArrayList<>();
    
    public CollisionObject(GameObject object, boolean movable) 
    {
        // Separate GameObject
        this.position = object.getPosition();        
        this.connectedGameObject = object;
    }

    // Getters
    public boolean getIsMovable() 
    {return this.isMovable;}   
    
    public GameObject getGameObject() 
    {return this.connectedGameObject;}    
    
    public boolean getIsColliding() 
    {return this.isColliding;}

    // Setters
    public void setIsMovable(boolean b) 
    {this.isMovable = b;}   

    // Connected to the update function in gameObjects
    public abstract  void checkCollisions();


    public ArrayList<CollisionObject> getCollidingWith() 
    {return collidingWith;}

    // Collision check
    public abstract boolean isColliding(GameObject comparedObject);

    public abstract String toString();
}
