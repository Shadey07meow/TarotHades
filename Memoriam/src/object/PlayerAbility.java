package object;

/* all  abilities a player can unlock during a run.
 add new abilities here first, then:
    1. handle them in player 
    2. wire the Card in CardManager m*/
public enum PlayerAbility {

    // Common
    /* Two of Cups: slow passive HP regeneration meaning one heart per 2 runs */
    HP_REGEN,

    /* Ace of Wands: basic fire-projectile attack unlocked */
    FLAME_SHOT,

    //Rare 
    /* Ten of Swords: high-damage single-target melee strike */
    HEAVY_STRIKE,

    /* Nine of Pentacles: faster HP regen + resistance to status effects */
    FORTIFIED_REGEN,

    // Legendary 
    /* Queen of Cups: deployable shield with duration + long cooldown */
    SHIELD,

    /* Knight of Wands: projectiles bounce to nearby enemies */
    BOUNCING_SHOT
}
