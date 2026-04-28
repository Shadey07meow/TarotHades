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


public class Level4 extends PlayableScreen {
    
    public Level4(GameFrame g)
    {
        super("Level 4", 4, g);
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
        return new Map(ImageLibrary.get().map4, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 15 * 120)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }


}
