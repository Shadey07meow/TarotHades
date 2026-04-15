package scenes;

import scenes.*;
import systems.*;
import collision.*;
import object.*;
import images.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;



public class GameFrame extends JFrame {

    private final GameStart gameStart;
    private final CreditScreen creditScreen;
    private final MenuScreen menuScreen;
    private final LoseScreen loseScreen;
    private final PauseScreen pauseScreen;
    final private CardLayout cardLayout = new CardLayout();
    private final JPanel parentPanel;
    Image cursor = new ImageLibrary().quillCursor;
    
    private ArrayList<ShowablePanel> allPanels = new ArrayList<ShowablePanel>();

    // Game frame will contain the entire frame of the game

    public GameFrame(Dimension resolution) {        
        setTitle("Memoriam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(resolution);
        setUndecorated(true);   
        
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            cursor,
            new Point(0, 0),
            "cursor"
        );

        setCursor(customCursor);

        // Panel containing all the screens we can switch to
        parentPanel = new JPanel(cardLayout);
        

        // Initiates all screens 
        gameStart = new GameStart(this);
        creditScreen = new CreditScreen(this);
        menuScreen = new MenuScreen(this);
        loseScreen = new LoseScreen(this);
        pauseScreen = new PauseScreen(this);



        this.allPanels.add(gameStart);
        this.allPanels.add(creditScreen);
        this.allPanels.add(menuScreen);
        this.allPanels.add(loseScreen);
        this.allPanels.add(pauseScreen);
       
        // Adds all panels to panelManager
        for(ShowablePanel curPanel : allPanels)
        {
            parentPanel.add(curPanel, curPanel.getShowablePanelName());
        }

        
        add(parentPanel);
        showPanel(menuScreen.getShowablePanelName());

        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
    }
    
    

    // Method to easily switch between panels, use ShowablePanel.getShowableName() as input
    public void showPanel(String panelName)
    {
        try {
            for(ShowablePanel curPanel : allPanels)
            {
                if(curPanel.getShowablePanelName().trim().equals(panelName.trim()))
                {
                    cardLayout.show(parentPanel, panelName);
                    curPanel.onInitiate();
                } else
                {
                    curPanel.onExit();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    // Quick way to create an Image button
    public JButton createImageButton(Image img, int width, int height) {




        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JButton button = new JButton(new ImageIcon(scaled));

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }
    
}
    
   