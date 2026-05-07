package scenes.levels;


import images.*;
import object.*;
import scenes.*;
import systems.*;


public class BossLevel extends PlayableScreen {
    
    public BossLevel(GameFrame g)
    {
        super("Boss level", 5, g);
    }

    @Override
    public void startGamePanel()
    {
        TreasureChest tr1 = new TreasureChest(
            Vector2.add(
                this.player.getPosition(), 
                Vector2.multiply(
                    Vector2.UP, 
                    -24 * 120)),  
            player, 2, this);

        world.addObject(tr1);


        // Add Barriers
        
        // Add Barrier objects
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-9 * 120, 0)),
                this,
                new Bounds(4 * 120, 4 * 120, 4 * 120, 4 * 120
            )));
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(9 * 120, 0)),
                this,
                new Bounds(4 * 120, 4 * 120, 4 * 120, 4 * 120
            )));


        // Pillars

        // Right pillars
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, -35 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, -31 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, -27 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));

        // Left Pillars

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120, -35 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120, -31 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-7 * 120, -27 * 120)),
                this,
                new Bounds(2 * 120, 2 * 120, 2 * 120, 2 * 120
            )));


        // Top Colliders
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
                new Vector2(-7 * 120, -56 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            ))); 

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
                new Vector2(7 * 120, -56 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            ))); 

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(7 * 120, -45 * 120)),
                this,
                new Bounds(3 * 120, 3 * 120, 3 * 120, 3 * 120
            ))); 
        
        FinalBoss bossy = new FinalBoss(
            Vector2.add(
                this.center,
                new Vector2(0, -50 * 64)
            ),
            2, 
            this
        );

        world.addObject(bossy);

    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public Map setMap()
    {
        return new Map(ImageLibrary.get().map5, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 29 * 64)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }




}
