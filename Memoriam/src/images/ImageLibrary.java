package images;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageLibrary
{
    // Library of images
    
    // UI
    public final Image background;
    public final Image exitBtn;
    public final Image optionBtn;
    public final Image startBtn;

    // Player
    public final Image playerSpritesUP;
    public final Image playerSpritesDOWN;
    public final Image playerSpritesLEFT;
    public final Image playerSpritesRIGHT;

    // Cursor
    public final Image quillCursor;
    public final Image swordCursor;

    public ImageLibrary()
    {


        // Player Sprites
        playerSpritesUP = new ImageIcon(getClass().getResource("assets/PlayerSprites/foolUp.png")).getImage();   
        playerSpritesDOWN =  new ImageIcon(getClass().getResource("assets/PlayerSprites/foolDown.png")).getImage();
        playerSpritesLEFT = new ImageIcon(getClass().getResource("assets/PlayerSprites/foolLeft.png")).getImage();
        playerSpritesRIGHT = new ImageIcon(getClass().getResource("assets/PlayerSprites/foolRight.png")).getImage();

        // Cursor
        quillCursor = new ImageIcon(getClass().getResource("assets/MainAssets/cursor.png")).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        swordCursor = new ImageIcon(getClass().getResource("assets/MainAssets/swordCursor.png")).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);

        // Backgrounds
        background = new ImageIcon(getClass().getResource("assets/Panels/backgroundImage.png")).getImage();
        exitBtn = new ImageIcon(getClass().getResource("assets/Panels/placeholderBtn.png")).getImage();
        optionBtn = new ImageIcon(getClass().getResource("assets/Panels/placeholderBtn.png")).getImage();
        startBtn = new ImageIcon(getClass().getResource("assets/Panels/placeholderBtn.png")).getImage();

    }

    
}