import java.awt.Dimension;
import javax.swing.JFrame;

public class GameFrame extends JFrame{

    public GameFrame(Dimension resolution)
    {
        setPreferredSize(resolution);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Memoriam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();    
    }
}
