package org.example.ExoPlant;

import static org.example.ExoPlant.LeafColor.*;


public class Plant {
    private String name = "Plantu";
    private double height = 0;  //meters
    private LeafColor color = GREEN;

    public Plant(String name, double height, LeafColor color) {
        this.name = name;
        this.height = height;
        this.color = color;
    }

    @Override
    public String toString() {
        return  super.getClass().getSimpleName() +
                " --> name='" + name +
                ", height=" + height +
                ", color=" + color;
    }
}
