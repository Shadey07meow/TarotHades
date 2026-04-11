package scenes;

import scenes.*;
import systems.*;
import collision.*;
import object.*;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditScreen extends UIScreen {
    private JButton exitBtn;

    public CreditScreen(GameFrame gameFrame){
        // Credit screen, shows people who contributed to the project
        super("credits");
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Credits");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);


        exitBtn = gameFrame.createImageButton("/assets/startBtn.PNG", 150, 60); // change when theres exit img
        exitBtn.addActionListener(e -> {
            gameFrame.showPanel("menu"); // go back to main menu
        });

        // Button panel at bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); 
        bottomPanel.add(exitBtn);
        add(bottomPanel, BorderLayout.SOUTH); 
    }
}
