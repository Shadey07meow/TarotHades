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


public class Level3 extends PlayableScreen {
    
    public Level3(GameFrame g)
    {
        super("Level 3", 3, g);
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

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, 0)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));  


        // Top part of the map
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, -51 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120, -51 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120, -46 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, -56 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));                
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120, -56 * 120)),
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
        return new Map(ImageLibrary.get().map3, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 26 * 120)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }


}
