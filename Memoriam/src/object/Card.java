package object;

import java.awt.Image;

public class Card {

    public String name;
    public Image image;
    public Rarity rarity;
    public PlayerAbility ability;

    public Card(String name, Image image, Rarity rarity, PlayerAbility ability) {
        this.name = name;
        this.image = image;
        this.rarity = rarity;
        this.ability = ability;
    }
}