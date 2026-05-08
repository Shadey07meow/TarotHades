package scenes.levels;


import images.*;
import object.*;
import scenes.*;
import systems.*;


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
                    -50 * 64)), 
            player, 2, this);
        
        // Add Barrier objects
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-12 * 64, 15 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-10 * 64, -1 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13 * 64, 2 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64, -3 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-10 * 64, -12 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));
         
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13 * 64, -17 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-12 * 64, -22 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-15  * 64, -32 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
                Vector2.add(
                    this.center,
                    new Vector2(-11  * 64, -35 * 64)),
                    this,
                    new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
                )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14  * 64, -46 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-10  * 64, -48 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
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
        return new Map(ImageLibrary.get().map4, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 8* 120)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    } 


}
