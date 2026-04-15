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
import javax.swing.JLabel;
import javax.swing.JPanel;
import object.*;
import systems.*;

public class GameStart extends PlayableScreen {



    private GameFrame gameFrame;
    private JButton killButton;
    private Image map;

    // chest system
    private boolean showChestUI = false;
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

        map = new ImageLibrary().map;

        JLabel title = new JLabel("Game start");
        title.setFont(title.getFont().deriveFont(32f));
        title.setBounds(100, 100, 400, 100);

        killButton = gameFrame.createImageButton(new ImageLibrary().placeholderBtn, 200, 100);

        killButton.addActionListener(e -> {
        if (player != null) {
            player.setHealth(0);
        }
         });

        JButton menuButton = gameFrame.createImageButton(new ImageLibrary().optionBtn, 200, 100);
        menuButton.addActionListener(e -> {
            gameFrame.showPanel("menu");
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(killButton);
        bottomPanel.add(menuButton);

        add(bottomPanel, BorderLayout.SOUTH);
        add(title, BorderLayout.NORTH);

        // chest init
        cardManager = new CardManager(new ImageLibrary());

        chestButton = new Rectangle(50, 200, 200, 200);
        backButton = new Rectangle(860, 650, 200, 80);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {

                if (!showChestUI && chestButton.contains(e.getPoint())) {
                    currentCards.clear();
                    currentCards.add(cardManager.drawCard());
                    currentCards.add(cardManager.drawCard());
                    currentCards.add(cardManager.drawCard());
                    showChestUI = true;
                    chestAnim = 0f;
                    cardFlip = 0f;
                }

                if (showChestUI && backButton.contains(e.getPoint())) {
                    showChestUI = false;
                }
            }
        });
    }

    @Override
    public void startGamePanel()
    { 
        // Make World renderer
        super.startGamePanel();
        
        Vector2 centerHalf = new Vector2(getWidth() / 2, getHeight() /  2);
        player = new Player(centerHalf, 3, 10, 10, inputManager, gameFrame); 
        world = new WorldRenderer(player);
        world.setCenterPosition(centerHalf);
    

        player.setCollider(new RectangleCollider(player, true));

        // Add player 
    

        

        GameObject box1 = new GameObject(300, 300, 50);
        box1.setCollider(new RectangleCollider(box1, true));

        GameObject box2 = new GameObject(300, 300, 50);
        box2.setCollider(new RectangleCollider(box2, true));

        // Background  object,  scuffed, have to optimize this later
        GameObject bgObject = new GameObject(100, 500, 1);
        bgObject.setImage(new ImageLibrary().map);

        // Add walls (unmovable) 
        //objects.add(new CollisionObject(300, 300, 50, false)); 
        
        // Add box (movable) 
        //objects.add(new CollisionObject(500, 300, 50, true));

        world.addObject(box1);
        world.addObject(box2);
        world.addObject(bgObject);
    }

    @Override
    public void stopGamePanel()
    {
        // Does nothing on exit i think
    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D graphics2 = (Graphics2D) g;

        

        // // render smooth position
        // graphics2.drawImage(
        //     player.getImage(),
        //     (int) player.getRenderX() - ((int)player.getScaledWidth() / 2) ,
        //     (int) player.getRenderY() - ((int) player.getScaledHeight() / 2),
        //     player.getScaledWidth(),
        //     player.getScaledHeight(),
        //     null
        // );
        // Debug mode, make a point at the middle of the object
        // graphics2.setColor(Color.BLUE);
        // graphics2.fillRect(object1.getX(), object1.getY(), 4, 4);


        if (!showChestUI) {

            graphics2.drawImage(
                new ImageLibrary().placeholderBtn,
                chestButton.x,
                chestButton.y,
                chestButton.width,
                chestButton.height,
                null
            );
        }

        if (showChestUI) {

            graphics2.setColor(new Color(0, 0, 0, 200));
            graphics2.fillRect(0, 0, getWidth(), getHeight());

            int cardW = 300;
            int cardH = 450;

            int spacing = 40;
            int totalWidth = (cardW * 3) + (spacing * 2);

            int startX = (getWidth() - totalWidth) / 2;
            int y = (getHeight() - cardH) / 2;

            for (int i = 0; i < currentCards.size(); i++) {

                Card c = currentCards.get(i);

                if (c != null && c.image != null) {

                    int x = startX + i * (cardW + spacing);

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

            graphics2.drawImage(
                new ImageLibrary().backBtn,
                backButton.x,
                backButton.y,
                backButton.width,
                backButton.height,
                null
            );
        }
    }

    @Override
    public void update()
    {
        super.update();
        
        // Move player
        if(world != null)
        {
            if(world.getPlayer() != null)
            {
                world.getPlayer().update();
            }
        }
        // logic handled in paint/update cycle
        // update game logic

        // when esc is press, show pause panel
         if (inputManager.isPausePressed()) {
            gameFrame.showPanel("pause");
            return;
        }
        
        /////// Should make an arrayList for every GameObject present in a scene so that they autoUpdate 
    }
}