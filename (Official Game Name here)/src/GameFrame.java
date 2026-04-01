import javax.swing.JFrame;
import java.awt.Dimension;

public class GameFrame extends JFrame{

    public GameFrame(Dimension resolution)
    {
        setPreferredSize(resolution);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Tarot Hades");
        pack();    
    }
}
