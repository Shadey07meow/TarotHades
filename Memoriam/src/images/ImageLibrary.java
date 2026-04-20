package images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLibrary
{
    // Library of images
        private static ImageLibrary instance;


    public static ImageLibrary get() {
        if (instance == null) {
            instance = new ImageLibrary();
        }
        return instance;
    }

    // UI
    
    public final BufferedImage exitBtn;
    public final BufferedImage optionBtn;
    public final BufferedImage startBtn;
    public final BufferedImage backBtn;
    public final BufferedImage placeholderBtn;

    public final BufferedImage loadingScreen;
    public final BufferedImage loseScreen;
    public final BufferedImage background;

    // ui - hover
    public final BufferedImage backBtnHover;
    public final BufferedImage startBtnHover;
    public final BufferedImage optionBtnHover;
    public final BufferedImage exitBtnHover;
    public final BufferedImage placeholderBtnHover;

    // Player (GIFs must stay Image for animation)
    public final Image playerSpritesUP;
    public final Image playerSpritesDOWN;
    public final Image playerSpritesLEFT;
    public final Image playerSpritesRIGHT;

    // Enemy Blue
    public final Image blueRIGHT;
    public final Image blueLEFT;

    // Cursor
    public final Image quillCursor;
    public final Image swordCursor;
    
    // for scaling
    private Graphics2D g2;
    public final BufferedImage rawMap;
    public final BufferedImage rawProjectile;


    // map
    public final BufferedImage map;

    // cards
    public final BufferedImage Empress;
    public final BufferedImage KnightOfWands;
    public final BufferedImage Magician;
    public final BufferedImage NineOfPentacles;
    public final BufferedImage QueenOfCups;
    public final BufferedImage TenOfSwords;
    public final BufferedImage TwoOfCups;
    public final BufferedImage AceOfWands;
    public final BufferedImage Death;

    // Objects
    public final BufferedImage treasureChest;
    public final BufferedImage treasureChestH;
    public final BufferedImage projectile;

    private ImageLibrary()
    {
        try {

            // Player Sprites (GIF animation preserved)
            playerSpritesDOWN = new ImageIcon(getClass().getResource(
                    "assets/PlayerSprites/animated/idle/fool_idle_front.gif"
            )).getImage();

            playerSpritesLEFT = new ImageIcon(getClass().getResource(
                    "assets/PlayerSprites/animated/idle/fool_idle_left.gif"
            )).getImage();

            playerSpritesUP = new ImageIcon(getClass().getResource(
                    "assets/PlayerSprites/animated/idle/fool_idle_back.gif"
            )).getImage();

            playerSpritesRIGHT = new ImageIcon(getClass().getResource(
                    "assets/PlayerSprites/animated/idle/fool_idle_right.gif"
            )).getImage();
            
            // Cursor
            quillCursor = ImageIO.read(getClass().getResource(
                    "assets/MainAssets/cursor.png"
            )).getScaledInstance(64, 64, Image.SCALE_SMOOTH);

            swordCursor = ImageIO.read(getClass().getResource(
                    "assets/MainAssets/swordCursor.png"
            )).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            
            // Backgrounds
            background = ImageIO.read(getClass().getResource("assets/Panels/backgroundImage.png"));
            loadingScreen = ImageIO.read(getClass().getResource("assets/Panels/loadingScreen.png"));
            loseScreen = ImageIO.read(getClass().getResource("assets/Panels/loseScreen.png"));


            // buttons
            exitBtn = ImageIO.read(getClass().getResource("assets/Panels/exitBtn.png"));
            optionBtn = ImageIO.read(getClass().getResource("assets/Panels/creditsBtn.png"));
            startBtn = ImageIO.read(getClass().getResource("assets/Panels/startBtn.png"));
            backBtn = ImageIO.read(getClass().getResource("assets/Panels/backBtn.png"));
            placeholderBtn = ImageIO.read(getClass().getResource("assets/Panels/placeholderBtn.png"));
            
            // hover buttons
            exitBtnHover = ImageIO.read(getClass().getResource("assets/Panels/exitBtnHover.png"));
            optionBtnHover = ImageIO.read(getClass().getResource("assets/Panels/creditsBtnHover.png"));
            startBtnHover = ImageIO.read(getClass().getResource("assets/Panels/startBtnHover.png"));
            backBtnHover = ImageIO.read(getClass().getResource("assets/Panels/backBtnHover.png"));
            placeholderBtnHover = ImageIO.read(getClass().getResource("assets/Panels/placeholderBtnHover.png"));
            
            // Maps
            rawMap = ImageIO.read(getClass().getResource("assets/MainAssets/mapTest.png"));
            map = new BufferedImage(32 * 80, 16 * 80, BufferedImage.TYPE_INT_ARGB);
            g2 = map.createGraphics();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(rawMap, 0, 0, 32 * 80, 16 * 80, null);
            g2.dispose();
            
            // cards
            Empress = ImageIO.read(getClass().getResource("assets/Cards/Empress.png"));
            KnightOfWands = ImageIO.read(getClass().getResource("assets/Cards/KnightOfWands.png"));
            Magician = ImageIO.read(getClass().getResource("assets/Cards/Magician.png"));
            NineOfPentacles = ImageIO.read(getClass().getResource("assets/Cards/NineOfPentacles.png"));
            QueenOfCups = ImageIO.read(getClass().getResource("assets/Cards/QueenOfCups.png"));
            TenOfSwords = ImageIO.read(getClass().getResource("assets/Cards/TenOfSwords.png"));
            TwoOfCups = ImageIO.read(getClass().getResource("assets/Cards/TwoOfCups.png"));
            AceOfWands = ImageIO.read(getClass().getResource("assets/Cards/AceOfWands.png"));
            Death = ImageIO.read(getClass().getResource("assets/Cards/Death.png"));
            
            // Objects
            treasureChest = ImageIO.read(getClass().getResource("assets/MainAssets/treasureChest.png"));
            treasureChestH = ImageIO.read(getClass().getResource("assets/MainAssets/treasureChestHighlighted.png"));
            rawProjectile = ImageIO.read(getClass().getResource("assets/objects/projectile.png"));
            projectile = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            g2 = projectile.createGraphics();
            g2.drawImage(rawProjectile, 0, 0, 32, 32, null);
            g2.dispose();


            // Enemy GIFs animation preserved
            blueLEFT =      new ImageIcon(getClass().getResource("assets/objects/enemyAnim/blueLeft.gif")).getImage();
            blueRIGHT =     new ImageIcon(getClass().getResource("assets/objects/enemyAnim/blueRight.gif")).getImage();
        

        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to load images: " + e.getMessage(), e);
        }
    }
}