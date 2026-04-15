package object;

import java.awt.Image;

public class Card {
    public final String name;
    public final Image image;

    public Card(String name, Image image) {
        this.name = name;
        this.image = image;
    }
}