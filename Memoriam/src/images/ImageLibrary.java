package images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

    public final BufferedImage calryaIcon;
    public final BufferedImage shadeyIcon;
    public final BufferedImage herielleIcon;
    public final BufferedImage samIcon;

    private Graphics2D g2;

    private ImageLibrary() {

        try {

            projectile = loadScaledImage("/assets/objects/projectile.png", 32, 32);
            enemyProjectile = loadScaledImage("/assets/objects/enemyProjectile.png", 32, 32);

            playerSpritesDOWN = loadGif("/assets/PlayerSprites/animated/idle/fool_idle_front.gif");
            playerSpritesLEFT = loadGif("/assets/PlayerSprites/animated/idle/fool_idle_left.gif");
            playerSpritesUP = loadGif("/assets/PlayerSprites/animated/idle/fool_idle_back.gif");
            playerSpritesRIGHT = loadGif("/assets/PlayerSprites/animated/idle/fool_idle_right.gif");

            foolShieldDown = loadImage("/assets/PlayerSprites/shield/foolShieldDown.png");
            foolShieldRight = loadImage("/assets/PlayerSprites/shield/foolShieldRight.png");
            foolShieldLeft = loadImage("/assets/PlayerSprites/shield/foolShieldLeft.png");
            foolShieldUp = loadImage("/assets/PlayerSprites/shield/foolShieldUp.png");

            foolAtkDown = loadImage("/assets/PlayerSprites/atk/foolDownAtk.PNG");
            foolAtkRight = loadImage("/assets/PlayerSprites/atk/foolRightAtk.PNG");
            foolAtkLeft = loadImage("/assets/PlayerSprites/atk/foolLeftAtk.PNG");
            foolAtkUp = loadImage("/assets/PlayerSprites/atk/foolUpAtk.PNG");

            quillCursor = loadScaledImage("/assets/MainAssets/cursor.png", 64, 64);
            swordCursor = loadScaledImage("/assets/MainAssets/swordCursor.png", 64, 64);

            background = loadImage("/assets/Panels/menuScreenBG.png");
            creditBackground = loadImage("/assets/Panels/creditBackground.png");
            loadingScreen = loadImage("/assets/Panels/loadingScreen.png");
            loseScreen = loadImage("/assets/Panels/loseScreen.png");
            boboLogo = loadImage("/assets/Panels/boboFront.png");
            logo = loadImage("/assets/Panels/logo.png");

            exitBtn = loadImage("/assets/Panels/exitBtn.PNG");
            practiceBtn = loadImage("/assets/Panels/practiceBtn.png");
            startBtn = loadImage("/assets/Panels/startBtn.PNG");
            backBtn = loadImage("/assets/Panels/backBtn.png");
            mainMenuBtn = loadImage("/assets/Panels/mainMenu.png");
            quitBtn = loadImage("/assets/Panels/quitBtn.png");
            quitBtnGO = loadImage("/assets/Panels/quitBtnGO.png");
            resumeBtn = loadImage("/assets/Panels/resumeBtn.png");
            resumeBtnHover = loadImage("/assets/Panels/resumeButtonHover.png");
            restartBtn = loadImage("/assets/Panels/restart.png");

            placeholderBtn = loadImage("/assets/Panels/placeholderBtn.png");
            quitBtnExit = loadImage("/assets/Panels/quitBtnExit.png");
            creditBtn = loadImage("/assets/Panels/creditBtn.png");
            tutorialBtn = loadImage("/assets/Panels/tutorialBtn.png");

            pauseBg = loadImage("/assets/Panels/pauseBg.png");
            loadSaveBtn = loadImage("/assets/Panels/loadSave.png");
            loadSaveBtnHover = loadImage("/assets/Panels/loadSaveHover.png");
            newSaveButton = loadImage("/assets/Panels/newSaveButton.png");
            newSaveButtonHover = loadImage("/assets/Panels/newSaveButtonHover.png");
            loadBg = loadImage("/assets/Panels/backDrop2.png");

            exitBtnHover = loadImage("/assets/Panels/exitBtnHover.png");
            practiceBtnHover = loadImage("/assets/Panels/practiceBtnHover.png");
            startBtnHover = loadImage("/assets/Panels/startBtnHover.png");
            backBtnHover = loadImage("/assets/Panels/backBtnHover.png");
            placeholderBtnHover = loadImage("/assets/Panels/placeholderBtnHover.png");
            creditBtnHover = loadImage("/assets/Panels/creditBtnHover.png");
            tutorialBtnHover = loadImage("/assets/Panels/tutorialBtnHover.png");

            rawMap = loadImage("/assets/MainAssets/mapTest.png");

            map = new BufferedImage(32 * 80, 16 * 80, BufferedImage.TYPE_INT_ARGB);
            g2 = map.createGraphics();
            g2.drawImage(rawMap, 0, 0, 32 * 150, 16 * 150, null);
            g2.dispose();

            map0 = loadScaledImage("/assets/maps/lobby.png", 1920, 1080);
            map1 = loadScaledImage("/assets/maps/map1.png", 1920, 4320);
            map2 = loadScaledImage("/assets/maps/map2.png", 1920, 4320);
            map3 = loadScaledImage("/assets/maps/map3.png", 1920, 4320);
            map4 = loadScaledImage("/assets/maps/map4.png", 1920, 4320);
            map5 = loadScaledImage("/assets/maps/boss map.png", 1920, 4320);
            infiniteMap = loadScaledImage("/assets/maps/infiniteMap.png", 1920, 4320);
            tutorialMap = loadImage("/assets/maps/tutorialMap.png");

            Empress = loadImage("/assets/Cards/Empress.png");
            KnightOfWands = loadImage("/assets/Cards/KnightOfWands.png");
            Magician = loadImage("/assets/Cards/Magician.png");
            NineOfPentacles = loadImage("/assets/Cards/NineOfPentacles.png");
            QueenOfCups = loadImage("/assets/Cards/QueenOfCups.png");
            TenOfSwords = loadImage("/assets/Cards/TenOfSwords.png");
            TwoOfCups = loadImage("/assets/Cards/TwoOfCups.png");
            AceOfWands = loadImage("/assets/Cards/AceOfWands.png");
            Death = loadImage("/assets/Cards/Death.png");

            EmpressHover = loadImage("/assets/Cards/EmpressHover.png");
            KnightOfWandsHover = loadImage("/assets/Cards/KnightOfWandsHover.png");
            MagicianHover = loadImage("/assets/Cards/MagicianHover.png");
            NineOfPentaclesHover = loadImage("/assets/Cards/NineOfPentaclesHover.png");
            QueenOfCupsHover = loadImage("/assets/Cards/QueenOfCupsHover.png");
            TenOfSwordsHover = loadImage("/assets/Cards/TenOfSwordsHover.png");
            TwoOfCupsHover = loadImage("/assets/Cards/TwoOfCupsHover.png");
            AceOfWandsHover = loadImage("/assets/Cards/AceOfWandsHover.png");
            DeathHover = loadImage("/assets/Cards/DeathHover.png");

            // FIXED PATH: PowerUps (capital U)
            iconTheEmpress = loadImage("/assets/PowerUps/TheEmpress.png");
            iconKnightOfWands = loadImage("/assets/PowerUps/KnightOfWands.png");
            iconTheMagician = loadImage("/assets/PowerUps/TheMagician.png");
            iconNineOfPentacles = loadImage("/assets/PowerUps/NineofPentacles.png");
            iconQueenOfCups = loadImage("/assets/PowerUps/QueenofCups.png");
            iconTenOfSwords = loadImage("/assets/PowerUps/TenOfSwords.png");
            iconTwoOfCups = loadImage("/assets/PowerUps/TwoOfCups.png");
            iconAceOfWands = loadImage("/assets/PowerUps/AceofWands.png");
            iconDeath = loadImage("/assets/PowerUps/Death.png");

            treasureChest = loadImage("/assets/MainAssets/treasureChest.png");
            lockedTreasureChest = loadImage("/assets/MainAssets/lockedChest.png");
            treasureChestH = loadImage("/assets/MainAssets/treasureChestHighlighted.png");

            rawProjectile = loadImage("/assets/objects/projectile.png");
            fireProjectile = loadImage("/assets/objects/fireProjectile.png");

            blueLEFT = loadGif("/assets/objects/enemyAnim/blueLeft.gif");
            blueRIGHT = loadGif("/assets/objects/enemyAnim/blueRight.gif");
            purpleLEFT = loadGif("/assets/objects/enemyAnim/purpleLeft.gif");
            purpleRIGHT = loadGif("/assets/objects/enemyAnim/purpleRight.gif");
            yellowLEFT = loadGif("/assets/objects/enemyAnim/yellowLeft.gif");
            yellowRIGHT = loadGif("/assets/objects/enemyAnim/yellowRight.gif");

            finalBoss = loadScaledImage("/assets/objects/enemyAnim/finalBoss.png", 32 * 4, 40 * 4);
            enemyHurt = loadImage("/assets/objects/enemyHurt.png");
            finalBossHurt = loadScaledImage("/assets/objects/finalBossHurt.png", 32 * 4, 40 * 4);

            heart = loadImage("/assets/MainAssets/heart.png");
            deadHeart = loadImage("/assets/MainAssets/heartDead.png");
            splash = loadGif("/assets/MainAssets/splash.gif");

            calryaIcon = loadImage("/assets/MainAssets/calryaIcon.png");
            shadeyIcon = loadImage("/assets/MainAssets/shadey07Icon.png");
            herielleIcon = loadImage("/assets/MainAssets/herielleIcon.png");
            samIcon = loadImage("/assets/MainAssets/samIcon.png");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load images", e);
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            String finalPath = getAssetPath(path);

            File file = new File(finalPath);
            if (file.exists()) {
                return ImageIO.read(file);
            }

            // JAR fallback
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                return ImageIO.read(is);
            }

            throw new IOException("Missing asset: " + path);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load: " + path, e);
        }
    }

    private Image loadScaledImage(String path, int width, int height) {
        return loadImage(path).getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private String getAssetPath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        String basePath = System.getProperty("user.dir");

        return path;
        // return basePath
        //         + File.separator
        //         + "Memoriam"
        //         + File.separator
        //         + path.replace("/", File.separator);
    }

    private Image loadGif(String path) {
        // URL url = getClass().getResource(path);

        // if (url != null) {
        //         return new ImageIcon(url).getImage();
        // }

        // fallback filesystem (IDE mode)
        String finalPath = getAssetPath(path);
        return new ImageIcon(finalPath).getImage();
    }
}