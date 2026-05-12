package systems;

import java.util.EnumMap;
import java.util.Map;
import object.PlayerAbility;

public class PowerUpManager {

    private static final Map<PlayerAbility, PowerUp[]> leveledPowerUps =
            new EnumMap<>(PlayerAbility.class);

    static {

        leveledPowerUps.put(PlayerAbility.HP_REGEN, new PowerUp[]{
            new PowerUp("Two of Cups I", "+1 max heart",
                new StatModifier(StatType.MAX_HP, 1, false)),
            new PowerUp("Two of Cups II", "+1 max heart",
                new StatModifier(StatType.MAX_HP, 1, false)),
            new PowerUp("Two of Cups III", "+1 max heart",
                new StatModifier(StatType.MAX_HP, 1, false))
        });

        leveledPowerUps.put(PlayerAbility.FLAME_SHOT, new PowerUp[]{
            new PowerUp("Ace of Wands I", "+4 ATK",
                new StatModifier(StatType.ATTACK, 1, false)),
            new PowerUp("Ace of Wands II", "+4 ATK",
                new StatModifier(StatType.ATTACK, 1, false)),
            new PowerUp("Ace of Wands III", "+4 ATK",
                new StatModifier(StatType.ATTACK, 2, false))
        });

        leveledPowerUps.put(PlayerAbility.MULTI_SHOT, new PowerUp[]{
            new PowerUp("Ten of Swords I",
                "3 projectiles",
                new StatModifier(StatType.PROJECTILE_COUNT, 2, false)),
            new PowerUp("Ten of Swords II",
                "4 projectiles",
                new StatModifier(StatType.PROJECTILE_COUNT, 1, false)),
            new PowerUp("Ten of Swords III",
                "5 projectiles",
                new StatModifier(StatType.PROJECTILE_COUNT, 1, false))
        });

        leveledPowerUps.put(PlayerAbility.FORTIFIED_REGEN, new PowerUp[]{
            new PowerUp("Nine of Pentacles I",
                "+HP +DEF",
                new StatModifier(StatType.MAX_HP, 1, false),
                new StatModifier(StatType.DEFENSE, 0.5, false)),
            new PowerUp("Nine of Pentacles II",
                "+HP +DEF",
                new StatModifier(StatType.MAX_HP, 1, false),
                new StatModifier(StatType.DEFENSE, 0.5, false)),
            new PowerUp("Nine of Pentacles III",
                "+HP +DEF",
                new StatModifier(StatType.MAX_HP, 2, false),
                new StatModifier(StatType.DEFENSE, 1, false))
        });

        leveledPowerUps.put(PlayerAbility.SHIELD, new PowerUp[]{
            new PowerUp("Queen of Cups I",
                "+DEF",
                new StatModifier(StatType.DEFENSE, 0, false)),
            new PowerUp("Queen of Cups II",
                "+DEF",
                new StatModifier(StatType.DEFENSE, 0, false)),
            new PowerUp("Queen of Cups III",
                "+DEF",
                new StatModifier(StatType.DEFENSE, 1, false))
        });

        leveledPowerUps.put(PlayerAbility.SPEED_ENHANCE, new PowerUp[]{
            new PowerUp("Knight of Wands I",
                "+20% speed",
                new StatModifier(StatType.SPEED, 0.20, true)),
            new PowerUp("Knight of Wands II",
                "+20% speed",
                new StatModifier(StatType.SPEED, 0.20, true)),
            new PowerUp("Knight of Wands III",
                "+20% speed",
                new StatModifier(StatType.SPEED, 0.20, true))
        });
    }

    public static PowerUp get(PlayerAbility ability, int stackLevel) {
        PowerUp[] levels = leveledPowerUps.get(ability);
        if (levels == null) return null;

        int index = stackLevel - 1;
        if (index < 0 || index >= levels.length) return null;

        return levels[index];
    }

    public static int getMaxLevel(PlayerAbility ability) {
        PowerUp[] levels = leveledPowerUps.get(ability);
        return (levels == null) ? 0 : levels.length;
    }
}