package object;

import images.*;
import systems.*;

public abstract class Enemy extends Entity {

    protected int detectionRange = 100;
    protected ImageLibrary img = ImageLibrary.get();

    public Enemy(Vector2 p, double s)
    {
        super(p, s);
    }
    
}
