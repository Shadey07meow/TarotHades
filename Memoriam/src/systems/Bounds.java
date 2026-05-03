package systems;


public class Bounds
{
    // Made bounds class for easy access of 4 points
    public int  TOP, BOTTOM, LEFT, RIGHT;
    public Bounds (int point1, int point2, int point3, int point4){
        this.TOP = point1;
        this.BOTTOM = point2;
        this.LEFT = point3;
        this.RIGHT = point4;
    }

    public int  getLength()
    {
        return (this.TOP + this.BOTTOM);
    }

    public int getWidth()
    {
        return (this.RIGHT + this.LEFT);
    }
}
