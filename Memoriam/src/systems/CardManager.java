package systems;

import images.ImageLibrary;
import java.util.ArrayList;
import java.util.Random;
import object.Card;
import scenes.*;
import object.PlayerAbility;
import object.Rarity;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class CardManager {

    private final ArrayList<Card> common = new ArrayList<>();
    private final ArrayList<Card> rare = new ArrayList<>();
    private final ArrayList<Card> legendary = new ArrayList<>();
    private final ImageLibrary images = ImageLibrary.get();
    private final PlayableScreen scrn;
    private final InputManager inps;

    private final Random random = new Random();

    
    private int hoveredCardIndex = -1;

    private int selectedCardTimer = 0;
    

    // powerup
    private int chestState = 0; 
    private int spinTicks = 0;
    private int selectedCardIndex = -1;


    // transition
    private boolean isFading = false;
    private float fade = 0f;

    // chest system
    public boolean showChestUI = false;
    private ArrayList<Card> currentCards = new ArrayList<>();






    public CardManager(PlayableScreen scrn) {
    this.scrn = scrn;
    inps = this.scrn.getInputManager();

    // COMMON MINOR ARCANA
    common.add(new Card(
        "Two of Cups",
        images.TwoOfCups,
        Rarity.COMMON,
        PlayerAbility.HP_REGEN
    ));

    common.add(new Card(
        "Ace of Wands",
        images.AceOfWands,
        Rarity.COMMON,
        PlayerAbility.FLAME_SHOT
    ));

    // RARE MINOR ARCANA
    rare.add(new Card(
        "Ten of Swords",
        images.TenOfSwords,
        Rarity.RARE,
        PlayerAbility.HEAVY_STRIKE
    ));

    rare.add(new Card(
        "Nine of Pentacles",
        images.NineOfPentacles,
        Rarity.RARE,
        PlayerAbility.FORTIFIED_REGEN
    ));

    // LEGENDARY MINOR ARCANA
    legendary.add(new Card(
        "Queen of Cups",
        images.QueenOfCups,
        Rarity.LEGENDARY,
        PlayerAbility.SHIELD
    ));

    legendary.add(new Card(
        "Knight of Wands",
        images.KnightOfWands,
        Rarity.LEGENDARY,
        PlayerAbility.BOUNCING_SHOT
    ));
    }

    public Card drawCard(int level) {

        double roll = random.nextDouble();

        double legendaryChance = Math.min(0.05 + (level * 0.02), 0.25);
        double rareChance = 0.30;

        if (roll < legendaryChance) {
            return legendary.get(random.nextInt(legendary.size()));
        }
        else if (roll < legendaryChance + rareChance) {
            return rare.get(random.nextInt(rare.size()));
        }
        else {
            return common.get(random.nextInt(common.size()));
        }
    }

    public void drawCardsUI(Graphics g)
    {

    }

    // chest methods
    public void openChest() {

        if (showChestUI) return;

        showChestUI = true;
        chestState = 1;

        // Makes the player not able to fight when chest is open
        this.scrn.getWorldRenderer().getPlayer().setUIOpen(true);

        currentCards.clear();

        // EXACTLY 2 cards
        currentCards.add(drawCard(this.scrn.getID()));
        currentCards.add(drawCard(this.scrn.getID()));

        spinTicks = 0;
    }


    public void spinCard()
    {
        if (chestState == 1) 
        {
            spinTicks++;

            int speed = Math.max(2, 10 - spinTicks / 10);

            if (spinTicks % speed == 0) {

                currentCards.clear();
                currentCards.add(drawCard(this.scrn.getID()));
                currentCards.add(drawCard(this.scrn.getID()));   
            }
            if (spinTicks > 60) {
                chestState = 2; // stop spinning, allow selection
            }
        }

    }

    public void sizeCard()
    {        
        if (showChestUI && chestState == 2 && inps.consumeClick()) 
        {
            // Make this constant later
            int cardW = 300;
            int cardH = 450;
            int spacing = 40;

            int totalWidth = (cardW * 3) + (spacing * 2);
            int startX = (scrn.getWidth() - totalWidth) / 2;
            int y = (scrn.getHeight() - cardH) / 2;

            Vector2 mouse = inps.getClickPosition();

            for (int i = 0; i < currentCards.size(); i++) {

                int x = startX + i * (cardW + spacing);

                Rectangle rect = new Rectangle(x, y, cardW, cardH);

                if (rect.contains(mouse.x, mouse.y)) {

                    selectedCardIndex = i;
                    selectCard(i);

                    break;
                }
            }
        }
    }

    
    // card stuff
    public void showCards()
    {
        currentCards.clear();

        currentCards.add(drawCard(this.scrn.getID()));
        currentCards.add(drawCard(this.scrn.getID()));
        
        showChestUI = true;

        selectedCardIndex = -1;

    }
 

    public void resetUIOnTransition() {
        showChestUI = false;
        currentCards.clear();

    }


    public void requestLevelChange() {
        isFading = true;

        showChestUI = false;

    }

    
    public void selectCard(int index) {

        Card c = currentCards.get(index);
        if (c == null) return;

        selectedCardIndex = index;


        selectedCardTimer = 60; // show for ~1 second

        System.out.println("Card selected: " + c.name + " | Ability: " + c.ability);
        this.scrn.getWorldRenderer().getPlayer().applyAbility(c.ability); 

        showChestUI = false;
        chestState = 0;

        // Enables inputs
        this.scrn.getWorldRenderer().getPlayer().setUIOpen(false); 


        // Wtf is this
        // Just call LevelManager
        // this.getGameFrame().cutsceneScreen.loadCutsceneForLevel(this.id + 1);
        // this.getGameFrame().showPanel("cutscene");

        
        LevelManager.loadLevel(this.scrn.getID() + 1, this.scrn);
        // Do the two things stated here for transition
    }

    
    public void closeChestUI() {
        showChestUI = false;
        this.scrn.getWorldRenderer().getPlayer().setUIOpen(false);
        currentCards.clear();
    }
    
   

    
    public void triggerLevelChange() {
        isFading = true;

        resetUIOnTransition();
    }  

    

    public void checkHoveringButtons()
    {
        // HOVER DETECTION
        if (showChestUI && chestState == 2) {

            int cardW = 300;
            int cardH = 450;
            int spacing = 40;

            int totalWidth = (cardW * currentCards.size()) + (spacing * (currentCards.size() - 1));
            int startX = (this.scrn.getWidth() - totalWidth) / 2;
            int y = (this.scrn.getHeight() - cardH) / 2;

            Vector2 mouse = this.scrn.getInputManager().getClickPosition(); // last mouse pos

            hoveredCardIndex = -1;

            for (int i = 0; i < currentCards.size(); i++) {

                int x = startX + i * (cardW + spacing);

                Rectangle rect = new Rectangle(x, y, cardW, cardH);

                if (rect.contains(mouse.x, mouse.y)) {
                    hoveredCardIndex = i;
                    break;
                }
            }
        }
        if (selectedCardTimer > 0) {selectedCardTimer--;}


    }


    
    public void drawChestUI(Graphics g)
    {
        Graphics2D graphics2 = (Graphics2D) g;
    
        graphics2.setColor(new Color(0, 0, 0, 200));
        graphics2.fillRect(0, 0, this.scrn.getWidth(), this.scrn.getHeight());

        int cardW = 300;
        int cardH = 450;

        int spacing = 40;
        int totalWidth = (cardW * currentCards.size()) + (spacing * (currentCards.size() - 1));

        int startX = (this.scrn.getWidth() - totalWidth) / 2;
        int y = (this.scrn.getHeight() - cardH) / 2;

        for (int i = 0; i < currentCards.size(); i++) {

            int x = startX + i * (cardW + spacing);

            Card c = currentCards.get(i);

            // hover grey
            if (i == hoveredCardIndex) {
                graphics2.setColor(new Color(0, 0, 0, 120));
                graphics2.fillRect(x, y, cardW, cardH);
            }

            // select highlight
            if (i == selectedCardIndex) {
                graphics2.setColor(new Color(255, 255, 0, 100));
                graphics2.fillRect(x, y, cardW, cardH);
            }

            if (c != null && c.image != null) {

                graphics2.drawImage(
                    c.image,
                    x,
                    y,
                    cardW,
                    cardH,
                    null
                );
            }
        }
    }

}