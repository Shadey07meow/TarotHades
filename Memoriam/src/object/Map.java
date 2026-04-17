package object;



import java.awt.Image;
import systems.*;

public class Map extends GameObject {
    
    // A map object is just a game object that is a map, this will be used to differentiate backgrounds and actual gameObjects
    public Map(Image mapImg, Vector2 position, double scale)
    {
        super(position, scale);
        super.setImage(mapImg);
    }
}
