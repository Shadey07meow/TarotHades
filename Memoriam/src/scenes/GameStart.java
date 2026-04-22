package scenes;
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


public class GameStart extends PlayableScreen {

    // main game screen
    private GameFrame gameFrame;
    private JButton killButton;
    private JButton menuButton;
    private Image map;

    // level system
    private int currentLevel = 1;

    private LevelFactory levelFactory;
    private String levelText = "";
    private boolean showLevelText = false;
    private JButton nextLevelButton;
    private int hoveredCardIndex = -1;
    private String selectedCardName = "";
    private int selectedCardTimer = 0;
    

    // powerup
    private ArrayList<Card> spinCards = new ArrayList<>();
    private int chestState = 0; 
    private int spinTicks = 0;
    private int selectedCardIndex = -1;
    private boolean chestSelectionMode = false;


    // transition
    private boolean isFading = false;
    private float fade = 0f;

    // chest system
    public boolean showChestUI = false;
    private ArrayList<Card> currentCards = new ArrayList<>();
    private CardManager cardManager;


    private Rectangle chestButton;
    private Rectangle backButton;

    private float chestAnim = 0f;
    private float cardFlip = 0f;



    public GameStart(GameFrame gameFrame) {
        super("start");


        this.gameFrame = gameFrame;

        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        map = ImageLibrary.get().map;


  

        killButton = gameFrame.createImageButton(ImageLibrary.get().placeholderBtn, 200, 100);

        killButton.addActionListener(e -> {
        if (player != null) {
           player.minusHP(1); // minus 1 heart
        }
         });
        // button
        killButton = gameFrame.createImageButton(ImageLibrary.get().placeholderBtn, 353, 100);
        killButton.addActionListener(e -> { if (player != null) {player.setHealth(0);}});

        menuButton = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100);
        menuButton.addActionListener(e -> {gameFrame.showPanel("menu");});

        gameFrame.addHoverEffect(menuButton, ImageLibrary.get().optionBtn, ImageLibrary.get().optionBtnHover, 353, 100);
        gameFrame.addHoverEffect(killButton, ImageLibrary.get().placeholderBtn, ImageLibrary.get().placeholderBtnHover, 353, 100);

        // temporary next level (replace with the opening thing)
        nextLevelButton = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100);
        nextLevelButton.addActionListener(e -> {requestLevelChange();});

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(killButton);
        bottomPanel.add(menuButton);
        bottomPanel.add(nextLevelButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // chest init
        cardManager = new CardManager(ImageLibrary.get());

    }

    @Override
    public void startGamePanel()
    { 
        // Make World renderer
        super.startGamePanel();
        
        Vector2 centerHalf = new Vector2(getWidth() / 2, getHeight() /  2);
        // Add player 
        player = new Player(centerHalf, 3, 10, 10, this, gameFrame); 
        // Map Creation
        Map bgObject = new Map(ImageLibrary.get().map, new Vector2(100, 500), 1 , this);
        
        
        this.world = new WorldRenderer(player, bgObject, this);
        

        System.out.println("I ran here");
        this.world.setCenterPosition(centerHalf);

        
  
        
        
        
        player.setWorld(world);
        GameObject box1 = new GameObject(300, 300, 50, this);
        box1.setCollider(new RectangleCollider(box1, true, 20, 20, 20, 20));
        GameObject box2 = new GameObject(300, 500, 50, this);
        box2.setCollider(new RectangleCollider(box2, true, 20, 20, 20, 20));
        BlueWisp bluey = new BlueWisp(player.getPosition(), 2, this);
        // Background  object,  scuffed, have to optimize this later

        TreasureChest tr1 = new TreasureChest(100, 100, player, 2, this);

        // Add walls (unmovable) 
        //objects.add(new CollisionObject(300, 300, 50, false)); 
        
        // Add box (movable) 
        //objects.add(new CollisionObject(500, 300, 50, true));

        world.addObject(box1);
        world.addObject(box2);
        world.addObject(tr1);
        world.addObject(bluey);



        player.getStats().debugPrint(); // debug
    }

    @Override
    public void stopGamePanel()
    {
        // Does nothing on exit i think
    }

    // chest methods
    public void openChest() {

        if (showChestUI) return;

        showChestUI = true;
        chestState = 1;
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

    @Override
    public void update() {

        super.update(); // THIS already updates world + player

                    // when esc is press, show pause panel
            if (inputManager.isPausePressed()) {
                gameFrame.showPanel("pause");
                return;
            }
        

            if (chestState == 1) {

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
                return;
                }

                LevelFactory.loadLevel(currentLevel, world, player, this);

                isFading = false;
                fade = 0f;
            }
        }

       if (showChestUI && chestState == 2 && inputManager.consumeClick()) {

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

    // level methods

    public void setLevelText(String text) {
        levelText = text;
        showLevelText = true;
    }

    public void clearLevelText() {
        showLevelText = false;
    }

    // card stuff
    public void showCards()
    {
        currentCards.clear();

        currentCards.add(cardManager.drawCard(currentLevel));
        currentCards.add(cardManager.drawCard(currentLevel));
        
        showChestUI = true;
        chestSelectionMode = true;

        selectedCardIndex = -1;

        chestAnim = 0f;
        cardFlip = 0f;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D graphics2 = (Graphics2D) g;

        if (showChestUI) {

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
    }

    private void resetUIOnTransition() {
        showChestUI = false;
        currentCards.clear();
        showLevelText = false;
    }


    public void requestLevelChange() {
        isFading = true;

        showChestUI = false;
        showLevelText = false;
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

}