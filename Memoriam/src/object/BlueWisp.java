package object;

import systems.*;
import scenes.*;

public class BlueWisp extends Enemy
{
    private final int maxHealth = 10;
    private final double currentSpeed = 30;    

    public BlueWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.image = img.blueRIGHT;
    }

}
