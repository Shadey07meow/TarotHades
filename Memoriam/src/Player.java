import java.awt.event.KeyListener;

public class Player extends GameObject implements Runnable  {
    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that
    private int speed = 1;   
    private int health = 1;   
    Thread playerThread = new Thread(this);
    InputManager inputs = null;
    
    public Player(int x, int y, int scale, int speed, int health, InputManager inps)
    {
        super(x, y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;
        playerThread.start();
        
    }
 
    @Override
    public void run()
    {
        while (true) { 
            movePlayer();
            try {
                Thread.sleep(PlayableScreen.framesPerSecond);
            } catch (Exception e) {
                System.out.println("Cannot slleep");
            }
        }
    }
    
    public void movePlayer()
    {

        move(inputs.moveVector.x * speed, inputs.moveVector.y * speed);
        System.out.println(inputs.moveVector.x * speed + " " + inputs.moveVector.y * speed);
    }
}
