package scenes;

import images.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuScreen extends UIScreen {

    private final Image backgroundImage;
    private final JButton startBtn;
    private final JButton creditBtn;
    private final JButton exitBtn;

    public MenuScreen(GameFrame gameFrame) {

        super("menu");
        this.backgroundImage = new ImageLibrary().background;

        // Buttons
        startBtn = gameFrame.createImageButton(new ImageLibrary().startBtn, 353, 100);
        creditBtn = gameFrame.createImageButton(new ImageLibrary().optionBtn, 353, 100);
        exitBtn = gameFrame.createImageButton(new ImageLibrary().exitBtn, 353, 100);

        styleButton(startBtn);
        styleButton(creditBtn);
        styleButton(exitBtn);

        // Actions
        startBtn.addActionListener(e -> gameFrame.showPanel("start"));
        creditBtn.addActionListener(e -> gameFrame.showPanel("credits"));
        exitBtn.addActionListener(e -> System.exit(0));

        setLayout(new BorderLayout());

        // Left panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);

        //Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
            450, // Up and Down (lower = increase, higher = decrease)
            100, // Left and Right (move right = increase, left = decrease)
            0,
            0
        ));


        Dimension btnSize = new Dimension(370, 100);

        startBtn.setPreferredSize(btnSize);
        startBtn.setMaximumSize(btnSize);

        creditBtn.setPreferredSize(btnSize);
        creditBtn.setMaximumSize(btnSize);

        exitBtn.setPreferredSize(btnSize);
        exitBtn.setMaximumSize(btnSize);

  
        buttonPanel.add(startBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(creditBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(exitBtn);

        // Add to container and screen
        leftPanel.add(buttonPanel, BorderLayout.WEST);
        add(leftPanel, BorderLayout.WEST);
    }

    // Clean button styling
    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}