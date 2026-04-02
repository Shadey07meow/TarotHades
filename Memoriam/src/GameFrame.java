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
    final private CardLayout cardLayout = new CardLayout();
    private final JPanel panelChanger;

    // Game frame will contain the entire frame of the game

    public GameFrame(Dimension resolution) {        
        setTitle("Memoriam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(resolution);

        
        panelChanger = new JPanel(cardLayout);

        gameStart = new GameStart(this);
        panelChanger.add(gameStart, "start");

        StartScreen startScreen = new StartScreen(this);
        
        panelChanger.add(startScreen, "menu");

        
        add(panelChanger);
        cardLayout.show(panelChanger, "menu");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public void startGame(){
        try {
            cardLayout.show(panelChanger, "start");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Won't show start screen");
        }
    }

    public void showMenu(){
        try {
            cardLayout.show(panelChanger, "menu");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Won't show menu screen");
        }
    }

    
    public JButton createImageButton(String imgPath, int width, int height) {

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
    
   