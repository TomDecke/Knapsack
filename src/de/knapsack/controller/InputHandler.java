package de.knapsack.controller;

import de.knapsack.model.InputValidationException;
import de.knapsack.model.Item;
import de.knapsack.model.KnapsackProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Class to extract a List of Knapsack-Problems from a given file.
 * @author TDecke
 */
public class InputHandler {

    /**
     * Reads data from a given file and converts it into a list of knapsack problems.
     * @param aFilepath path to input file
     * @return List of Knapsack-Problems
     */
    public List<KnapsackProblem> extractKnapsackProblemsFromFile(String aFilepath){

        List<KnapsackProblem> knapsackProblems = new ArrayList<>();
        File inputFile = new File(aFilepath);
        Scanner fileScanner;
        try {
            // id to track which knapsack problem is being extracted
            int knapsackId = 1;
            fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                if (!currentLine.isBlank()) {
                    String[] knapsackInformation = currentLine.split(":");
                    if(knapsackInformation.length == 2){
                        try {
                            knapsackProblems.add(new KnapsackProblem(extractCapacity(knapsackInformation[0]), extractItems(knapsackInformation[1])));
                        }catch(InputValidationException anExc){
                            System.out.println("Invalid input on data entry #"+knapsackId+": "+anExc.getMessage());
                        }
                    }else{
                        System.out.println("Invalid input on data entry #" + knapsackId + ": wrong format (exactly one colon expected)");
                    }

                    knapsackId++;
                }
            }
        } catch (FileNotFoundException anExc) {
            System.out.println("Could not find file: " + aFilepath);
        }

        return knapsackProblems;
    }

    /**
     * Get the capacity for the problem from a String.
     * @param aCapacity capacity in String-form
     * @return the max capacity for the current knapsack problem
     * @throws InputValidationException in case the input is corrupted
     */
    private BigDecimal extractCapacity(String aCapacity) throws InputValidationException {
        aCapacity = aCapacity.strip();
        BigDecimal capacity;
        try {
            capacity = new BigDecimal(aCapacity);
            if(BigDecimal.ZERO.compareTo(capacity) == 1 || BigDecimal.valueOf(100L).compareTo(capacity) == -1){
                throw new InputValidationException("Invalid weight. " + aCapacity +" is not within range 0 <= x <= 100 ");
            }
        }catch(NumberFormatException exc) {
            throw new InputValidationException(aCapacity + " is not a valid weight");
        }

        return capacity;

    }

    /**
     * Creates a List of items from a String.
     * @param someItemsAsString possible items in String-form
     * @return List of items that are part of the current problem
     * @throws InputValidationException in case the input is corrupted
     */
    private List<Item> extractItems(String someItemsAsString) throws InputValidationException {
        List<Item> items = new ArrayList<>();

        someItemsAsString = someItemsAsString.strip();
        String[] itemsAsStrings = someItemsAsString.split(" ");

        for (String itemString: itemsAsStrings) {

            // Alter the String so that the item-information is only separated by a colon
            itemString = itemString.replace(" ","");
            itemString = itemString.replace("(","");
            itemString = itemString.replace(")","");
            itemString = itemString.replace("€","");
            String[] itemProperties = itemString.split(",");

            if(itemProperties.length == 3){
                items.add(createItem(itemProperties[0].strip(),itemProperties[1].strip(),itemProperties[2].strip()));
            }else {
                throw new InputValidationException("Invalid item-dataset");
            }

        }

        if(items.size() > 15){
            throw new InputValidationException("Exceeded item limit - to many items: "+ items.size());
        }
        return items;
    }

    /**
     * Creates an item for a bag.
     * @param anId potential id as String
     * @param aWeight potential weight as String
     * @param aValue potential value as String
     * @return an item that can belong to the solution of the problem
     * @throws InputValidationException in case the input is corrupted
     */
    private Item createItem(String anId, String aWeight, String aValue) throws  InputValidationException{
        int itemId;
        BigDecimal itemWeight;
        BigDecimal itemCost;
        try {
            itemId = Integer.parseInt(anId);
        }catch(NumberFormatException exc){
            throw new InputValidationException("Invalid input. The given id was not a number: " + anId);
        }

        try {
            itemWeight = new BigDecimal(aWeight);
            if(BigDecimal.ZERO.compareTo(itemWeight) == 1 || BigDecimal.valueOf(100L).compareTo(itemWeight) == -1){
                throw new InputValidationException("Invalid weight. " + itemWeight +" is not within range 0 <= x <= 100 ");
            }
        }catch(NumberFormatException exc){
            throw new InputValidationException(aWeight+" is not a valid weight");
        }

        try {
            itemCost = new BigDecimal(aValue);
            if(BigDecimal.ZERO.compareTo(itemWeight) == 1 || BigDecimal.valueOf(100L).compareTo(itemCost) == -1){
                throw new InputValidationException("Invalid price. " + itemCost +" is not within range 0 <= x <= 100 ");
            }
        }catch(NumberFormatException exc){
            throw new InputValidationException(anId+" is not a valid price");
        }

        return new Item(itemId, itemWeight, itemCost);
    }



}
