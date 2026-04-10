import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoseScreen extends UIScreen {

    private JButton retryBtn;
    private JButton menuBtn;

    public LoseScreen(GameFrame gameFrame) {
        super("lose");

        setLayout(new BorderLayout());

        JLabel title = new JLabel("You Lost");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setHorizontalAlignment(JLabel.CENTER);

        add(title, BorderLayout.NORTH);

        retryBtn = gameFrame.createImageButton("/assets/startBtn.PNG", 250, 100);
        retryBtn.addActionListener(e -> {
            gameFrame.showPanel("start"); // retry game
        });

        menuBtn = gameFrame.createImageButton("/assets/optionBtn.PNG", 250, 100);
        menuBtn.addActionListener(e -> {
            gameFrame.showPanel("menu"); // back to menu
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(retryBtn);
        buttonPanel.add(menuBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }
}