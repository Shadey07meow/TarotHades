import java.awt.Color;

public class CollisionObject extends GameObject {
    private boolean isMovable;

    public CollisionObject(int x, int y, int scale, boolean isMovable) 
    {
        super(x, y, scale);
        this.isMovable = isMovable;
        setColor(Color.RED);
    }

    public boolean getIsMovable() 
    {
        return isMovable;
    }

    // Basic AABB collision check
    public boolean isColliding(GameObject other) 
    {
        int thisLeft   = getX() - getScaledWidth() / 2;
        int thisRight  = getX() + getScaledWidth() / 2;
        int thisTop    = getY() - getScaledHeight() / 2;
        int thisBottom = getY() + getScaledHeight() / 2;

        int otherLeft   = other.getX() - other.getScaledWidth() / 2;
        int otherRight  = other.getX() + other.getScaledWidth() / 2;
        int otherTop    = other.getY() - other.getScaledHeight() / 2;
        int otherBottom = other.getY() + other.getScaledHeight() / 2;

        return thisRight > otherLeft &&
            thisLeft < otherRight &&
            thisBottom > otherTop &&
            thisTop < otherBottom;
    }
}
