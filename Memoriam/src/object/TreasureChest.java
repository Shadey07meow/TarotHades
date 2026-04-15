package object;

import images.ImageLibrary;
import systems.*;
public class TreasureChest extends GameObject {
    
    // When player presses letter E, the treasure chest panel pops up
    private int interactionDistance = 400;
    private Player targetPlayer =  null;
    

    // Check when the player is close enough to this particular
    public TreasureChest(Vector2  v, Player  p, int s)
    {
        super(v,  s);
        this.targetPlayer =  p;
        setImage(new ImageLibrary().treasureChest);
    }     
    
    public TreasureChest(int a, int b, Player  p,  int s)
    {
        super(a, b, s);
        this.targetPlayer =  p;
        setImage(new ImageLibrary().treasureChest);
        
    } 


    @Override
    public void update()
    {
        if(checkPlayerDistance())
        {
            System.out.println("Hello there,  I am now in interaction distance");
            doInteractionLogic();
        }
    }

    public boolean checkPlayerDistance()
    {
        // Compare distance
        boolean isWithinInteraction;

        isWithinInteraction = (Vector2.distance(this.position, this.targetPlayer.position) < interactionDistance);

        return false;   
    }

    public void doInteractionLogic()
    {
        // When pressed, the card-popup will appear
    }
}
