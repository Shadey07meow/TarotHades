package scenes.ui;

import images.ImageLibrary;
import scenes.templates.PlayableScreen;
import scenes.templates.UIScreen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;
import systems.LevelManager;
import systems.SoundManager;

public class LoseScreen extends UIScreen {

    private JButton retryBtn;
    private JButton menuBtn;
    private final Image loseScreen;
    private PlayableScreen playableScreen;

    public LoseScreen(GameFrame gameFrame) {

        super("lose", gameFrame);

        this.loseScreen = ImageLibrary.get().loseScreen;

        setLayout(new BorderLayout());

        retryBtn = gameFrame.createImageButton(ImageLibrary.get().restartBtn, 250, 100);
        retryBtn.addActionListener(e -> {
            SoundManager.get().playSFX("button");
            LevelManager.resetRun();
            if (LevelManager.isInfiniteRun) {
                gameFrame.showPanel("infinite");
            } else {
                gameFrame.showPanel("start");
            }
        });

        menuBtn = gameFrame.createImageButton(ImageLibrary.get().quitBtnGO, 250, 100);
        menuBtn.addActionListener(e -> {
            SoundManager.get().playSFX("button");
            LevelManager.resetRun();
            gameFrame.showPanel("menu");
        });

        gameFrame.addHoverEffect(
            menuBtn,
            ImageLibrary.get().quitBtnGO,
            ImageLibrary.get().quitBtnGO,
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