import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StartScreen extends JPanel{
    // Initial screen when opening the game
    private final Image backgroundImage;
    private JButton startBtn;
    private JButton creditBtn;


    public StartScreen(GameFrame gameFrame)
    {
        this.backgroundImage = new ImageIcon(getClass().getResource("/assets/backgroundImage.PNG")).getImage();
        setLayout(new GridBagLayout());

            startBtn = gameFrame.createImageButton("/assets/startBtn.PNG", 200, 100);
            creditBtn = gameFrame.createImageButton("/assets/optionBtn.PNG", 500,300);

        startBtn.addActionListener(e -> {
            gameFrame.startGame();
        });
        creditBtn.addActionListener(e -> {
            gameFrame.showCredits();
        });

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(180, 120, 0, 0));
        buttonPanel.setMaximumSize(new Dimension(500, 700));

        startBtn.setAlignmentX(LEFT_ALIGNMENT);
        creditBtn.setAlignmentX(LEFT_ALIGNMENT);

        buttonPanel.add(startBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(creditBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
}
