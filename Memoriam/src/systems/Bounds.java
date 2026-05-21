package systems;

import scenes.ui.GameFrame;

public class Bounds
{
    // Made bounds class for easy access of 4 points
    public double  TOP, BOTTOM, LEFT, RIGHT;
    public Bounds (int point1, int point2, int point3, int point4){
        this.TOP = (float)point1 * GameFrame.getScreenMultiplier();
        this.BOTTOM =(float) point2 * GameFrame.getScreenMultiplier();
        this.LEFT = (float)point3 * GameFrame.getScreenMultiplier(); 
        this.RIGHT = (float)point4 * GameFrame.getScreenMultiplier();
    }

    public double  getLength()
    {
        return (this.TOP + this.BOTTOM);
    }

    public double getWidth()
    {
        return (this.RIGHT + this.LEFT);
    }
}
