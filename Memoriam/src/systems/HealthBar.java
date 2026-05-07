package systems;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;
import object.Player;
import object.PlayerAbility;
import object.RelicStatusEffect;
import object.StatusEffect;

/* Draws the player's heart-based health bar in the upper-left corner.
 * Usage (in PlayableScreen.paintComponent):
 *    healthBarUI.draw(g, player);
 */
public class HealthBar {

    // Pixel size each heart is drawn at. 
    private static final int HEART_SIZE     = 50;

    // Horizontal gap between hearts. 
    private static final int HEART_SPACING  = 4;

    // Distance from the left edge of the screen. 
    private static final int MARGIN_LEFT    = 16;

    // Distance from the top edge of the screen
    private static final int MARGIN_TOP     = 16;

    // Row gap (top of row N to top of row N+1)
    private static final int ROW_HEIGHT     = HEART_SIZE + 6;

    // How many hearts fit in one row before wrapping. 
    private static final int HEARTS_PER_ROW = 10;

    private static final int ICON_SIZE      = 50;
    private static final int ICON_SPACING   = 8;
    private static final int ICON_MARGIN    = 16;    // from left edge (same as heart bar)
    // Gap between the bottom of the last heart row and the top of the icon strip
    private static final int ICON_TOP_GAP   = 10;

    private int empressPulseTimer = 0;
    private boolean pulseRising  = true;


    private final BufferedImage heartAlive;
    private final BufferedImage heartDead;



    public HealthBar() {
        ImageLibrary img = ImageLibrary.get();
        heartAlive = img.heart;
        heartDead  = img.deadHeart;
    }

    /** Renders the full heart bar for the given player.
     * Call this inside paintComponent after drawing the world.
     */
    public void draw(Graphics g, Player player) {
        if (player == null) return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawHearts(g2, player);
        drawStatusIcons(g2, g2.getClipBounds(), player);
        drawEmpressVignette(g2, g2.getClipBounds(), player);
    }

    private void drawHearts(Graphics2D g2, Player player) {
        PlayerStats stats = player.getStats();
        int maxHP         = stats.getMaxHP();
        int currentHP     = Math.max(0, Math.min(stats.getCurrentHP(), maxHP));

        for (int i = 0; i < maxHP; i++) {
            int col = i % HEARTS_PER_ROW;
            int row = i / HEARTS_PER_ROW;
            int x   = MARGIN_LEFT + col * (HEART_SIZE + HEART_SPACING);
            int y   = MARGIN_TOP  + row * ROW_HEIGHT;
            BufferedImage sprite = (i < currentHP) ? heartAlive : heartDead;
            if (sprite != null) g2.drawImage(sprite, x, y, HEART_SIZE, HEART_SIZE, null);
        }
    }


        private void drawStatusIcons(Graphics2D g2, Rectangle clip, Player player) {
            List<StatusEffect> effects = StatusEffectManager.get().getActiveEffects();
            List<RelicStatusEffect> relics = StatusEffectManager.get().getActiveRelics();
            if (effects.isEmpty() && relics.isEmpty()) return;

            ImageLibrary img = ImageLibrary.get();

            // sit below the heart bar
            int heartRows = (int) Math.ceil((double) player.getStats().getMaxHP() / HEARTS_PER_ROW);
            int iconsY    = MARGIN_TOP + heartRows * ROW_HEIGHT + 10;
            int x         = MARGIN_LEFT; // left-aligned, same as hearts
            
            // paint relics first
            for (RelicStatusEffect r : relics) {

                BufferedImage icon = switch (r.getRelic()) {
                    case DEATH -> img.iconDeath;
                    case MAGICIAN -> img.iconTheMagician;
                    case THE_EMPRESS -> img.iconTheEmpress;
                };

                g2.setColor(new Color(0, 0, 0, 160));
                g2.fillRoundRect(x - 3, iconsY - 3, ICON_SIZE + 6, ICON_SIZE + 22, 10, 10);

                if (icon != null) {
                    g2.drawImage(icon, x, iconsY, ICON_SIZE, ICON_SIZE, null);
                }

                g2.setFont(new Font("Monospaced", Font.BOLD, 11));
                g2.setColor(new Color(255, 220, 120));
                g2.drawString("REL", x + 10, iconsY + ICON_SIZE + 13);

                x += ICON_SIZE + ICON_SPACING;
            }

            for (StatusEffect effect : effects) {

                // pick the right icon image from ImageLibrary
                BufferedImage icon = switch (effect.getAbility()) {
                    case HP_REGEN        -> img.iconTwoOfCups;
                    case FLAME_SHOT      -> img.iconAceOfWands;
                    case MULTI_SHOT      -> img.iconTenOfSwords;
                    case FORTIFIED_REGEN -> img.iconNineOfPentacles;
                    case SHIELD          -> img.iconQueenOfCups;
                    case SPEED_ENHANCE   -> img.iconKnightOfWands;
                };

                // dark background pill
                g2.setColor(new Color(0, 0, 0, 160));
                g2.fillRoundRect(x - 3, iconsY - 3, ICON_SIZE + 6, ICON_SIZE + 22, 10, 10);

                // draw the actual icon image
                if (icon != null) {
                    g2.drawImage(icon, x, iconsY, ICON_SIZE, ICON_SIZE, null);
                }

                // "2L" levels-remaining label underneath
                g2.setFont(new Font("Monospaced", Font.BOLD, 11));
                String lvlText = effect.getLevelsRemaining() + "L";
                int lw = g2.getFontMetrics().stringWidth(lvlText);
                g2.setColor(new Color(220, 220, 100));
                g2.drawString(lvlText, x + (ICON_SIZE - lw) / 2, iconsY + ICON_SIZE + 13);

                x += ICON_SIZE + ICON_SPACING; // next icon goes to the right
        }
    }

    /** Small dots below the icon indicating power/stack level. */
    private void drawPowerDots(Graphics2D g2, int iconX, int iconY, int power) {
        int dotSize    = 5;
        int dotSpacing = 3;
        int totalW     = power * dotSize + (power - 1) * dotSpacing;
        int startX     = iconX + (ICON_SIZE - totalW) / 2;
        int dotY       = iconY + ICON_SIZE + 4;

        for (int i = 0; i < power; i++) {
            g2.setColor(new Color(255, 200, 60));
            int dx = startX + i * (dotSize + dotSpacing);
            g2.fillOval(dx, dotY, dotSize, dotSize);
        }
    }

    private void drawEmpressVignette(Graphics2D g2, Rectangle clip, Player player) {
        if (!player.isHalfHpWarning()) {
            empressPulseTimer = 0;
            pulseRising       = true;
            return;
        }

        // Animate alpha pulsing 0 → 80 → 0
        if (pulseRising) {
            empressPulseTimer += 2;
            if (empressPulseTimer >= 80) pulseRising = false;
        } else {
            empressPulseTimer -= 2;
            if (empressPulseTimer <= 0) pulseRising = true;
        }

        int w = clip != null ? clip.width  : 1920;
        int h = clip != null ? clip.height : 1080;

        // Radial-like vignette (gradient from edges)
        int alpha = empressPulseTimer;
        g2.setColor(new Color(180, 0, 0, alpha));
        g2.fillRect(0, 0, w, h);
    }

    private Color abilityColor(PlayerAbility ability) {
        return switch (ability) {
            case HP_REGEN        -> new Color(220, 60,  60);   // red
            case FORTIFIED_REGEN -> new Color(200, 130, 30);   // orange
            case SHIELD          -> new Color(60,  130, 220);  // blue
            case SPEED_ENHANCE   -> new Color(60,  200, 100);  // green
            case FLAME_SHOT      -> new Color(220, 120, 40);   // fire orange
            case MULTI_SHOT      -> new Color(140, 60,  220);  // purple
        };
    }
    private String abilityInitials(PlayerAbility ability) {
        return switch (ability) {
            case HP_REGEN        -> "HP";
            case FORTIFIED_REGEN -> "FR";
            case SHIELD          -> "SH";
            case SPEED_ENHANCE   -> "SP";
            case FLAME_SHOT      -> "FS";
            case MULTI_SHOT      -> "MS";
        };
    }



}
