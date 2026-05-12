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

        // this.fx.generateLoadingScreen();

                SoundManager.get().playMusic("gameMusic");

        // Add Treasure box
        TreasureChest tr1 = new TreasureChest(
            Vector2.add(
                this.center, 
                Vector2.multiply(
                    Vector2.UP, 
                    -45 * 64)), 
            player, 2, this);


            // Add Barrier objects
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(13 * 64, 15 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(10 * 64, 17 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(12 * 64, 2 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(12 * 64, -1 * 64)),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(9 * 64, 0 * 64)),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(9 * 64, 2 * 64)),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64, -1 * 64)),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13 * 64, 3 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-12 * 64, -3 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64, -15 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13  * 64, -11 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(8 * 64, -25 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(12 * 64, -20 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64, -40 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(15 * 64, -45 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64, -43 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-10 * 64, -45 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        // Add Enemy Objects

        //mid right
        for (int i = 0; i <= 3; i++)
        {
            world.addObject(new PurpleWisp(
                Vector2.add(
                    player.getPosition(),
                    new Vector2(i * 64, -40 * 64)
                ),
                2,
                this
            ));
        }

        //mid left
        for (int i = 0; i <= 5; i++)
        {
            world.addObject(new PurpleWisp(
                Vector2.add(
                    player.getPosition(),
                    new Vector2(i * -64, -35 * 64)
                ),
                2,
                this
            ));
        }

        //bottom left
        for (int i = 0; i <= 5; i++)
        {
            world.addObject(new PurpleWisp(
                Vector2.add(
                    player.getPosition(),
                    new Vector2(i * 80, -20 * 64)
                ),
                2,
                this
            ));
        }

        //bottom right
        for (int i = 0; i <= 3; i++)
        {
            world.addObject(new PurpleWisp(
                Vector2.add(
                    player.getPosition(),
                    new Vector2(i * -80, -20 * 64)
                ),
                2,
                this
            ));
        }

        PurpleWisp miniBoss = new PurpleWisp(
            Vector2.add(
                this.center,
                new Vector2(0, -45 * 64)),
            4,
            this
        );
        
        miniBoss.spawnBossPurple();
        world.addObject(tr1);

    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public MapObj setMap()
    {
        return new MapObj(ImageLibrary.get().map2, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 8* 120)), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }

}
