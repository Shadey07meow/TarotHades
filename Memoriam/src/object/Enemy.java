package object;

import images.*;
import systems.*;
import scenes.*;
import java.awt.Image;

public abstract class Enemy extends Entity {

    protected int detectionRange = 500;

    protected ImageLibrary img = ImageLibrary.get();
    protected Player pl;
    protected Vector2 currentSpeed;

    protected Image moveLeftImg = null;
    protected Image moveRightImg = null;


    protected boolean hasDetectedPlayer;

    public Enemy(Vector2 p, double s, PlayableScreen scrn)
    {
        super(p, s, scrn);
        
    }
    

    public void damage(int i)
    {
        minusHP(i);

        if(getHP() <= 0)
        {
            die();
        }
    }

    @Override
    public void die()
    {
        world.removeObject(this);
    }
    
    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void logicMethods()
    {
        if(this.pl == null) 
        {
            this.pl = this.world.getPlayer();
            System.out.println("I am bein called");
            return;
        }

        // Run when undetected
        if(!this.hasDetectedPlayer)
        {
            detectForPlayer();
        } else
        {
            // Move
            updateSpeed();
            moveTowardsPlayer();
            updSprites();
        }
        
        
    }

    private void detectForPlayer()
    {
        if(Math.abs(Vector2.distance(this.getPosition(), pl.getPosition())) < detectionRange)
        {
            this.hasDetectedPlayer = true;
        }
    }

    private void moveTowardsPlayer()
    {
        this.move(currentSpeed);
    }

    private void updateSpeed()
    {
        Vector2 unitVector = Vector2.getUnitVector(this.position, this.pl.getPosition());
        currentSpeed = Vector2.multiply(unitVector, this.speed);
    }

    private void updSprites()
    {
        if(moveLeftImg == null || moveRightImg == null) return; 

        if(currentSpeed.x >= 0) {this.setImage(moveRightImg);} else {this.setImage(moveLeftImg);}
    }


    
}
