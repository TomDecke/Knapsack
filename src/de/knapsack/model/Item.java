package de.knapsack.model;

import java.math.BigDecimal;

/**
 * Represents an item that can be stored in a bag.
 * @author TDecke
 */
public class Item {

    /** Id of the item. */
    private int id;

    /** weight of the item. */
    private BigDecimal weight;

    /** value of the item. */
    private BigDecimal value;


    public Item(int anId, BigDecimal aWeight, BigDecimal aValue) {
        this.id = anId;
        this.weight = aWeight;
        this.value = aValue;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
