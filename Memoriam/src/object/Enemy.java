package object;

import images.*;
import systems.*;
import scenes.*;
import java.awt.Image;

public abstract class Enemy extends Entity {

    protected int detectionRange = 500;
    protected int attackingRange = 250;

    protected ImageLibrary img = ImageLibrary.get();
    protected Player pl;
    protected Vector2 currentSpeed;

    protected Image moveLeftImg = null;
    protected Image moveRightImg = null;


    protected boolean hasDetectedPlayer = false;
    protected boolean withinShootingDistance = false;

    private boolean goesLeft;
    private double trackSpeed;
    private double followSpeed;


    public Enemy(Vector2 p, double s, PlayableScreen scrn)
    {
        super(p, s, scrn);
        randomizeDir();
    }

    private void randomizeDir()
    {
        int r = (int)(Math.random() * 10);
        this.goesLeft = r % 2 == 0; 

        double r2 = Math.random();
        this.trackSpeed = r2 + 0.7;

        double r3 = Math.random();
        this.followSpeed = r3 + 1;
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
            // Using this structure so that if you are detected once, you will be detected forever
            detectForPlayer();
        } else
        {
            // Move
            updateSpeed();

            if(!canShootPlayer())
            {
                moveTowardsPlayer();
            } else{
                if(this.goesLeft)
                {
                    moveLeftOfPlayer();
                } else
                {
                    moveRightOfPlayer();
                }
            }
            updSprites();
        }
        
        
    }

    // Checks if the player should be noticed by the enemy
    protected void  detectForPlayer()
    {
        if(Math.abs(Vector2.distance(this.getPosition(), pl.getPosition())) < detectionRange)
        {
            this.hasDetectedPlayer = true;
        }
    }


    // Detects if the player is within the range to be shot
    protected boolean canShootPlayer()
    {
        if(Math.abs(Vector2.distance(this.getPosition(), pl.getPosition())) < attackingRange)
        {
            return true;
        }
        else return false;
        
    }

    private void moveTowardsPlayer()
    {
        this.move(Vector2.multiply(currentSpeed, this.followSpeed));
    }


    private void moveLeftOfPlayer()
    {
        Vector2 leftV = new Vector2(currentSpeed.y, -currentSpeed.x);
        this.move(Vector2.multiply(leftV, trackSpeed));
    }


    private void moveRightOfPlayer()
    {
        Vector2 rightV = new Vector2(-currentSpeed.y, currentSpeed.x);  
        this.move(Vector2.multiply(rightV, trackSpeed));
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
