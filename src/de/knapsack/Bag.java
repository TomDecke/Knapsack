package de.knapsack;

public class Bag {
   private int maxWeight;

    public Bag(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return "Package{" +
                "maxWeight=" + maxWeight +
                '}';
    }
}
