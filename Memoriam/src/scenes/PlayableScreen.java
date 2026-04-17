package scenes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

import object.GameObject;
import object.Player;
import systems.*;



public abstract class PlayableScreen extends ShowablePanel implements Runnable{
    
    // This is a screen where the player can move or any entities can exist in
    // The main reason for having this class is to simultaneously keep track of all the different thing

    protected WorldRenderer world = null;
    protected Player player;
    Thread gameLoop = null;
    InputManager inputManager = new InputManager();
    static int framesPerSecond = 60;
    protected Vector2 center = new Vector2();
    
    

    public PlayableScreen(String panelName)
    {
        super(panelName);
        // Lets work on making shit appear first

        // Initiates game loop
        gameLoop = new Thread(this);
  
        // Adds input manager
        addKeyListener(inputManager); 
        addMouseListener(inputManager);
        setFocusable(true);
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
        {gameLoop.interrupt();
         world.closeWorld();
         world = null;
        }
        catch(Exception e)
        {System.out.println("Game Loop already stopped");}   
    }
    
    public void startGamePanel()
    {
        
    }
    public void stopGamePanel()
    {

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
    {
        if(world !=  null)
        {
            // Handles all the world logic, physics, camera movement, etc
            if(world != null) world.updateWorld();
            if(world.getPlayer() != null) world.getPlayer().update();
        }
    }

    @Override
    public void paintComponent (Graphics g)
    {
        // Playable screen class is the class responsible for all the drawing in the frames, do not put paint component in the individual levels anymore
        super.paintComponent(g);
        Graphics2D graphics2 = (Graphics2D) g;


        // Draws all the images in the world renderer

        if(world != null)
        {
            // In world renderer, the map must always be drawn first
            for (GameObject obj : world.getObjectList()) {
                if (obj.getImage() != null) {
                    graphics2.drawImage(
                        obj.getImage(),
                        (int) obj.getRenderX() - (int)(obj.getScaledWidth() / 2),
                        (int) obj.getRenderY() - (int)(obj.getScaledHeight() / 2),
                        (int)obj.getScaledWidth(),
                        (int)obj.getScaledHeight(),
                        null
                    );
                } else {
                    graphics2.setColor(obj.getColor());
                    graphics2.fillRect(
                        (int) obj.getRenderX() - (int)(obj.getScaledWidth() / 2),
                        (int) obj.getRenderY() - (int)(obj.getScaledHeight() / 2),
                        (int)obj.getScaledWidth(),
                        (int)obj.getScaledHeight()
                        
                    );
                }
            }
    
        }
        
        if(world != null)
        {
            if(world.getDebug() == true)
            {
                
                g.setColor(Color.BLUE);
                graphics2.setStroke(new BasicStroke(20));
                g.drawOval(
                    (int)(world.getCenterPosition().x - (world.getDistanceFromCenter())), 
                    (int)(world.getCenterPosition().y - (world.getDistanceFromCenter())), 
                    world.getDistanceFromCenter() * 2, 
                    world.getDistanceFromCenter() * 2);
            }

        }

    }


}
