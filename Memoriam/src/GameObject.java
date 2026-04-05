public class GameObject {
    /// What is a GameObject?
    /// A gameObject is anything that has a position in the world
    /// It has both an X and Y coordinate
    /// To make things easier for us in case we have to scale any objects or items to a certain size
    /// There will also be a scale
    
    private int x;
    private int y;
    private int scale;
    
    public GameObject()
    {
        // Default constructor, whenever a new gameObject is created you spawn it at World Origin
        this.x = 0;
        this.y = 0;
        this.scale = 1;
    }

    public GameObject(int x, int  y)
    {
        // Constructor 2, whenever a new gameObject is created, you spawn it at given coordinates
        this.x = 0;
        this.y = 0;
        this.scale = 1;
    }

    
    public GameObject(GameObject spawnPoint)
    {
        // Constructor 3, whenever a new gameObject is created, you spawn it at it's given spawnpoint
        this.x = 0;
        this.y = 0;
        this.scale = 1;
    }

    // Setters

    public void setX(int x) 
    {
        this.x = x;
    }
    
    public void setY(int y) 
    {
        this.y = y;
    }
    
    public void setSccale(int s) 
    {
        this.scale = s;
    }

    // Getters
    public int getX() 
    {
        return this.x;
    }

    public int getY() 
    {
        return this.y;
    }
    
    public int getScale() 
    {
        return this.scale;
    }

    // Stuff
    public void setPosition(int x, int y) 
    {
        // Sets position of the gameObject
        this.x = x;
        this.x = y;
    }
    
    public void move(int x, int y)
    {
        // Moves the object relative to it's current position
        this.x += x;
        this.y += y;
    }
}
