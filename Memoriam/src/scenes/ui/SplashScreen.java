package scenes.ui;

import images.ImageLibrary;
import scenes.templates.UIScreen;
import systems.SoundManager;

import java.awt.*;
import javax.swing.*;

public class SplashScreen extends UIScreen {

    private float alpha = 1f;
    private Timer fadeTimer;
    private final GameFrame frame;

    private final Image gif;

    public SplashScreen(GameFrame frame) {
        super("splashScreen", frame);
        this.frame = frame;

        setLayout(null);
        setBackground(Color.BLACK);

        gif = ImageLibrary.get().splash;
    }

    @Override
    public void onInitiate() {

        alpha = 1f;


        repaint();

        Timer wait = new Timer(500, e -> startFade());
        wait.setRepeats(false);
        wait.start();
    }

    private void startFade() {

        fadeTimer = new Timer(30, e -> {
            alpha -= 0.02f;

            if (alpha <= 0) {
                alpha = 0;
                frame.showPanel(frame.getPanel("menu").getShowablePanelName());
                fadeTimer.stop();
            }

            repaint();
        });

        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // black background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // centered gif
        int x = 128;
        int y = 128;

        g2.drawImage(gif, getWidth() / 2 - x/2, getHeight() / 2 - y/2  , x, y, this);
        g2.dispose();
    }

    @Override
    public String getShowablePanelName() {
        return "splashScreen";
    }
}