package systems;

import java.util.ArrayList;
import java.util.List;

/*Stats are computed as:
    finalValue = (base + sumOfFlat) * (1 + sumOfPercent) */

public class PlayerStats{

    private int baseMaxHP;
    private int baseAtk;
    private int baseDef;
    private double baseSpeed;

    private int currentHP;

    // modifiers
    private final List<StatModifier> modifiers = new ArrayList<>();

    // constructor
    public PlayerStats(int baseMaxHP, int baseAtk, int baseDef, double baseSpeed) {
        this.baseMaxHP   = baseMaxHP;
        this.baseAtk  = baseAtk;
        this.baseDef = baseDef;;
        this.baseSpeed   = baseSpeed;
        this.currentHP   = baseMaxHP; // start full
    }

    //apply a powerup
    public void applyPowerUp(PowerUp powerUp) {
        if (powerUp == null) return;

        modifiers.addAll(powerUp.getModifiers());
        System.out.println("Applied PowerUp: " + powerUp.getName());
        debugPrint();
    }

    //stat getters
    public int getMaxHP(){ return (int) Math.round(computeStat(StatType.MAX_HP,  baseMaxHP));}
    public int getAttack(){ return (int) Math.round(computeStat(StatType.ATTACK,  baseAtk)); }
    public int getDefense(){ return (int) Math.round(computeStat(StatType.DEFENSE, baseDef));}
    public double getSpeed(){ return computeStat(StatType.SPEED, baseSpeed); }
    
    public int getCurrentHP() {return currentHP;}

    public void heal(int amount) {
        currentHP += amount;
        if (currentHP > getMaxHP()) {
            currentHP = getMaxHP();
        }
    }

    public void takeDamage(int amount) {
        int damage = amount - getDefense();
        if (damage < 0) damage = 0;

        currentHP -= damage;
        if (currentHP < 0) currentHP = 0;
    }

    public void setCurrentHP(int hp) {
        currentHP = hp;
        if (currentHP > getMaxHP()) currentHP = getMaxHP();
        if (currentHP < 0) currentHP = 0;
    }

     public boolean isDead() { return currentHP <= 0; }

     //reset (new run)

      public void reset(int baseMaxHP, int baseAtk, int baseDef, double baseSpeed) {
        this.baseMaxHP   = baseMaxHP;
        this.baseAtk  = baseAtk;
        this.baseDef = baseDef;
        this.baseSpeed   = baseSpeed;
        this.currentHP   = baseMaxHP;
        this.modifiers.clear();
    }

    // Compute Stats
    private double computeStat(StatType type, double base) {
        double flat = 0;
        double percent = 0;

        for (StatModifier m : modifiers) {
            if (m.getType() == type) {
                if (m.isPercent()) {
                    percent += m.getValue();
                } else {
                    flat += m.getValue();
                }
            }
        }

        return (base + flat) * (1 + percent);
    }

    // Debug
    public void debugPrint() {
        System.out.println(
            "HP: " + currentHP + "/" + getMaxHP() +
            " | ATK: " + getAttack() +
            " | DEF: " + getDefense() +
            " | SPD: " + getSpeed()
        );
    }
}