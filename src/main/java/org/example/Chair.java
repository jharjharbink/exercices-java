package org.example;

public class Chair {
    private int legNbr = 4;
    private String material = "wood";
    private String color = "brown";
    private float price = 0.0F;

    public Chair() {}

    public Chair(int legNbr, String material, String color, float price) {
        this.legNbr = legNbr;
        this.material = material;
        this.color = color;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Je suis une chaise avec " + this.legNbr + " pied(s) en " + this.material + " de couleur " + this.color +
                " à un prix de " + this.price + ".";
    }
}
