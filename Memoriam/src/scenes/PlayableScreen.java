package scenes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import object.*;
import systems.*;

public abstract class PlayableScreen extends ShowablePanel implements Runnable{
    
    // This is a screen where the player can move or any entities can exist in
    // The main reason for having this class is to simultaneously keep track of all the different thing

    protected WorldRenderer world = null;
    protected Player player;
    
    private Thread gameLoop = null;
    private volatile  boolean isRunning = true;

    InputManager inputManager = new InputManager();

    static int framesPerSecond = 60;
    protected Vector2 center = new Vector2();
    protected Map     currentMap;

    private CardManager crdManager  = new CardManager(this);
    private HealthBar healthBar = new HealthBar();  

    private int hoveredCardIndex = -1;
    private int selectedCardTimer = 0;

    private boolean isFading = false;
    private float fade     = 0f;

    private final int id;

    // constructor
    public PlayableScreen(String panelName, int ID, GameFrame g)
    {
        super(panelName, g);
        this.id = ID;

        // Initiates game loop
        this.gameLoop = new Thread(this);
        this.isRunning = true;

        // Adds input manager
        addKeyListener(inputManager); 
        addMouseListener(inputManager);
        addMouseMotionListener(inputManager);  // fixes mouse tracking
        setFocusable(true);
        requestFocusInWindow();

        

    }
    
    @Override
    public String getShowablePanelName(){ return this.name;}

    @Override
    public void setShowablePanelName(String name){ this.name = name; }

    public int getID(){return this.id;}
    
    public abstract Map setMap();
    public abstract Player setPlayer();
    public abstract void startGamePanel();
    public abstract void stopGamePanel();

    @Override
    public void onInitiate()
    {
        this.isRunning = true;
        this.gameLoop = new Thread(this);
        requestFocusInWindow();
        initWindow();
        startGamePanel();
    }
    
    @Override
    public void onExit(){    
        terminateWindow();
        stopGamePanel();
    }

    // Unique shit
    private  void initWindow(){
        requestFocusInWindow();
        
        System.out.println("Initialized : " + this.getName());
        this.player = setPlayer();
        this.currentMap = setMap();
        this.center = this.player.getPosition();
        this.world = new WorldRenderer(this.player, this.currentMap, this);

        this.player.setWorld(world);

        // restore abilities from previous level
        LevelManager.restorePlayerAbilities(this.player);
        
        // Initialized first before running game loop
        try{

            this.gameLoop.start();
        }
        catch(Exception e) {

            System.out.println("Game Loop already running");       
         }
    }
    
    public void terminateWindow()
    {
        try{
            closeGameLoop();
            if(world != null)
            {
                this.world.closeWorld();
            }
        }
        catch(Exception e)
        {System.out.println("Game Loop already stopped : " + e.getMessage());}   
    }
    
   
    @Override
    public void run(){
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

    protected void update()
    {
        if (world == null) return;

        if (world.getPlayer() != null)
        {
            updateCollisions();
        }

        // when esc is press, show pause panel
        checkPausing();
        
        doFading();
        
        crdManager.spinCard();
        crdManager.sizeCard();
        crdManager.checkHoveringButtons();
        crdManager.checkHoveringButtons();

        world.updateWorld();
    }

    // Update function methods
    public void checkPausing()
    {
        // Pause Logic
        if (inputManager.isPausePressed()) {
            this.getGameFrame().showPanel("pause");
        }
    }

    public void doFading()
    {
        // fade logic only
        if (isFading) {

            // force UI cleanup during fade
            crdManager.showChestUI = false;

            // Move this line to cardmanager currentCards.clear();

            fade += 0.05f;

            if (fade >= 1f) {
                fade = 1f;


                // Should be handled by level manager
                // if (currentLevel > 5) {
                // currentLevel = 5; // lock at final boss
                // showFinalMessage();
                // return;}

                isFading = false;
                fade = 0f;
            }
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
  
        drawWorld(g);

        if(world != null)
        {
            world.drawDebugWorld(g);
        }
        
        if (crdManager.showChestUI) {
            crdManager.drawChestUI(g);
        }

        if (world != null && world.getPlayer() != null) {
            healthBar.draw(g, world.getPlayer());
        }

    }

    private void drawWorld(Graphics g)
    {
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

    }

    public InputManager getInputManager()
    {
        return this.inputManager;
    }

    public WorldRenderer getWorldRenderer()
    {
        return this.world;
    }
    
    public void updateCollisions()
    {
        for(int x = 0; x < world.getObjectList().size(); x++)
        {
            ArrayList<GameObject> list =  world.getObjectList();

            if(list.get(x).getCollider() != null)
            {
                list.get(x).getCollider().checkCollisions();
            }
            
        }
    }

     public InputManager getInputManager(){return this.inputManager;}
    public WorldRenderer getWorldRenderer(){return this.world;}
    public CardManager getCardManager(){ return this.crdManager;}
    
}
