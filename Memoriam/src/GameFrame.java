import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    private final GameStart gameStart;
    private final CreditScreen creditScreen;
    private final StartScreen startScreen;
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

        creditScreen = new CreditScreen(this);
        panelChanger.add(creditScreen, "credits");

        startScreen = new StartScreen(this);
        panelChanger.add(startScreen, "menu");

        
        add(panelChanger);
        cardLayout.show(panelChanger, "menu");

        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
        
    }
    
    public void startGame(){
        try {
            cardLayout.show(panelChanger, "start");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Won't show start screen");
        }
    }

        public void showCredits(){
        try {
            cardLayout.show(panelChanger, "credits");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Won't show menu screen");
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
    
   