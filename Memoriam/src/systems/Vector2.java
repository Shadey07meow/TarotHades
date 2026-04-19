package systems;


import java.lang.Math;

public class Vector2 {
    /// A class to quickly store 2 points
    /// Will be able to do quick vector operations
    
    // Does not need setters or getters, they can be manipulated at any point in time
    public double x = 0;
    public double y = 0;

    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2()
    {
        // Making this better
        this.x = 0;
        this.y = 0;
    }

    final static Vector2 UP = new Vector2(0, 1);
    final static Vector2 DOWN = new Vector2(0, -1);
    final static Vector2 RIGHT = new Vector2(1, 0);
    final static Vector2 LEFT = new Vector2(-1, 0);

    public static Vector2 add(Vector2 a, Vector2 b)
    {
        // Add 2 vectors
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 subtract(Vector2 a, Vector2 b)
    {
        // Add 2 vectors
        return new Vector2(a.x - b.x, a.y - b.y);
    }
    public static Vector2 multiply(Vector2 a, int s)
    {
        // Multiply a vector and a scalar number
        return new Vector2(a.x * s, a.y * s);
    }

    public static double distance(Vector2 a, Vector2 b)
    {
        double xDiff  = Math.pow((b.x - a.x), 2);
        double yDiff  = (int)Math.pow((b.y - a.y), 2);
        return Math.sqrt(xDiff + yDiff);
    }

    public String toString()
    {
        String output = "(" + String.valueOf(this.x) + " " + String.valueOf(this.y) + ")" ;
        return output;
    }

    static public Vector2 normalized(Vector2 a)
    {
        int x1  = (a.x >= 0) ?  1 :  -1;
        int y1  = (a.y >= 0) ?  1 :  -1;

        if(a.x == 0) x1 = 0;
        if(a.y == 0) y1 = 0;

        return new Vector2(x1, y1);
    }

}
