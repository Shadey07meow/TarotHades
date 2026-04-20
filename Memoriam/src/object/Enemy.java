package object;

import systems.*;

public abstract class Enemy extends Entity {

    protected int detectionRange = 100;

    public Enemy(Vector2 p, double s, int health, double  speed)
    {
        super(p, s);
        this.health = health;
        this.speed = speed;
    }
    
}
