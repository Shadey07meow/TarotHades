package object;

import collision.*;
import scenes.*;
import systems.*;

public class BlueWisp extends Enemy
{
    private final int maxHealth = 18;
    private final int maxHealthBoss = 100;
    private final double currentSpeed = 5;    

    public BlueWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;
        
        normalBlue();
    }

    public void spawnBossBlue(){
        BlueWisp bossBlue = new BlueWisp(new Vector2(this.position.x + 100, this.position.y), 4, this.playScrn);
        bossBlue.makeBossBlue();
        world.addObject(bossBlue);
    }

    private void normalBlue()
    {
        this.setImage(img.blueRIGHT);
        this.moveRightImg = img.blueRIGHT;
        this.moveLeftImg = img.blueLEFT;
        this.hurtImg = img.enemyHurt;
        this.usesProjectiles = false;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
        
    }

    private void makeBossBlue(){
        this.health = maxHealthBoss;
        this.speed = 10;
        this.detectionRange = 800;
        this.fireCooldown = 0.2 * 1000;
        this.setImage(img.blueRIGHT);
        this.moveRightImg = img.blueRIGHT;
        this.moveLeftImg = img.blueLEFT;
        this.hurtImg = img.enemyHurt;
        this.usesProjectiles = false;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));

    }

}
