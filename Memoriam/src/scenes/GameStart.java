package scenes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import scenes.*;
import systems.*;
import collision.*;
import images.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.*;

public class GameStart extends PlayableScreen {



    private GameFrame gameFrame;
    private JButton killButton;
    private Image map;


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