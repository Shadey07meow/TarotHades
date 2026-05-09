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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import systems.*;

public class MenuScreen extends UIScreen implements Runnable {

    private final Image backgroundImage;
    private final JButton startBtn;
    private final JButton creditBtn;
    private final JButton exitBtn;

    private boolean inMenu = true;

    private Vector2 boboPosition = new Vector2(-20, 0);
    private Vector2 logoPosition = new Vector2(520, 225);
    private int lWidth = (int)(406 * 2.4) ;
    private int lHeight = (int)(156 * 2.4); 

    private int OlWidth = (int)(406 * 2.6) ;
    private int OlHeight = (int)(156 * 2.6); 

    
        


    private int currentTime = 0;

    public MenuScreen(GameFrame gameFrame) {

        super("menu", gameFrame);
        this.backgroundImage = ImageLibrary.get().background;
        Thread menuThread = new Thread(this);
        this.currentTime = 0;
        menuThread.start();

        // Buttons
        startBtn = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100);
        creditBtn = gameFrame.createImageButton(ImageLibrary.get().optionBtn, 353, 100);
        exitBtn = gameFrame.createImageButton(ImageLibrary.get().exitBtn, 353, 100);

        gameFrame.addHoverEffect(startBtn, ImageLibrary.get().startBtn, ImageLibrary.get().startBtnHover, 353, 100);
        gameFrame.addHoverEffect(creditBtn, ImageLibrary.get().optionBtn, ImageLibrary.get().optionBtnHover, 353, 100);
        gameFrame.addHoverEffect(exitBtn, ImageLibrary.get().exitBtn, ImageLibrary.get().exitBtnHover, 353, 100);

        styleButton(startBtn);
        styleButton(creditBtn);
        styleButton(exitBtn);

        // Actions
        startBtn.addActionListener(e -> {
            int select = JOptionPane.showConfirmDialog(null, "Load last run", "Play game", JOptionPane.YES_NO_OPTION);
            
            this.inMenu = false;
            if(select == JOptionPane.YES_OPTION)
            {
                loadRun();
            } else{
                gameFrame.showPanel("loading");
                gameFrame.showPanel("prologue"); 
            }
        });
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

        g.drawImage(ImageLibrary.get().boboLogo,
            (int)this.boboPosition.x,
            (int)this.boboPosition.y,
            getWidth(),
            getHeight(),
        null);


        g.drawImage(ImageLibrary.get().logo,
            (int)this.logoPosition.x - (int)(lWidth/2),
            (int)this.logoPosition.y - (int)(lHeight/2),
            lWidth,
            lHeight,
        null);
    }

    @Override
    public void onInitiate() {
        SoundManager.playMusic("assets/music/TempMainMenu.wav");
        this.inMenu = true;
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
        this.inMenu = false;
    }

    private void loadRun()
    {
        SaveSystem.loadLastSave();
    }


    // Animate screen
    @Override
    public void run()
    {
        while(this.inMenu)
        {
            update();
            repaint();
            try{
                Thread.sleep(1000/60);
            } catch (Exception e)
            {

            }  
        }
    }


    public void update()
    {
        // One second is 1000
        this.currentTime += 1000/60;
        this.boboPosition = new Vector2(this.boboPosition.x + (0.3 * ( Math.sin((double)this.currentTime/500))), this.boboPosition.y);
        this.logoPosition = new Vector2(this.logoPosition.x + (0.3 * ( Math.sin((double)this.currentTime/500))), this.logoPosition.y);
        
    }



}