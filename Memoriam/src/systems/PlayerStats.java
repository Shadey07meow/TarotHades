package systems;

import java.util.ArrayList;

/*Stats are computed as:
    finalValue = (base + sumOfFlat) * (1 + sumOfPercent) */

public class PlayerStats{

    private int baseMaxHP;
    private int baseAtk;
    private int baseDef;
    private double baseSpeed;
    private int baseProjectileCount = 1;   // default: 1 projectile per shot
    private boolean empress = false;
    private boolean magicianActive = false;
    private int empressHP = 15;
    private long shieldEndTime = 0;

    private int currentHP;

    // modifiers
    private final ArrayList<StatModifier> modifiers = new ArrayList<>();

    // constructor
    public PlayerStats(int baseMaxHP, int baseAtk, int baseDef, double baseSpeed) {
        this.baseMaxHP   = baseMaxHP;
        this.baseAtk  = baseAtk;
        this.baseDef = baseDef;;
        this.baseSpeed   = baseSpeed;
        this.currentHP   = baseMaxHP; // start full
    }

    //apply a powerup
    public synchronized void applyPowerUp(PowerUp powerUp) {
        if (powerUp == null) return;

        modifiers.addAll(powerUp.getModifiers());
        System.out.println("Applied PowerUp: " + powerUp.getName());

    }

    //stat getters

    public int getAttack(){ return (int) Math.round(computeStat(StatType.ATTACK,  baseAtk)); }
    public int getDefense(){ return (int) Math.round(computeStat(StatType.DEFENSE, baseDef));}
    public double getSpeed(){ return computeStat(StatType.SPEED, baseSpeed); }
    public ArrayList<StatModifier> getModifiers(){ return modifiers;}

    public int getProjectileCount() { 
        return (int) Math.round(computeStat(StatType.PROJECTILE_COUNT, baseProjectileCount)); 
    }

    public double getCritChance() {
        double bonus = computeStat(StatType.CRIT_CHANCE, 0.0);
        return Math.min(0.05 + bonus, 0.35);   // base 5%, hard cap 35%
    }

    public double getCritMultiplier() {
        return 2.0;
    }

    // hp helpers

    public synchronized int getCurrentHP() {return currentHP;}

    public void heal(int amount) {
        currentHP += amount;

        int max = getMaxHP();

        if (currentHP > max) {
            currentHP = max;
        }
    }

    public void takeDamage(int amount) {

        if (isShielded()) {
            return;
        }

        int damage = amount - getDefense();

        if (damage < 0) damage = 0;

        currentHP -= damage;

        if (currentHP < 0) currentHP = 0;
    }

    public synchronized void setCurrentHP(int hp) {
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

    

    public void setEmpress() {
        this.empress = true;

        // force max HP to 15 behavior
        modifiers.add(new StatModifier(StatType.MAX_HP, 5, false));

        // heal immediately
        this.currentHP = getMaxHP();
    }

    public void setMagicianActive(boolean active) {
        this.magicianActive = active;
    
    }

    public int getMaxHP() {

        return (int) Math.round(computeStat(StatType.MAX_HP, baseMaxHP));
    }

    public void activateShield(long durationMillis) {
    shieldEndTime = System.currentTimeMillis() + durationMillis;
}

    public boolean isShielded() {
        return System.currentTimeMillis() < shieldEndTime;
    }

    // Debug
    public void debugPrint() {
        // System.out.println(
        //     "HP: " + currentHP + "/" + getMaxHP() +
        //     " | ATK: " + getAttack() +
        //     " | DEF: " + getDefense() +
        //     " | SPD: " + getSpeed()
        // );
    }
}