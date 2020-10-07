package de.knapsack.model;

import java.math.BigDecimal;

/**
 * Class to represent a bag that is to be filled.
 * @author TDecke
 */
public class Bag {

    /** maximum carrying capacity of the bag. */
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
