package de.knapsack.model;

import java.math.BigDecimal;

public class Bag {
   private BigDecimal maxWeight;

    public Bag(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return "Package{" +
                "maxWeight=" + maxWeight +
                '}';
    }
}
