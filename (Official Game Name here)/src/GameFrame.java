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

public class GameFrame extends JFrame {

    private GameStart gameStart;
    private CardLayout cardLayout;
    private Image backgroundImage;
    private final JPanel container;

    public GameFrame(Dimension resolution) {

        setTitle("Memoriam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(resolution);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        gameStart = new GameStart(this);

        container.add(gameStart, "start");

        backgroundImage = new ImageIcon(
                getClass().getResource("/assets/backgroundImage.PNG")
        ).getImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.setLayout(new GridBagLayout());


        JButton startBtn = createImageButton("/assets/startBtn.PNG", 500, 300);

        startBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Starting Game...");
        });


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;              
        gbc.weightx = 1.0;         
        gbc.weighty = 1.0;        
        gbc.anchor = GridBagConstraints.SOUTHWEST;

        gbc.insets = new Insets(60, 20, 250, 0); // spacing from edges

        panel.add(startBtn, gbc);

        setContentPane(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void startGame(){
        cardLayout.show(container, "start");
    }

    private JButton createImageButton(String imgPath, int width, int height) {

        ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));


        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JButton button = new JButton(new ImageIcon(scaled));

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }
}   