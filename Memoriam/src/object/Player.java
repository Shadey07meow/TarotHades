package object;

import images.*;
import java.awt.Image;

import collision.RectangleCollider;
import scenes.*;
import systems.*;

public class Player extends Entity {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that
    private final GameFrame gameFrame;
    private final ImageLibrary imgLib = ImageLibrary.get();
    
    private InputManager inputs = null;
    
    // Combat variables
    private final double projectileSpeed = 15;
    private final double fireCooldown = 10;
    
    // World renderer
    private WorldRenderer world;
    
    // for sprites
    private final Image spriteDown = imgLib.playerSpritesDOWN;
    private final Image spriteUp = imgLib.playerSpritesUP;
    private final Image spriteLeft = imgLib.playerSpritesLEFT;
    private final Image spriteRight = imgLib.playerSpritesRIGHT;
    
    // Tracker variables
    private boolean hasShotProjectile = false;
    private boolean isInteracting = false;
    private boolean isDead = false;
    private double currentCooldown = 0;
    



    public Player(Vector2 position, int scale, int speed, int health, PlayableScreen scrn, GameFrame gameFrame)
    {
        super(position, scale, scrn);
        this.speed = speed;
        this.health = health;
        
        this.playScrn = scrn;
        this.inputs = this.playScrn.getInputManager();
        this.world = this.playScrn.getWorldRenderer();

        this.gameFrame = gameFrame;
        this.setCollider(new RectangleCollider(this, true));
        setImage(spriteDown);
    }


    @Override
    public void update()
    {
        super.update();

        if(world != null)
        {

            if (!isDead)
            {
                // loser condition
                inputOperations();
            } else
            {
                    
                gameFrame.showPanel("lose");
            }

            isDead = (health <= 0);

        }

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% every time
        // makes it smoother
    }


    public void inputOperations()
    {

        movePlayer();        
        combatMethod();
        checkInteracting();
    }

    private void movePlayer() 
    {
        Vector2 inpVector = inputs.getInputVector();
        Vector2 speedVector = Vector2.multiply(inputs.getInputVector(), this.speed);

        // Clamp movement speed so that it never exceeds speed
        if(Math.abs(speedVector.findMag()) > speed) speedVector = Vector2.magConvert(speedVector, speed);

        
        move(speedVector);
        

        // Set images, make looking up and down priority
        if (inpVector.x > 0) setImage(spriteRight);
        else if (inpVector.x < 0) setImage(spriteLeft);
        
        if (inpVector.y > 0) setImage(spriteUp);
        else if (inpVector.y < 0) setImage(spriteDown);
    }


    private void combatMethod()
    {


        if (currentCooldown > 0) currentCooldown--;
        

        // shoot once per click
        if(!hasShotProjectile)
        {
            if(inputs.getClickingStatus())
            {
                shootProjectile();
                hasShotProjectile = true;
            }
        }
        else
        {
            // reset when mouse released
            if(!inputs.getClickingStatus())
            {
                hasShotProjectile = false;
            }
        }
    }

    /*
    private void handleCollision(int dx, int dy)
    {
        for (GameObject obj : objects) {
            if (obj == this) continue;

            if (obj instanceof CollisionObject) {
                CollisionObject col = (CollisionObject) obj;

                if (col.isColliding(this)) {

                    int overlapX = (getScaledWidth()/2 + col.getScaledWidth()/2)
                                - Math.abs(getX() - col.getX());

                    int overlapY = (getScaledHeight()/2 + col.getScaledHeight()/2)
                                - Math.abs(getY() - col.getY());

                    // Resolve the smaller overlap (prevents teleporting)
                    if (overlapX < overlapY) {
                        if (getX() < col.getX()) {
                            setX(getX() - overlapX);
                        } else {
                            setX(getX() + overlapX);
                        }
                    } else {
                        if (getY() < col.getY()) {
                            setY(getY() - overlapY);
                        } else {
                            setY(getY() + overlapY);
                        }
                    }

                    // PUSHING LOGIC
                    if (col.getIsMovable()) {
                        col.move(dx / 2, dy / 2);
                    }
                }
            }
        }
    }
    */

    private void shootProjectile(){
        // Checks if we can shoot after shooting the last shot
        // cooldown
        
    
        if (currentCooldown != 0) return;


        double spawnX = getX();
        double spawnY = getY(); 

        Vector2 click = inputs.getClickPosition();

        double dx = click.x - spawnX;
        double dy = click.y - spawnY;

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        double vx = (dx / length) * projectileSpeed;
        double vy = -(dy / length) * projectileSpeed;

        Vector2 velocity = new Vector2(
        (int)Math.round(vx),
        (int)Math.round(vy)
        );

        world.addObject(new Projectile(
            (int)spawnX,
            (int)spawnY,
            velocity,
        1, 
            this.playScrn));

        currentCooldown = fireCooldown;
        System.out.println("Shot fired");
    }

    // Setters getters

    public void setWorldRenderer(WorldRenderer w)
    {
        this.world = w;
        System.out.println("Added a world renderer");
    }

    public Vector2 getVelocity(){return Vector2.multiply(inputs.getInputVector(), speed);}
    public double  getHealth() {return this.health;}
    public boolean isInteracting(){ return this.isInteracting;}

    public void checkInteracting()
    {
        isInteracting = inputs.getIsInteracting();
    }
}