package object;

import images.*;
import systems.*;
import scenes.*;
public abstract class Enemy extends Entity {

    protected int detectionRange = 100;
    protected ImageLibrary img = ImageLibrary.get();

    public Enemy(Vector2 p, double s, PlayableScreen scrn)
    {
        super(p, s, scrn);
    }

    public void damage(int i)
    {
        minusHP(i);

        if(getHP() <= 0)
        {
            die();
        }
    }

    @Override
    public void die()
    {
        world.removeObject(this);
    }

    
}
