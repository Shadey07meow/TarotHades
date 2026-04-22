package scenes;

import java.awt.Dimension;


public class MainGameDriver
{
    final static Dimension RESOLUTION = new Dimension(1920, 1080); 

    public static void main(String[] args)
    {
        /// Stores information to give to other classes for organization
        ///     Game Resolution

        
        /// What does this do?
        ///     Runs the application
        ///         Initiate the frame
        
        GameFrame gameFrame = new GameFrame(RESOLUTION);
    }
}