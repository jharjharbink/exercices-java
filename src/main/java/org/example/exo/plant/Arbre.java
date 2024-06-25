package org.example.exo.plant;

public class Arbre extends Plant{
    private double stemCircumference;

    public Arbre(String name, double height, LeafColor color, double stemCircumference) {
        super(name, height, color);
        this.stemCircumference = stemCircumference;
    }

    public double getStemCircumference() {
        return stemCircumference;
    }

    public void setStemCircumference(double stemCircumference) {
        this.stemCircumference = stemCircumference;
    }

    public String toString(){
        return super.toString() + ", stemCircumference=" + stemCircumference;
    }
}
