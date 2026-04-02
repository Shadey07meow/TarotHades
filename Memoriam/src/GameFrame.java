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
    private final MenuScreen menuScreen;
    final private CardLayout cardLayout = new CardLayout();
    private final JPanel panelChanger;

    // Game frame will contain the entire frame of the game

    public GameFrame(Dimension resolution) {        
        setTitle("Memoriam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(resolution);

        // Panel containing all the screens we can switch to
        panelChanger = new JPanel(cardLayout);

        // Initiates all screens 
        gameStart = new GameStart(this);
        panelChanger.add(gameStart, gameStart.getShowablePanelName());
        creditScreen = new CreditScreen(this);
        panelChanger.add(creditScreen, creditScreen.getShowablePanelName());
        menuScreen = new MenuScreen(this);
        panelChanger.add(menuScreen, menuScreen.getShowablePanelName());

        
        add(panelChanger);
        cardLayout.show(panelChanger, menuScreen.getShowablePanelName());

        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
        
    }
    
    

    // Method to easily switch between panels, use ShowablePanel.getShowableName() as input
    public void showPanel(String panelName)
    {
        try {
            cardLayout.show(panelChanger, panelName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Screen: [" + panelName + "] does not exist");
        }
    }
    
    // Quick way to create an Image button
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
    
   