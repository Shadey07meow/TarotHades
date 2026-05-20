package scenes.ui;

import images.ImageLibrary;
import scenes.templates.UIScreen;
import systems.SoundManager;

import java.awt.*;
import javax.swing.*;

public class SplashScreen extends UIScreen implements Runnable{

    private float alpha = 1f;
    private Timer fadeTimer;
    private final GameFrame frame;

    private final Image gif;

    public SplashScreen(GameFrame frame) {
        super("splashScreen", frame);
        this.frame = frame;

        setLayout(null);
        setBackground(Color.BLACK);

        gif = new ImageIcon("assets/MainAssets/splash.gif").getImage();

    }

    @Override
    public void onInitiate() {   }

    public void startSplash()
    {
        Timer wait = new Timer(500, e -> startFade());
        wait.setRepeats(false);
        wait.start();
    }

    private  void startFade() {


        Thread loadThread = new Thread(this);
        fadeTimer = new Timer(3, e -> {


            alpha -= 0.00045;

            if (alpha <= 0.001) {
                alpha = 0;
                //frame.showPanel(frame.getPanel("menu").getShowablePanelName());
                fadeTimer.stop();
                frame.init();
            }
            repaint();
        });

        loadThread.start();
        fadeTimer.start();
    }

    @Override
    public void run()
    {
        ImageLibrary.get();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // black background
        g2.setColor(Color.BLACK);
        //g2.fillRect(0, 0, getWidth(), getHeight());

        // centered gif
        int x = 128;
        int y = 128;

        g.drawImage(gif, (getWidth() - x) / 2, (getHeight()/2) - (y/2), x, y, null);
        
        g2.dispose();
    }

    @Override
    public String getShowablePanelName() {
        return "splashScreen";
    }
}