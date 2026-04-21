package scenes;

import images.ImageLibrary;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import object.GameObject;
import object.Player;
import systems.*;



public abstract class PlayableScreen extends ShowablePanel implements Runnable{
    
    // This is a screen where the player can move or any entities can exist in
    // The main reason for having this class is to simultaneously keep track of all the different thing

    protected WorldRenderer world = null;
    protected Player player;
    private Thread gameLoop = null;
    private boolean isRunning = true;
    InputManager inputManager = new InputManager();
    static int framesPerSecond = 60;
    protected Vector2 center = new Vector2();
    
    

    public PlayableScreen(String panelName)
    {
        super(panelName);
        // Lets work on making shit appear first

        // Initiates game loop
        this.gameLoop = new Thread(this);
        this.isRunning = true;
  
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
        this.isRunning = true;
        System.out.println("1 - isRunning : " + String.valueOf(this.isRunning));
        this.gameLoop = new Thread(this);
        requestFocusInWindow();
        initWindow();
        startGamePanel();
        System.out.println("2 - isRunning : " + String.valueOf(this.isRunning));
        
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
        this.world = new WorldRenderer(this);
        System.out.println("Initialized : " + this.getName());

        // Initialized first before running game loop
        try
        {
            this.gameLoop.start();
        }
        catch(Exception e)
        {
            System.out.println("Game Loop already running");        }
    }
    
    public void terminateWindow()
    {
        try
        {
            closeGameLoop();
            this.world.closeWorld();
        }
        catch(Exception e)
        {System.out.println("Game Loop already stopped : " + e.getMessage());}   
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
        System.out.println("Thread started");


        while(this.isRunning)
        {
            //System.out.println("Update function is being called");

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

        System.out.println("Thread stopped");
    }

    public void closeGameLoop()
    {
        this.isRunning = false;
    }


    // Update function for all playable screen, Override this for game logic
   /* protected  void update()
    {
        if(world !=  null)
        {
            // Handles all the world logic, physics, camera movement, etc
            if(world != null) world.updateWorld();
            if(world.getPlayer() != null) world.getPlayer().update();
        }
    }*/

   // testing -calrya
    protected void update()
    {
        if (world == null) return;

        if (world.getPlayer() != null)
        {
            world.getPlayer().update();
        }

        world.updateWorld();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics2 = (Graphics2D) g;

        if (world != null)
        {
            //System.out.println("I am rendering shit rn");
            ArrayList<GameObject> list = world.getObjectList();

            // In world renderer, the map must always be drawn first
            for (int x = 0; x < world.getObjectList().size(); x++) 
            {
                if (list.get(x).getImage() != null) {

                    graphics2.drawImage(
                        list.get(x).getImage(),
                        (int) list.get(x).getRenderX() - (int)(list.get(x).getScaledWidth() / 2),
                        (int) list.get(x).getRenderY() - (int)(list.get(x).getScaledHeight() / 2),
                        (int)list.get(x).getScaledWidth(),
                        (int)list.get(x).getScaledHeight(),
                        null
                    );

                } else {

                    graphics2.setColor(list.get(x).getColor());

                    graphics2.fillRect(
                        (int) list.get(x).getRenderX() - (int)(list.get(x).getScaledWidth() / 2),
                        (int) list.get(x).getRenderY() - (int)(list.get(x).getScaledHeight() / 2),
                        (int) list.get(x).getScaledWidth(),
                        (int) list.get(x).getScaledHeight()
                    );
                }
            }
        }

        if (world != null && world.getDebug())
        {
            g.setColor(Color.BLUE);
            graphics2.setStroke(new BasicStroke(20));

            g.drawOval(
                (int)(world.getCenterPosition().x - (world.getDistanceFromCenter())),
                (int)(world.getCenterPosition().y - (world.getDistanceFromCenter())),
                world.getDistanceFromCenter() * 2,
                world.getDistanceFromCenter() * 2
            );

            java.util.ArrayList<GameObject> debugList =
                new java.util.ArrayList<>(world.getObjectList());

            for (GameObject obj : debugList) {

                if (obj.getImage() != null) {

                    graphics2.fillRect(
                        (int) obj.getRenderX() - (int)(obj.getScaledWidth() / 2),
                        (int) obj.getRenderY() - (int)(obj.getScaledHeight() / 2),
                        50,
                        50
                    );
                }
            }
        }

        if (player !=null){
            int heartSize = 40;
            int spacing = 5;
            int maxHealth = 10;

            ImageLibrary img = ImageLibrary.get();

            for (int i =0; i<maxHealth; i++){
                int x = 20 +(i*(heartSize + spacing));
                int y = 20;

                if (i<player.getHealth()){
                    graphics2.drawImage(
                    img.heart,
                    x,
                    y,
                    heartSize,
                    heartSize,
                    null);
                }
                else{
                    graphics2.drawImage(
                    ImageLibrary.get().deadHeart,
                    x,
                    y,
                    heartSize,
                    heartSize,
                    null);
                }
            }

        }

    }
}
