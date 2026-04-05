public class Player extends GameObject {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that

    private int speed = 1;
    private int health = 1;
    private InputManager inputs = null;

    public Player(int x, int y, int scale, int speed, int health, InputManager inps)
    {
        super(x, y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;
    }

    public void update()
    {
        move(inputs.moveVector.x * speed, inputs.moveVector.y * speed);
        System.out.println(inputs.moveVector.x * speed + " " + inputs.moveVector.y * speed);
    }
}