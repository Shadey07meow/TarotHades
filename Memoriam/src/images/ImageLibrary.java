package images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
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
    public final BufferedImage practiceBtn;
    public final BufferedImage startBtn;
    public final BufferedImage creditBtn;
    public final BufferedImage backBtn;
    public final BufferedImage placeholderBtn;
    public final BufferedImage quitBtnExit;
    public final BufferedImage loadSaveBtn;
    public final BufferedImage loadSaveBtnHover;
    public final BufferedImage newSaveButton;
    public final BufferedImage tutorialBtn;
    public final BufferedImage newSaveButtonHover;

    public final BufferedImage loadingScreen;
    public final BufferedImage loseScreen;
    public final BufferedImage background;
    public final BufferedImage creditBackground;
    public final BufferedImage mainMenuBtn;
    public final BufferedImage quitBtn;
    public final BufferedImage quitBtnGO;
    public final BufferedImage resumeBtn ;
    public final BufferedImage resumeBtnHover;
    public final BufferedImage restartBtn;
    public final BufferedImage boboLogo;
    public final BufferedImage logo;

    public final BufferedImage pauseBg;
    public final BufferedImage loadBg;

    // ui - hover
    public final BufferedImage backBtnHover;
    public final BufferedImage startBtnHover;
    public final BufferedImage practiceBtnHover;
    public final BufferedImage exitBtnHover;
    public final BufferedImage placeholderBtnHover;
    public final BufferedImage creditBtnHover;
    public final BufferedImage tutorialBtnHover;

    // Player (GIFs must stay Image for animation)
    public final Image playerHurtUP;
    public final Image playerHurtDOWN;
    public final Image playerHurtLEFT;
    public final Image playerHurtRIGHT;

    public final Image playerSpritesUP;
    public final Image playerSpritesDOWN;
    public final Image playerSpritesLEFT;
    public final Image playerSpritesRIGHT;

    public final Image foolAtkDown;
    public final Image foolAtkRight;
    public final Image foolAtkLeft;
    public final Image foolAtkUp;

    //player shielded

    public final Image foolShieldDown;
    public final Image foolShieldRight;
    public final Image foolShieldLeft;
    public final Image foolShieldUp;
    
    // Enemy Blue
    public final Image blueRIGHT;
    public final Image blueLEFT;
    
    // Enemy Purple
    public final Image purpleRIGHT;
    public final Image purpleLEFT;

    public final Image yellowRIGHT;
    public final Image yellowLEFT;
    
    public final Image finalBoss;

    //enemy hurt
    public final Image enemyHurt;
    public final Image finalBossHurt;

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
    public final Image infiniteMap;
    public final Image tutorialMap;

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
    public final BufferedImage lockedTreasureChest;
    public final BufferedImage treasureChestH;
    public final Image projectile;
    public final BufferedImage fireProjectile;

    //Skill Icons
    public final BufferedImage EmpressHover2;
    
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
    public final Image splash;
    public final BufferedImage placeHolderIcon;
    public final BufferedImage calryaIcon;
    public final BufferedImage shadeyIcon;
    public final BufferedImage herielleIcon;
    public final BufferedImage samIcon;
    




private ImageLibrary()
{
        try {
                projectile = new ImageIcon(
                "assets/objects/projectile.png"
                ).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);

                // Player Sprites (GIF animation preserved)
                playerSpritesDOWN = new ImageIcon(
                        "assets/PlayerSprites/animated/idle/fool_idle_front.gif"
                ).getImage();

                playerSpritesLEFT = new ImageIcon(
                        "assets/PlayerSprites/animated/idle/fool_idle_left.gif"
                ).getImage();

                playerSpritesUP = new ImageIcon("assets/PlayerSprites/animated/idle/fool_idle_back.gif"
                ).getImage();

                playerSpritesRIGHT = new ImageIcon("assets/PlayerSprites/animated/idle/fool_idle_right.gif").getImage();

                foolShieldDown = new ImageIcon("assets/PlayerSprites/shield/foolShieldDown.png").getImage();
                foolShieldRight = new ImageIcon("assets/PlayerSprites/shield/foolShieldRight.png").getImage();
                foolShieldLeft = new ImageIcon("assets/PlayerSprites/shield/foolShieldLeft.png").getImage();
                foolShieldUp = new ImageIcon("assets/PlayerSprites/shield/foolShieldUp.png").getImage();

                foolAtkDown = new ImageIcon("assets/PlayerSprites/atk/foolDownAtk.PNG").getImage();
                foolAtkRight = new ImageIcon("assets/PlayerSprites/atk/foolRightAtk.PNG").getImage();
                foolAtkLeft = new ImageIcon("assets/PlayerSprites/atk/foolLeftAtk.PNG").getImage();
                foolAtkUp = new ImageIcon("assets/PlayerSprites/atk/foolUpAtk.PNG").getImage();


                // Cursor
                quillCursor = ImageIO.read(getImageFile("assets/MainAssets/cursor.png")
                ).getScaledInstance(64, 64, Image.SCALE_SMOOTH);

                swordCursor = ImageIO.read(getImageFile(
                        "assets/MainAssets/swordCursor.png"
                )).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                
                // Backgrounds
                background = ImageIO.read(getImageFile("assets/Panels/menuScreenBG.png"));
                creditBackground = ImageIO.read(getImageFile("assets/Panels/creditBackground.png"));
                loadingScreen = ImageIO.read(getImageFile("assets/Panels/loadingScreen.png"));
                loseScreen = ImageIO.read(getImageFile("assets/Panels/loseScreen.png"));
                boboLogo = ImageIO.read(getImageFile("assets/Panels/boboFront.png"));
                logo = ImageIO.read(getImageFile("assets/Panels/logo.png"));
                
                // player hurt
                playerHurtUP = ImageIO.read(getImageFile("assets/PlayerSprites/hurt/foolUpHurt.png"));
                playerHurtDOWN = ImageIO.read(getImageFile("assets/PlayerSprites/hurt/foolDownHurt.png"));
                playerHurtLEFT = ImageIO.read(getImageFile("assets/PlayerSprites/hurt/foolLeftHurt.png"));
                playerHurtRIGHT = ImageIO.read(getImageFile("assets/PlayerSprites/hurt/foolRightHurt.png"));

                // buttons
                exitBtn = ImageIO.read(getImageFile("assets/Panels/exitBtn.png"));
                practiceBtn = ImageIO.read(getImageFile("assets/Panels/practiceBtn.png"));
                startBtn = ImageIO.read(getImageFile("assets/Panels/startBtn.png"));
                backBtn = ImageIO.read(getImageFile("assets/Panels/backBtn.png"));
                mainMenuBtn = ImageIO.read(getImageFile("assets/Panels/mainMenu.png"));
                quitBtn = ImageIO.read(getImageFile("assets/Panels/quitBtn.png"));
                quitBtnGO = ImageIO.read(getImageFile("assets/Panels/quitBtnGO.png"));
                resumeBtn = ImageIO.read(getImageFile("assets/Panels/resumeBtn.png"));
                resumeBtnHover = ImageIO.read(getImageFile("assets/Panels/resumeButtonHover.png"));
                restartBtn = ImageIO.read(getImageFile("assets/Panels/restart.png"));
                placeholderBtn = ImageIO.read(getImageFile("assets/Panels/placeholderBtn.png"));
                
                quitBtnExit = ImageIO.read(getImageFile("assets/Panels/quitBtnExit.png"));
                creditBtn = ImageIO.read(getImageFile("assets/Panels/creditBtn.png"));
                tutorialBtn = ImageIO.read(getImageFile("assets/Panels/tutorialBtn.png"));

                pauseBg = ImageIO.read(getImageFile("assets/Panels/pauseBg.png"));
                loadSaveBtn = ImageIO.read(getImageFile("assets/Panels/loadSave.png"));
                loadSaveBtnHover = ImageIO.read(getImageFile("assets/Panels/loadSaveHover.png"));
                newSaveButton = ImageIO.read(getImageFile("assets/Panels/newSaveButton.png"));
                newSaveButtonHover = ImageIO.read(getImageFile("assets/Panels/newSaveButtonHover.png"));
                loadBg = ImageIO.read(getImageFile("assets/Panels/backDrop2.png"));
                
                
                // hover buttons
                exitBtnHover = ImageIO.read(getImageFile("assets/Panels/exitBtnHover.png"));
                practiceBtnHover = ImageIO.read(getImageFile("assets/Panels/practiceBtnHover.png"));
                startBtnHover = ImageIO.read(getImageFile("assets/Panels/startBtnHover.png"));
                backBtnHover = ImageIO.read(getImageFile("assets/Panels/backBtnHover.png"));
                placeholderBtnHover = ImageIO.read(getImageFile("assets/Panels/placeholderBtnHover.png"));
                creditBtnHover = ImageIO.read(getImageFile("assets/Panels/creditBtnHover.png"));
                tutorialBtnHover = ImageIO.read(getImageFile("assets/Panels/tutorialBtnHover.png"));
                
                // Maps
                rawMap = ImageIO.read(getImageFile("assets/MainAssets/mapTest.png"));
                map = new BufferedImage(32 * 80, 16 * 80, BufferedImage.TYPE_INT_ARGB);
                g2 = map.createGraphics();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(rawMap, 0, 0, 32 * 150, 16 * 150, null);
                g2.dispose();

                map0 =  new ImageIcon(
                        "assets/maps/lobby.png").getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);            
                
                map1 =  new ImageIcon(
                        "assets/maps/map1.png").getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map2 =  new ImageIcon(
                        "assets/maps/map2.png").getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map3 =  new ImageIcon(
                        "assets/maps/map3.png").getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map4 =  new ImageIcon(
                        "assets/maps/map4.png").getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                map5 =  new ImageIcon(
                        "assets/maps/boss map.png").getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                infiniteMap =  new ImageIcon(
                        "assets/maps/infiniteMap.png").getImage().getScaledInstance(1920, 4320, Image.SCALE_SMOOTH);

                tutorialMap =  new ImageIcon(
                        "assets/maps/tutorialMap.png").getImage(); 



                // cards
                Empress = ImageIO.read(getImageFile("assets/Cards/Empress.png"));
                KnightOfWands = ImageIO.read(getImageFile("assets/Cards/KnightOfWands.png"));
                Magician = ImageIO.read(getImageFile("assets/Cards/Magician.png"));
                NineOfPentacles = ImageIO.read(getImageFile("assets/Cards/NineOfPentacles.png"));
                QueenOfCups = ImageIO.read(getImageFile("assets/Cards/QueenOfCups.png"));
                TenOfSwords = ImageIO.read(getImageFile("assets/Cards/TenOfSwords.png"));
                TwoOfCups = ImageIO.read(getImageFile("assets/Cards/TwoOfCups.png"));
                AceOfWands = ImageIO.read(getImageFile("assets/Cards/AceOfWands.png"));
                Death = ImageIO.read(getImageFile("assets/Cards/Death.png"));

                // card hovers
                EmpressHover = ImageIO.read(getImageFile("assets/Cards/EmpressHover.png"));
                KnightOfWandsHover = ImageIO.read(getImageFile("assets/Cards/KnightOfWandsHover.png"));
                MagicianHover = ImageIO.read(getImageFile("assets/Cards/MagicianHover.png"));
                NineOfPentaclesHover = ImageIO.read(getImageFile("assets/Cards/NineOfPentaclesHover.png"));
                QueenOfCupsHover = ImageIO.read(getImageFile("assets/Cards/QueenOfCupsHover.png"));
                TenOfSwordsHover = ImageIO.read(getImageFile("assets/Cards/TenOfSwordsHover.png"));
                TwoOfCupsHover = ImageIO.read(getImageFile("assets/Cards/TwoOfCupsHover.png"));
                AceOfWandsHover = ImageIO.read(getImageFile("assets/Cards/AceOfWandsHover.png"));
                DeathHover = ImageIO.read(getImageFile("assets/Cards/DeathHover.png"));

                //card icons

                iconTheEmpress = ImageIO.read(getImageFile("assets/Powerups/TheEmpress.png"));
                iconKnightOfWands = ImageIO.read(getImageFile("assets/Powerups/KnightOfWands.png"));
                iconTheMagician =  ImageIO.read(getImageFile("assets/Powerups/TheMagician.png"));
                iconNineOfPentacles = ImageIO.read(getImageFile("assets/Powerups/NineofPentacles.png"));
                iconQueenOfCups = ImageIO.read(getImageFile("assets/Powerups/QueenofCups.png"));
                iconTenOfSwords =  ImageIO.read(getImageFile("assets/Powerups/TenOfSwords.png"));
                iconTwoOfCups= ImageIO.read(getImageFile("assets/Powerups/TwoOfCups.png"));
                iconAceOfWands = ImageIO.read(getImageFile("assets/Powerups/AceofWands.png"));
                iconDeath = ImageIO.read(getImageFile("assets/Powerups/Death.png"));

                EmpressHover2 = ImageIO.read(getImageFile("assets/Cards/EmpressHover.png"));
                
    
                
                // Objects
                treasureChest = ImageIO.read(getImageFile("assets/MainAssets/treasureChest.png"));
                lockedTreasureChest = ImageIO.read(getImageFile("assets/MainAssets/lockedChest.png"));
                treasureChestH = ImageIO.read(getImageFile("assets/MainAssets/treasureChestHighlighted.png"));
                rawProjectile = ImageIO.read(getImageFile("assets/objects/projectile.png"));
                fireProjectile = ImageIO.read(getImageFile("assets/objects/fireProjectile.png"));

                

                // Enemy GIFs animation preserved
                blueLEFT =      new ImageIcon("assets/objects/enemyAnim/blueLeft.gif").getImage();
                blueRIGHT =     new ImageIcon("assets/objects/enemyAnim/blueRight.gif").getImage();
                
                purpleLEFT =      new ImageIcon("assets/objects/enemyAnim/purpleLeft.gif").getImage();
                purpleRIGHT=     new ImageIcon("assets/objects/enemyAnim/purpleRight.gif").getImage();
        
                yellowLEFT  =      new ImageIcon("assets/objects/enemyAnim/yellowLeft.gif").getImage();
                yellowRIGHT =     new ImageIcon("assets/objects/enemyAnim/yellowRight.gif").getImage();
        
                finalBoss =     new ImageIcon("assets/objects/enemyAnim/finalBoss.png"
                        ).getImage().getScaledInstance(32 * 4, 40 * 4, Image.SCALE_SMOOTH);
                enemyHurt = ImageIO.read(getImageFile("assets/objects/enemyHurt.png"));
                finalBossHurt = ImageIO.read(getImageFile("assets/objects/finalBossHurt.png")).getScaledInstance(32 * 4, 40 * 4, Image.SCALE_SMOOTH);



                // Player UI
                heart = ImageIO.read(getImageFile("assets/MainAssets/heart.png"));
                deadHeart = ImageIO.read(getImageFile("assets/MainAssets/heartDead.png"));

                splash = new ImageIcon(
                        "assets/MainAssets/splash.gif").getImage();

                placeHolderIcon = ImageIO.read(getImageFile("assets/MainAssets/placeholderIcon.png"));
                calryaIcon = ImageIO.read(getImageFile("assets/MainAssets/calryaIcon.png"));
                shadeyIcon = ImageIO.read(getImageFile("assets/MainAssets/shadey07Icon.png"));
                herielleIcon = ImageIO.read(getImageFile("assets/MainAssets/herielleIcon.png"));
                samIcon = ImageIO.read(getImageFile("assets/MainAssets/samIcon.png"));

                

                // Maps
        
                        
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to load images: " + e.getMessage(), e);
        }
    }

    private File getImageFile(String directory)
    {
        return new File(directory);
    }
}