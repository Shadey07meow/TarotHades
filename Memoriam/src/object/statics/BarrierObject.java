package object.statics;

import systems.*;
import collision.*;
import scenes.templates.PlayableScreen;
import scenes.ui.GameFrame;

public class BarrierObject extends GameObject {

    public BarrierObject(Vector2 position, PlayableScreen scrn, Bounds b)
    {
        
        super(new Vector2(position.x , position.y), scrn);
        setImage(null);
        this.setCollider(new RectangleCollider(this, true, b));
        this.getCollider().setIsMovable(false);

    }
}
