package object;

import scenes.*;
import systems.*;

public abstract class Entity extends GameObject {
    
    protected int health = 5;
    protected double speed = 10;
    protected int damage = 1;

    public Entity(Vector2 position, double s, PlayableScreen p)
    {
        super(position.x, position.y, s, p);
    }

    public void setHealth(int health) {this.health = health;}
    public void minusHP(double a) {this.health -= a;}
    public void addHP(double a) {this.health += a;}
    public int getHP(){return this.health;}

    // Overridable method
    public void die(){}
}