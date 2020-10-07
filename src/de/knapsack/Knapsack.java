package de.knapsack;

import java.util.List;

public class Knapsack {

    int id;

    Bag bag;

    List<Item> items;

    public Knapsack(int id, Bag bag, List<Item> items) {
        this.id = id;
        this.bag = bag;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}