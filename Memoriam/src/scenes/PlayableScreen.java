package scenes;

import scenes.*;
import systems.*;
import collision.*;
import object.*;

import java.awt.Graphics;



public abstract class PlayableScreen extends ShowablePanel implements Runnable{
    
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
    
        @Override
    public String getShowablePanelName()
    {
        return this.name;
    }

    @Override
    public void setShowablePanelName(String name)
    {
        this.name = name;
    }
    
    @Override
    public void onInitiate()
    {
        requestFocusInWindow();
        initWindow();
        startGamePanel();
    }
    
    @Override
    public void onExit()
    {    
        terminateWindow();
        stopGamePanel();
    }


    // Unique shit
    public void initWindow()
    {
        requestFocusInWindow();
        // Initialized first before running game loop
        try
        {gameLoop.start();}
        catch(Exception e)
        {System.out.println("Game Loop already running");}
    }
    
    public void terminateWindow()
    {
        try
        {gameLoop.interrupt();}
        catch(Exception e)
        {System.out.println("Game Loop already stopped");}   
    }
    
    abstract public void startGamePanel();
    abstract public void stopGamePanel();
 
    

    
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
