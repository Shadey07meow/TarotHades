package scenes.Levels;


import collision.*;
import images.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import object.*;
import systems.*;
import scenes.*;


public class Level1 extends PlayableScreen {
    
    public Level1(GameFrame g)
    {
        super("Level 1", 1, g);
    }

    @Override
    public void startGamePanel()
    {

    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public Map setMap()
    {
        return null;
    }

    public Player setPlayer()
    {
        return null;
    }


}
