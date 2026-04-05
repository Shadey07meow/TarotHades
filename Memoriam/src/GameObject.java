import java.awt.Color;
import java.awt.Image;

public class GameObject {

    /// What is a GameObject?
    /// A gameObject is anything that has a position in the world
    /// It has both an X and Y coordinate
    /// It also has a render position for interpolation (smooth movement)

    private int x;
    private int y;
    private int scale;
    private Color color;
    private Image image;

    // for interpolation (prev positions)
    private double renderX;
    private double renderY;

    public GameObject()
    {
        this.x = 0;
        this.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = 0;
        this.renderY = 0;
    }

    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = x;
        this.renderY = y;
    }

    public GameObject(int x, int y, int s)
    {
        this.x = x;
        this.y = y;
        this.scale = s;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = x;
        this.renderY = y;
    }

    public GameObject(GameObject spawnPoint)
    {
        this.x = 0;
        this.y = 0;
        this.scale = 1;
        this.color = Color.BLACK;
        this.image = null;

        this.renderX = 0;
        this.renderY = 0;
    }

    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setScale(int s) { this.scale = s; }
    public void setColor(Color c) { this.color = c; }
    public void setImage(Image i) { this.image = i; }

    // Getters
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getScale() { return this.scale; }
    public Color getColor() { return this.color; }
    public Image getImage() { return this.image; }

    // Position
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Movement (logic position)
    public void move(int x, int y)
    {
        this.x += x;
        this.y -= y;
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
        renderX += (x - renderX) * alpha;
        renderY += (y - renderY) * alpha;

        /// basic logic goes as follows
        /// calculate how far u are from ur taget position
        /// alpha = moves at a fraction of a distant towards the target
    }

    // Render getters
    public double getRenderX() { return renderX; }
    public double getRenderY() { return renderY; }
}