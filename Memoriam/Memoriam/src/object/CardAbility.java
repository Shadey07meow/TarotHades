package object;

// functional interface for card ability effects
 
@FunctionalInterface
public interface CardAbility {
    void apply(Player player);
}