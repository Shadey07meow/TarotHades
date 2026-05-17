package object.statics;

import object.Entities.Player;

// functional interface for card ability effects
 
@FunctionalInterface
public interface CardAbility {
    void apply(Player player);
}