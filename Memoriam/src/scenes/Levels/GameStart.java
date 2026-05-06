package scenes.Levels;

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
import scenes.GameFrame;
import scenes.PlayableScreen;
import systems.*;


public class GameStart extends PlayableScreen {

    // main game screen
    //private JButton killButton;
    //private JButton menuButton;




    public GameStart(GameFrame gameFrame) {
        super("start", 0, gameFrame);


                

        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        // killButton = gameFrame.createImageButton(ImageLibrary.get().placeholderBtn, 200, 100);
        // killButton.addActionListener(e -> {
        // if (player != null) {
        //    player.minusHP(1); // minus 1 heart
        // }});
        
        // // button
        // killButton = gameFrame.createImageButton(ImageLibrary.get().placeholderBtn, 353, 100);
        // killButton.addActionListener(e -> { if (player != null) {player.setHealth(0);}});

        // menuButton = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100);
        // menuButton.addActionListener(e -> {gameFrame.showPanel("menu");});

        // gameFrame.addHoverEffect(menuButton, ImageLibrary.get().optionBtn, ImageLibrary.get().optionBtnHover, 353, 100);
        // gameFrame.addHoverEffect(killButton, ImageLibrary.get().placeholderBtn, ImageLibrary.get().placeholderBtnHover, 353, 100);

        // temporary next level (replace with the opening thing)
        //nextLevelButton = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100);
        //nextLevelButton.addActionListener(e -> {requestLevelChange();});

        // JPanel bottomPanel = new JPanel();
        // bottomPanel.setOpaque(false);
        // bottomPanel.add(killButton);
        //  bottomPanel.add(menuButton);
        // bottomPanel.add(nextLevelButton);

        // add(bottomPanel, BorderLayout.SOUTH);

        // chest init
        

    }

    @Override
    public void startGamePanel()
    { 
        
        // GameObject box1 = new GameObject(300, 300, 50, this);
        // box1.setCollider(new RectangleCollider(box1, true, 20, 20, 20, 20));
        // GameObject box2 = new GameObject(300, 500, 50, this);
        TreasureChest tr1 = new TreasureChest(
            Vector2.add(
                this.player.getPosition(), 
                Vector2.multiply(
                    Vector2.UP, 
                    -300)), 
            player, 2, this);
        

        // BlueWisp bluey = new BlueWisp(Vector2.add(player.getPosition(), Vector2.multiply(Vector2.RIGHT, -100)) , 2, this);
         BlueWisp bluey2 = new BlueWisp(Vector2.add(player.getPosition(), Vector2.multiply(Vector2.RIGHT, -300)), 2, this);
         FinalBoss bossy = new FinalBoss(Vector2.add(player.getPosition(), Vector2.multiply(Vector2.RIGHT, -500)), 2, this);

         PurpleWisp purply = new PurpleWisp(Vector2.add(player.getPosition(), Vector2.multiply(Vector2.RIGHT, -600)), 2, this);

        // box1.getCollider().setIsMovable(false);
        // world.addObject(box1);
        // world.addObject(box2);
        // world.addObject(bluey);
        world.addObject(bluey2);
        world.addObject(purply);
        world.addObject(bossy);
        
        world.addObject(tr1);
        //world.addObject(new BarrierObject(player.getPosition(), this, new Bounds(50, 50, 50, 50)));

        player.getStats().debugPrint(); // debug
    }

    @Override
    public void stopGamePanel()
    {
        // Does nothing on exit i think
    }

    @Override
    public void update() {

        super.update(); // THIS already updates world + player
    }   

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    @Override
    public Map setMap()
    {
        return new Map(ImageLibrary.get().map0, player.getPosition(), 1 , this);
    }

    @Override
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }

}