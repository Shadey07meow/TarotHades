package object;

import systems.*;

public class BlueWisp extends Enemy
{
    private final int maxHealth = 10;
    private final double currentSpeed = 30;    

    public BlueWisp(Vector2 position, double scale)
    {
        super(position, scale);
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.image = img.blueRIGHT;
    }

}
