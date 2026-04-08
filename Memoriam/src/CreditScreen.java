import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditScreen extends UIScreen {
    public CreditScreen(GameFrame gameFrame){
        // Credit screen, shows people who contributed to the project
        super("credits");
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Credits");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        
        add(title, BorderLayout.NORTH);
    }
}
