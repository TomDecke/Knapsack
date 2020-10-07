package de.knapsack.controller;

import de.knapsack.model.Item;
import de.knapsack.model.KnapsackProblem;

public class KnapsackSolver {

    /**
     * Contains the logic to solve and print a solution to a knapsack-problem.
     * @param aKnapsackProblem the problem to solve
     */
    public void solve(KnapsackProblem aKnapsackProblem){
        String itemsAsStrings = "";
        for (Item i:aKnapsackProblem.getItems()) {
            itemsAsStrings += i;
        }
        System.out.println(aKnapsackProblem.getId()+": "+aKnapsackProblem.getBag().toString()+itemsAsStrings);
    }
}
