import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameStart extends PlayableScreen {
    Image up = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolUp.png")).getImage();
    Image down = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolDown.png")).getImage();
    Image left = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolLeft.png")).getImage();
    Image right = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolRight.png")).getImage();

    
    Player object1 = null;

    public GameStart(GameFrame gameFrame) {
        super("start");

        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Game start");
        title.setFont(title.getFont().deriveFont(32f));
        title.setBounds(100, 100, 400, 100);

        JButton menuButton = gameFrame.createImageButton("/assets/startBtn.PNG", 200, 100);
        menuButton.addActionListener(e -> {
            gameFrame.showPanel("menu");
        });

        add(menuButton, BorderLayout.SOUTH);
        add(title, BorderLayout.NORTH);
    }

    @Override
    protected void onInitiate()
    {
        object1 = new Player(2 * 32, 2 * 32, 5, 5, 10, inputManager, up, down, left, right);
        startGamePanel();
    }

    @Override
    protected void onExit()
    {
        stopGamePanel();
    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D graphics2 = (Graphics2D) g;

        // update game logic
        if (object1 != null)
        {
            object1.update();
            object1.interpolate(1); 
            // alpha = 0.25, you move towards the target by 25% everytime.
            // makes it smoother
        }

        // render smooth position
        graphics2.drawImage(
            object1.getImage(),
            (int) object1.getRenderX(),
            (int) object1.getRenderY(),
            object1.getScaledWidth(),
            object1.getScaledHeight(),
            null
        );
    }

    @Override
    public void update()
    {
        // logic handled in paint/update cycle
    }
}