package object;

import collision.*;
import scenes.*;
import systems.*;

public class YellowWisp extends Enemy
{
    private final int maxHealth = 5;
    private final double currentSpeed = 7;    

    public YellowWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.setImage(img.yellowRIGHT);
        this.moveRightImg = img.yellowRIGHT;
        this.moveLeftImg = img.yellowLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
    }

}
