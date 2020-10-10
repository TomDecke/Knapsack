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
    private BigDecimal cost;


    public Item(int id, BigDecimal weight, BigDecimal cost) {
        this.id = id;
        this.weight = weight;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
