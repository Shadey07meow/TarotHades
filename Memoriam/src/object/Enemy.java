package object;

import systems.*;
import images.*;

public abstract class Enemy extends Entity {

    protected int detectionRange = 100;
    protected ImageLibrary img = new ImageLibrary();

    public Enemy(Vector2 p, double s)
    {
        super(p, s);
    }
    
}
