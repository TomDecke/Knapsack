package de.knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) {
        String filepath = args[0];
        if(filepath == null) {
            System.out.println("Filepath as argument required");
            return;
        }
        InputHandler handler = new InputHandler();
        handler.extractKnapsackproblemsFromFile(args[0]);
    }






}
