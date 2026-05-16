package scenes.levels;


import images.*;
import object.Entities.BlueWisp;
import object.Entities.Player;
import object.statics.BarrierObject;
import object.statics.MapObj;
import object.statics.TreasureChest;
import scenes.templates.PlayableScreen;
import scenes.ui.GameFrame;
import systems.*;


public class Level1 extends PlayableScreen {
    
    public Level1(GameFrame g)
    {
        super("Level 1", 1, g);
    }

    @Override
    public void startGamePanel()
    {
        // this.fx.generateLoadingScreen();
        

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
                new Vector2(-13 * 64, 10 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-11 * 64, 15 * 64)), //left/right first, up down second
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64, 17 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 3 * 64, 4 * 64
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64, 6 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(9 * 64, -1 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64, -1 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
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
                new Vector2(-13 * 64, -11 * 64)),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-10 * 64, -14 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(13 * 64, -13 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64, -8 * 64)),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(16 * 64, -30 * 64)),
                this,
                new Bounds(2 * 64, 2* 64, 2 * 64, 2 * 64
            )));
        
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(15 * 64, -31 * 64)),
                this,
                new Bounds(2 * 64, 2* 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64, -44 * 64)),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-12 * 64, -46 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4     * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13 * 64, -42 * 64)),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64, -38 * 64)),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));


        // Add Enemy Objects

        //mid right
        for (int i = 0; i <= 5; i++)
        {
            world.addObject(new BlueWisp(
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
            world.addObject(new BlueWisp(
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
            world.addObject(new BlueWisp(
                Vector2.add(
                    player.getPosition(),
                    new Vector2(i * 80, -20 * 64)
                ),
                2,
                this
            ));
        }

        //bottom right
        for (int i = 0; i <= 5; i++)
        {
            world.addObject(new BlueWisp(
                Vector2.add(
                    player.getPosition(),
                    new Vector2(i * -80, -20 * 64)
                ),
                2,
                this
            ));
        }

        BlueWisp miniBoss = new BlueWisp(
            Vector2.add(
                this.center,
                new Vector2(0, -45 * 64)),
            4,
            this
        );
        
        miniBoss.spawnBossBlue();
        world.addObject(tr1);
        SoundManager.get().playMusic("gameMusic");
    }

    @Override
    public void stopGamePanel()
    {
        
    }


    @Override
    public MapObj setMap()
    {
        return new MapObj(ImageLibrary.get().map1, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 8* 120)), 1 , this);
    }
    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }

}
