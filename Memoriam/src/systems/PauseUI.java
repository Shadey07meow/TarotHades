package systems;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import scenes.*;

public class PauseUI {
    // The last thing a dying dog can do
    PlayableScreen scrn  = null;
    
    // Define the UI elements
    Rectangle backDrop = null;
    Rectangle resumeButton = null;
    Rectangle quitButton = null;
    private GameFrame gFrame;

    public PauseUI(PlayableScreen scrn, GameFrame frame)
    {
        this.scrn = scrn;
        int width = 500;
        int height = 300;
        this.backDrop = new Rectangle(scrn.getWidth() / 2 - (width / 2) , scrn.getHeight() / 2 - (height/ 2), width, height);

        int width1 = 150;
        int height1 = 50;
        this.resumeButton = new Rectangle(scrn.getWidth() / 2 - (width1 / 2) , (scrn.getHeight() / 2 - (height1/ 2)) - 30, width1, height1);
    
        int width2 = 150;
        int height2 = 50;
        this.quitButton = new Rectangle(scrn.getWidth() / 2 - (width2 / 2) , (scrn.getHeight() / 2 - (height2/ 2)) + 30, width2, height2);

        this.gFrame = frame;
    }

    // Pause the game
    public void pauseGame()
    {
        this.scrn.setIsPaused(!this.scrn.getIsPaused());
    }

    public void onClicked(Vector2 mousePos)
    {
        // Handles on pause click events
        System.out.println("Hello there I am paaused and you clicked, the location of the click is at : " + mousePos.toString());
        if(resumeButton.contains(mousePos.x, mousePos.y))
        {
            pauseGame();
        } else if (quitButton.contains(mousePos.x, mousePos.y)) 
        {
            this.gFrame.showPanel("menu");
            pauseGame();
        }
    }
    
    // Detect button presses
    public void drawPause(Graphics g)
    {   
        g.setColor(new Color(0, 0, 0, 120));
        g.fillRect(
            0,
            0,
            1920,
            1080
        );

        g.setColor(new Color(255, 255, 255, 255));

        g.drawImage(
            ImageLibrary.get().pauseBg,
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
        //     quitButton.x,
        //     quitButton.y,
        //     quitButton.width,
        //     quitButton.height
        // );

        g.drawImage(
            ImageLibrary.get().resumeBtn,
            resumeButton.x,
            resumeButton.y,
            resumeButton.width,
            resumeButton.height,
            null
        );
        g.drawImage(
            ImageLibrary.get().quitBtn,
            quitButton.x,
            quitButton.y,
            quitButton.width,
            quitButton.height,
            null
        );
        

    }
}
