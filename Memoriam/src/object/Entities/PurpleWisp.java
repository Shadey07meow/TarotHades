package object.Entities;

import collision.*;
import scenes.templates.PlayableScreen;
import systems.*;

public class PurpleWisp extends Enemy
{
    private final int maxHealth = 18;
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
    public void makeBossPurple(){
        this.health = maxHealthBoss;
        this.speed = 10;
        this.detectionRange = 800;
        this.fireCooldown = 0.2 * 1000;
        this.setImage(img.purpleRIGHT);
        this.moveRightImg = img.purpleRIGHT;
        this.moveLeftImg = img.purpleLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 2;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));

    }
}
