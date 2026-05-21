package scenes.levels;


import images.*;
import object.Entities.Player;
import object.Entities.PurpleWisp;
import object.statics.BarrierObject;
import object.statics.MapObj;
import object.statics.TreasureChest;
import scenes.templates.PlayableScreen;
import scenes.ui.GameFrame;
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
                    -45 * 64 * GameFrame.getScreenMultiplier())), 
            player, 2, this);


            // Add Barrier objects
        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(13 * 64* GameFrame.getScreenMultiplier(), 15 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(10 * 64 * GameFrame.getScreenMultiplier(), 17 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(12 * 64 * GameFrame.getScreenMultiplier(), 2 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(3 * 64, 3 * 64, 3 * 64, 3 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(12 * 64 * GameFrame.getScreenMultiplier(), -1 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(9 * 64 * GameFrame.getScreenMultiplier(), 0 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(9 * 64 * GameFrame.getScreenMultiplier(), 2 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64 * GameFrame.getScreenMultiplier(), -1 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(1 * 64, 1 * 64, 1 * 64, 1 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13 * 64 * GameFrame.getScreenMultiplier(), 3 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-12 * 64 * GameFrame.getScreenMultiplier(), -3 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64 * GameFrame.getScreenMultiplier(), -15 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-13  * 64 * GameFrame.getScreenMultiplier(), -11 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(8 * 64 * GameFrame.getScreenMultiplier(), -25 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(12 * 64* GameFrame.getScreenMultiplier(), -20 * 64 * GameFrame.getScreenMultiplier())),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(14 * 64* GameFrame.getScreenMultiplier(), -40 * 64* GameFrame.getScreenMultiplier())),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(15 * 64* GameFrame.getScreenMultiplier(), -45 * 64* GameFrame.getScreenMultiplier())),
                this,
                new Bounds(2 * 64, 2 * 64, 2 * 64, 2 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-14 * 64* GameFrame.getScreenMultiplier(), -43 * 64* GameFrame.getScreenMultiplier())),
                this,
                new Bounds(4 * 64, 4 * 64, 4 * 64, 4 * 64
            )));

        world.addObject(new BarrierObject(
            Vector2.add(
                this.center,
                new Vector2(-10 * 64* GameFrame.getScreenMultiplier(), -45 * 64* GameFrame.getScreenMultiplier())),
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
                    new Vector2(i * 64* GameFrame.getScreenMultiplier(), -40 * 64* GameFrame.getScreenMultiplier())
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
                    new Vector2(i * -64* GameFrame.getScreenMultiplier(), -35 * 64* GameFrame.getScreenMultiplier())
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
                    new Vector2(i * 80* GameFrame.getScreenMultiplier(), -20 * 64* GameFrame.getScreenMultiplier())
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
                    new Vector2(i * -80* GameFrame.getScreenMultiplier(), -20 * 64* GameFrame.getScreenMultiplier())
                ),
                2,
                this
            ));
        }

        PurpleWisp miniBoss = new PurpleWisp(
            Vector2.add(
                this.center,
                new Vector2(0, -45 * 64* GameFrame.getScreenMultiplier())),
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
        return new MapObj(ImageLibrary.get().map2, Vector2.add(player.getPosition(), Vector2.multiply(Vector2.DOWN, 8* 120 * GameFrame.getScreenMultiplier())), 1 , this);
    }

    @Override   
    public Player setPlayer()
    {
        return new Player(new Vector2(GameFrame.getScreenWidth() / 2, GameFrame.getScreenHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }

}
