package collision;


import object.*;
import systems.*;

public class RectangleCollider extends CollisionObject {
    // A collision object with 4 points representing it's size
    
    // Bounds class here in Rectangle collider
    public class Bounds
    {
        // Made bounds class for easy access of 4 points
        public Vector2 TOP, BOTTOM, LEFT, RIGHT;
        public Bounds (Vector2 point1, Vector2 point2, Vector2 point3, Vector2 point4){
            this.TOP = point1;
            this.BOTTOM = point2;
            this.LEFT = point3;
            this.RIGHT = point4;
        }
    }

    
    // Bounds set for the collision object
    private Bounds localBounds;

    // Prescence of said bounds in the collision object
    private Bounds globalBounds;

    public RectangleCollider(GameObject object, boolean movable) 
    {
        // Separate GameObject
        super(object, movable);
        Vector2 halfScales = new Vector2(object.getScaledHeight() / 2, object.getScaledWidth() / 2);
        
        // Points for each one  
        Vector2 point1 = new Vector2(-halfScales.x, halfScales.y);
        Vector2 point2 = new Vector2(halfScales.x, halfScales.y);
        Vector2 point3 = new Vector2(-halfScales.x, -halfScales.y);
        Vector2 point4 = new Vector2(halfScales.x, -halfScales.y);

        this.localBounds = new Bounds(point1, point2, point3, point4);
        updateBounds();

    }
    
    public void updateBounds()
    {
        Vector2 newT = Vector2.add(this.localBounds.TOP, this.connectedGameObject.getPosition());
        Vector2 newB = Vector2.add(this.localBounds.BOTTOM, this.connectedGameObject.getPosition());
        Vector2 newL = Vector2.add(this.localBounds.LEFT, this.connectedGameObject.getPosition());
        Vector2 newR = Vector2.add(this.localBounds.RIGHT, this.connectedGameObject.getPosition());

        this.globalBounds = new Bounds(newT, newB, newL, newR);
    }


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
        
        return false;
    }
    
}
