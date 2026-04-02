import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStart extends JPanel {

    public GameStart(GameFrame gameFrame) {
        setBackground(Color.RED);
        setLayout(new BorderLayout()); 

        JLabel title = new JLabel("Memoriam");
        title.setFont(title.getFont().deriveFont(64f));
        title.setBounds(100, 100, 400, 100); 

        JButton menuButton = gameFrame.createImageButton("/assets/startBtn.PNG", 500, 300);

        menuButton.addActionListener(e -> {
            gameFrame.showMenu();
        });

        add(menuButton, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
    }
}