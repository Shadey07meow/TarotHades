package systems;

import java.util.Arrays;
import java.util.List;

/**
 * A named bundle of StatModifiers applied when a card is selected.
 * Stackable — the same PowerUp can be applied multiple times.
 */
public class PowerUp {

    private final String name;
    private final String description;
    private final List<StatModifier> modifiers;

    public PowerUp(String name, String description, StatModifier... modifiers) {
        this.name        = name;
        this.description = description;
        this.modifiers   = Arrays.asList(modifiers);
    }

    public String getName(){ return name; }
    public String getDescription() { return description; }
    public List<StatModifier> getModifiers(){ return modifiers; }
}