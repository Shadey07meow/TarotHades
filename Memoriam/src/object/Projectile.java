package object;
import collision.*;
import images.ImageLibrary;
import java.awt.Color;
import java.util.ArrayList;
import scenes.*;
import systems.*;

public class Projectile extends GameObject{
    // A projectile class with a constant velocity
    // When created, it moves at a given velocity and dissapates after a given amount of time

    private Vector2 velocity;
    private WorldRenderer world;
    private int lifeTime = 120; // frames
    private int damage = 1;
     private int bounceCount = 0;
     private boolean isFlame = false;

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


    @Override
    public void onCollision()
    {
        // Double check if there is nothing being collided with
        if(!this.collider.getCollidingWith().isEmpty())
        {
            ArrayList<CollisionObject> colList = this.collider.getCollidingWith();
            for (int x = 0; x < colList.size(); x++) {
                CollisionObject col = colList.get(x);
                GameObject other = col.getGameObject();

                // Ignore the player and other projectiles (fixes spread-shot self-collision)
                if (other instanceof Player) continue;
                if (other instanceof Projectile) continue;

                // Hit an enemy
                    if (other instanceof Enemy) {
                        Enemy enmy = (Enemy) other;
                        enmy.damage(this.damage);

                    // Bouncing shot: bounce off enemy once, then die on next hit
                    if (bounceCount > 0) {
                        bounceCount = 0;       // only one more bounce allowed after hitting enemy
                        velocity = new Vector2(-velocity.x, -velocity.y);
                        lifeTime = 80;
                    } 
                    else 
                    {
                        world.removeObject(this);
                    }
                    return; // stop processing after first meaningful hit
                }

                // Hit an immovable wall
                if (!col.getIsMovable()) {
                    if (bounceCount > 0) {
                        bounceCount--;
                        velocity = new Vector2(-velocity.x, -velocity.y);
                        lifeTime = 80;
                    } else {
                        world.removeObject(this);
                    }
                    return;
                }
            // Hit anything else — remove
            world.removeObject(this);
            return;
        }  
    }
}

    //SETTERS

    public void setDamage(int d){this.damage = d;}
    public void setBounces(int b){this.bounceCount =b;}
    public void setFlame(boolean f){
        this.isFlame=f;
        if (f) setColor(new Color(255,100,0));
    }
}
