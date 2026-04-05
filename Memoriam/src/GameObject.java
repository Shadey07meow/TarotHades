import java.awt.Color;
import java.awt.Image;

public class GameObject {
    /// What is a GameObject?
    /// A gameObject is anything that has a position in the world
    /// It has both an X and Y coordinate
    /// To make things easier for us in case we have to scale any objects or items to a certain size
    /// There will also be a scale
    
    private int x;
    private int y;
    private int scale;
    private Color color;
    private Image image;
    
    public GameObject()
    {
        // Default constructor, whenever a new gameObject is created you spawn it at World Origin
        this.x = 0;
        this.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;
    }

    public GameObject(int x, int  y)
    {
        // Constructor 2, whenever a new gameObject is created, you spawn it at given coordinates
        this.x = x;
        this.y = y;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;
    }

    public GameObject(int x, int  y, int  s)
    {
        // Constructor 3, whenever a new gameObject is created, you spawn it at given coordinates
        this.x = x;
        this.y = y;
        this.scale = s;
        this.color = Color.BLACK;
        this.image = null;
    }

    
    public GameObject(GameObject spawnPoint)
    {
        // Constructor 4, whenever a new gameObject is created, you spawn it at it's given spawnpoint
        this.x = 0;
        this.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;
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
    
    public void setScale(int s) 
    {
        this.scale = s;
    }

    public void setColor(Color c) 
    {
        this.color = c;
    }

    
    public void setImage(Image i) 
    {
        this.image = i;
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

    
    public Color getColor() 
    {
        return this.color;
    }

    public Image getImage( ) 
    {
        return this.image;
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
        // Moves the object relative to it's current position in terms of 1 second
        this.x += (x);
        this.y += (x);
    }

    
}
