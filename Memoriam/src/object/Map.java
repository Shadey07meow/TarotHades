package object;



import java.awt.Image;
import systems.*;
import scenes.*;

public class Map extends GameObject {
    
    // A map object is just a game object that is a map, this will be used to differentiate backgrounds and actual gameObjects
    private int TOP;
    private int BOTTOM;
    private int LEFT;
    private int RIGHT;

    // Will contain multiple colliders, spawn points and such

    public Map(Image mapImg, Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        super.setImage(mapImg);
        setBounds();
    }

    public void setBounds()
    {
        this.TOP = (int)this.position.y - (int)(scale / 2);
        this.BOTTOM = (int)this.position.y + (int)(scale / 2);
        this.RIGHT = (int)this.position.x + (int)(scale / 2);
        this.LEFT = (int)this.position.x - (int)(scale / 2);
    }

    @Override
    public void update()
    {
        setBounds();
    }
}
