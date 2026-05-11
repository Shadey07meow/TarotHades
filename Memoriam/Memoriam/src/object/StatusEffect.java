package object;

public class StatusEffect {

    public final PlayerAbility ability;
    private int remainingLevels;
    private int powerLevel; 
    private final int durationPerStack;

    public StatusEffect(PlayerAbility ability, int durationLevels){
        this.ability = ability;
        this.remainingLevels = durationLevels;
        this.powerLevel = 1;
        this.durationPerStack = durationLevels;
    }


    public void stack() {
        remainingLevels += durationPerStack;  
        powerLevel++;                          
    }

   public boolean tickLevel() {
        if (remainingLevels > 0) remainingLevels--;
        return remainingLevels <= 0;
    }

    public PlayerAbility getAbility(){ return ability; }
    public boolean isExpired(){return remainingLevels <=0;}
    public int getLevelsRemaining(){return remainingLevels;}
    public int getPowerLevel(){ return powerLevel; }
}
