package object;

import systems.*;
import scenes.*;
import collision.*;

public class BlueWisp extends Enemy
{
    private final int maxHealth = 5;
    private final double currentSpeed = 30;    

    public BlueWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.image = img.blueRIGHT;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
    }

}
