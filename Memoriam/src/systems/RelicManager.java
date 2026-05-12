package systems;

import object.Player;
import object.Relic;

public class RelicManager {

    private static RelicManager instance;

    public static RelicManager get() {
        if (instance == null) instance = new RelicManager();
        return instance;
    }

    public static void reset() {
        instance = new RelicManager();
    }

    private Relic chosenRelic = null;
    private boolean deathUsed = false;
    private boolean relicApplied = false;

    public void applyRelic(Relic relic, Player player) {
        StatusEffectManager.get().addRelic(relic);

        if (relicApplied) return; // block re-picking entirely
        relicApplied = true;
        chosenRelic = relic;

        applyRelicStats(relic, player);
    }

    public void reapplyRelicStats(Player player) {
        if (chosenRelic == null) return;
        applyRelicStats(chosenRelic, player);
    }

    private void applyRelicStats(Relic relic, Player player) {
        switch (relic) {
            case MAGICIAN -> {
                PowerUp buff = new PowerUp(
                    "The Magician",
                    "+30% stats",
                    new StatModifier(StatType.MAX_HP, 0.30, true),
                    new StatModifier(StatType.ATTACK, 0.30, true),
                    new StatModifier(StatType.DEFENSE, 0.30, true),
                    new StatModifier(StatType.SPEED, 0.30, true)
                );
                player.getStats().applyPowerUp(buff);
                player.getStats().setMagicianActive(true);
                player.getStats().setCurrentHP(player.getStats().getMaxHP());
            }

            case THE_EMPRESS -> {
                player.getStats().setEmpress();
                player.getStats().setCurrentHP(player.getStats().getMaxHP());
                System.out.println("[RelicManager] EMPRESS → HP set to " + player.getStats().getMaxHP());
            }

            case DEATH -> {
                System.out.println("[RelicManager] DEATH equipped");
            }
        }
    }

    public boolean tryResurrect(Player player) {
        if (chosenRelic != Relic.DEATH) return false;
        if (deathUsed) return false;

        deathUsed = true;

        int reviveHP = Math.max(1, player.getStats().getMaxHP() / 2);
        player.getStats().setCurrentHP(reviveHP);
        player.setHealth(reviveHP);
        
        StatusEffectManager.get().removeRelic(Relic.DEATH);

        return true;
    }

    public Relic getChosenRelic() { return chosenRelic; }
    public boolean hasRelic() {return chosenRelic != null;}
    public boolean choseRelic(Relic relic) {return chosenRelic == relic;}
}