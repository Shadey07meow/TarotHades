package object;

import collision.*;
import scenes.*;
import systems.*;

public class YellowWisp extends Enemy
{
    private final int maxHealth = 18;
    private final int maxHealthBoss = 100;
    private final double currentSpeed = 7;    

    public YellowWisp(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.health = maxHealth;
        this.speed = currentSpeed;

        normalYellow();
        
    }

    public void spawnBossYellow(){
        YellowWisp bossYellow  = new YellowWisp(new Vector2(this.position.x + 100, this.position.y), 4, this.playScrn);
        bossYellow.makeBossYellow();
        world.addObject(bossYellow);

    }
    private void normalYellow(){
        this.setImage(img.yellowRIGHT);
        this.moveRightImg = img.yellowRIGHT;
        this.moveLeftImg = img.yellowLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));

    }
    private void makeBossYellow(){
        this.health = maxHealthBoss;
        this.speed = 10;
        this.detectionRange = 800;
        this.fireCooldown = 0.2 * 1000;
        this.setImage(img.yellowRIGHT);
        this.moveRightImg = img.yellowRIGHT;
        this.moveLeftImg = img.yellowLEFT;
        this.hurtImg = img.enemyHurt;
        this.damage = 1;

        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));

    }

}
