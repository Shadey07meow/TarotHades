package systems;

import java.util.*;
import object.Player;
import object.PlayerAbility;
import object.Relic;
import object.RelicStatusEffect;
import object.StatusEffect;

public class StatusEffectManager {


    private static StatusEffectManager instance;
    // for relic status
    private final ArrayList<RelicStatusEffect> activeRelics = new ArrayList<>();

    public static StatusEffectManager get() {
        if (instance == null) instance = new StatusEffectManager();
        return instance;
    }

    // add relics
    public void addRelic(Relic relic) {

        for (RelicStatusEffect existing : activeRelics) {
            if (existing.getRelic() == relic) return; // already present, skip
        }

        activeRelics.add(new RelicStatusEffect(relic));
    }
    //get active relics
    public ArrayList<RelicStatusEffect> getActiveRelics() {
        return activeRelics;
    }

        //get active relics
    public  void setActiveRelics(ArrayList<RelicStatusEffect> relics) {
        activeRelics.clear();
        activeRelics.addAll(relics);
    }
    
    public void addOrStack(PlayerAbility ability, int durationLevels) {
        StatusEffect existing = activeEffects.get(ability);
        if (existing != null) {
            existing.stack();           // extend + power up
        } else {
            activeEffects.put(ability, new StatusEffect(ability, durationLevels));
        }
        System.out.println("[StatusEffectManager] " + ability +
                " → levelsLeft=" + activeEffects.get(ability).getLevelsRemaining() +
                " power=" + activeEffects.get(ability).getPowerLevel());
    }


    /** Call between runs to wipe all effects. */
    public static void reset() { instance = new StatusEffectManager(); }

    private final Map<PlayerAbility, StatusEffect> activeEffects =
            new EnumMap<>(PlayerAbility.class);


    public void onLevelCompleted(Player player) {

        List<PlayerAbility> toRemove = new ArrayList<>();

        for (Map.Entry<PlayerAbility, StatusEffect> entry : activeEffects.entrySet()) {
            StatusEffect effect = entry.getValue();

            boolean expired = effect.tickLevel();

            if (expired) {
                onEffectExpired(entry.getKey(), player);
                toRemove.add(entry.getKey());
                System.out.println("[StatusEffectManager] " + entry.getKey() + " expired.");
            }
        }

        // remove AFTER iteration
        for (PlayerAbility ability : toRemove) {
            activeEffects.remove(ability);
        }
    }

    public List<StatusEffect> getActiveEffects() {
        return new ArrayList<>(activeEffects.values());
    }

    public boolean isActive(PlayerAbility ability) {
        StatusEffect e = activeEffects.get(ability);
        return e != null && !e.isExpired();
    }

    public int getPowerLevel(PlayerAbility ability) {
        StatusEffect e = activeEffects.get(ability);
        return (e != null) ? e.getPowerLevel() : 0;
    }

    private void onEffectExpired(PlayerAbility ability, Player player) {
        switch (ability) {
            case SPEED_ENHANCE -> {
                // Speed reverts automatically if you remove the modifier.
                System.out.println("[StatusEffectManager] SPEED_ENHANCE expired — " +
                        "speed bonus persists this run (by design).");
            }
            case SHIELD -> {
                // Shield game-object removal is handled by Player itself
                // when no SHIELD ability stacks are present.
                System.out.println("[StatusEffectManager] SHIELD timed out.");
            }
            default -> {}
        }
    }
}
