package systems;


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
