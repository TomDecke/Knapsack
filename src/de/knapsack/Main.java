package de.knapsack;

import de.knapsack.controller.InputHandler;
import de.knapsack.controller.KnapsackSolver;
import de.knapsack.model.KnapsackProblem;

import java.util.*;

/**
 * Main class with entrypoint for the solver.
 * @author TDecke
 */
public class Main {

    public static void main(String[] args) {
        String filepath = args[0];
        if(filepath == null) {
            System.out.println("Filepath as argument required");
            return;
        }
        InputHandler handler = new InputHandler();
        List<KnapsackProblem> problems = handler.extractKnapsackproblemsFromFile(args[0]);

        KnapsackSolver knapsackSolver = new KnapsackSolver();

        for (KnapsackProblem problem:problems) {
            knapsackSolver.solve(problem);
        }
    }
}
