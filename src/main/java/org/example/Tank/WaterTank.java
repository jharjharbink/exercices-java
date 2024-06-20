package org.example.Tank;

public class WaterTank {
    private final double emptyWeight;  //kg
    private final double maxCapacity;  // L
    private double existingVolume;  // L
    private static double totalVolume = 0;

    // Constructors
    public WaterTank(double emptyWeight, double maxCapacity, double currentLevel) {
        this.emptyWeight = emptyWeight;
        this.maxCapacity = maxCapacity;
        this.existingVolume = currentLevel;
        totalVolume += currentLevel;
    }

    public WaterTank(double emptyWeight, double maxCapacity) {
        this(emptyWeight, maxCapacity, 0);
    }

    // Useful methods
    public double fill(double waterQuantity) {
        totalVolume -= existingVolume;

        double currentVolume = existingVolume + waterQuantity;
        double rest;

        if (currentVolume < 0){
            double recoveredQuantity = existingVolume;
            existingVolume = 0;
            rest = recoveredQuantity;

        } else if (currentVolume > this.maxCapacity){
            existingVolume = maxCapacity;
            rest = currentVolume - maxCapacity;

        } else{
            existingVolume = currentVolume;
            rest = 0;
        }

        totalVolume += existingVolume;
        return rest;
    }

    public double empty(double waterQuantity){
        return fill(-waterQuantity);
    }


    // Getter
    public double getTotalWeight(){
        return this.emptyWeight + this.existingVolume;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public double getFillLevel() {
        return existingVolume;
    }

    public static double getTotalFillLevels(){
        return totalVolume;
    }

    @Override
    public String toString() {
        return "WaterTank{" +
                "emptyWeight=" + emptyWeight +
                ", maxCapacity=" + maxCapacity +
                ", currentVolume=" + existingVolume +
                '}';
    }
}
