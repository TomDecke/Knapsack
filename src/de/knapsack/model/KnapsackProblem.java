package de.knapsack.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Representation of an instance of the Knapsack-Problem.
 * @author TDecke
 */
public class KnapsackProblem {

    /** Maximum capacity of the bag. */
    BigDecimal capacity;

    /** List of items to choose from. */
    List<Item> items;

    /**
     * Constructor
     * @param aCapacity max capacity
     * @param items list of items to choose from
     */
    public KnapsackProblem(BigDecimal aCapacity, List<Item> items) {
        this.capacity = aCapacity;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }
}
