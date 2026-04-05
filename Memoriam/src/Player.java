import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends GameObject {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that

    
    private int speed = 1;
    private int health = 1;
    private InputManager inputs = null;
    private boolean hasShotProjectile = false;

    // for sprites
    private final Image spriteUp;
    private final Image spriteDown;
    private final Image spriteLeft;
    private final Image spriteRight;

    public Player(int x, int y, int scale, int speed, int health, InputManager inps)
    {
        super(x, y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;

        this.spriteUp = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolUp.png")).getImage();   
        this.spriteDown =  new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolDown.png")).getImage();
        this.spriteLeft = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolLeft.png")).getImage();
        this.spriteRight = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolRight.png")).getImage();

         
        setImage(spriteDown);
    }

    @Override
    public void update()
    {
        // Separate functionality 
        movePlayer();
        combatMethod();

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% everytime.
        // makes it smoother
        super.interpolate(1);
    }

    private void movePlayer()
    {
        Vector2 inpVector = inputs.getInputVector();
        move(inpVector.x * speed, inpVector.y * speed);


        if (inpVector.x > 0)
        {
            setImage(spriteRight);
        }
        else if (inpVector.x < 0)
        {
            setImage(spriteLeft);
        }
        else if (inpVector.y > 0)
        {
            setImage(spriteUp);
        }
        else if (inpVector.y < 0)
        {
            setImage(spriteDown);
        }
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
}