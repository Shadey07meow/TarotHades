package object;

import java.util.ArrayList;


import collision.*;
import images.ImageLibrary;
import java.awt.Color;
import java.util.ArrayList;
import scenes.*;
import systems.*;

import collision.CollisionObject;
import collision.RectangleCollider;
import images.ImageLibrary;
import scenes.PlayableScreen;
import systems.Vector2;
import systems.WorldRenderer;

public class EnemyProjectile extends GameObject{

    

    // A projectile class with a constant velocity
    // When created, it moves at a given velocity and dissapates after a given amount of time

    private Vector2 velocity;
    private WorldRenderer world;
    private int lifeTime = 120; // frames
    private int damage = 1;
    private boolean isFlame = false;

    public EnemyProjectile(int x, int y, Vector2 velocity, int scale, PlayableScreen scrn){
        super(x, y, scale, scrn);
        this.velocity = velocity;
        this.world = scrn.getWorldRenderer();
        this.setImage(ImageLibrary.get().projectile);
        
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
            
            // Hit an enemy
            if (other instanceof Player) {
                ((Player) other).minusHP(1);
                world.removeObject(this);
                return;
            } else if (!col.getIsMovable()) { // Hit an immovable wall 
                world.removeObject(this);
                return;
            } 

        }
    }

    
    

}

