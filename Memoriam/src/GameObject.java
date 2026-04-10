import java.awt.Color;
import java.awt.Image;

public class GameObject {

    /// What is a GameObject?
    /// A gameObject is anything that has a position in the world
    /// It has both an X and Y coordinate
    /// It also has a render position for interpolation (smooth movement)

    private Vector2 position;
    private int scale;
    private Color color;
    private Image image;
    private CollisionObject collider;

    // for interpolation (prev positions)
    private double renderX;
    private double renderY;

    public GameObject()
    {
        this.position.x = 0;
        this.position.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = 0;
        this.renderY = 0;
    }

    public GameObject(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = x;
        this.renderY = y;
    }

    public GameObject(int x, int y, int s)
    {
        this.position.x = x;
        this.position.y = y;
        this.scale = s;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = x;
        this.renderY = y;
    }

    public GameObject(GameObject spawnPoint)
    {
        this.position.x = 0;
        this.position.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = 0;
        this.renderY = 0;
    }

    // Setters
    public void setX(int x) { this.position.x = x; }
    public void setY(int y) { this.position.y = y; }
    public void setScale(int s) { this.scale = s; }
    public void setColor(Color c) { this.color = c; }
    public void setImage(Image i) { this.image = i; }

    // Getters
    public int getX() { return this.position.x; }
    public int getY() { return this.position.y; }
    public Vector2 getPosition() { return this.position; }
    public int getScale() { return this.scale; }
    public Color getColor() { return this.color; }
    public Image getImage() { return this.image; }

    // Position
    public void setPosition(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;
    }

    // Movement (logic position)
    public void move(int x, int y)
    {
        this.position.x += x;
        this.position.y -= y;
    }

    public int getScaledWidth()
    {
        if (image == null) return scale;
        return image.getWidth(null) * scale;
    }

    public int getScaledHeight()
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

    // Overridable update method, on default it interpolates the object
    public void update()
    {
        interpolate(1);
    }

    // Collider
    public void setCollider(CollisionObject col)
    {
        this.collider = col;
    }

    // Render getters
    public double getRenderX() { return renderX; }
    public double getRenderY() { return renderY; }
}