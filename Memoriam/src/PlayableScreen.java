import java.awt.Graphics;


public class PlayableScreen extends ShowablePanel implements Runnable{
    
    // This is a screen where the player can move or any entities can exist in
    // The main reason for having this class is to simultaneously keep track of all the different thing

    Thread gameLoop = null;
    InputManager inputManager = new InputManager();
    static int framesPerSecond = 60;
    

    public PlayableScreen(String panelName)
    {
        super(panelName);
        // Lets work on making shit appear first

        // Initiates game loop
        gameLoop = new Thread(this);

        // Adds input manager
        addKeyListener(inputManager); 
        addMouseListener(inputManager);
    }
    

    
    public void startGamePanel()
    {
        requestFocusInWindow();
        // Initialized first before running game loop
        try
        {gameLoop.start();}
        catch(Exception e)
        {System.out.println("Game Loop already running");}
    }

    public void stopGamePanel()
    {
        try
        {gameLoop.interrupt();}
        catch(Exception e)
        {System.out.println("Game Loop already stopped");}   
    }

    
    @Override
    public void run()
    {
        while (gameLoop != null) {
            // Call update function
            update();

            // Paint the panel every frame
            try {
                Thread.sleep(1000 / framesPerSecond);
            } 
            catch (Exception e)
            {
                System.out.println("Cannot be paused");
            }

            repaint();
        }

    }


    // Update function for all playable screen, Override this for game logic
    protected  void update()
    {}

    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
    }
}
