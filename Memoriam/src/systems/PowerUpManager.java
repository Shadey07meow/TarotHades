package systems;

import java.util.EnumMap;
import java.util.Map;
import object.PlayerAbility;

/**
 * Stores all PowerUps and links them to abilities
 */
public class PowerUpManager {

    private static final Map<PlayerAbility, PowerUp[]> leveledPowerUps =
            new EnumMap<>(PlayerAbility.class);

    static {

        // COMMON

        // HP_Regen
        leveledPowerUps.put(PlayerAbility.HP_REGEN, new PowerUp[]{
            new PowerUp(
                "Two of Cups I",
                "+1 max heart, slow passive regeneration",
                new StatModifier(StatType.MAX_HP, 1, false)
            ),
            new PowerUp(
                "Two of Cups II",
                "+1 max heart, regeneration doubled",
                new StatModifier(StatType.MAX_HP, 1, false)
            ),
            new PowerUp(
                "Two of Cups III",
                "+1 max heart, regeneration tripled",
                new StatModifier(StatType.MAX_HP, 1, false)
            )
        });

        // Flame_Shot

        leveledPowerUps.put(PlayerAbility.FLAME_SHOT, new PowerUp[]{
            new PowerUp(
                "Ace of Wands I",
                "+4 ATK, fire projectile unlocked",
                new StatModifier(StatType.ATTACK, 4, false)
            ),
            new PowerUp(
                "Ace of Wands II",
                "+4 ATK, stronger fire projectiles",
                new StatModifier(StatType.ATTACK, 4, false)
            ),
            new PowerUp(
                "Ace of Wands III",
                "+4 ATK, blazing fire projectiles",
                new StatModifier(StatType.ATTACK, 4, false)
            )
        });

        
        // RARE
         // heavy strike
        leveledPowerUps.put(PlayerAbility.MULTI_SHOT, new PowerUp[]{
            new PowerUp(
                "Ten of Swords I",
                "Fire 3 projectiles in a spread",
                new StatModifier(StatType.PROJECTILE_COUNT, 2, false)  // 1 base + 2 = 3
            ),
            new PowerUp(
                "Ten of Swords II",
                "Fire 4 projectiles in a wider spread",
                new StatModifier(StatType.PROJECTILE_COUNT, 1, false)  // +1 = 4
            ),
            new PowerUp(
                "Ten of Swords III",
                "Fire 5 projectiles in a full fan",
                new StatModifier(StatType.PROJECTILE_COUNT, 1, false)  // +1 = 5
            )
        });

        //FORTIFIIED_REGEN
         leveledPowerUps.put(PlayerAbility.FORTIFIED_REGEN, new PowerUp[]{
            new PowerUp(
                "Nine of Pentacles I",
                "+1 max heart, +1 DEF, regen ticks faster",
                new StatModifier(StatType.MAX_HP,  1, false),
                new StatModifier(StatType.DEFENSE, 1, false)
            ),
            new PowerUp(
                "Nine of Pentacles II",
                "+1 max heart, +1 DEF",
                new StatModifier(StatType.MAX_HP,  1, false),
                new StatModifier(StatType.DEFENSE, 1, false)
            ),
            new PowerUp(
                "Nine of Pentacles III",
                "+2 max hearts, +2 DEF, near-invincible regen",
                new StatModifier(StatType.MAX_HP,  2, false),
                new StatModifier(StatType.DEFENSE, 2, false)
            )
        });


        // LEGENDARY
        // QUEEN OF CUPS _SHIELD
        leveledPowerUps.put(PlayerAbility.SHIELD, new PowerUp[]{
            new PowerUp(
                "Queen of Cups I",
                "+2 DEF, deployable shield (5s cooldown)",
                new StatModifier(StatType.DEFENSE, 2, false)
            ),
            new PowerUp(
                "Queen of Cups II",
                "+2 DEF, shorter shield cooldown (3.5s)",
                new StatModifier(StatType.DEFENSE, 2, false)
            ),
            new PowerUp(
                "Queen of Cups III",
                "+2 DEF, shield refreshes quickly (2s)",
                new StatModifier(StatType.DEFENSE, 2, false)
            )
        });

        leveledPowerUps.put(PlayerAbility.SPEED_ENHANCE, new PowerUp[]{
            new PowerUp(
                "Knight of Wands I",
                "+20% movement speed",
                new StatModifier(StatType.SPEED, 0.20, true)
            ),
            new PowerUp(
                "Knight of Wands II",
                "+20% movement speed (×2 total boost)",
                new StatModifier(StatType.SPEED, 0.20, true)
            ),
            new PowerUp(
                "Knight of Wands III",
                "+20% movement speed (×3 total boost)",
                new StatModifier(StatType.SPEED, 0.20, true)
            )
        });
    }

    public static PowerUp get(PlayerAbility ability, int stackLevel) {
        PowerUp[] levels = leveledPowerUps.get(ability);
        if (levels == null) return null;

        int index = stackLevel - 1;
        if (index < 0 || index >= levels.length) return null;

        return levels[index];
    }

    public static PowerUp get(PlayerAbility ability) {
        return get(ability, 1);
    }

    // how many stack levels are defined for this ability. 
    public static int getMaxLevel(PlayerAbility ability) {
        PowerUp[] levels = leveledPowerUps.get(ability);
        return (levels == null) ? 0 : levels.length;
    }
}