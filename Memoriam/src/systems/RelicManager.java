package systems;

import object.Player;
import object.Relic;

public class RelicManager {
    private static RelicManager instance;

    public static RelicManager get() {
        if (instance == null) instance = new RelicManager();
        return instance;
    }

    public static void reset() { instance = new RelicManager(); }

    private Relic chosenRelic = null;
    private boolean deathUsed = false;
    private boolean relicStatApplied = false;

    public void applyRelic(Relic relic, Player player) {

        StatusEffectManager.get().addRelic(relic);

        if (!relicStatApplied) {
            relicStatApplied = true;
            this.chosenRelic = relic;

            switch (relic) {
                case MAGICIAN -> {
                    PowerUp magicianBuff = new PowerUp(
                        "The Magician",
                        "+8 % all stats (permanent)",
                        new StatModifier(StatType.MAX_HP,  0.08, true),
                        new StatModifier(StatType.ATTACK,  0.08, true),
                        new StatModifier(StatType.DEFENSE, 0.08, true),
                        new StatModifier(StatType.SPEED,   0.08, true)
                    );
                    player.getStats().applyPowerUp(magicianBuff);
                    if (player.getStats().getCurrentHP() > player.getStats().getMaxHP()) {
                        player.getStats().setCurrentHP(player.getStats().getMaxHP());
                    }
                    System.out.println("[RelicManager] MAGICIAN relic applied — +8% all stats.");
                }

                case THE_EMPRESS -> {
                    int delta = 15 - player.getStats().getMaxHP();
                    PowerUp empressBuff = new PowerUp(
                        "The Empress",
                        "Starting HP set to 15 hearts",
                        new StatModifier(StatType.MAX_HP, delta, false)
                    );
                    player.getStats().applyPowerUp(empressBuff);
                    player.getStats().setCurrentHP(player.getStats().getMaxHP());
                   
                    System.out.println("[RelicManager] EMPRESS relic applied — Max HP set to 15.");
                }

                case DEATH -> {
                    System.out.println("[RelicManager] DEATH relic equipped — resurrection ready.");
                }
            }
        } else {
            // relic already applied, only re-heal for Empress
            if (relic == Relic.THE_EMPRESS) {
                player.getStats().setCurrentHP(player.getStats().getMaxHP());
                System.out.println("[RelicManager] EMPRESS re-applied — healed to max.");
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

        System.out.println("[RelicManager] DEATH relic triggered — revived at " + reviveHP + " HP.");
        return true;
    }

    public Relic getChosenRelic() { return chosenRelic; }
    public boolean hasRelic(Relic r) { return chosenRelic == r; }
    public boolean isDeathUsed() { return deathUsed; }
    public boolean isRelicChosen() { return chosenRelic != null; }
    public void resetStatApplied() {  this.relicStatApplied = false;
        System.out.println("[RelicManager] resetStatApplied called");
    }
}