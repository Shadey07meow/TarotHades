package object.statics;

import object.Entities.Player;
import scenes.templates.PlayableScreen;
import systems.Vector2;

/** A special TreasureChest placed in the lobby (GameStart level).
 * Instead of opening a random card draw, it opens the "Big Three" relic
 */

public class LobbyTreasureChest extends TreasureChest {

    public LobbyTreasureChest(Vector2 v, Player p, int s, PlayableScreen scrn) {
        super(v, p, s, scrn);
    }
    @Override
    public void doInteractionLogic() {
        if (targetPlayer.isInteracting()) {
            if (playScrn.getID() == 67) {
                playScrn.getGameFrame().showPanel("menu");
            }
            else {
                playScrn.getCardManager().openLobbyChest();
            }
        }
    }
}
