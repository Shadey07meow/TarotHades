package systems;

import object.Player;
import object.PlayerAbility;
import object.Relic;

/** We call the skills in the lobby as relics, and this stores the chosen relic
 * 
 * 1. Player picks a card in the lobby → CardManager calls RelicManager.get().applyRelic(relic, player);
 *   2. The DEATH resurrection check is wired into Player.minusHP().
 *   3. Call  RelicManager.get().hasRelic(StartingRelic.X)  anywhere you need
 *      to query which relic is active.
 */

public class RelicManager{
    private static RelicManager instance;

    public static RelicManager get() {
        if (instance == null) instance = new RelicManager();
        return instance;
    }

    public static void reset() { instance = new RelicManager(); }

    private Relic chosenRelic = null;
    private boolean deathUsed = false;     // resurrection already consumed?
    
    public void applyRelic(Relic relic, Player player) {
        this.chosenRelic = relic;
        this.deathUsed   = false;

        switch (relic) {

            case DEATH -> {
                // no immediate stat change, resurrection handled reactively.
                System.out.println("[RelicManager] DEATH relic equipped — resurrection ready.");
            }

            case MAGICIAN -> {
                // +8 % to all stats as percent modifiers via the PowerUp / StatModifier system.
                PowerUp magicianBuff = new PowerUp(
                    "The Magician",
                    "+8 % all stats (permanent)",

                    new StatModifier(StatType.MAX_HP,  0.08, true),
                    new StatModifier(StatType.ATTACK,  0.08, true),
                    new StatModifier(StatType.DEFENSE, 0.08, true),
                    new StatModifier(StatType.SPEED,   0.08, true)
                );
                player.getStats().applyPowerUp(magicianBuff);

                // clamp current HP to the new max
                if (player.getStats().getCurrentHP() > player.getStats().getMaxHP()) {
                    player.getStats().setCurrentHP(player.getStats().getMaxHP());
                }
                System.out.println("[RelicManager] MAGICIAN relic applied — +8 % all stats.");
            }

            case THE_EMPRESS -> {
                // Force max HP to 15.  We do this by computing the delta and adding a flat modifier.
                int currentMax = player.getStats().getMaxHP();
                int target     = 15;
                int delta      = target - currentMax;   // may be negative if already > 15

                PowerUp empressBuff = new PowerUp(
                    "The Empress",
                    "Starting HP set to 15 hearts",
                    new StatModifier(StatType.MAX_HP, delta, false)
                );

                player.getStats().applyPowerUp(empressBuff);

                // Heal up to the new maximum
                player.getStats().setCurrentHP(player.getStats().getMaxHP());

                player.setHealth(player.getStats().getMaxHP());

                System.out.println("[RelicManager] EMPRESS relic applied — Max HP set to 15.");
            }
        }
    }

    // death ressurection logic

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

    public Relic getChosenRelic(){ return chosenRelic; }
    public boolean hasRelic(Relic r){ return chosenRelic == r; }
    public boolean isDeathUsed(){ return deathUsed; }
    public boolean isRelicChosen(){ return chosenRelic != null; }

}