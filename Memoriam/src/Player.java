import java.awt.Image;

public class Player extends GameObject {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that

    
    private int speed = 1;
    private int health = 1;
    private InputManager inputs = null;

    // for sprites
    private final Image spriteUp;
    private final Image spriteDown;
    private final Image spriteLeft;
    private final Image spriteRight;

    public Player(int x, int y, int scale, int speed, int health, InputManager inps, Image up, Image down, Image left, Image right)
    {
        super(x, y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;

        this.spriteUp = up;
        this.spriteDown = down;
        this.spriteLeft = left;
        this.spriteRight = right;

        setImage(spriteDown);
    }

    public void update()
    {
        move(inputs.moveVector.x * speed, inputs.moveVector.y * speed);
        System.out.println(inputs.moveVector.x * speed + " " + inputs.moveVector.y * speed);

        int moveX = inputs.moveVector.x * speed;
        int moveY = inputs.moveVector.y * speed;


        if (moveX > 0)
        {
            setImage(spriteRight);
        }
        else if (moveX < 0)
        {
            setImage(spriteLeft);
        }
        else if (moveY > 0)
        {
            setImage(spriteUp);
        }
        else if (moveY < 0)
        {
            setImage(spriteDown);
        }
    }
}