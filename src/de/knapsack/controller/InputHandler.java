package de.knapsack.controller;

import de.knapsack.model.InputValidationException;
import de.knapsack.model.Bag;
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
@SuppressWarnings("ALL")
public class InputHandler {

    /**
     * Reads data from a given file and converts it into a list of knapsack problems.
     * @param aFilepath path to input file
     * @return
     */
    public List<KnapsackProblem> extractKnapsackproblemsFromFile(String aFilepath){

        List<KnapsackProblem> knapsacks = new ArrayList<>();
        File inputFile = new File(aFilepath);
        Scanner fileScanner;
        try {
            int knapsackId = 1;
            fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                if (!currentLine.isBlank()) {
                    String[] knapsackInformation = currentLine.split(":");
                    if(knapsackInformation.length != 2){
                        System.out.println("Invalid input on data entry #"+knapsackId+": wrong format [one colon expected]");
                        knapsackId++;
                        continue;
                    }
                    try {
                        knapsacks.add(new KnapsackProblem(knapsackId,createBag(knapsackInformation[0]),createItems(knapsackInformation[1])));
                    }catch(InputValidationException anExc){
                        System.out.println("Invalid input on data entry #"+knapsackId+": "+anExc.getMessage());
                    }
                    knapsackId++;
                }
            }
        } catch (FileNotFoundException anExc) {
            System.out.println("Could not find file: "+aFilepath);
        }

        return knapsacks;
    }

    /**
     * Creates a bag with the given maximum weight.
     * @param aMaxWeight
     * @return
     * @throws InputValidationException
     */
    private Bag createBag(String aMaxWeight) throws InputValidationException {
        aMaxWeight = aMaxWeight.strip();
        BigDecimal maxWeight;
        try {
            maxWeight = new BigDecimal(aMaxWeight);
            if(BigDecimal.ZERO.compareTo(maxWeight) == 1 || BigDecimal.valueOf(100L).compareTo(maxWeight) == -1){
                throw new InputValidationException("Invalid weight. " + aMaxWeight +" is not within range 0 <= x <= 100 ");
            }
        }catch(NumberFormatException exc) {
            throw new InputValidationException(aMaxWeight + " is not a valid weight");
        }

        return new Bag(maxWeight);

    }

    /**
     * Creates a List of items from a String.
     * @param someItemsAsString
     * @return
     * @throws InputValidationException
     */
    private List<Item> createItems(String someItemsAsString) throws InputValidationException {
        List<Item> items = new ArrayList<>();
        String[] itemsAsStrings = someItemsAsString.split(" ");

        for (String itemString: itemsAsStrings) {

            // A line comes with a leading blank that we want to ignore
            if(itemString.isBlank()){
                continue;
            }

            // Alter the String so that the item-information is only seperated by a colon
            itemString = itemString.replace(" ","");
            itemString = itemString.replace("(","");
            itemString = itemString.replace(")","");
            itemString = itemString.replace("€","");
            String[] itemProperties = itemString.split(",");

            if(itemProperties.length == 3){
                // TODO ggf das array übergeben?!
                items.add(createItem(itemProperties[0],itemProperties[1],itemProperties[2]));
            }else {
                throw new InputValidationException("Invalid item dataset");
            }

        }

        if(items.size() > 15){
            throw new InputValidationException("Exceeded item limit: "+ items.size());
        }
        return items;
    }

    /**
     * Creates an item for a bag.
     * @param anId
     * @param aWeight
     * @param aCost
     * @return
     * @throws InputValidationException
     */
    private Item createItem(String anId, String aWeight, String aCost) throws  InputValidationException{
        int itemId;
        BigDecimal itemWeight;
        BigDecimal itemCost;
        try {
            itemId = Integer.parseInt(anId);
        }catch(NumberFormatException exc){
            throw new InputValidationException("Invalid input. The given id was not a number: "+anId);
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
            itemCost = new BigDecimal(aCost);
            if(BigDecimal.ZERO.compareTo(itemWeight) == 1 || BigDecimal.valueOf(100L).compareTo(itemCost) == -1){
                throw new InputValidationException("Invalid price. " + itemCost +" is not within range 0 <= x <= 100 ");
            }
        }catch(NumberFormatException exc){
            throw new InputValidationException(anId+" is not a valid price");
        }

        return new Item(itemId, itemWeight, itemCost);
    }



}
