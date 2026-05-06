package object;

import collision.*;
import images.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import scenes.*;
import systems.*;

public class Player extends Entity {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that
    private final GameFrame gameFrame;
    private ImageLibrary imgLib = ImageLibrary.get();
    private final PlayerStats stats;
    
   private final Map<PlayerAbility, Integer> abilityStacks = new EnumMap<>(PlayerAbility.class);
   private InputManager inputs = null;
    
    // Combat variables
    private final double projectileSpeed = 15;
    private final double fireCooldown = 10;
    private int regenTickCounter = 0;
    private int regenInterval   = 120;   // frames between each regen tick (HP_REGEN)
    private int fortRegenInterval = 60;  // faster regen for FORTIFIED_REGEN
    private boolean fortifiedActive = false;    
    private Shield shield = null;   
    
    // World renderer
    private WorldRenderer world;
    
    // for sprites
    private final Image spriteDown = ImageLibrary.get().playerSpritesDOWN;
    private final Image spriteUp = ImageLibrary.get().playerSpritesUP;
    private final Image spriteLeft = ImageLibrary.get().playerSpritesLEFT;
    private final Image spriteRight = ImageLibrary.get().playerSpritesRIGHT;

      // Tracker variables
    private boolean hasShotProjectile = false;
    private boolean isInteracting = false;
    private boolean isDead = false;
    private double currentCooldown = 0;
    private boolean uiOpen = false;    

    private final int regenBase   = 120;
    
    // constructor
    public Player(Vector2 position, int scale, int speed, int health, PlayableScreen scrn, GameFrame gameFrame)
    {
        super(position, scale, scrn);

        this.stats = new PlayerStats(health, 10, 0, speed);

        // Keep entity.health in sync for any legacy reads
        this.health = stats.getCurrentHP();
        this.speed  = stats.getSpeed();
        this.playScrn = scrn;
        this.inputs = this.playScrn.getInputManager();
        this.world = this.playScrn.getWorldRenderer();
        this.gameFrame = gameFrame;

        collider = new RectangleCollider(this, true, 40, 40, 40 ,40);
        this.collider.setIsMovable(true);
        setImage(spriteDown);
    }

    // update
    @Override
    public void update(){ super.update();}

    @Override
    public void logicMethods()
    {
        this.health = stats.getCurrentHP();
        this.speed  = stats.getSpeed();

        if (world == null) return;

        if (!isDead) {
            inputOperations();
            tickRegen();
        } else {
            gameFrame.showPanel("lose");
        }
        isDead = (health <= 0);
    }


    // ability / power-up application
    public void applyAbility(PlayerAbility ability) {
        if (ability == null) return;

        int currentLevel = getAbilityLevel(ability);
        if (currentLevel >= PlayerAbility.MAX_STACKS) return;   // already maxed
 
        int nextLevel = currentLevel + 1;
        abilityStacks.put(ability, nextLevel);

        // look up and apply the PowerUp from the manager
        PowerUp levelPowerUp = PowerUpManager.get(ability, nextLevel);
        if (levelPowerUp != null) {
            stats.applyPowerUp(levelPowerUp);
            // Clamp current HP to new max
            if (stats.getCurrentHP() > stats.getMaxHP()) {
                stats.setCurrentHP(stats.getMaxHP());
            }
        }

        // gameplay-side effects (behaviours, projectile types, etc.)
        onAbilityLevelGained(ability, nextLevel);
        System.out.println(ability + " → level " + nextLevel);
        
    }

     private void onAbilityLevelGained(PlayerAbility ability, int level) {
        switch (ability) {
            case HP_REGEN -> {
                // Regen interval is recalculated live in tickRegen()
            }
            case FLAME_SHOT -> {
                // Damage increase already handled by atk modifier in PowerUpManager.
            }
            case MULTI_SHOT-> {
                // projectile count already handled by PROJECTILE_COUNT modifier.
            }
            case FORTIFIED_REGEN -> {
                // DEF + MAX_HP modifiers already applied; regen speed covered in tickRegen().
            }
            case SHIELD -> updateShieldCooldown(level);

            case SPEED_ENHANCE -> {
                // Speed modifier applied; stats.getSpeed() now returns the updated value.
            }
        }
    }

    // input
    public void inputOperations()
    {
        movePlayer();
        // Does not fight when UI is open

        if (!uiOpen) combatMethod();
        checkInteracting();
    }

    private void movePlayer() 
    {
        Vector2 inpVector = inputs.getInputVector();
        Vector2 speedVector = Vector2.multiply(inputs.getInputVector(), this.speed);

        // Clamp movement speed so that it never exceeds speed
        if(Math.abs(speedVector.findMag()) > speed) speedVector = Vector2.magConvert(speedVector, speed);
    
        move(speedVector);
        
        // Set images, make looking up and down priority
        if (inpVector.x > 0) setImage(spriteRight);
        else if (inpVector.x < 0) setImage(spriteLeft);
        
        if (inpVector.y > 0) setImage(spriteUp);
        else if (inpVector.y < 0) setImage(spriteDown);
    }

    // combat 
    private void combatMethod()
    {

        if (currentCooldown > 0) currentCooldown--;
        
        // shoot once per click
        if(!hasShotProjectile)
        {
            if(inputs.consumeClick())
            {
                shootProjectile();
                hasShotProjectile = true;
            }
        }
        else
        {
            // reset when mouse released
            if(!inputs.consumeClick())
            {
                hasShotProjectile = false;
            }
        }
    }

    private void shootProjectile(){
        // Checks if we can shoot after shooting the last shot
        // cooldown
    
        if (currentCooldown != 0) return;

        Vector2 click = inputs.getClickPosition(); 
        Vector2 baseDir  = Vector2.getUnitVector(this.position, click);       
        int baseDmg  = stats.getAttack();
        boolean flame = hasAbility(PlayerAbility.FLAME_SHOT) && Math.random() < 0.3; // 30% chance
        boolean heavy = hasAbility(PlayerAbility.MULTI_SHOT);
        int count   = stats.getProjectileCount();   // 1, 3, 4, or 5
        
        double[] angles = buildSpreadAngles(count);

        for (double deg : angles) {
            Vector2 velocity = rotate(baseDir, deg);
            spawnProjectile(velocity, baseDmg, flame);
        }

        currentCooldown = fireCooldown;
    }

    private double[] buildSpreadAngles(int count) {
        if (count <= 1) return new double[]{0};

        double spread = 15.0 * (count - 1);   // total arc
        double step   = spread / (count - 1);
        double[] angles = new double[count];
        for (int i = 0; i < count; i++) {
            angles[i] = -spread / 2.0 + i * step;
        }
        return angles;
    }

    private void spawnProjectile(Vector2 velocity, int dmg, boolean flame) {
        Projectile p = new Projectile((int) getX(), (int) getY(), velocity, 1, playScrn);
        p.setDamage(dmg);
        p.setFlame(flame);
        world.addObject(p);
    }

    // rotate a direction vector by degrees (used for spread shot)
    private Vector2 rotate(Vector2 dir, double degrees) {
        double rad = Math.toRadians(degrees);
        double cos = Math.cos(rad), sin = Math.sin(rad);
        double nx  = dir.x * cos - dir.y * sin;
        double ny  = dir.x * sin + dir.y * cos;
        return Vector2.multiply(new Vector2(nx, ny), projectileSpeed);
    }

    // regen
     private void tickRegen() {
        boolean hasRegen = hasAbility(PlayerAbility.HP_REGEN) || hasAbility(PlayerAbility.FORTIFIED_REGEN);
        if (!hasRegen) return;

        int regenLevel   = Math.max(getAbilityLevel(PlayerAbility.HP_REGEN), getAbilityLevel(PlayerAbility.FORTIFIED_REGEN));
        int regenInterval = Math.max(regenBase / regenLevel, 1);
 
        regenTickCounter++;
        if (regenTickCounter >= regenInterval) {
            regenTickCounter = 0;
            addHP(1);
        }
}

    //  shield
    private void updateShieldCooldown(int level) {
        if (shield == null) {
            shield = new Shield(this, playScrn);
            world.addObject(shield);
        }
        int cooldown = switch (level) {
            case 2  -> 210;
            case 3  -> 120;
            default -> 300;   // level 1
        };
        shield.setCooldownMax(cooldown);
        System.out.println("Shield cooldown set to " + cooldown + " frames");
    }
 
    // Collision
    @Override
    public void onCollision()
    {
        // Double check if there is nothing being collided with
        if(!this.collider.getCollidingWith().isEmpty())
        {
            ArrayList<CollisionObject> colList = this.collider.getCollidingWith();
            boolean hashitUnmovable = false;
            
            for(int x = 0; x < colList.size() && !colList.isEmpty() ; x++)
            {
                // Unmovable object check
                if(colList.get(x).getIsMovable() == false && hashitUnmovable == false)
                {
                    this.move(Vector2.multiply(this.getVelocity(), -1));
                    hashitUnmovable = true;
                }
            }
        }
    }

    // HP helpers 
    @Override
    public void minusHP(double a) {
        stats.takeDamage((int) a);
        this.health = stats.getCurrentHP();
    }

    @Override
    public void addHP(double a) {
        stats.heal((int) a);
        this.health = stats.getCurrentHP();
    }

    @Override
    public void setHealth(int hp) {
        stats.setCurrentHP(hp);
        this.health = stats.getCurrentHP();
    }
  
    // abilitiy helper
    public void addAbility(PlayerAbility ability) {
        applyAbility(ability); 
    }

    public boolean hasAbility(PlayerAbility ability) {
        return abilities.contains(ability);
    }   

    @Override
    public void onDeath()
    {
        
    }

        return getAbilityLevel(ability) >= 1;
    }   

    public int getAbilityLevel(PlayerAbility ability) {
        return abilityStacks.getOrDefault(ability, 0);
    }

    public boolean isAbilityMaxed(PlayerAbility ability) {
        return getAbilityLevel(ability) >= PlayerAbility.MAX_STACKS;
    }
    // Setters getters

    public void setWorldRenderer(WorldRenderer w)
    {
        this.world = w;
        System.out.println("Added a world renderer");
    }
    // Getters
    public double     getHealth()    { return stats.getCurrentHP(); }
    public int        getMaxHP()     { return stats.getMaxHP(); }
    public int        getAttack()    { return stats.getAttack(); }
    public int        getDefense()   { return stats.getDefense(); }
    public PlayerStats getStats()    { return stats; }
    

    public void setUIOpen(boolean open) {this.uiOpen = open;}
    public Vector2 getVelocity(){return Vector2.multiply(inputs.getInputVector(), speed);}
    public boolean isInteracting(){ return this.isInteracting;}
    public void checkInteracting(){isInteracting = inputs.getIsInteracting();}
}