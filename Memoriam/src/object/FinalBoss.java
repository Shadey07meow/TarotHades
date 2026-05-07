package object;

import collision.*;
import scenes.*;
import systems.*;


public class FinalBoss extends Enemy{
    private final int maxHealth = 100;
    private final double currentSpeed = 2;    

    public FinalBoss(Vector2 position, double scale, PlayableScreen scrn)
    {
        super(position, scale, scrn);
        this.fireCooldown = 0.2 * 1000;
        this.health = maxHealth;
        this.speed = currentSpeed;
        this.setImage(img.finalBoss);
        this.moveRightImg = img.finalBoss;
        this.moveLeftImg = img.finalBoss;
        this.damage = 1;
        this.setCollider(new RectangleCollider(this, true, 32, 32,32, 32));
    }

    @Override
    public void onDeath()
    {
        // Remove sellf first, then pass the level
        super.onDeath();
        passLevel();
    }

    // Passing level logic
    private void passLevel()
    {
        this.playScrn.getGameFrame().showPanel("menu");
    }

}
