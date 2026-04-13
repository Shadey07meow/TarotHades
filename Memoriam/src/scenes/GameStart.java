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


    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    Player player;
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

        killButton = gameFrame.createImageButton(new ImageLibrary().optionBtn, 200, 100);

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

        objects.clear();

        player = new Player(new Vector2(getWidth() / 2, getHeight() / 2), 3, 5, 10, inputManager, objects); 
        player.setCollider(new RectangleCollider(player, true));

        // Add player 
        objects.add(player); 
        

        GameObject box1 = new GameObject(300, 300, 50);
        box1.setCollider(new RectangleCollider(box1, true));

        GameObject box2 = new GameObject(300, 300, 50);
        box2.setCollider(new RectangleCollider(box2, true));

        // Add walls (unmovable) 
        //objects.add(new CollisionObject(300, 300, 50, false)); 
        
        // Add box (movable) 
        //objects.add(new CollisionObject(500, 300, 50, true));

        objects.add(box1);
        objects.add(box2);
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

        if (map != null) {
            graphics2.drawImage(map, 0, 0, getWidth(), getHeight(), null);
        }

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

        for (GameObject obj : objects) {

            if (obj.getImage() != null) {
                graphics2.drawImage(
                    obj.getImage(),
                    (int) obj.getRenderX() - (obj.getScaledWidth() / 2),
                    (int) obj.getRenderY() - (obj.getScaledHeight() / 2),
                    obj.getScaledWidth(),
                    obj.getScaledHeight(),
                    null
                );
            } else {
                graphics2.setColor(obj.getColor());
                graphics2.fillRect(
                    obj.getX(),
                    obj.getY(),
                    obj.getScaledWidth(),
                    obj.getScaledHeight()
                );
            }
        }
    }

    @Override
    public void update()
    {
        // logic handled in paint/update cycle
        // update game logic
        
        /////// Should make an arrayList for every GameObject present in a scene so that they autoUpdate 
        for (GameObject obj : objects) {
            obj.update();
        }
        
 
    }
}