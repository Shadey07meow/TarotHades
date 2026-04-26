package scenes;

import images.ImageLibrary;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;

import systems.*;
import collision.*;


import collision.*;
import images.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import object.*;
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




    // level system
    private int currentLevel = 1;


    private int hoveredCardIndex = -1;

    private int selectedCardTimer = 0;
    

    // powerup

    private int chestState = 0; 
    private int spinTicks = 0;
    private int selectedCardIndex = -1;


    // transition
    private boolean isFading = false;
    private float fade = 0f;

    // chest system
    public boolean showChestUI = false;
    private ArrayList<Card> currentCards = new ArrayList<>();
    private CardManager cardManager;


   
    

    public PlayableScreen(String panelName, GameFrame g)
    {
        super(panelName, g);
        // Lets work on making shit appear first

        // Initiates game loop
        this.gameLoop = new Thread(this);
        this.isRunning = true;

  
        // Adds input manager
        addKeyListener(inputManager); 
        addMouseListener(inputManager);
        addMouseMotionListener(inputManager);  // fixes mouse tracking
        setFocusable(true);
        requestFocusInWindow();

        cardManager = new CardManager(ImageLibrary.get());
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
            if(world != null)
            {

                this.world.closeWorld();
            }
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


    protected void update()
    {
        if (world == null) return;

        if (world.getPlayer() != null)
        {
            updateCollisions();
        }

        // when esc is press, show pause panel
        checkPausing();
        
        if (chestState == 1) 
        {
            spinTicks++;

            int speed = Math.max(2, 10 - spinTicks / 10);

            if (spinTicks % speed == 0) {

                currentCards.clear();
                currentCards.add(cardManager.drawCard(currentLevel));
                currentCards.add(cardManager.drawCard(currentLevel));   
            }
            if (spinTicks > 60) {
                chestState = 2; // stop spinning, allow selection
            }
        }

        if (showChestUI && chestState == 2 && inputManager.consumeClick()) 
        {

            int cardW = 300;
            int cardH = 450;
            int spacing = 40;

            int totalWidth = (cardW * 3) + (spacing * 2);
            int startX = (getWidth() - totalWidth) / 2;
            int y = (getHeight() - cardH) / 2;

            Vector2 mouse = inputManager.getClickPosition();

            for (int i = 0; i < currentCards.size(); i++) {

                int x = startX + i * (cardW + spacing);

                Rectangle rect = new Rectangle(x, y, cardW, cardH);

                if (rect.contains(mouse.x, mouse.y)) {

                    selectedCardIndex = i;
                    selectCard(i);

                    break;
                }
            }
        }
        
        doFading();

        checkHoveringButtons();

        world.updateWorld();
    }

    // Update function methods
    public void checkPausing()
    {
        if (inputManager.isPausePressed()) {
            gameFrame.showPanel("pause");
        }
    }

    public void doFading()
    {
        // fade logic only
        if (isFading) {

            // force UI cleanup during fade
            showChestUI = false;
            currentCards.clear();

            fade += 0.05f;

            if (fade >= 1f) {
                fade = 1f;

                currentLevel++;

                if (currentLevel > 5) {
                currentLevel = 5; // lock at final boss
                showFinalMessage();
                return;}

                changeLevel(currentLevel);

                isFading = false;
                fade = 0f;
            }
        }

    }

    public void changeLevel(int x)
    {
        LevelFactory.loadLevel(x, world, player, this);
    }

    public void checkHoveringButtons()
    {
        // HOVER DETECTION
        if (showChestUI && chestState == 2) {

            int cardW = 300;
            int cardH = 450;
            int spacing = 40;

            int totalWidth = (cardW * currentCards.size()) + (spacing * (currentCards.size() - 1));
            int startX = (getWidth() - totalWidth) / 2;
            int y = (getHeight() - cardH) / 2;

            Vector2 mouse = inputManager.getClickPosition(); // last mouse pos

            hoveredCardIndex = -1;

            for (int i = 0; i < currentCards.size(); i++) {

                int x = startX + i * (cardW + spacing);

                Rectangle rect = new Rectangle(x, y, cardW, cardH);

                if (rect.contains(mouse.x, mouse.y)) {
                    hoveredCardIndex = i;
                    break;
                }
            }
        }
        if (selectedCardTimer > 0) {selectedCardTimer--;}


    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
  
        drawWorld(g);
        drawDebugWorld(g);

        if (showChestUI) {
            drawChestUI(g);

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

    private void drawDebugWorld(Graphics g)
    {
        Graphics2D graphics2 = (Graphics2D) g;

        
        if(world != null)
        {
            // Draws World dddebug stuff first 
            if(world.getDebug() == true)
            {
                graphics2.setStroke(new BasicStroke(2));
                for (GameObject obj : world.getObjectList()) {
                    if (obj.getCollider() != null) {
                        if(obj.getCollider() instanceof RectangleCollider)
                        {
                            if(obj.getCollider().getIsColliding() == true)
                            {
                                g.setColor(obj.getCollider().activeColor);
                            } else
                            {
                                g.setColor(obj.getCollider().inactiveColor);
                            }

                            if(obj.getCollider().getIsMovable() == false) g.setColor(Color.RED);


                            RectangleCollider tempCol = (RectangleCollider)obj.getCollider(); 
                            graphics2.drawRect(
                                (int) obj.getRenderX() - (((int)Math.abs(tempCol.getLocalBounds().LEFT.x) + (int)Math.abs(tempCol.getLocalBounds().RIGHT.x )) / 2),
                                (int) obj.getRenderY() - (((int)Math.abs(tempCol.getLocalBounds().BOTTOM.y) + (int)Math.abs(tempCol.getLocalBounds().TOP.y )) / 2),
                                (int)Math.abs(tempCol.getLocalBounds().LEFT.x) + Math.abs((int)tempCol.getLocalBounds().RIGHT.x ),
                                (int)Math.abs(tempCol.getLocalBounds().BOTTOM.y) + Math.abs((int)tempCol.getLocalBounds().TOP.y )
                            );
                        }
                    }
                }         
            }
        }
        

    }

    private void drawChestUI(Graphics g)
    {
        Graphics2D graphics2 = (Graphics2D) g;
    
        graphics2.setColor(new Color(0, 0, 0, 200));
        graphics2.fillRect(0, 0, getWidth(), getHeight());

        int cardW = 300;
        int cardH = 450;

        int spacing = 40;
        int totalWidth = (cardW * currentCards.size()) + (spacing * (currentCards.size() - 1));

        int startX = (getWidth() - totalWidth) / 2;
        int y = (getHeight() - cardH) / 2;

        for (int i = 0; i < currentCards.size(); i++) {

            int x = startX + i * (cardW + spacing);

            Card c = currentCards.get(i);

            // hover grey
            if (i == hoveredCardIndex) {
                graphics2.setColor(new Color(0, 0, 0, 120));
                graphics2.fillRect(x, y, cardW, cardH);
            }

            // select highlight
            if (i == selectedCardIndex) {
                graphics2.setColor(new Color(255, 255, 0, 100));
                graphics2.fillRect(x, y, cardW, cardH);
            }

            if (c != null && c.image != null) {

                graphics2.drawImage(
                    c.image,
                    x,
                    y,
                    cardW,
                    cardH,
                    null
                );
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

    
    // chest methods
    public void openChest() {

        if (showChestUI) return;

        showChestUI = true;
        chestState = 1;

        // Makes the player not able to fight when chest is open
        player.setUIOpen(true);

        currentCards.clear();

        // EXACTLY 2 cards
        currentCards.add(cardManager.drawCard(currentLevel));
        currentCards.add(cardManager.drawCard(currentLevel));

        spinTicks = 0;
    }

    public void closeChestUI() {
        showChestUI = false;
         player.setUIOpen(false);
        currentCards.clear();
    }

    
    public void clearLevelText() {

    }

    // card stuff
    public void showCards()
    {
        currentCards.clear();

        currentCards.add(cardManager.drawCard(currentLevel));
        currentCards.add(cardManager.drawCard(currentLevel));
        
        showChestUI = true;

        selectedCardIndex = -1;

    }

    
    public void selectCard(int index) {

        Card c = currentCards.get(index);
        if (c == null) return;

        selectedCardIndex = index;


        selectedCardTimer = 60; // show for ~1 second

        System.out.println("Card selected: " + c.name + " | Ability: " + c.ability);
        player.applyAbility(c.ability); 

        showChestUI = false;
        chestState = 0;

        // Enables inputs
        player.setUIOpen(false); 

        javax.swing.Timer t = new javax.swing.Timer(800, e -> {
            gameFrame.cutsceneScreen.loadCutsceneForLevel(currentLevel);
            gameFrame.showPanel("cutscene");
        });

        t.setRepeats(false);
        t.start();
    }

    private void resetUIOnTransition() {
        showChestUI = false;
        currentCards.clear();

    }


    public void requestLevelChange() {
        isFading = true;

        showChestUI = false;

    }

    public void triggerLevelChange() {
        isFading = true;

        resetUIOnTransition();
    }

    private void showFinalMessage() {
        setLevelText("YOU HAVE REACHED THE END");

        javax.swing.Timer t = new javax.swing.Timer(3000, e -> {
            clearLevelText();
            gameFrame.showPanel("menu"); // or credits screen
        });

        t.setRepeats(false);
        t.start();
    }

    public void nextLevelAfterCutscene() {

        currentLevel++;

        if (currentLevel > 6) {
            showFinalMessage(); // optional
            return;
        }

        LevelFactory.loadLevel(currentLevel, world, player, this);
    }


    public void setLevelText(String text) {

    }
}
