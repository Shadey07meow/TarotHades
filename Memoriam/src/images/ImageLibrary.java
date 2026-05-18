package images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLibrary {

    private static ImageLibrary instance;

    public static ImageLibrary get() {
        if (instance == null) {
            instance = new ImageLibrary();
        }
        return instance;
    }

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
    public final BufferedImage resumeBtn;
    public final BufferedImage resumeBtnHover;
    public final BufferedImage restartBtn;
    public final BufferedImage boboLogo;
    public final BufferedImage logo;

    public final BufferedImage pauseBg;
    public final BufferedImage loadBg;

    public final BufferedImage backBtnHover;
    public final BufferedImage startBtnHover;
    public final BufferedImage practiceBtnHover;
    public final BufferedImage exitBtnHover;
    public final BufferedImage placeholderBtnHover;
    public final BufferedImage creditBtnHover;
    public final BufferedImage tutorialBtnHover;

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

    public final Image foolShieldDown;
    public final Image foolShieldRight;
    public final Image foolShieldLeft;
    public final Image foolShieldUp;

    public final Image blueRIGHT;
    public final Image blueLEFT;

    public final Image purpleRIGHT;
    public final Image purpleLEFT;

    public final Image yellowRIGHT;
    public final Image yellowLEFT;

    public final Image finalBoss;

    public final Image enemyHurt;
    public final Image finalBossHurt;

    public final Image quillCursor;
    public final Image swordCursor;

    private Graphics2D g2;

    public final BufferedImage rawMap;
    public final BufferedImage rawProjectile;

    public final BufferedImage map;
    public final Image map0;
    public final Image map1;
    public final Image map2;
    public final Image map3;
    public final Image map4;
    public final Image map5;
    public final Image infiniteMap;
    public final Image tutorialMap;

    public final BufferedImage Empress;
    public final BufferedImage KnightOfWands;
    public final BufferedImage Magician;
    public final BufferedImage NineOfPentacles;
    public final BufferedImage QueenOfCups;
    public final BufferedImage TenOfSwords;
    public final BufferedImage TwoOfCups;
    public final BufferedImage AceOfWands;
    public final BufferedImage Death;

    public final BufferedImage EmpressHover;
    public final BufferedImage KnightOfWandsHover;
    public final BufferedImage MagicianHover;
    public final BufferedImage NineOfPentaclesHover;
    public final BufferedImage QueenOfCupsHover;
    public final BufferedImage TenOfSwordsHover;
    public final BufferedImage TwoOfCupsHover;
    public final BufferedImage AceOfWandsHover;
    public final BufferedImage DeathHover;

    public final BufferedImage treasureChest;
    public final BufferedImage lockedTreasureChest;
    public final BufferedImage treasureChestH;
    public final Image projectile;
    public final BufferedImage fireProjectile;
    public final Image enemyProjectile;
    

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

    public final BufferedImage heart;
    public final BufferedImage deadHeart;
    public final Image splash;
    public final BufferedImage placeHolderIcon;
    public final BufferedImage calryaIcon;
    public final BufferedImage shadeyIcon;
    public final BufferedImage herielleIcon;
    public final BufferedImage samIcon;

    private ImageLibrary() {

        try {

            projectile = loadScaledImage(
                    "/assets/objects/projectile.png",
                    32,
                    32
            );

            enemyProjectile = loadScaledImage(
                    "/assets/objects/enemyProjectile.png",
                    32,
                    32
            );

            playerSpritesDOWN = loadImage(
                    "/assets/PlayerSprites/animated/idle/fool_idle_front.gif"
            );

            playerSpritesLEFT = loadImage(
                    "/assets/PlayerSprites/animated/idle/fool_idle_left.gif"
            );

            playerSpritesUP = loadImage(
                    "/assets/PlayerSprites/animated/idle/fool_idle_back.gif"
            );

            playerSpritesRIGHT = loadImage(
                    "/assets/PlayerSprites/animated/idle/fool_idle_right.gif"
            );

            foolShieldDown = loadImage(
                    "/assets/PlayerSprites/shield/foolShieldDown.png"
            );

            foolShieldRight = loadImage(
                    "/assets/PlayerSprites/shield/foolShieldRight.png"
            );

            foolShieldLeft = loadImage(
                    "/assets/PlayerSprites/shield/foolShieldLeft.png"
            );

            foolShieldUp = loadImage(
                    "/assets/PlayerSprites/shield/foolShieldUp.png"
            );

            foolAtkDown = loadImage(
                    "/assets/PlayerSprites/atk/foolDownAtk.PNG"
            );

            foolAtkRight = loadImage(
                    "/assets/PlayerSprites/atk/foolRightAtk.PNG"
            );

            foolAtkLeft = loadImage(
                    "/assets/PlayerSprites/atk/foolLeftAtk.PNG"
            );

            foolAtkUp = loadImage(
                    "/assets/PlayerSprites/atk/foolUpAtk.PNG"
            );

            quillCursor = loadScaledImage(
                    "/assets/MainAssets/cursor.png",
                    64,
                    64
            );

            swordCursor = loadScaledImage(
                    "/assets/MainAssets/swordCursor.png",
                    64,
                    64
            );

            background = loadBuffered("/assets/Panels/menuScreenBG.png");
            creditBackground = loadBuffered("/assets/Panels/creditBackground.png");
            loadingScreen = loadBuffered("/assets/Panels/loadingScreen.png");
            loseScreen = loadBuffered("/assets/Panels/loseScreen.png");
            boboLogo = loadBuffered("/assets/Panels/boboFront.png");
            logo = loadBuffered("/assets/Panels/logo.png");

            playerHurtUP = loadBuffered("/assets/PlayerSprites/hurt/foolUpHurt.png");
            playerHurtDOWN = loadBuffered("/assets/PlayerSprites/hurt/foolDownHurt.png");
            playerHurtLEFT = loadBuffered("/assets/PlayerSprites/hurt/foolLeftHurt.png");
            playerHurtRIGHT = loadBuffered("/assets/PlayerSprites/hurt/foolRightHurt.png");

            exitBtn = loadBuffered("/assets/Panels/exitBtn.png");
            practiceBtn = loadBuffered("/assets/Panels/practiceBtn.png");
            startBtn = loadBuffered("/assets/Panels/startBtn.png");
            backBtn = loadBuffered("/assets/Panels/backBtn.png");
            mainMenuBtn = loadBuffered("/assets/Panels/mainMenu.png");
            quitBtn = loadBuffered("/assets/Panels/quitBtn.png");
            quitBtnGO = loadBuffered("/assets/Panels/quitBtnGO.png");
            resumeBtn = loadBuffered("/assets/Panels/resumeBtn.png");
            resumeBtnHover = loadBuffered("/assets/Panels/resumeButtonHover.png");
            restartBtn = loadBuffered("/assets/Panels/restart.png");
            placeholderBtn = loadBuffered("/assets/Panels/placeholderBtn.png");
            quitBtnExit = loadBuffered("/assets/Panels/quitBtnExit.png");
            creditBtn = loadBuffered("/assets/Panels/creditBtn.png");
            tutorialBtn = loadBuffered("/assets/Panels/tutorialBtn.png");

            pauseBg = loadBuffered("/assets/Panels/pauseBg.png");
            loadSaveBtn = loadBuffered("/assets/Panels/loadSave.png");
            loadSaveBtnHover = loadBuffered("/assets/Panels/loadSaveHover.png");
            newSaveButton = loadBuffered("/assets/Panels/newSaveButton.png");
            newSaveButtonHover = loadBuffered("/assets/Panels/newSaveButtonHover.png");
            loadBg = loadBuffered("/assets/Panels/backDrop2.png");

            exitBtnHover = loadBuffered("/assets/Panels/exitBtnHover.png");
            practiceBtnHover = loadBuffered("/assets/Panels/practiceBtnHover.png");
            startBtnHover = loadBuffered("/assets/Panels/startBtnHover.png");
            backBtnHover = loadBuffered("/assets/Panels/backBtnHover.png");
            placeholderBtnHover = loadBuffered("/assets/Panels/placeholderBtnHover.png");
            creditBtnHover = loadBuffered("/assets/Panels/creditBtnHover.png");
            tutorialBtnHover = loadBuffered("/assets/Panels/tutorialBtnHover.png");

            rawMap = loadBuffered("/assets/MainAssets/mapTest.png");

            map = new BufferedImage(
                    32 * 80,
                    16 * 80,
                    BufferedImage.TYPE_INT_ARGB
            );

            g2 = map.createGraphics();

            g2.setRenderingHint(
                    java.awt.RenderingHints.KEY_INTERPOLATION,
                    java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR
            );

            g2.drawImage(
                    rawMap,
                    0,
                    0,
                    32 * 150,
                    16 * 150,
                    null
            );

            g2.dispose();

            map0 = loadScaledImage(
                    "/assets/maps/lobby.png",
                    1920,
                    1080
            );

            map1 = loadScaledImage(
                    "/assets/maps/map1.png",
                    1920,
                    4320
            );

            map2 = loadScaledImage(
                    "/assets/maps/map2.png",
                    1920,
                    4320
            );

            map3 = loadScaledImage(
                    "/assets/maps/map3.png",
                    1920,
                    4320
            );

            map4 = loadScaledImage(
                    "/assets/maps/map4.png",
                    1920,
                    4320
            );

            map5 = loadScaledImage(
                    "/assets/maps/boss map.png",
                    1920,
                    4320
            );

            infiniteMap = loadScaledImage(
                    "/assets/maps/infiniteMap.png",
                    1920,
                    4320
            );

            tutorialMap = loadImage(
                    "/assets/maps/tutorialMap.png"
            );

            Empress = loadBuffered("/assets/Cards/Empress.png");
            KnightOfWands = loadBuffered("/assets/Cards/KnightOfWands.png");
            Magician = loadBuffered("/assets/Cards/Magician.png");
            NineOfPentacles = loadBuffered("/assets/Cards/NineOfPentacles.png");
            QueenOfCups = loadBuffered("/assets/Cards/QueenOfCups.png");
            TenOfSwords = loadBuffered("/assets/Cards/TenOfSwords.png");
            TwoOfCups = loadBuffered("/assets/Cards/TwoOfCups.png");
            AceOfWands = loadBuffered("/assets/Cards/AceOfWands.png");
            Death = loadBuffered("/assets/Cards/Death.png");

            EmpressHover = loadBuffered("/assets/Cards/EmpressHover.png");
            KnightOfWandsHover = loadBuffered("/assets/Cards/KnightOfWandsHover.png");
            MagicianHover = loadBuffered("/assets/Cards/MagicianHover.png");
            NineOfPentaclesHover = loadBuffered("/assets/Cards/NineOfPentaclesHover.png");
            QueenOfCupsHover = loadBuffered("/assets/Cards/QueenOfCupsHover.png");
            TenOfSwordsHover = loadBuffered("/assets/Cards/TenOfSwordsHover.png");
            TwoOfCupsHover = loadBuffered("/assets/Cards/TwoOfCupsHover.png");
            AceOfWandsHover = loadBuffered("/assets/Cards/AceOfWandsHover.png");
            DeathHover = loadBuffered("/assets/Cards/DeathHover.png");

            iconTheEmpress = loadBuffered("/assets/Powerups/TheEmpress.png");
            iconKnightOfWands = loadBuffered("/assets/Powerups/KnightOfWands.png");
            iconTheMagician = loadBuffered("/assets/Powerups/TheMagician.png");
            iconNineOfPentacles = loadBuffered("/assets/Powerups/NineofPentacles.png");
            iconQueenOfCups = loadBuffered("/assets/Powerups/QueenofCups.png");
            iconTenOfSwords = loadBuffered("/assets/Powerups/TenOfSwords.png");
            iconTwoOfCups = loadBuffered("/assets/Powerups/TwoOfCups.png");
            iconAceOfWands = loadBuffered("/assets/Powerups/AceofWands.png");
            iconDeath = loadBuffered("/assets/Powerups/Death.png");

            EmpressHover2 = loadBuffered("/assets/Cards/EmpressHover.png");

            treasureChest = loadBuffered("/assets/MainAssets/treasureChest.png");
            lockedTreasureChest = loadBuffered("/assets/MainAssets/lockedChest.png");
            treasureChestH = loadBuffered("/assets/MainAssets/treasureChestHighlighted.png");

            rawProjectile = loadBuffered("/assets/objects/projectile.png");
            fireProjectile = loadBuffered("/assets/objects/fireProjectile.png");

            blueLEFT = loadImage(
                    "/assets/objects/enemyAnim/blueLeft.gif"
            );

            blueRIGHT = loadImage(
                    "/assets/objects/enemyAnim/blueRight.gif"
            );

            purpleLEFT = loadImage(
                    "/assets/objects/enemyAnim/purpleLeft.gif"
            );

            purpleRIGHT = loadImage(
                    "/assets/objects/enemyAnim/purpleRight.gif"
            );

            yellowLEFT = loadImage(
                    "/assets/objects/enemyAnim/yellowLeft.gif"
            );

            yellowRIGHT = loadImage(
                    "/assets/objects/enemyAnim/yellowRight.gif"
            );

            finalBoss = loadScaledImage(
                    "/assets/objects/enemyAnim/finalBoss.png",
                    32 * 4,
                    40 * 4
            );

            enemyHurt = loadBuffered(
                    "/assets/objects/enemyHurt.png"
            );

            finalBossHurt = loadScaledImage(
                    "/assets/objects/finalBossHurt.png",
                    32 * 4,
                    40 * 4
            );

            heart = loadBuffered("/assets/MainAssets/heart.png");
            deadHeart = loadBuffered("/assets/MainAssets/heartDead.png");

            splash = loadImage(
                    "/assets/MainAssets/splash.gif"
            );

            placeHolderIcon = loadBuffered("/assets/MainAssets/placeholderIcon.png");
            calryaIcon = loadBuffered("/assets/MainAssets/calryaIcon.png");
            shadeyIcon = loadBuffered("/assets/MainAssets/shadey07Icon.png");
            herielleIcon = loadBuffered("/assets/MainAssets/herielleIcon.png");
            samIcon = loadBuffered("/assets/MainAssets/samIcon.png");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load images",
                    e
            );
        }
    }

private BufferedImage loadBuffered(String path) {

    try {

        String finalPath = getAssetPath(path);

        System.out.println(finalPath);

        return ImageIO.read(
                new java.io.File(finalPath)
        );

    } catch (IOException e) {

        throw new RuntimeException(
                "Failed to load: " + path,
                e
        );
    }
}

private Image loadImage(String path) {

    return new ImageIcon(
            getAssetPath(path)
    ).getImage();
}

private Image loadScaledImage(
        String path,
        int width,
        int height
) {

    return loadImage(path).getScaledInstance(
            width,
            height,
            Image.SCALE_SMOOTH
    );
}

private String getAssetPath(String path) {

    if (path.startsWith("/")) {

        path = path.substring(1);
    }

        String basePath =
                System.getProperty("user.dir");

        return basePath
                + java.io.File.separator
                + "Memoriam"
                + java.io.File.separator
                + path.replace(
                        "/",
                        java.io.File.separator
                );
        }
}