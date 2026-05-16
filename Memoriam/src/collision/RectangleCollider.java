package collision;


import java.util.ArrayList;

import object.statics.GameObject;
import systems.*;

public class RectangleCollider extends CollisionObject {
    // A collision object with 4 points representing it's size
    
    // Bounds class here in Rectangle collider


    
    // Bounds set for the collision object
    private final Bounds localBounds;

    // Prescence of said bounds in the collision object
    private Bounds globalBounds;

    public RectangleCollider(GameObject object, boolean movable) 
    {
        // Separate GameObject
        super(object, movable);
        System.out.println("Created a rectangle collider");

        Vector2 halfScales = new Vector2(object.getScaledHeight() / 2, object.getScaledWidth() / 2);
        
        this.localBounds = new Bounds((int)halfScales.y, (int)-halfScales.y, (int)-halfScales.x, (int)halfScales.x);

    }

    public RectangleCollider(GameObject object, boolean movable, int top, int bottom, int right, int left) 
    {
        // Separate GameObject
        super(object, movable);
        
        ///System.out.println("Created a rectangle collider");
        this.localBounds = new Bounds(top, bottom, left, right);
    }

    public RectangleCollider(GameObject object, boolean movable, Bounds b) 
    {
        // Separate GameObject
        super(object, movable);
        
        ///System.out.println("Created a rectangle collider");
        this.localBounds = b;

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
        int newT = this.localBounds.TOP + (int)this.connectedGameObject.getPosition().y;
        int newB = -this.localBounds.BOTTOM + (int)this.connectedGameObject.getPosition().y;
        int newL = -this.localBounds.LEFT + (int)this.connectedGameObject.getPosition().x;
        int newR = this.localBounds.RIGHT + (int)this.connectedGameObject.getPosition().x;

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

            boolean yChecks = (this.globalBounds.TOP < other.globalBounds.BOTTOM) || (other.globalBounds.TOP < this.globalBounds.BOTTOM) ;
            boolean xChecks = (this.globalBounds.RIGHT < other.globalBounds.LEFT) || (other.globalBounds.RIGHT < this.globalBounds.LEFT) ;

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

    public Bounds getGlobalBounds()
    {
        return this.globalBounds;
    }
    
    public String toString()
    {
        return "Rectangle Colllider";
    }

}
