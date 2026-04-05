import javax.swing.JPanel;
import java.lang.Thread;


public class PlayableScreen extends ShowablePanel implements Runnable{
    
    // This is a screen where the player can move or any entities can exist in
    // The main reason for having this class is to simultaneously keep track of all the different thing

    Thread gameLoop = null;

    public PlayableScreen(String panelName)
    {
        super(panelName);
        // Lets work on making shit appear first

        // We want to make a thing appear at (5, 2), 5 Right and 2 down from origin
        gameLoop = new Thread(this);
        
    }
    
    public void startGamePanel()
    {
        // Initialized first before running game loop
        try
        {gameLoop.start();}
        catch(Exception e)
        {System.out.println("Game Loop already running");}
    }

    public void stopGamePanel()
    {
        try
        {gameLoop.interrupt();}
        catch(Exception e)
        {System.out.println("Game Loop already stopped");}   
    }

    
    @Override
    public void run()
    {
        while (gameLoop != null) {
            // Call update function
            this.update();

            // Paint the panel every frame
            repaint();
        }
    }

    public void update()
    {
        System.out.println("Hello");
    }
}
