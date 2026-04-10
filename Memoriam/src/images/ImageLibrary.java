package images;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageLibrary
{
    // Library of images
    
    // UI
    public static Image background;
    public static Image exitBtn;
    public static Image optionBtn;
    public static Image startBtn;

    // Player
    public static Image playerSpritesUP;
    public static Image playerSpritesDOWN;
    public static Image playerSpritesLEFT;
    public static Image playerSpritesRIGHT;

    // Cursor
    public static Image quillCursor;
    public static Image swordCursor;

    public ImageLibrary()
    {


        // Player Sprites
        playerSpritesUP = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolUp.png")).getImage();   
        playerSpritesDOWN =  new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolDown.png")).getImage();
        playerSpritesLEFT = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolLeft.png")).getImage();
        playerSpritesRIGHT = new ImageIcon(getClass().getResource("/assets/PlayerSprites/foolRight.png")).getImage();

        // Cursor
        quillCursor = new ImageIcon(getClass().getResource("/assets/MainAssets/cursor.png")).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        quillCursor = new ImageIcon(getClass().getResource("/assets/MainAssets/swordCursor.png")).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
    }

    
}