


import images.*;
import java.awt.Dimension;
import systems.LevelManager;
import scenes.ui.GameFrame;
import java.awt.Toolkit;


public class MainGameDriver
{
    final static Dimension RESOLUTION = new Dimension(Toolkit.getDefaultToolkit().getScreenSize()); 

    public static void main(String[] args)
    {
        /// Stores information to give to other classes for organization
        ///     Game Resolution

        
        /// What does this do?
        ///     Runs the application
        ///         Initiate the frame
        // ImageLibrary.get();
        GameFrame gameFrame = new GameFrame(RESOLUTION);

    }
}