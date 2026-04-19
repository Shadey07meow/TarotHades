package object;

import images.ImageLibrary;
import systems.*;
import scenes.*;
public class TreasureChest extends GameObject {
    
    // When player presses letter E, the treasure chest panel pops up
    private int interactionDistance = 300;
    private Player targetPlayer =  null;
    private GameStart world;
    

    // Check when the player is close enough to this particular
    public TreasureChest(Vector2  v, Player  p, int s, GameStart world)
    {
        super(v,  s);
        this.targetPlayer =  p;
        this.world = world;
        setImage(new ImageLibrary().treasureChest);
    }     
    
    public TreasureChest(int a, int b, Player  p,  int s, GameStart world)
    {
        super(a, b, s);
        this.targetPlayer =  p;
        this.world = world;
        setImage(new ImageLibrary().treasureChest);
        
    } 


    @Override
    public void update()
    {
        System.out.println(Vector2.distance(this.position, this.targetPlayer.position));
        if(checkPlayerDistance())
        {
            System.out.println("Hello there,  I am now in interaction distance");
            setImage(new ImageLibrary().treasureChestH);
            doInteractionLogic();
        } else
        {
            setImage(new ImageLibrary().treasureChest);
            world.showChestUI = false;

        }
    }

    public boolean checkPlayerDistance()
    {
        // Compare distance
        boolean isWithinInteraction;

        isWithinInteraction = (Vector2.distance(this.position, this.targetPlayer.position) < interactionDistance);

        return isWithinInteraction;   
    }

    public void doInteractionLogic()
    {
        // When pressed, the card-popup will appear
        if(targetPlayer.isInteracting())
        {
            world.showCards();
            world.showChestUI = true;

        }
    }
}
