import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditScreen extends UIScreen {
    public CreditScreen(GameFrame gameFrame){
        // Credit screen, shows people who contributed to the project
        super("credits");
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Credits", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 64));
        add(title, BorderLayout.NORTH);

        JButton exitBtn = gameFrame.createImageButton("/assets/startBtn.PNG", 200, 100);
        exitBtn.addActionListener(e -> 
            gameFrame.showPanel("menu")
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(exitBtn);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
