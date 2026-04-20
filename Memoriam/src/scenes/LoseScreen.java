package scenes;

import images.ImageLibrary;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoseScreen extends UIScreen {

    private JButton retryBtn;
    private JButton menuBtn;
    private final Image loseScreen;

    public LoseScreen(GameFrame gameFrame) {

        super("lose");

        this.loseScreen = ImageLibrary.get().loseScreen;

        setLayout(new BorderLayout());

        retryBtn = gameFrame.createImageButton(ImageLibrary.get().optionBtn, 250, 100);
        retryBtn.addActionListener(e -> {
            gameFrame.showPanel("start");
        });

        menuBtn = gameFrame.createImageButton(ImageLibrary.get().optionBtn, 250, 100);
        menuBtn.addActionListener(e -> {
            gameFrame.showPanel("menu");
        });

        gameFrame.addHoverEffect(
            menuBtn,
            ImageLibrary.get().optionBtn,
            ImageLibrary.get().optionBtnHover,
            353, 100
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(retryBtn);
        buttonPanel.add(menuBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (loseScreen != null)
        {
            g.drawImage(loseScreen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}