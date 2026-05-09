package object;

import collision.*;
import scenes.*;
import systems.*;

public class PurpleWisp extends Enemy
{
    private final int maxHealth = 20;
    private final int maxHealthBoss = 100;
    private final double currentSpeed = 5;    

    public PurpleWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;

        normalPurple();
        
    }

    public void spawnBossPurple(){
        PurpleWisp bossPurple  = new PurpleWisp(new Vector2(this.position.x + 100, this.position.y), 4, this.playScrn);
        bossPurple.makeBossPurple();
        world.addObject(bossPurple);
        
    }
    private void normalPurple(){
        this.setImage(img.purpleRIGHT);
        this.moveRightImg = img.purpleRIGHT;
        this.moveLeftImg = img.purpleLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));

    }
    private void makeBossPurple(){
        this.health = maxHealthBoss;
        this.speed = 6;
        this.setImage(img.purpleRIGHT);
        this.moveRightImg = img.purpleRIGHT;
        this.moveLeftImg = img.purpleLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));

    }
}
