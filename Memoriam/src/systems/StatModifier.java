package systems;

// One stackable stat change.
 
public class StatModifier {

    private final StatType type;
    private final double   value;
    private final boolean  percent; // true = percent boost, false = flat add

    public StatModifier(StatType type, double value, boolean percent) {
        this.type    = type;
        this.value   = value;
        this.percent = percent;
    }

    public StatType getType()    { return type; }
    public double   getValue()   { return value; }
    public boolean  isPercent()  { return percent; }
}