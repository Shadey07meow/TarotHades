package object.Entities;

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
    /* Ten of Swords: Multiple projectiles out, 
    like how projectiles come out of PVZ Threepeater, like 5 projectiles will come out at the s
    ame time and not sunod sunod like PVZ Cotling-pea */
    MULTI_SHOT,

    /* Nine of Pentacles: faster HP regen + resistance to status effects IDK HOW TO IMPLEMENT*/
    FORTIFIED_REGEN,

    // Legendary 
    /* Queen of Cups: deployable shield with duration + long cooldown */
    SHIELD,

    /* Knight of Wands: speed up*/
    SPEED_ENHANCE;

    public static final int MAX_STACKS = 3;

    
}
