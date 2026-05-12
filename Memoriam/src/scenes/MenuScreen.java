package scenes;

import images.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import systems.*;

public class MenuScreen extends UIScreen implements Runnable, MouseListener, MouseMotionListener {

    private final Image backgroundImage;
    private final JButton startBtn;
    private final JButton practiceBtn;
    private final JButton exitBtn;
    private boolean startingGame = false;

    private boolean inMenu = true;
    private Vector2 mousePosition = new Vector2();

    private Vector2 boboPosition = new Vector2(-20, 0);
    private Vector2 logoPosition = new Vector2(520, 225);
    private int lWidth = (int)(406 * 2.4) ;
    private int lHeight = (int)(156 * 2.4); 

    private int OlWidth = (int)(406 * 2.6) ;
    private int OlHeight = (int)(156 * 2.6); 
    Thread menuThread = new Thread(this);

    int width = 500;
    int height = 250;
    // Start thing
    Rectangle backDrop = null;

    int width1 = 200;
    int height1 = 70;
    int xOffset1 = 0;
    int yOffset1 = -10;

    Rectangle loadGameButton = null;    
    int width2 = 200;
    int height2 = 70;
    int xOffset2 = 0;
    int yOffset2 = 60;
    Rectangle newGameButton= null;


    Image loadRunBtnImage = ImageLibrary.get().loadSaveBtn;
    Image startNewRunBtnImage = ImageLibrary.get().newSaveButton;


    
        


    private int currentTime = 0;

    public MenuScreen(GameFrame gameFrame) {

        super("menu", gameFrame);
        this.backgroundImage = ImageLibrary.get().background;
        this.currentTime = 0;


        // Buttons
        startBtn = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100);
        practiceBtn = gameFrame.createImageButton(ImageLibrary.get().practiceBtn, 353, 100);
        exitBtn = gameFrame.createImageButton(ImageLibrary.get().exitBtn, 353, 100);

     

        gameFrame.addHoverEffect(startBtn, ImageLibrary.get().startBtn, ImageLibrary.get().startBtnHover, 353, 100);
        gameFrame.addHoverEffect(practiceBtn, ImageLibrary.get().practiceBtn, ImageLibrary.get().practiceBtnHover, 353, 100);
        gameFrame.addHoverEffect(exitBtn, ImageLibrary.get().exitBtn, ImageLibrary.get().exitBtnHover, 353, 100);

        styleButton(startBtn);
        styleButton(practiceBtn);
        styleButton(exitBtn);

        // Actions
        startBtn.addActionListener(e -> {


            if(SaveSystem.getLevel() == 0)
            {
                startNewGame();
            } else
            {
                startingGame = true;                
            }


            // JButton newGameButton = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100); 
            // JButton loadButton = gameFrame.createImageButton(ImageLibrary.get().startBtn, 353, 100); 
            // Object[] options = {newGameButton, loadButton};
            // int select = JOptionPane.showOptionDialog(
            //     null,
            //     "Load last run", 
            //     "Play game", 
            //     JOptionPane.YES_NO_OPTION, 
            //     JOptionPane.PLAIN_MESSAGE, 
            //     null, 
            //     options, 
            //     options[0]);
            

            // this.inMenu = false;
            // if(select == 0)
            // {
                
            //     loadRun();
            // } else{
            //     startNewGame();
            // }
        });
        practiceBtn.addActionListener(e -> gameFrame.showPanel("infinite"));
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

        practiceBtn.setPreferredSize(btnSize);
        practiceBtn.setMaximumSize(btnSize);

        exitBtn.setPreferredSize(btnSize);
        exitBtn.setMaximumSize(btnSize);

  
        buttonPanel.add(startBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(practiceBtn);
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

    private void startNewGame()
    {
        LevelManager.gFrame.showPanel("loading");
        LevelManager.gFrame.showPanel("prologue"); 
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

        if(startingGame)
        {
            drawStartUI(g);
        }
    }

    @Override
    public void onInitiate() {
        requestFocusInWindow();
        
        SoundManager.playMusic("assets/music/TempMainMenu.wav");
        menuThread = new Thread(this);
        menuThread.start();
        addMouseListener(this);
        addMouseMotionListener(this);

        
        SwingUtilities.invokeLater(() -> {
            backDrop = new Rectangle((getWidth()/2) - (width / 2) , (getHeight()/ 2 - (height/ 2)), width, height);
            loadGameButton = new Rectangle(getWidth() / 2 - (width1 / 2) + xOffset1, (getHeight() / 2 - (height1/ 2)) + yOffset1, width1, height1);
            newGameButton= new Rectangle(getWidth() / 2 - (width2 / 2) + xOffset2, (getHeight() / 2 - (height2/ 2)) + yOffset2, width2, height2);
        });



        this.inMenu = true;
    }

    private void drawStartUI(Graphics g)
    {
        g.drawImage(
            ImageLibrary.get().loadBg,
            backDrop.x,
            backDrop.y,
            backDrop.width,
            backDrop.height,
            null
        );


        
        // g.setColor(Color.BLUE);
        // g.fillRect(
        //     resumeButton.x,
        //     resumeButton.y,
        //     resumeButton.width,
        //     resumeButton.height
        // );

        
        // g.setColor(Color.RED);
        // g.fillRect(
        //     newGameButton.x,
        //     newGameButton.y,
        //     newGameButton.width,
        //     newGameButton.height
        // );

        g.drawImage(
            loadRunBtnImage,
            loadGameButton.x,
            loadGameButton.y,
            loadGameButton.width,
            loadGameButton.height,
            null
        );
        g.drawImage(
            startNewRunBtnImage,
            newGameButton.x,
            newGameButton.y,
            newGameButton.width,
            newGameButton.height,
            null
        );

    }

    @Override
    public void onExit() {
        this.inMenu = false;
        SoundManager.stopMusic();
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

            if (startingGame)
            {
                updateHovers(mousePosition);
            }
            //System.out.println(mousePosition.toString());
            repaint();
            try{
                Thread.sleep(1000/60);
            } catch (Exception e)
            {

            }  
        }
    }

    public void clickedScreen(int x, int y)
    {
        if(startingGame)
        {
            if(loadGameButton.contains(x, y))
            {
                loadRun();
                startingGame = false;
            } else  if(newGameButton.contains(x, y))
            {
                startNewGame();
                startingGame = false;
            }
        }
    }

    public void updateHovers(Vector2 mousePosition)
    {

            if(loadGameButton.contains((int)mousePosition.x, (int)mousePosition.y))
            {
                loadRunBtnImage = ImageLibrary.get().loadSaveBtnHover;
                startNewRunBtnImage = ImageLibrary.get().newSaveButton;
            } else  if(newGameButton.contains((int)mousePosition.x, (int)mousePosition.y))
            {
                loadRunBtnImage = ImageLibrary.get().loadSaveBtn;
                startNewRunBtnImage = ImageLibrary.get().newSaveButtonHover;            
            }

    }


    public void update()
    {
        // One second is 1000
        this.currentTime += 1000/60;
        this.boboPosition = new Vector2(this.boboPosition.x + (0.3 * ( Math.sin((double)this.currentTime/500))), this.boboPosition.y);
        this.logoPosition = new Vector2(this.logoPosition.x + (0.3 * ( Math.sin((double)this.currentTime/500))), this.logoPosition.y);
        
    }

    @Override
    public void mousePressed(MouseEvent m){}

    @Override
    public void mouseReleased(MouseEvent m){}

    @Override
    public void mouseClicked(MouseEvent m) {clickedScreen(m.getX(), m.getY());}
    
    @Override
    public void mouseEntered(MouseEvent m){}
    
    @Override
    public void mouseExited(MouseEvent m){}

    @Override
    public void mouseMoved(MouseEvent m) {
        mousePosition.x = m.getX();
        mousePosition.y = m.getY();
    }

    @Override
    public void mouseDragged(MouseEvent m) {
        mousePosition.x = m.getX();
        mousePosition.y = m.getY();
    }


}