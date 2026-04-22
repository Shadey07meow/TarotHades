package systems;

import object.PlayerAbility;
import java.util.EnumMap;
import java.util.Map;

/**
 * Stores all PowerUps and links them to abilities
 */
public class PowerUpManager {

    private static final Map<PlayerAbility, PowerUp> powerUps =
            new EnumMap<>(PlayerAbility.class);

    static {

        // COMMON
        powerUps.put(PlayerAbility.HP_REGEN,
            new PowerUp(
                "Two of Cups — HP Regen",
                "+1 max heart, slow passive regeneration",
                new StatModifier(StatType.MAX_HP, 1, false)
            )
        );

        powerUps.put(PlayerAbility.FLAME_SHOT,
            new PowerUp(
                "Ace of Wands — Flame Shot",
                "+8 ATK, fire projectile unlocked",
                new StatModifier(StatType.ATTACK, 8, false)
            )
        );

        // RARE
        powerUps.put(PlayerAbility.HEAVY_STRIKE,
            new PowerUp(
                "Ten of Swords — Heavy Strike",
                "+15 ATK, heavy melee enabled",
                new StatModifier(StatType.ATTACK, 15, false)
            )
        );

        powerUps.put(PlayerAbility.FORTIFIED_REGEN,
            new PowerUp(
                "Nine of Pentacles — Fortified Regen",
                "+2 max hearts, +10% DEF",
                new StatModifier(StatType.MAX_HP, 2, false),
                new StatModifier(StatType.DEFENSE, 0.10, true)
            )
        );

        // LEGENDARY
        powerUps.put(PlayerAbility.SHIELD,
            new PowerUp(
                "Queen of Cups — Shield",
                "+3 DEF, deployable shield unlocked",
                new StatModifier(StatType.DEFENSE, 3, false)
            )
        );

        powerUps.put(PlayerAbility.BOUNCING_SHOT,
            new PowerUp(
                "Knight of Wands — Bouncing Shot",
                "+20% Speed, projectiles bounce",
                new StatModifier(StatType.SPEED, 0.20, true)
            )
        );
    }

    public static PowerUp get(PlayerAbility ability) {
        return powerUps.get(ability);
    }
}