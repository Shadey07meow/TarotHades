package object;
import images.ImageLibrary;
import java.awt.Color;
import systems.*;

public class Projectile extends GameObject{
    // A projectile class with a constant velocity
    // When created, it moves at a given velocity and dissapates after a given amount of time

     private Vector2 velocity;
    private int lifeTime = 120; // frames

    public Projectile(int x, int y, Vector2 velocity, int scale){
        super(x, y, scale);
        this.velocity = velocity;
        this.setImage(ImageLibrary.get().projectile);
        setColor(Color.RED);
    }

    @Override
    public void update(){
        super.update();
        move((int)velocity.x, (int)velocity.y);

        lifeTime--;

        if(lifeTime <= 0){
            // mark for removal later
            setScale(0);
        }
    }
}
