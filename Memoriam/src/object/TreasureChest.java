package object;

import collision.RectangleCollider;
import images.ImageLibrary;
import scenes.*;
import systems.*;
public class TreasureChest extends GameObject {
    
    // When player presses letter E, the treasure chest panel pops up
    private int interactionDistance = 150;
    private boolean  stageCleared = false;
    protected Player targetPlayer = null;   // changed from priv to protected


    // Check when the player is close enough to this particular
    public TreasureChest(Vector2  v, Player  p, int s, PlayableScreen scrn)
    {
        super(v, s, scrn);
        this.targetPlayer =  p;

        setImage(ImageLibrary.get().treasureChest);

        this.setCollider(new RectangleCollider(this, true, 32, 32, 32, 32));
        this.getCollider().setIsMovable(false);
    }     
    
    public TreasureChest(int a, int b, Player  p,  int s, PlayableScreen scrn)
    {
        super(a, b, s, scrn);
        this.targetPlayer =  p;
        setImage(ImageLibrary.get().treasureChest);
        
        this.setCollider(new RectangleCollider(this, true, 32, 32, 32, 32));
        this.getCollider().setIsMovable(false);

    } 


    @Override
    public void update()
    {
        if(stageCleared)
        {
            //System.out.println(Vector2.distance(this.position, this.targetPlayer.position));
            if(checkPlayerDistance())
            {
                //System.out.println("Hello there,  I am now in interaction distance");
                setImage(ImageLibrary.get().treasureChestH);
                doInteractionLogic();
            } else
            {
                setImage(ImageLibrary.get().treasureChest);
                playScrn.getCardManager().showChestUI = false;

            }
        } else
        {
            setImage(ImageLibrary.get().lockedTreasureChest);
            stageCleared = checkStageClear();
        }
        
    }


    public boolean checkStageClear()
    {
        for(GameObject obj : this.playScrn.getWorldRenderer().getObjectList())
        {
            if(obj instanceof Enemy) return false;
        }   

        return true;
    }

    public boolean checkPlayerDistance()
    {
        // Compare distance
        boolean isWithinInteraction;

        isWithinInteraction = (Vector2.distance(this.position, this.targetPlayer.position) < interactionDistance);

        return isWithinInteraction;   
    }

    public void doInteractionLogic() {

        if (targetPlayer.isInteracting()) {
            Player.canMove = false;
            playScrn.getCardManager().openChest();
        }
    }
}
