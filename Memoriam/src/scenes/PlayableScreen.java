package scenes;

import java.awt.Color;
import java.awt.Font;
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
    public static final int FRAMESPERSECOND = 60;
    public static final int SINGLEFRAME = 1000/ FRAMESPERSECOND;
    protected Vector2 center = new Vector2();
    protected MapObj     currentMap;
    private CardManager crdManager  = new CardManager(this);
    private HealthBar healthBar = new HealthBar();  
    private int hoveredCardIndex = -1;
    private int selectedCardTimer = 0;
    protected SpecialEffects fx = new SpecialEffects();
    
    private PauseUI pauseUI;
    private boolean isFading = false;
    private boolean isPaused = false;
    private float fade = 0f;
    
    //private JPanel gameWorldPanel = new JPanel();
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
    
    public abstract MapObj setMap();
    public abstract Player setPlayer();
    public abstract void startGamePanel();
    public abstract void stopGamePanel();

    @Override
    public void onInitiate()
    {
        this.isRunning = true;
        this.gameLoop = new Thread(this);
        this.pauseUI = new PauseUI(this, this.getGameFrame());
        requestFocusInWindow();
        if (this.id == 0) LevelManager.startNewRun();
        initWindow();
        startGamePanel();
        inputManager.resetInputs();
        
        // this.player.setHP(SaveSystem);
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
        LevelManager.restorePlayerAbilities(this.player);

        System.out.println("HP = " + SaveSystem.getHP());
        if(this.id != 0) this.player.setHealth(SaveSystem.getHP());
        

        
        // System.out.println("Saving Progress");
        //         SaveSystem.saveProgress(
        //         this.id,
        //         (int)this.player.getHealth(),
        //         this.player.getAbilityMap(),
        //         GameStats.get().getEnemiesKilled()
        //     );


        

        this.player.setWorld(world);

        // restore abilities from previous level
        
        
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
        {System.out.println("Game Loop already stopped : " + e.getCause());}   
    }
    
   
    @Override
    public void run(){
        System.out.println("Thread started");
        


        fx.generateLoadingScreen();

        while(this.isRunning)
        {
            //System.out.println("Update function is being called");
            if(!SpecialEffects.getIsLoading()) checkPausing();
            // Call update function
            if(!isPaused)
            {
                update();
            } else
            {
                if(!this.player.getUIOpen())
                {
                    checkPausing();

                    pauseUI.onMouseMove(inputManager.getMousePosition());
                    if(inputManager.getMouseClicked())
                    {
                        pauseUI.onClicked(inputManager.getClickPosition());
                        
                    }        
                }
            }
    
            // Paint the panel every frame
            try {
                Thread.sleep(1000 / FRAMESPERSECOND);
            } 
            catch (Exception e)
            {
                System.out.println("Cannot be paused");
            }

            fx.update(SINGLEFRAME);
    
            repaint();
        } 

        

        System.out.println("Thread stopped");
    }

    public void closeGameLoop()
    {
        this.isRunning = false;
    }

    protected synchronized  void update()
    {
        if (world == null) return;

        if (world.getPlayer() != null)
        {
            updateCollisions();
        }

        keepInsideScreen();


        // when esc is press, show pause panel
        
        doFading();
        
        crdManager.spinCard();
        crdManager.sizeCard();
        crdManager.checkHoveringButtons();
        world.updateWorld();
        
    }

    // Update function methods
    public void checkPausing()
    {   
         if (player != null && player.getUIOpen()) return;

        // Pause Logic
        if (inputManager.isPausePressed()) {
            // this.getGameFrame().showPanel("pause");
            this.isPaused = !this.isPaused;
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

        // extra UI
        GameStats stats = GameStats.get();

        // bottom-left anchor
        int x = 15;
        int y = getHeight() - 110;

        // panel
        g.setColor(new Color(10, 10, 10, 180));
        g.fillRoundRect(x, y, 220, 85, 14, 14);

        // line on left
        g.setColor(new Color(255, 200, 60));
        g.fillRect(x, y, 4, 85);

        g.setFont(new Font("Monospaced", Font.BOLD, 22));

        // LEVEL
        g.setColor(Color.WHITE);
        g.drawString("LEVEL", x + 12, y + 30);
        g.setColor(new Color(255, 200, 60));
        g.drawString(String.valueOf(this.id), x + 95, y + 30);

        // KILLS
        g.setColor(Color.WHITE);
        g.drawString("KILLS", x + 12, y + 60);
        g.setColor(new Color(120, 220, 255));
        g.drawString(String.valueOf(stats.getEnemiesKilled()), x + 95, y + 60);

        fx.drawEffects(g);
               
        if(isPaused)
        {
            // System.out.println("Hello there i am aabout to destroyyy this");
            pauseUI.drawPause(g);
        }


    }


    private void drawWorld(Graphics g)
    {
        // Draw effects


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

    private void keepInsideScreen() {
            int halfW = this.player.getScaledWidth() / 2;
            int halfH = this.player.getScaledHeight() / 2;

      
            int screenWidth = getWidth();
            int screenHeight =  getHeight();

            if (this.player.getX() - halfW < 0) this.player.setX(halfW);
            if (this.player.getY() - halfH < 0) this.player.setY(halfH);


            if (this.player.getX() + halfW > screenWidth) this.player.setX(screenWidth - halfW);
            if (this.player.getY() + halfH > screenHeight) this.player.setY(screenHeight - halfH);
        }


    public InputManager getInputManager(){return this.inputManager;}
    public WorldRenderer getWorldRenderer(){return this.world;}
    public CardManager getCardManager(){ return this.crdManager;}
    public boolean getIsPaused(){return this.isPaused;}
    public void setIsPaused(boolean r){this.isPaused = r;}
    public SpecialEffects getSpecialEffects(){return this.fx;}
    public void onPowerupSelected() {}
    
}
