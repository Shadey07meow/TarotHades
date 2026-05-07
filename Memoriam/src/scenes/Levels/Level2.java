package scenes.levels;


import images.*;
import object.*;
import scenes.*;
import systems.*;


public class Level2 extends PlayableScreen {
    
    public Level2(GameFrame g)
    {
        super("Level 2", 2, g);
    }

    @Override
    public void startGamePanel()
    {
        // Add Treasure box
        TreasureChest tr1 = new TreasureChest(
            Vector2.add(
                this.center, 
                Vector2.multiply(
                    Vector2.UP, 
                    -50 * 64)), 
            player, 2, this);

        world.addObject(tr1);

        // Add Barrier objects
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-8 * 120, 0)),
                this,
                new Bounds(4 * 120, 4 * 120, 4 * 120, 4 * 120
            )));
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(8 * 120, 0)),
                this,
                new Bounds(4 * 120, 4 * 120, 4 * 120, 4 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(5 * 120, -35 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));

        // Tree            
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120, -45 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));

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


    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public Map setMap()
    {
        return new Map(ImageLibrary.get().map2, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 29 * 64)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }


}
