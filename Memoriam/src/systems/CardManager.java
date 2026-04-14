package systems;

import images.ImageLibrary;
import java.util.ArrayList;
import java.util.Random;
import object.Card;

public class CardManager {

    private final ArrayList<Card> cards = new ArrayList<>();
    private final Random random = new Random();

    public CardManager(ImageLibrary images) {

        // Example cards (add your full deck here)
        cards.add(new Card("AceOfWands", images.AceOfWands));
        cards.add(new Card("Death", images.Death));
        cards.add(new Card("Empress", images.Empress));
        cards.add(new Card("KnightOfWands", images.KnightOfWands));
        cards.add(new Card("Magician", images.Magician));
        cards.add(new Card("NineOfPentacles", images.NineOfPentacles));
        cards.add(new Card("QueenOfCups", images.QueenOfCups));
        cards.add(new Card("TenOfSwords", images.TenOfSwords));
        cards.add(new Card("TwoOfCups", images.TwoOfCups));
    }

    public Card drawCard() {
        if (cards.isEmpty()) return null;
        return cards.get(random.nextInt(cards.size()));
    }
}