package collision;


import java.util.ArrayList;

import jdk.net.UnixDomainPrincipal;
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
        System.out.println("Created a rectangle collider");

        Vector2 halfScales = new Vector2(object.getScaledHeight() / 2, object.getScaledWidth() / 2);
        
        // Points for each one  
        Vector2 point1 = new Vector2(-halfScales.x, halfScales.y);
        Vector2 point2 = new Vector2(halfScales.x, halfScales.y);
        Vector2 point3 = new Vector2(-halfScales.x, -halfScales.y);
        Vector2 point4 = new Vector2(halfScales.x, -halfScales.y);

        this.localBounds = new Bounds(point1, point2, point3, point4);

    }

    public RectangleCollider(GameObject object, boolean movable, int top, int bottom, int right, int left) 
    {
        // Separate GameObject
        super(object, movable);
        
        ///System.out.println("Created a rectangle collider");
        this.localBounds = new Bounds(
                new Vector2(0, top),
                new Vector2(0, - bottom),
                new Vector2(-left, 0),
                new Vector2(right, 0)
            );

    }
    
    @Override
    public void checkCollisions()
    {
        updateBounds();

        // Clear first before updating
        collidingWith.clear();
        this.isColliding = false;
        
        ArrayList<GameObject> objects = this.connectedGameObject.getWorld().getObjectList();

        for(int x = 0; x < objects.size(); x++)
        {
            if(this.connectedGameObject == objects.get(x) ) continue;
            if(objects.get(x).getCollider() == null) continue;

            GameObject obj = this.connectedGameObject.getWorld().getObjectList().get(x);
            if(this.isColliding(obj))
            {
                this.isColliding = true;
                collidingWith.add(obj.getCollider());
                //System.out.println("Collison detected at :" + this.connectedGameObject.getPosition().toString());

            }
        }
        
    }

    public void updateBounds()
    {
        Vector2 newT = Vector2.add(this.localBounds.TOP, this.connectedGameObject.getPosition());
        Vector2 newB = Vector2.add(this.localBounds.BOTTOM, this.connectedGameObject.getPosition());
        Vector2 newL = Vector2.add(this.localBounds.LEFT, this.connectedGameObject.getPosition());
        Vector2 newR = Vector2.add(this.localBounds.RIGHT, this.connectedGameObject.getPosition());

        this.globalBounds = new Bounds(newT, newB, newL, newR);
    }


    @Override
    public boolean isColliding(GameObject comparedObject) 
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

        // Method compares this specific globa lBounds for collision object to another globalBounds collision object
        
        
        
        if(comparedObject.getCollider() instanceof  RectangleCollider other)
        {

            // Collision Detection if Rectangle Collider

            
            this.updateBounds();
            other.updateBounds();

            boolean yChecks = (this.globalBounds.TOP.y < other.globalBounds.BOTTOM.y) || (other.globalBounds.TOP.y < this.globalBounds.BOTTOM.y) ;
            boolean xChecks = (this.globalBounds.RIGHT.x < other.globalBounds.LEFT.x) || (other.globalBounds.RIGHT.x < this.globalBounds.LEFT.x) ;

            boolean finalCheck = xChecks || yChecks;
            return !finalCheck;

        } else{
            
            return false;
        }
        
    }

    public Bounds getLocalBounds()
    {
        return this.localBounds;
    }
    
    public String toString()
    {
        return "Rectangle Colllider";
    }

}
