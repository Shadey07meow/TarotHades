package systems;

import images.ImageLibrary;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import object.Player;

/**
 * Draws the player's heart-based health bar in the upper-left corner.
 *
 * Layout:
 *  - Hearts are arranged horizontally, HEARTS_PER_ROW per row.
 *  - Filled hearts use the alive sprite; empty slots use the dead sprite.
 *  - When max HP exceeds one row, hearts wrap to the next row automatically.
 *
 * Usage (in PlayableScreen.paintComponent):
 *    healthBarUI.draw(g, player);
 */
public class HealthBar {

    // ── Layout constants ─────────────────────────────────────────────────────

    /** Pixel size each heart is drawn at. */
    private static final int HEART_SIZE     = 36;

    /** Horizontal gap between hearts. */
    private static final int HEART_SPACING  = 4;

    /** Distance from the left edge of the screen. */
    private static final int MARGIN_LEFT    = 16;

    /** Distance from the top edge of the screen. */
    private static final int MARGIN_TOP     = 16;

    /** Row gap (top of row N to top of row N+1). */
    private static final int ROW_HEIGHT     = HEART_SIZE + 6;

    /** How many hearts fit in one row before wrapping. */
    private static final int HEARTS_PER_ROW = 10;

    // ── Sprites ──────────────────────────────────────────────────────────────

    private final BufferedImage heartAlive;
    private final BufferedImage heartDead;

    // ── Constructor ──────────────────────────────────────────────────────────

    public HealthBar() {
        ImageLibrary img = ImageLibrary.get();
        heartAlive = img.heart;
        heartDead  = img.deadHeart;
    }

    // ── Public draw method ───────────────────────────────────────────────────

    /**
     * Renders the full heart bar for the given player.
     * Call this inside paintComponent after drawing the world.
     */
    public void draw(Graphics g, Player player) {
        if (player == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        PlayerStats stats = player.getStats();
        int maxHP     = stats.getMaxHP();
        int currentHP = stats.getCurrentHP();

        // Clamp so we never render negative filled hearts
        if (currentHP < 0) currentHP = 0;
        if (currentHP > maxHP) currentHP = maxHP;

        for (int i = 0; i < maxHP; i++) {
            int col = i % HEARTS_PER_ROW;
            int row = i / HEARTS_PER_ROW;

            int x = MARGIN_LEFT + col * (HEART_SIZE + HEART_SPACING);
            int y = MARGIN_TOP  + row * ROW_HEIGHT;

            BufferedImage sprite = (i < currentHP) ? heartAlive : heartDead;

            if (sprite != null) {
                g2.drawImage(sprite, x, y, HEART_SIZE, HEART_SIZE, null);
            }
        }
    }
}
