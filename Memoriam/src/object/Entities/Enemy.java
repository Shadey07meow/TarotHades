package object.Entities;

import collision.CollisionObject;
import images.*;
import object.statics.Projectile;

import java.awt.Image;
import java.util.ArrayList;
import scenes.levels.InfiniteLevel;
import scenes.templates.PlayableScreen;
import systems.*;

public abstract class Enemy extends Entity {

    protected int detectionRange = 500;
    protected int attackingRange = 250;
    protected double fireCooldown = 0.5 * 1000;

    protected ImageLibrary img = ImageLibrary.get();
    protected Player pl;
    protected Vector2 currentSpeed;

    protected Image moveLeftImg = null;
    protected Image moveRightImg = null;

    protected Image hurtImg = null;

    protected boolean usesProjectiles = true;
    protected boolean hasDetectedPlayer = false;
    protected boolean withinShootingDistance = false;
    protected int projectileSpeed = 10;

    private boolean goesLeft;
    private double trackSpeed;
    private double followSpeed;
    private double currentCooldown = 0;
    
    private boolean isHurt = false;
    private double hurtTimer = 0;
    private double hurtDuration = 200; 


    public Enemy(Vector2 p, double s, PlayableScreen scrn)
    {
        super(p, s, scrn);
        randomizeDir();
        this.hurtImg = ImageLibrary.get().enemyHurt;
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
        SoundManager.get().playSFX("enemyHit");
        minusHP(i);
        isHurt = true;
        hurtTimer = hurtDuration;
    }

    @Override
    public void onDeath()
    {
        SoundManager.get().playSFX("enemyDeath");
        world.removeObject(this);

        if (playScrn instanceof InfiniteLevel inf)
            {
                inf.onEnemyKilled();
            }
        GameStats.get().addKill();
    }
    
    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void logicMethods()
    {

        if (isHurt)
        {
            hurtTimer -= PlayableScreen.SINGLEFRAME;

            if (hurtTimer <= 0)
            {
                isHurt = false;
            }
        }

        if(this.pl == null) 
        {
            this.pl = this.world.getPlayer();
            //System.out.println("I am bein called");
            return;
        }

        // Run when undetected
        if(!this.hasDetectedPlayer)
        {
            // Using this structure so that if you are detected once, you will be detected forever
            detectForPlayer();
            this.currentCooldown = 0;
        } else
        {
            // Move
            updateSpeed();

            if(this.usesProjectiles)
            {

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
                if(currentCooldown <= 0)
                {
                    shootAtPlayer();
                } else
                {
                    currentCooldown -= PlayableScreen.SINGLEFRAME;
                }
            } else
            {
                moveTowardsPlayer();
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
        this.move(Vector2.multiply(this.currentSpeed, this.followSpeed));
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
        if (isHurt){this.setImage(hurtImg); return;}
        if (moveLeftImg == null || moveRightImg == null) return;
        
        if(moveLeftImg == null || moveRightImg == null) return; 

        if(currentSpeed.x >= 0) {this.setImage(moveRightImg);} else {this.setImage(moveLeftImg);}
    }


    @Override
    public void onCollision()
    {
        // Double check if there is nothing being collided with
        if(!this.collider.getCollidingWith().isEmpty())
        {
            ArrayList<CollisionObject> colList = this.collider.getCollidingWith();
            boolean hashitUnmovable = false;
            
            for(int x = 0; x < colList.size() && !colList.isEmpty() ; x++)
            {
                // Unmovable object check
                if(colList.get(x).getGameObject() == this.pl)
                {
                    // Death on collision Logic
                    if(this.usesProjectiles) return;
                    SoundManager.get().playSFX("playerHit");
                    this.pl.minusHP(this.damage);
                    damage(1000);
                }
            }
        }
    }

    @Override
    public void onHit(int a)
    {
        damage(a);
        this.playScrn.getSpecialEffects().spawnNumberPopup(this.position, a);

    }

    
    public void setDetectionDistance(int distance)
    {
        this.detectionRange = distance;
    }    
    public void setAttackingRange(int distance)
    {
        this.attackingRange = distance;
    }

    protected void shootAtPlayer(){
        // Find unit vectore from player this e nemy object
        Vector2 baseDir = Vector2.getUnitVector(this.position, Vector2.add(this.pl.getPosition(), this.pl.getVelocity()));
        Vector2 projectileVelocity = Vector2.multiply(baseDir, projectileSpeed);

        SoundManager.get().playSFX("shoot");

        // Make the projectile
        this.world.addObject(new Projectile((int)this.position.x, (int)this.position.y, projectileVelocity, 1, this.playScrn, Enemy.class, ImageLibrary.get().projectile ));
        this.currentCooldown = this.fireCooldown;
    }

    public void setDetectedPlayer(boolean v)
    {
        this.hasDetectedPlayer = v;
    }


    
}
