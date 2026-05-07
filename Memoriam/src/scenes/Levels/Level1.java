package scenes.levels;


import images.*;
import object.*;
import scenes.*;
import systems.*;


public class Level1 extends PlayableScreen {
    
    public Level1(GameFrame g)
    {
        super("Level 1", 1, g);
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
                    -24 * 120)), 
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

                    
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(5 * 120, -35 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));
        
            world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(5 * 120, -33 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));

        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(6 * 120, -30 * 120)),
                this,
                new Bounds(1 * 120, 1 * 120, 1 * 120, 1 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(2 * 120, -34 * 120)),
                this,
                new Bounds(1 * 120, 1 * 120, 1 * 120, 1 * 120
            )));

        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(2 * 120, -32 * 120)),
                this,
                new Bounds(100, 100, 100, 100
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(5 * 120 + 80, -50 * 120)),
                this,
                new Bounds(1 * 120, 1 * 120, 1 * 120, 1 * 120
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(4 * 120 + 80, -52 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(6 * 120 + 80, -56 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));


        //
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120 + 80, -50 * 120)),
                this,
                new Bounds(1 * 120, 1 * 120, 1 * 120, 1 * 120
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120 + 80, -52 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-6 * 120 + 80, -56 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            )));

            



        // Add Enemy Objects

        world.addObject(new PurpleWisp(
            Vector2.add(new Vector2(120 +10, 120 +1), player.getPosition()), 
            6, 
            this
            ));
        
        

        world.addObject(tr1);
    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public Map setMap()
    {
        return new Map(ImageLibrary.get().map1, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 8* 120)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }


}
