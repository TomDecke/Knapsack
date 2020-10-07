package de.knapsack.model;

import java.math.BigDecimal;

/**
 * Represents an item that can be stored in a bag.
 * @author TDecke
 */
public class Item {

    /** Id of the item. */
    private int id;

    /** value of the item. */
    private BigDecimal cost;

    /** weight of the item. */
    private BigDecimal weight;

    public Item(int id, BigDecimal cost, BigDecimal weight) {
        this.id = id;
        this.cost = cost;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cost=" + cost +
                ", weight=" + weight +
                '}';
    }
}
