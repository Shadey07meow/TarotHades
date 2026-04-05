import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStart extends PlayableScreen {

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
        startGamePanel();
    }

    @Override
    protected void onExit()
    {
        stopGamePanel();
    }
}