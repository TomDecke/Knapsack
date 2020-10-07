package de.knapsack.model;

import de.knapsack.model.Bag;
import de.knapsack.model.Item;

import java.util.List;

public class KnapsackProblem {

    int id;

    Bag bag;

    List<Item> items;

    public KnapsackProblem(int id, Bag bag, List<Item> items) {
        this.id = id;
        this.bag = bag;
        this.items = items;
    }

    public void solve(){
        String itemsAsStrings = "";
        for (Item i:items) {
            itemsAsStrings += i;
        }
        System.out.println(id+": "+bag.toString()+itemsAsStrings);
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
