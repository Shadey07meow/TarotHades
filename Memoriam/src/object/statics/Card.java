package object.statics;

import java.awt.Image;

import object.Entities.PlayerAbility;

public class Card {

    public String name;
    public Image image;
    public Image imageHover;
    public Rarity rarity;
    public PlayerAbility ability;

    public Card(String name, Image image, Rarity rarity, PlayerAbility ability, Image imageHover) {
        this.name = name;
        this.image = image;
        this.rarity = rarity;
        this.ability = ability;
        this.imageHover = imageHover;
    }
}