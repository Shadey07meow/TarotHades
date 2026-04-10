import java.awt.Color;

public class CollisionObject {
    public class Bounds
    {
        // Made bounds class for easy access of 4 points
        public int p1, p2, p3, p4;
        public Bounds (Vector2 v1, Vector2 v2){
            this.p1 = v1.x;
            this.p2 = v1.y;
            this.p3 = v2.x;
            this.p4 = v2.y;
        }
    }
    
    private boolean isMovable;
    private Vector2 position;
    private Bounds localBounds;
    private Bounds globalBounds;
    
    public CollisionObject(GameObject object, boolean movable) 
    {
        // Separate GameObject
        this.position = object.getPosition();
        this.localBounds = new Bounds( -object.getScaledWidth() / 2, );++
        
        
    }

    public boolean getIsMovable() 
    {
        return isMovable;
    }

    // Basic AABB collision check
    public boolean isColliding(GameObject other) 
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
        return thisRight > otherLeft &&
            thisLeft < otherRight &&
            thisBottom > otherTop &&
            thisTop < otherBottom;
    }
}
