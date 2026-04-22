package object;

import images.*;
import java.awt.Image;
import java.util.HashSet;
import scenes.*;
import systems.*;

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

    public Player(Vector2 position, int scale, int speed, int health, InputManager inps, GameFrame gameFrame)
    {
        super(position, scale);

        this.stats = new PlayerStats(health, 10, 0, speed);

        // Keep entity.health in sync for any legacy reads
        this.health = stats.getCurrentHP();
        this.speed  = stats.getSpeed();

        this.inputs = inps;
        this.gameFrame = gameFrame;
        this.world = null;
        setImage(spriteDown);
    }


    @Override
    public void update()
    {
        super.update();

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

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% every time
        // makes it smoother
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

    /*
    private void handleCollision(int dx, int dy)
    {
        for (GameObject obj : objects) {
            if (obj == this) continue;

            if (obj instanceof CollisionObject) {
                CollisionObject col = (CollisionObject) obj;

                if (col.isColliding(this)) {

                    int overlapX = (getScaledWidth()/2 + col.getScaledWidth()/2)
                                - Math.abs(getX() - col.getX());

                    int overlapY = (getScaledHeight()/2 + col.getScaledHeight()/2)
                                - Math.abs(getY() - col.getY());

                    // Resolve the smaller overlap (prevents teleporting)
                    if (overlapX < overlapY) {
                        if (getX() < col.getX()) {
                            setX(getX() - overlapX);
                        } else {
                            setX(getX() + overlapX);
                        }
                    } else {
                        if (getY() < col.getY()) {
                            setY(getY() - overlapY);
                        } else {
                            setY(getY() + overlapY);
                        }
                    }

                    // PUSHING LOGIC
                    if (col.getIsMovable()) {
                        col.move(dx / 2, dy / 2);
                    }
                }
            }
        }
    }
    */

    private void shootProjectile(){
        // Checks if we can shoot after shooting the last shot
        // cooldown
        
    
        if (currentCooldown != 0) return;


        double spawnX = getX();
        double spawnY = getY(); 

        Vector2 click = inputs.getClickPosition();

        double dx = click.x - spawnX;
        double dy = click.y - spawnY;

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        double vx = (dx / length) * projectileSpeed;
        double vy = -(dy / length) * projectileSpeed;

        Vector2 velocity = new Vector2(
        (int)Math.round(vx),
        (int)Math.round(vy)
        );

        world.addObject(new Projectile(
        (int)spawnX,
        (int)spawnY,
        velocity,
        1));

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