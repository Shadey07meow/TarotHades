import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Color;

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

        // Game start is the initial playable screen of the game
        // A playable screen is a ShowablePanel which can moveObjects in it
        // We first need a way to store positions in the map
        
    }

    @Override
    protected void onInitiate()
    {
        object1 = new Player(2 * 32, 2 * 32 , 32, 2, 10, inputManager);
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

        Graphics2D graphics2 = (Graphics2D)g;


        graphics2.setColor(Color.BLUE);
        graphics2.fillRect(object1.getX(), object1.getY(), 1 * object1.getScale(), 1 * object1.getScale());
    }

    @Override
    public void update()
    {

    }
}