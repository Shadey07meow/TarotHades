import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditScreen extends JPanel {
    public CreditScreen(GameFrame gameFrame){
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Credits");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        
        add(title, BorderLayout.NORTH);
    }
}
