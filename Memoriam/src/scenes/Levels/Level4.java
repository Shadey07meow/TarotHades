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
        TreasureChest tr1 = new TreasureChest(
            Vector2.add(
                this.player.getPosition(), 
                Vector2.multiply(
                    Vector2.UP, 
                    -57 * 120)), 
            player, 2, this);
        
        // Add Barrier objects
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120, 0)),
                this,
                new Bounds(4 * 120, 4 * 120, 4 * 120, 4 * 120
            )));

        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120, 4 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));
            
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-5 * 120, -20 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));

        //
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120, -(35) * 120)),
                this,
                new Bounds(4 * 120, 4 * 120, 4 * 120, 4 * 120
            )));

        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120, -(35-4) * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));
            
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-5 * 120, -(20 + 35) * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));



        world.addObject(tr1);    
    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public Map setMap()
    {
        return new Map(ImageLibrary.get().map4, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 26 * 120)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }


}
