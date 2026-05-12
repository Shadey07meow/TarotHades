package scenes.levels;

import java.util.Random;
import object.*;
import scenes.*;
import systems.*;

public class InfiniteLevel extends PlayableScreen {

    private final Random rand = new Random();

    private static final int MAX_ALIVE = 10;

    private int killsInWave = 0;

    public InfiniteLevel(GameFrame g) {
        super("infinite", 99, g);
    }

    @Override
    public void startGamePanel() {
        LevelManager.isInfiniteRun = true;

        if (world == null) return;

        killsInWave = 0;
        world.clearWaveObjects();

        spawnWave();
    }

    @Override
    public void stopGamePanel() {}

    @Override
    public void update() {
        super.update();

        if (world == null) return;

        // only check completion
        if (killsInWave >= MAX_ALIVE) {
            killsInWave = 0;
            spawnWave();
        }
    }

    // called from Enemy.onDeath()
    public void onEnemyKilled() {

        killsInWave++;

        System.out.println("Killed: " + killsInWave + " / " + MAX_ALIVE);
    }

    private void spawnWave() {

        System.out.println("Spawning 10 enemies");

        for (int i = 0; i < MAX_ALIVE; i++) {
            spawnEnemy();
        }
    }

    private void spawnEnemy() {

        final int MIN_RADIUS = 700;
        final int MAX_RADIUS = 1200;

        final int MAP_HALF_WIDTH = 960;
        final int MAP_HALF_HEIGHT = 2160;

        Vector2 playerPos = player.getPosition();
        Vector2 pos;

        int attempts = 0;

        do {
            double angle = rand.nextDouble() * Math.PI * 2;
            double distance = MIN_RADIUS + rand.nextDouble() * (MAX_RADIUS - MIN_RADIUS);

            int x = (int)(Math.cos(angle) * distance);
            int y = (int)(Math.sin(angle) * distance);

            pos = Vector2.add(playerPos, new Vector2(x, y));

            attempts++;

        } while (
            (pos.x < -MAP_HALF_WIDTH || pos.x > MAP_HALF_WIDTH ||
            pos.y < -MAP_HALF_HEIGHT || pos.y > MAP_HALF_HEIGHT)
            && attempts < 20
        );

        Enemy enemy;

        int type = rand.nextInt(3);

        switch (type) {
            case 0 -> enemy = new BlueWisp(pos, 2, this);
            case 1 -> enemy = new YellowWisp(pos, 2, this);
            default -> enemy = new PurpleWisp(pos, 2, this);
        }

        enemy.setHealth(10 + rand.nextInt(10));
        world.addObject(enemy);
    }
    @Override
    public MapObj setMap() {
        return new MapObj(
            images.ImageLibrary.get().infiniteMap,
            Vector2.add(player.getPosition(),
            Vector2.multiply(Vector2.DOWN, 8 * 120)),
            1,
            this
        );
    }

    @Override
    public Player setPlayer() {
        return new Player(
            new Vector2(getWidth() / 2, getHeight() / 2),
            3,
            10,
            10,
            this,
            this.getGameFrame()
        );
    }


}