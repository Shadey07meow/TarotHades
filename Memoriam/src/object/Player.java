package object;

import images.*;
import java.awt.Image;
import java.util.ArrayList;
import scenes.*;
import systems.*;

public class Player extends GameObject {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that

    
    private int speed = 1000;
    private int health = 1;
    private int fireCooldown = 0;
    private GameFrame gameFrame;
    private InputManager inputs = null;
    private boolean hasShotProjectile = false;
    private ImageLibrary imgLib = new ImageLibrary();
    private boolean isDead = false;
    private boolean canMove = true;

    // World renderer
    private WorldRenderer world;

    // for sprites
    private final Image spriteUp = imgLib.playerSpritesUP;
    private final Image spriteDown = imgLib.playerSpritesDOWN;
    private final Image spriteLeft = imgLib.playerSpritesLEFT;
    private final Image spriteRight = imgLib.playerSpritesRIGHT;




    public Player(Vector2 position, int scale, int speed, int health, InputManager inps, GameFrame gameFrame)
    {
        super(position.x, position.y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;
        this.gameFrame = gameFrame;
        this.world = null;
        setImage(spriteDown);
    }


    @Override
    public void update()
    {
        super.update();

        if(world != null)
        {
            // cooldown
            if (fireCooldown > 0)
                fireCooldown--;
        
            if (isDead) return;

            if(canMove)
            {
                movePlayer();
                //System.out.println("I am inside the circle");
            }
            combatMethod();

            // loser condition
            if (health <= 0) {
                isDead = true;
                gameFrame.showPanel("lose");
                return;
            }
        }

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% every time
        // makes it smoother
    }

    private void movePlayer() 
    {
        Vector2 inpVector = inputs.getInputVector();
        Vector2 speedVector = Vector2.multiply(inputs.getInputVector(), this.speed);




        // move x
        move(speedVector);
        

        if (inpVector.x > 0) setImage(spriteRight);
        else if (inpVector.x < 0) setImage(spriteLeft);
        else if (inpVector.y > 0) setImage(spriteUp);
        else if (inpVector.y < 0) setImage(spriteDown);
    }


    private void combatMethod()
    {
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

    public void minusHP(int  a)
    {
        this.health -= a;
    }


    private void shootProjectile(){
        // Checks if we can shoot after shooting the last shot
        if (fireCooldown != 0) return;


        double spawnX = getX();
        double spawnY = getY(); 

        Vector2 click = inputs.getClickPosition();

        double dx = click.x - spawnX;
        double dy = click.y - spawnY;

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        double vx = (dx / length) * 8;
        double vy = -(dy / length) * 8;

        Vector2 velocity = new Vector2(
        (int)Math.round(vx),
        (int)Math.round(vy)
        );

        world.addObject(new Projectile(
        (int)spawnX,
        (int)spawnY,
        velocity,
        30));

        fireCooldown = 10;
        System.out.println("Shot fired");
    }

    // Setters getters
    public void setHealth(int health) {this.health = health;}
    public void setMovable(boolean v){this.canMove = v;}
    public void setWorldRenderer(WorldRenderer w)
    {
        this.world = w;
        System.out.println("Added a world renderer");
    }

    public Vector2 getVelocity(){return Vector2.multiply(inputs.getInputVector(), speed);}
}