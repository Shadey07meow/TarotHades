package object;

import collision.*;
import scenes.*;
import systems.*;

public class PurpleWisp extends Enemy
{
    private final int maxHealth = 5;
    private final double currentSpeed = 5;    

    public PurpleWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.setImage(img.purpleRIGHT);
        this.moveRightImg = img.purpleRIGHT;
        this.moveLeftImg = img.purpleLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
    }

}
