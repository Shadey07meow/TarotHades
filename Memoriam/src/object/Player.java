package object;

import images.*;
import java.awt.Image;
import java.util.HashSet;
import java.util.ArrayList;

import scenes.*;
import systems.*;
import collision.*;

public class Player extends Entity {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that
    private final GameFrame gameFrame;
    private ImageLibrary imgLib = ImageLibrary.get();
    private final PlayerStats stats;
    
    private HashSet<PlayerAbility> abilities = new HashSet<>();
    private InputManager inputs = null;
    
    // Combat variables
    private final double projectileSpeed = 15;
    private final double fireCooldown = 10;
    
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


    @Override
    public void update()
    {
        super.update();

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% every time
        // makes it smoother
    }

    @Override
    public void logicMethods()
    {
        this.health = stats.getCurrentHP();
        this.speed  = stats.getSpeed();

        if(world != null)
        {
            if (!isDead)
            {
                // loser condition
                inputOperations();
            } else
            {
                    
                gameFrame.showPanel("lose");
            }

            isDead = (health <= 0);

        }

    }

    // ability / power-up application
    public void applyAbility(PlayerAbility ability) {
        if (ability == null) return;

        if (abilities.contains(ability)) return;
        abilities.add(ability);

        // look up and apply the PowerUp from the manager
        PowerUp powerUp = PowerUpManager.get(ability);
        if (powerUp != null) {
            stats.applyPowerUp(powerUp);
            // Clamp current HP to new max
            if (stats.getCurrentHP() > stats.getMaxHP()) {
                stats.setCurrentHP(stats.getMaxHP());
            }
        }

        // gameplay-side effects (behaviours, projectile types, etc.)
        switch (ability) {
            case HP_REGEN         -> enableRegen();
            case FLAME_SHOT       -> enableFlameShot();
            case HEAVY_STRIKE     -> enableHeavyStrike();
            case FORTIFIED_REGEN  -> enableFortifiedRegen();
            case SHIELD           -> enableShield();
            case BOUNCING_SHOT    -> enableBouncingShots();
        }
    }


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

    // Collision
    @Override
    public void onCollision()
    {
        // Double check if there is nothing being collided with
        if(!this.collider.getCollidingWith().isEmpty())
        {
            ArrayList<CollisionObject> colList = this.collider.getCollidingWith();
            boolean hashitUnmovable = false;
            
            for(int x = 0; x < colList.size(); x++)
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


    private void shootProjectile(){
        // Checks if we can shoot after shooting the last shot
        // cooldown
        
    
        if (currentCooldown != 0) return;


        double spawnX = getX();
        double spawnY = getY(); 

        Vector2 click = inputs.getClickPosition();        

        Vector2 velocity = Vector2.multiply(Vector2.getUnitVector(this.position, click ), projectileSpeed);

        world.addObject(new Projectile(
            (int)spawnX,
            (int)spawnY,
            velocity,
        1, 
            this.playScrn));

        currentCooldown = fireCooldown;
        System.out.println("Shot fired");
    }


    //holder lang muna so it compiles
    private void enableRegen()          { System.out.println("Regen enabled"); }
    private void enableFlameShot()      { System.out.println("Flame Shot enabled"); }
    private void enableHeavyStrike()    { System.out.println("Heavy Strike enabled"); }
    private void enableFortifiedRegen() { System.out.println("Fortified Regen enabled"); }
    private void enableShield()         { System.out.println("Shield enabled"); }
    private void enableBouncingShots()  { System.out.println("Bouncing Shots enabled"); }

    // Setters getters

    public void setWorldRenderer(WorldRenderer w)
    {
        this.world = w;
        System.out.println("Added a world renderer");
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