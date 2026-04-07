import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameStart extends PlayableScreen {


    
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
        object1 = new Player(getWidth() / 2, getHeight() / 2, 3, 5, 10, inputManager);
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
        // render smooth position
        graphics2.drawImage(
            object1.getImage(),
            (int) object1.getRenderX() - ((int)object1.getScaledWidth() / 2) ,
            (int) object1.getRenderY() - ((int) object1.getScaledHeight() / 2),
            object1.getScaledWidth(),
            object1.getScaledHeight(),
            null
        );
        // Debug mode, make a point at the middle of the object
        // graphics2.setColor(Color.BLUE);
        // graphics2.fillRect(object1.getX(), object1.getY(), 4, 4);
    }

    @Override
    public void update()
    {
        // logic handled in paint/update cycle
        // update game logic
        
        /////// Should make an arrayList for every GameObject present in a scene so that they autoUpdate 
        if (object1 != null)
        {
            object1.update();
        }  
    }
}