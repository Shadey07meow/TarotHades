import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class StartScreen extends JPanel{
    // Initial screen when opening the game
    private Image backgroundImage;

    public StartScreen(GameFrame gameFrame)
    {
        this.backgroundImage = new ImageIcon(getClass().getResource("/assets/backgroundImage.PNG")).getImage();
        setLayout(new GridBagLayout());

                JButton startBtn = gameFrame.createImageButton("/assets/startBtn.PNG", 500, 300);

        startBtn.addActionListener(e -> {
            gameFrame.startGame();
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;              
        gbc.weightx = 1.0;         
        gbc.weighty = 1.0;        
        gbc.anchor = GridBagConstraints.SOUTHWEST;

        gbc.insets = new Insets(60, 20, 250, 0); // spacing from edges

        add(startBtn, gbc);
        //panelChanger.add(panel, "menu")
    }

    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    

}
