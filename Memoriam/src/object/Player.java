package object;

import images.*;
import java.awt.Image;
import java.util.ArrayList;
import scenes.*;
import systems.*;

public class Player extends GameObject {

    /// Player Game Object
    /// Stores position and parameters for the player
    /// Handles movement, health, attacks and stuff like that

    
    private int speed = 1;
    private int health = 1;
    private int fireCooldown = 0;
    private GameFrame gameFrame;
    private InputManager inputs = null;
    private boolean hasShotProjectile = false;
    private ImageLibrary imgLib = new ImageLibrary();
    private boolean isDead = false;

    // for sprites
    private final Image spriteUp = imgLib.playerSpritesUP;
    private final Image spriteDown = imgLib.playerSpritesDOWN;
    private final Image spriteLeft = imgLib.playerSpritesLEFT;
    private final Image spriteRight = imgLib.playerSpritesRIGHT;

    // game objects
    private ArrayList<GameObject> objects;



    public Player(Vector2 position, int scale, int speed, int health, InputManager inps, ArrayList<GameObject> objs,GameFrame gameFrame)
    {
        super(position.x, position.y, scale);
        this.speed = speed;
        this.health = health;
        this.inputs = inps;
        this.objects = objs;
        this.gameFrame = gameFrame;
       
        setImage(spriteDown);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void update()
    {
        super.update();
        // cooldown
        if (fireCooldown > 0)
            fireCooldown--;
       
        if (isDead) return;

        movePlayer();
        combatMethod();

        // loser condition
        if (health <= 0) {
            isDead = true;
            gameFrame.showPanel("lose");
            return;
        }
        

        // Makes the rendering smooth
        // alpha = 0.25, you move towards the target by 25% every time
        // makes it smoother
    }

    private void movePlayer() 
    {
        Vector2 inpVector = inputs.getInputVector();

        double dx = inpVector.x * speed;
        double dy = inpVector.y * speed;

        // move x
        move(dx, 0);
        //handleCollision(dx, 0);

        // move y
        move(0, dy);
        //handleCollision(0, dy);

        // stops at screen edges
        keepInsideScreen();

        

        if (inpVector.x > 0) setImage(spriteRight);
        else if (inpVector.x < 0) setImage(spriteLeft);
        else if (inpVector.y > 0) setImage(spriteUp);
        else if (inpVector.y < 0) setImage(spriteDown);
    }


    private void combatMethod()
    {
        // shoot once per click
    if(!hasShotProjectile)
    {
        if(inputs.getClickingStatus())
        {
            shootProjectile();
            hasShotProjectile = true;
        }
    }
    else
    {
        // reset when mouse released
        if(!inputs.getClickingStatus())
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


    private void keepInsideScreen() {
        int halfW = getScaledWidth() / 2;
        int halfH = getScaledHeight() / 2;

        int maxX = 1920;
        int maxY = 1080;

        if (getX() - halfW < 0) setX(halfW);
        if (getY() - halfH < 0) setY(halfH);

        if (getX() + halfW > maxX) setX(maxX - halfW);
        if (getY() + halfH > maxY) setY(maxY - halfH);
    }

    private void shootProjectile(){
    if (fireCooldown != 0) return;

    double spawnX = getX();
    double spawnY = getY(); 

    Vector2 click = inputs.getClickPosition();

    double dx = click.x - spawnX;
    double dy = click.y - spawnY;

    double length = Math.sqrt(dx * dx + dy * dy);
    if (length == 0) return;

    double vx = (dx / length) * 8;
    double vy = -(dy / length) * 8;

    Vector2 velocity = new Vector2(
        (int)Math.round(vx),
        (int)Math.round(vy)
    );

      objects.add(new Projectile(
        (int)spawnX,
        (int)spawnY,
        velocity,
        30
    ));
        fireCooldown = 10;
        System.out.println("Shot fired");
    }

}