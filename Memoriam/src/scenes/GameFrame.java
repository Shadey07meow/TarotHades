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

    protected final GameStart gameStart;
    private final CreditScreen creditScreen;
    private final MenuScreen menuScreen;
    private final LoseScreen loseScreen;
    private final PauseScreen pauseScreen;
    final private CardLayout cardLayout = new CardLayout();
    private final JPanel parentPanel;
    private final PrologueScreen prologueScreen;
    protected final CutsceneScreen cutsceneScreen;

    private volatile boolean assetsLoaded = false;

    
    Image cursor = ImageLibrary.get().quillCursor;
    
    private ArrayList<ShowablePanel> allPanels = new ArrayList<ShowablePanel>();

    // Game frame will contain the entire frame of the game

    public GameFrame(Dimension resolution) {   
             
        // Sets up the initialization of the window
        setTitle("Memoriam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(resolution);
        setUndecorated(true);   

        // Sets the cursor
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
        
        // Note: Check out what these do later
        prologueScreen = new PrologueScreen(this);
        cutsceneScreen = new CutsceneScreen(this);

        // Adds all panels into the arrayList which contains all the panels
        this.allPanels.add(gameStart);
        this.allPanels.add(creditScreen);
        this.allPanels.add(menuScreen);
        this.allPanels.add(loseScreen);
        this.allPanels.add(pauseScreen);
        this.allPanels.add(prologueScreen);
        this.allPanels.add(cutsceneScreen);
       
        // Adds all panels to panelManager
        for(ShowablePanel curPanel : allPanels)
        {
            parentPanel.add(curPanel, curPanel.getShowablePanelName());
        }

        
        add(parentPanel);
        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
        
        // Initiates a panel
        initPanel();

    }

    private void initPanel()
    {
        showPanel(menuScreen.getShowablePanelName());
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

    // Quick hover button
    public void addHoverEffect(JButton button, Image normal, Image hover, int width, int height) {

        Image normalScaled = normal.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image hoverScaled = hover.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        button.setIcon(new ImageIcon(normalScaled));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setIcon(new ImageIcon(hoverScaled));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setIcon(new ImageIcon(normalScaled));
            }
        });
    }

    // Note: I have no idea what this does, double check later
        // // asset loader trigger??
        // public void loadAssetsAsync(Runnable onFinished){
        //     assetsLoaded = false;

        //     new Thread(() -> {

        //         // Force ImageLibrary initialization (this is your "loading work")
        //         ImageLibrary.get();

        //         assetsLoaded = true;

        //         javax.swing.SwingUtilities.invokeLater(onFinished);

        //     }).start();
        // }


    // private void playCutscene(int level) {

    //     String[] lines = switch (level) {

    //         case 1 -> new String[]{
    //             "You feel something shift.",
    //             "The world responds to your presence."
    //         };

    //         case 2 -> new String[]{
    //             "The halls stretch further.",
    //             "Something is watching."
    //         };

    //         case 3 -> new String[]{
    //             "The ruins whisper.",
    //             "You are not alone."
    //         };

    //         case 4 -> new String[]{
    //             "You’ve come far.",
    //             "But something awaits."
    //         };

    //         case 5 -> new String[]{
    //             "This is it.",
    //             "The final act."
    //         };

    //         default -> new String[]{"..."};
    //     };

    //     cutsceneScreen.setCutscene(lines);
    //     showPanel("cutscene");
    // }

}
    
   