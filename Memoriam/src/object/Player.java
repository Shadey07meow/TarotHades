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

    
    private int speed = 1;
    private int health = 1;
    private GameFrame gameFrame;
    private GameStart gameStart;
    private InputManager inputs = null;
    private boolean hasShotProjectile = false;
    private ImageLibrary imgLib = new ImageLibrary();
    private boolean isDead = false;

    // for sprites
    private final Image spriteUp = imgLib.playerSpritesUP;
    private final Image spriteDown = imgLib.playerSpritesDOWN;
    private final Image spriteLeft = imgLib.playerSpritesLEFT;
    private final Image spriteRight = imgLib.playerSpritesRIGHT;

    // game objects
    private ArrayList<GameObject> objects;



    public Player(Vector2 position, int scale, int speed, int health, InputManager inps, ArrayList<GameObject> objs, GameFrame gameFrame, GameStart gameStart)
    {
        super(position.x, position.y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;
        this.objects = objs;
        this.gameFrame = gameFrame;
        this.gameStart = gameStart;
        setImage(spriteDown);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void update()
    {
        super.update();
        if (isDead) return;

        movePlayer();
        keepInsideScreen();
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

        int dx = inpVector.x * speed;
        int dy = inpVector.y * speed;

        // move x
        move(dx, 0);
        //handleCollision(dx, 0);

        // move y
        move(0, dy);
        //handleCollision(0, dy);

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

        int screenWidth = gameStart.getWidth();
        int screenHeight = gameStart.getHeight();

        if (getX() - halfW < 0) setX(halfW);
        if (getY() - halfH < 0) setY(halfH);

        if (getX() + halfW > screenWidth) setX(screenWidth - halfW);
        if (getY() + halfH > screenHeight) setY(screenHeight - halfH);
    }

}