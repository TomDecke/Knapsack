package de.knapsack.controller;

import de.knapsack.model.Item;
import de.knapsack.model.KnapsackProblem;

/**
 * Class to find a solution for an instance of the knapsack problem.
 */
public class KnapsackSolver {

    /**
     * Contains the logic to solve and print a solution to a knapsack-problem.
     * @param aKnapsackProblem the problem to solve
     */
    public void solve(KnapsackProblem aKnapsackProblem){
       printSolution(aKnapsackProblem);
    }

    /**
     * Print the found solution to the console.
     * @param problem the problem once again
     */
    private void printSolution(KnapsackProblem problem){
        StringBuilder itemsAsStrings = new StringBuilder();
        for (Item i:problem.getItems()) {
            itemsAsStrings.append(i);
            itemsAsStrings.append(", ");
        }
        System.out.println(problem.getId()+": "+problem.getBag().toString()+itemsAsStrings.toString());
    }
}
