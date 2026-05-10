package systems;

public class GameStats {

    private static GameStats instance;

    public static GameStats get() {
        if (instance == null) instance = new GameStats();
        return instance;
    }

    private int level = 0;
    private int enemiesKilled = 0;
    
    public void resetStat() {
        enemiesKilled = 0;
    }
    public void nextLevel() {
        level++;
    }

    public void addKill() {
        enemiesKilled++;
    }

    public void setKills(int i) {
        enemiesKilled = i;
    }

    public int getLevel() {
        return level;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }



    public void reset() {
        level = 0;
        enemiesKilled = 0;
}
}