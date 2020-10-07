package de.knapsack.model;

import java.math.BigDecimal;

public class Item {
    private int id;

    private BigDecimal cost;

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
