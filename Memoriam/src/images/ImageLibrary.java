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
    public final BufferedImage mainMenuBtn;
    public final BufferedImage quitBtn;
    public final BufferedImage resumeBtn ;
    public final BufferedImage restartBtn;

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

// Enemy Purple
    public final Image purpleRIGHT;
    public final Image purpleLEFT;

    public final Image yellowRIGHT;
    public final Image yellowLEFT;
    
    public final Image finalBoss;

    // Cursor
    public final Image quillCursor;
    public final Image swordCursor;
    
    // for scaling
    private Graphics2D g2;
    public final BufferedImage rawMap;
    public final BufferedImage rawProjectile;


    // map
    public final BufferedImage map;
    public final Image map0;
    public final Image map1;
    public final Image map2;
    public final Image map3;
    public final Image map4;
    public final Image map5;

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

    // card hover
    public final BufferedImage EmpressHover;
    public final BufferedImage KnightOfWandsHover;
    public final BufferedImage MagicianHover;
    public final BufferedImage NineOfPentaclesHover;
    public final BufferedImage QueenOfCupsHover;
    public final BufferedImage TenOfSwordsHover;
    public final BufferedImage TwoOfCupsHover;
    public final BufferedImage AceOfWandsHover;
    public final BufferedImage DeathHover;

    // Objects
    public final BufferedImage treasureChest;
    public final BufferedImage treasureChestH;
    public final Image projectile;
    public final BufferedImage fireProjectile;

    //Skill Icons
    public final BufferedImage iconAceOfWands;
    public final BufferedImage iconDeath;
    public final BufferedImage iconKnightOfWands;
    public final BufferedImage iconNineOfPentacles;
    public final BufferedImage iconQueenOfCups;
    public final BufferedImage iconTheEmpress;
    public final BufferedImage iconTheMagician;
    public final BufferedImage iconTwoOfCups;
    public final BufferedImage iconTenOfSwords;
    

    // Player UI
    public final BufferedImage heart;
    public final BufferedImage deadHeart;




private ImageLibrary()
{
        try {
                projectile = new ImageIcon(getClass().getResource(
                "assets/objects/projectile.png"
                )).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);

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
                mainMenuBtn = ImageIO.read(getClass().getResource("assets/Panels/mainMenu.png"));
                quitBtn = ImageIO.read(getClass().getResource("assets/Panels/quitBtn.png"));
                resumeBtn = ImageIO.read(getClass().getResource("assets/Panels/resumeBtn.png"));
                restartBtn = ImageIO.read(getClass().getResource("assets/Panels/restart.png"));
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
                g2.drawImage(rawMap, 0, 0, 32 * 150, 16 * 150, null);
                g2.dispose();

                map0 =  new ImageIcon(getClass().getResource(
                        "assets/maps/lobby.png")).getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);            
                
                map1 =  new ImageIcon(getClass().getResource(
                        "assets/maps/map1.png")).getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map2 =  new ImageIcon(getClass().getResource(
                        "assets/maps/map2.png")).getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map3 =  new ImageIcon(getClass().getResource(
                        "assets/maps/map3.png")).getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map4 =  new ImageIcon(getClass().getResource(
                        "assets/maps/map4.png")).getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map5 =  new ImageIcon(getClass().getResource(
                        "assets/maps/boss map.png")).getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);



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

                // card hovers
                EmpressHover = ImageIO.read(getClass().getResource("assets/Cards/EmpressHover.png"));
                KnightOfWandsHover = ImageIO.read(getClass().getResource("assets/Cards/KnightOfWandsHover.png"));
                MagicianHover = ImageIO.read(getClass().getResource("assets/Cards/MagicianHover.png"));
                NineOfPentaclesHover = ImageIO.read(getClass().getResource("assets/Cards/NineOfPentaclesHover.png"));
                QueenOfCupsHover = ImageIO.read(getClass().getResource("assets/Cards/QueenOfCupsHover.png"));
                TenOfSwordsHover = ImageIO.read(getClass().getResource("assets/Cards/TenOfSwordsHover.png"));
                TwoOfCupsHover = ImageIO.read(getClass().getResource("assets/Cards/TwoOfCupsHover.png"));
                AceOfWandsHover = ImageIO.read(getClass().getResource("assets/Cards/AceOfWandsHover.png"));
                DeathHover = ImageIO.read(getClass().getResource("assets/Cards/DeathHover.png"));

                //card icons

                iconTheEmpress = ImageIO.read(getClass().getResource("assets/Powerups/The_Empress.png"));
                iconKnightOfWands = ImageIO.read(getClass().getResource("assets/Powerups/Knight_Of_Wands.png"));
                iconTheMagician =  ImageIO.read(getClass().getResource("assets/Powerups/The_Magician.png"));
                iconNineOfPentacles = ImageIO.read(getClass().getResource("assets/Powerups/Nine_of_Pentacles.png"));
                iconQueenOfCups = ImageIO.read(getClass().getResource("assets/Powerups/Queen_of_Cups.png"));
                iconTenOfSwords =  ImageIO.read(getClass().getResource("assets/Powerups/Ten_Of_Swords.png"));
                iconTwoOfCups= ImageIO.read(getClass().getResource("assets/Powerups/Two_Of_Cups.png"));
                iconAceOfWands = ImageIO.read(getClass().getResource("assets/Powerups/Ace_of_Wands.png"));
                iconDeath = ImageIO.read(getClass().getResource("assets/Powerups/Death.png"));
                
                
                // Objects
                treasureChest = ImageIO.read(getClass().getResource("assets/MainAssets/treasureChest.png"));
                treasureChestH = ImageIO.read(getClass().getResource("assets/MainAssets/treasureChestHighlighted.png"));
                rawProjectile = ImageIO.read(getClass().getResource("assets/objects/projectile.png"));
                fireProjectile = ImageIO.read(getClass().getResource("assets/objects/fireProjectile.png"));

                

                // Enemy GIFs animation preserved
                blueLEFT =      new ImageIcon(getClass().getResource("assets/objects/enemyAnim/blueLeft.gif")).getImage();
                blueRIGHT =     new ImageIcon(getClass().getResource("assets/objects/enemyAnim/blueRight.gif")).getImage();
                
                purpleLEFT =      new ImageIcon(getClass().getResource("assets/objects/enemyAnim/purpleLeft.gif")).getImage();
                purpleRIGHT=     new ImageIcon(getClass().getResource("assets/objects/enemyAnim/purpleRight.gif")).getImage();
        
                yellowLEFT  =      new ImageIcon(getClass().getResource("assets/objects/enemyAnim/yellowLeft.gif")).getImage();
                yellowRIGHT =     new ImageIcon(getClass().getResource("assets/objects/enemyAnim/yellowRight.gif")).getImage();
        
                finalBoss =     new ImageIcon(getClass().getResource("assets/objects/enemyAnim/finalBoss.png"
                        )).getImage().getScaledInstance(32 * 4, 40 * 4, Image.SCALE_SMOOTH);
                


                // Player UI
                heart = ImageIO.read(getClass().getResource("assets/MainAssets/heart.png"));
                deadHeart = ImageIO.read(getClass().getResource("assets/MainAssets/heartDead.png"));

                // Maps
        
                        
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to load images: " + e.getMessage(), e);
        }
    }
}