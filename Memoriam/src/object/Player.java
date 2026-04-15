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
    private GameFrame gameFrame;
    private InputManager inputs = null;
    private boolean hasShotProjectile = false;
    private ImageLibrary imgLib = new ImageLibrary();
    private boolean isDead = false;
    private boolean canMove = true;

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
       
        setImage(spriteDown);
    }


    @Override
    public void update()
    {
        super.update();
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
        

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% every time
        // makes it smoother
    }

    private void movePlayer() 
    {
        Vector2 inpVector = inputs.getInputVector();
        Vector2 speedVector = Vector2.multiply(inputs.getInputVector(), this.speed);


        //System.out.println(speedVector.toString());

        // move x
        move(speedVector);
        // stops at screen edges
        keepInsideScreen();

        

        if (inpVector.x > 0) setImage(spriteRight);
        else if (inpVector.x < 0) setImage(spriteLeft);
        else if (inpVector.y > 0) setImage(spriteUp);
        else if (inpVector.y < 0) setImage(spriteDown);
    }


    private void combatMethod()
    {
        // Checks if we have shot projectile
        if(hasShotProjectile == false)
        {
            if(inputs.getClickingStatus() == true)
            {
                // Shooting projectile logic
                hasShotProjectile = true;
            }
        } 
        else
        {
            // On mouse release, reset shooting logic
            if(inputs.getClickingStatus() == false)
            {
                // Shooting projectile logic
                hasShotProjectile = true;
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


    private void keepInsideScreen() {
        int halfW = getScaledWidth() / 2;
        int halfH = getScaledHeight() / 2;

        int maxX = 1920;
        int maxY = 1080;

        if (getX() - halfW < 0) setX(halfW);
        if (getY() - halfH < 0) setY(halfH);

        if (getX() + halfW > maxX) setX(maxX - halfW);
        if (getY() + halfH > maxY) setY(maxY - halfH);
    }

    // Setters getters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setMovable(boolean v)
    {
        this.canMove = v;
    }

    public Vector2 getVelocity()
    {
        return Vector2.multiply(inputs.getInputVector(), speed);
    }
}