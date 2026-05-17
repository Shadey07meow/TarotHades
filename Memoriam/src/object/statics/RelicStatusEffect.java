package object.statics;

import object.statics.Relic;

public class RelicStatusEffect {

    private final Relic relic;

    public RelicStatusEffect(Relic relic) {
        this.relic = relic;
    }

    public Relic getRelic() {
        return relic;
    }
}