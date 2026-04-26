package object;
import images.ImageLibrary;
import java.awt.Color;
import java.util.ArrayList;

import systems.*;
import scenes.*;
import collision.*;

public class Projectile extends GameObject{
    // A projectile class with a constant velocity
    // When created, it moves at a given velocity and dissapates after a given amount of time

    private Vector2 velocity;
    private WorldRenderer world;
    private int lifeTime = 120; // frames
    private int damage = 1;

    public Projectile(int x, int y, Vector2 velocity, int scale, PlayableScreen scrn){
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

    public void onCollision()
    {
        // Double check if there is nothing being collided with
        if(!this.collider.getCollidingWith().isEmpty())
        {
            ArrayList<CollisionObject> colList = this.collider.getCollidingWith();
            boolean hashitUnmovable = false;
            
            for(int x = 0; x < colList.size(); x++)
            {
                if(colList.get(x).getGameObject() instanceof Player) continue;
                // Unmovable object check
                if(colList.get(x).getGameObject() instanceof Enemy)
                {
                    ///System.out.println("I literally hit something");    
                    world.removeObject(this);
                    Enemy enmy = (Enemy)colList.get(x).getGameObject();
                    enmy.damage(this.damage);
                } else
                {
                    world.removeObject(this);
                }
            }
        }
    }
}
