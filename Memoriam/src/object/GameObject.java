package object;

import collision.*;
import java.awt.Color;
import java.awt.Image;
import systems.*;
import scenes.*;
import collision.*;
import object.*;
import java.util.ArrayList;

public class GameObject {

    /// What is a GameObject?
    /// A gameObject is anything that has a position in the world
    /// It has both an X and Y coordinate
    /// It also has a render position for interpolation (smooth movement)

    protected Vector2 position = new Vector2(0, 0);
    protected double  scale;
    protected Color color;
    protected Image image;
    protected CollisionObject collider;
    protected WorldRenderer world;
    protected PlayableScreen playScrn;


    // for interpolation (prev positions)
    protected double renderX;
    protected double renderY;

    public GameObject(PlayableScreen scrn)
    {
        this.position.x = 0;
        this.position.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = 0;
        this.renderY = 0;

        this.playScrn = scrn;
    }

    public GameObject(double x, double y, PlayableScreen scrn)
    {
        this.position.x = x;
        this.position.y = y;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = x;
        this.renderY = y;

        this.playScrn = scrn;
    }

    public GameObject(Vector2  v, double s, PlayableScreen scrn)
    {
        this.position.x = v.x;
        this.position.y = v.y;
        this.scale = s;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = v.x;
        this.renderY = v.y;

        this.playScrn = scrn;
    }

    public GameObject(Vector2  v, PlayableScreen scrn)
    {
        this.position.x = v.x;
        this.position.y = v.y;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = v.x;
        this.renderY = v.y;

        this.playScrn = scrn;
    }

    public GameObject(double x, double y, double s, PlayableScreen scrn)
    {
        this.position.x = x;
        this.position.y = y;
        this.scale = s;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = x;
        this.renderY = y;

        this.playScrn = scrn;
    }

    public GameObject(GameObject spawnPoint, PlayableScreen scrn)
    {
        this.position.x = 0;
        this.position.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = 0;
        this.renderY = 0;

        this.playScrn = scrn;
    }

    // Setters
    public void setX(int x) { this.position.x = x; }
    public void setY(int y) { this.position.y = y; }
    public void setScale(int s) { this.scale = s; }
    public void setColor(Color c) { this.color = c; }
    public void setImage(Image i) { this.image = i; }
    public void setWorld(WorldRenderer objs){ this.world = objs;}
    
    // Getters
    public double getX() { return this.position.x; }
    public double getY() { return this.position.y; }
    public Vector2 getPosition() { return this.position; }
    public double  getScale() { return this.scale; }
    public Color getColor() { return this.color; }
    public Image getImage() { return this.image; }
    public WorldRenderer getWorld(){ return this.world;}

    // Position
    public void setPosition(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;
    }

    // Movement (logic position)
    public void move(double x, double y)
    {
        this.position.x += x;
        this.position.y -= y;
    }

    public void move(Vector2 a)
    {
        this.position.x += a.x;
        this.position.y -= a.y;
    }

    public double getScaledWidth()
    {
        if (image == null) return scale;
        return image.getWidth(null) * scale;
    }

    public double getScaledHeight()
    {
        if (image == null) return scale;
        return image.getHeight(null) * scale;
    }

    /// INTERPOLATION (smooth rendering)
    public void interpolate(double alpha)
    {
        renderX += (position.x - renderX) * alpha;
        renderY += (position.y - renderY) * alpha;

        /// basic logic goes as follows
        /// calculate how far u are from ur taget position
        /// alpha = moves at a fraction of a distant towards the target
    }


    // Method called at panel  start
    //public void  start()
    //{

    //}

    // Overridable update method, on default it interpolates the object
    public void update()
    {
        // Check colliders if present
        if(this.collider != null && this.world != null)
        {
            this.collider.checkCollisions();
        }
        
        interpolate(1);
    }

    // Collider
    public void setCollider(CollisionObject col)
    {
        if(col == null) return;
        this.collider = col;
    }

    public CollisionObject getCollider()
    {   if(this.collider != null)return this.collider; else return null;}

    // Render getters
    public double getRenderX() { return renderX; }
    public double getRenderY() { return renderY; }
}