package object;

import images.ImageLibrary;
import systems.*;
import scenes.*;
public class TreasureChest extends GameObject {
    
    // When player presses letter E, the treasure chest panel pops up
    private int interactionDistance = 150;
    private Player targetPlayer =  null;
    private GameStart world;
    

    // Check when the player is close enough to this particular
    public TreasureChest(Vector2  v, Player  p, int s, GameStart world)
    {
        super(v, s, world);
        this.targetPlayer =  p;
        this.world = world;
        setImage(ImageLibrary.get().treasureChest);
    }     
    
    public TreasureChest(int a, int b, Player  p,  int s, GameStart world)
    {
        super(a, b, s, world);
        this.targetPlayer =  p;
        this.world = world;
        setImage(ImageLibrary.get().treasureChest);
        
    } 


    @Override
    public void update()
    {
        //System.out.println(Vector2.distance(this.position, this.targetPlayer.position));
        if(checkPlayerDistance())
        {
            System.out.println("Hello there,  I am now in interaction distance");
            setImage(ImageLibrary.get().treasureChestH);
            doInteractionLogic();
        } else
        {
            setImage(ImageLibrary.get().treasureChest);
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
