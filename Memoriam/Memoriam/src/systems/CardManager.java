package systems;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import object.Card;
import object.PlayerAbility;
import object.Rarity;
import object.Relic;
import scenes.*;

public class CardManager {

    private final ArrayList<Card> common = new ArrayList<>();
    private final ArrayList<Card> rare = new ArrayList<>();
    private final ArrayList<Card> legendary = new ArrayList<>();

    // the lobby relics
    private final ArrayList<Card> bigThree  = new ArrayList<>();

    private final ImageLibrary images = ImageLibrary.get();
    private final PlayableScreen scrn;
    private final InputManager inps;
    private final Random random = new Random();

    // ui stuff
    private int hoveredCardIndex = -1;
    private int selectedCardTimer = 0;
    private int chestState = 0; 
    private int spinTicks = 0;
    private int selectedCardIndex = -1;


    // transition
    private boolean isFading = false;
    private float fade = 0f;

    // chest system
    public boolean showChestUI = false;
    private ArrayList<Card> currentCards = new ArrayList<>();

    private boolean isLobbyChest = false; // true when the current chest is the lobby selection

    public CardManager(PlayableScreen scrn) {
        this.scrn = scrn;
        inps = this.scrn.getInputManager();

        // COMMON MINOR ARCANA
        common.add(new Card(
            "Two of Cups",
            images.TwoOfCups,
            Rarity.COMMON,
            PlayerAbility.HP_REGEN,
            images.TwoOfCupsHover
        ));

        common.add(new Card(
            "Ace of Wands",
            images.AceOfWands,
            Rarity.COMMON,
            PlayerAbility.FLAME_SHOT,
            images.AceOfWandsHover
        ));

        // RARE MINOR ARCANA
        rare.add(new Card(
            "Ten of Swords",
            images.TenOfSwords,
            Rarity.RARE,
            PlayerAbility.MULTI_SHOT,
            images.TenOfSwordsHover
        ));

        rare.add(new Card(
            "Nine of Pentacles",
            images.NineOfPentacles,
            Rarity.RARE,
            PlayerAbility.FORTIFIED_REGEN,
            images.NineOfPentaclesHover
        ));

        // LEGENDARY MINOR ARCANA
        legendary.add(new Card(
            "Queen of Cups",
            images.QueenOfCups,
            Rarity.LEGENDARY,
            PlayerAbility.SHIELD,
            images.QueenOfCupsHover
        ));

        legendary.add(new Card(
            "Knight of Wands",
            images.KnightOfWands,
            Rarity.LEGENDARY,
            PlayerAbility.SPEED_ENHANCE,
            images.KnightOfWandsHover
        ));

        bigThree.add(new RelicCard(
            "Death",       
            images.Death,      
            Relic.DEATH,
            images.DeathHover
        ));
        
        bigThree.add(new RelicCard(
            "The Magician",
            images.Magician,   
            Relic.MAGICIAN,
            images.MagicianHover
        ));

        bigThree.add(new RelicCard(
            "The Empress", 
            images.Empress,    
            Relic.THE_EMPRESS,
            images.EmpressHover
        ));
    }

    public Card drawCard(int level) {

        object.Player player = scrn.getWorldRenderer() != null ? scrn.getWorldRenderer().getPlayer(): null;

        double roll = random.nextDouble();

        double legendaryChance = Math.min(0.05 + (level * 0.02), 0.25);
        double rareChance = 0.30;

        if (roll < legendaryChance) {
            Card c = drawFromPool(legendary, player);
            if (c != null) return c;
        }
        if (roll < legendaryChance + rareChance) {
            Card c = drawFromPool(rare, player);
            if (c != null) return c;
        }
        Card c = drawFromPool(common, player);
        if (c != null) return c;

        // absolute fallback: scan all pools for any non-maxed card
        c = drawFromPool(rare, player);

        if (c != null) return c;

        c = drawFromPool(legendary, player);
        return c;  // may be null if truly everything is maxed

    }

    private Card drawFromPool(ArrayList<Card> pool, object.Player player) {
        // Build a filtered list of drawable cards
        ArrayList<Card> eligible = new ArrayList<>();
        for (Card card : pool) {
            if (player == null || !player.isAbilityMaxed(card.ability)) {
                eligible.add(card);
            }
        }
        if (eligible.isEmpty()) return null;
        return eligible.get(random.nextInt(eligible.size()));
    }

    // chest methods
    public void openChest() {

        if (showChestUI) return;
        isLobbyChest = false;
        showChestUI = true;
        chestState = 1;

        // Makes the player not able to fight when chest is open
        scrn.getWorldRenderer().getPlayer().setUIOpen(true);

        currentCards.clear();

        // EXACTLY 2 cards
        currentCards.clear();
        refillCurrentCards();
        spinTicks = 0;
    }

    public void openLobbyChest() {
        if (showChestUI) return;

        // only offer the Big Three if no relic has been chosen yet.
        if (RelicManager.get().hasRelic()) return;

        isLobbyChest = true;
        showChestUI  = true;
        chestState   = 2;   // skip the spin, go straight to selection
        scrn.getWorldRenderer().getPlayer().setUIOpen(true);
        currentCards.clear();
        currentCards.addAll(bigThree);   // show all three
    }


    public void spinCard()
    {
        if (chestState != 1) return;
        spinTicks++;
        
        int speed = Math.max(2, 10 - spinTicks / 10);
       
       if (spinTicks % speed == 0) {
            refillCurrentCards();
        }
        if (spinTicks > 60) {
            chestState = 2;
        }
    }

    public void sizeCard()
    {        
         if (!showChestUI || chestState != 2 || !inps.consumeClick()) return;

        int cardW = 300, cardH = 450, spacing = 40;
        int totalWidth = (cardW * currentCards.size()) + (spacing * (currentCards.size() - 1));
        int startX = (scrn.getWidth() - totalWidth) / 2;
        int y      = (scrn.getHeight() - cardH) / 2;

        Vector2 mouse = inps.getClickPosition();

        for (int i = 0; i < currentCards.size(); i++) {
            int x = startX + i * (cardW + spacing);
            if (new Rectangle(x, y, cardW, cardH).contains(mouse.x, mouse.y)) {
                selectCard(i);
                break;
            }
        }
    }
    
    // card stuff
    public void showCards()
    {
        currentCards.clear();
        refillCurrentCards();
        showChestUI    = true;
        selectedCardIndex = -1;

    }
 
    public void resetUIOnTransition() {
        showChestUI = false;
        currentCards.clear();
    }

    public void closeChestUI() {
        showChestUI = false;
        scrn.getWorldRenderer().getPlayer().setUIOpen(false);
        currentCards.clear();
    }

    public void requestLevelChange() {
        isFading = true;
        showChestUI = false;
    }

    
    public void selectCard(int index) {

        if (index < 0 || index >= currentCards.size()) return;
        Card c = currentCards.get(index);
        if (c == null) return;

        selectedCardIndex = index;
        selectedCardTimer = 60;

        System.out.println("Card selected: " + c.name + " | Ability: " + c.ability);
        scrn.getWorldRenderer().getPlayer().applyAbility(c.ability);

        showChestUI = false;
        chestState  = 0;
        scrn.getWorldRenderer().getPlayer().setUIOpen(false);

        if (c instanceof RelicCard relic) {
             //  Lobby Big Three path 
            System.out.println("Relic selected: " + relic.name);
            object.Player player = scrn.getWorldRenderer().getPlayer();
            RelicManager.get().applyRelic(relic.relic, player);
            LevelManager.loadLevel(scrn.getID() + 1, scrn);

        } 
        else 
        {
            // Regular mid-run card path 
            System.out.println("Card selected: " + c.name + " | Ability: " + c.ability);
            scrn.getWorldRenderer().getPlayer().applyAbility(c.ability);
            LevelManager.loadLevel(scrn.getID() + 1, scrn);
        }
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

            Vector2 mouse = this.scrn.getInputManager().getMousePosition(); // last mouse pos

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

        int cardW = 300, cardH = 450, spacing = 40;
        int totalWidth = (cardW * currentCards.size()) + (spacing * (currentCards.size() - 1));

        int startX = (this.scrn.getWidth() - totalWidth) / 2;
        int y = (this.scrn.getHeight() - cardH) / 2;

        for (int i = 0; i < currentCards.size(); i++) {

            int x = startX + i * (cardW + spacing);
            Card c = currentCards.get(i);

            if (c != null && c.image != null) {
                graphics2.drawImage(c.image, x, y, cardW, cardH, null);
            }

            // hover grey
            if (i == hoveredCardIndex) {
                graphics2.drawImage(c.imageHover, x, y, cardW, cardH, null);
            }

            // select highlight
            if (i == selectedCardIndex) {
                graphics2.setColor(new Color(255, 255, 0, 100));
                graphics2.fillRect(x, y, cardW, cardH);
            }

        }
    }

    private void refillCurrentCards() {
        currentCards.clear();
        Card a = drawCard(scrn.getID());
        Card b = drawCard(scrn.getID());

        if (a != null) currentCards.add(a);
        if (b != null) currentCards.add(b);
    }

    public static class RelicCard extends Card {
        public final Relic relic;

        public RelicCard(String name, Image image, Relic relic, Image imageHover) {
            super(name, image, Rarity.LEGENDARY, null, imageHover);   // no PlayerAbility
            this.relic = relic;
        }
    }
}