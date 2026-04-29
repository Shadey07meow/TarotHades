package object;

import collision.*;
import scenes.*;
import systems.*;

public class BlueWisp extends Enemy
{
    private final int maxHealth = 5;
    private final double currentSpeed = 5;    

    public BlueWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.setImage(img.blueRIGHT);
        this.moveRightImg = img.blueRIGHT;
        this.moveLeftImg = img.blueLEFT;
        this.usesProjectiles = false;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
    }

}
