package object.statics;

import systems.*;
import collision.*;
import scenes.templates.PlayableScreen;

public class BarrierObject extends GameObject {

    public BarrierObject(Vector2 position, PlayableScreen scrn, Bounds b)
    {
        super(position, scrn);
        setImage(null);
        this.setCollider(new RectangleCollider(this, true, b));
        this.getCollider().setIsMovable(false);

    }
}
