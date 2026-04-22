package systems;

import images.ImageLibrary;
import java.util.ArrayList;
import java.util.Random;
import object.Card;
import object.PlayerAbility;
import object.Rarity;

public class CardManager {

    private final ArrayList<Card> common = new ArrayList<>();
    private final ArrayList<Card> rare = new ArrayList<>();
    private final ArrayList<Card> legendary = new ArrayList<>();

    private final Random random = new Random();

    public CardManager(ImageLibrary images) {


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
}