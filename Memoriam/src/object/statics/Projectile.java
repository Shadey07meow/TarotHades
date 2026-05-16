package object.statics;
import images.ImageLibrary;
import collision.CollisionObject;
import collision.RectangleCollider;
import object.Entities.Entity;
import object.Entities.Enemy;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import scenes.templates.PlayableScreen;
import systems.*;

public class Projectile extends GameObject{
    // A projectile class with a constant velocity
    // When created, it moves at a given velocity and dissapates after a given amount of time

    private Vector2 velocity;
    private WorldRenderer world;
    private int lifeTime = 120; // frames
    private int damage = 1;
    private boolean isFlame = false;
    private final Class<?> parentClass;

    public Projectile(int x, int y, Vector2 velocity, int scale, PlayableScreen scrn, Class<?> parentClass, Image projectileImage){
        super(x, y, scale, scrn);
        this.velocity = velocity;
        this.world = scrn.getWorldRenderer();
        this.parentClass = parentClass;
        setImage(projectileImage);
        
        this.setCollider((new RectangleCollider(this, true, 16, 16, 16, 16)));
        setColor(Color.RED);
    }

    @Override
    public void update(){
        super.update();
        move((int)velocity.x, (int)velocity.y);

        lifeTime--;
        if(lifeTime <= 0){
            // mark for removal later
            world.removeObject(this);
        }
    }

    @Override
    public void onCollision()
    {
         if (this.collider.getCollidingWith().isEmpty()) return;

        ArrayList<CollisionObject> colList = this.collider.getCollidingWith();

        for (int x = 0; x < colList.size(); x++) {
            CollisionObject col   = colList.get(x);
            GameObject      other = col.getGameObject();

            // Never hit the player or other projectiles


            if(other.getClass().isAssignableFrom(parentClass)) return;
            // if(other.getClass().getCanonicalName().trim().equals(parentClass.getCanonicalName().trim())) return;
            
            if(other instanceof Entity entity )
            {
                entity.onHit(this.damage);
            }
            world.removeObject(this);
            return;

            // // Hit an enemy
            // if (other instanceof Enemy) {
            //     ((Enemy) other).damage(this.damage);
            //     ((Enemy) other).setDetectedPlayer(true);
            //     this.playScrn.getSpecialEffects().spawnNumberPopup(other.getPosition(), this.damage);
            // } else

            // // Hit an immovable wall
            // if (!col.getIsMovable()) {
            //     world.removeObject(this);
            //     return;
            // }

            // Hit anything else
        

        }
    }

    //SETTERS


    public Class<?> getParentClass()
    {
        return this.parentClass;
    }

    public void setDamage(int d){this.damage = d;}
  

}
