package object;

import collision.*;
import java.awt.Color;
import scenes.*;
import systems.SoundManager;

/** A deployable shield that orbits the player and blocks one hit per activation. */
public class Shield extends GameObject {

    private final Player owner;
    private boolean active = false;
    private int cooldownMax = 300;   // 5 s at 60 fps
    private int cooldown = 0;
    private int activeTimer = 0;
    private int activeMax = 180;     // 3 s active
    private double angle = 0;
    private static final double ORBIT_RADIUS = 60;

    public Shield(Player owner, PlayableScreen scrn) {
        super(owner.getX(), owner.getY(), 2, scrn);
        this.owner = owner;

        setColor(new Color(80, 160, 255, 180));
        setCollider(new RectangleCollider(this, true, 24, 24, 24, 24));
        
        this.collider.setIsMovable(true);
        deactivate();
    }

    @Override
    public void logicMethods() {
        if (cooldown > 0) { cooldown--; return; }

        if (!active) {
            activate();
        }

        if (active) {
            angle += 0.05;
            position.x = owner.getX() + Math.cos(angle) * ORBIT_RADIUS;
            position.y = owner.getY() + Math.sin(angle) * ORBIT_RADIUS;

            activeTimer--;
            if (activeTimer <= 0) deactivate();
        }
    }

    @Override
    public void onCollision() {
        if (!active) return;
        if (this.collider.getCollidingWith().isEmpty()) return;
        
        for (CollisionObject col : this.collider.getCollidingWith()) {
            GameObject other = col.getGameObject();

            if (other instanceof Projectile pr) {
                
                if(pr.getParentClass() == Player.class)
                {
                    return;
                }
                SoundManager.get().playSFX("pickup");
                world.removeObject(pr);
                deactivate();

                cooldown = cooldownMax;
                return;
            } else if (other instanceof Enemy en)
            {
                SoundManager.get().playSFX("pickup");
                world.removeObject(en);
                deactivate();

                cooldown = cooldownMax;
                return;
            }
        }
    }

    private void activate() {
        active = true;
        activeTimer = activeMax;
        setColor(new Color(80, 160, 255, 200));
    }

    private void deactivate() {
        active = false;
        setColor(new Color(80, 160, 255, 40));
        if (cooldown == 0) cooldown = cooldownMax;
    }

    public boolean isActive() { return active; }

    public void setCooldownMax(int frames) { this.cooldownMax = frames;}
}