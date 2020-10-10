package de.knapsack.controller;

import de.knapsack.model.Item;
import de.knapsack.model.KnapsackProblem;

import java.math.BigDecimal;
import java.util.*;

/**
 * Class to find a solution for an instance of the knapsack problem.
 * @author TDecke
 */
public class KnapsackSolver {

    /** The problem the solver is currently solving. */
    KnapsackProblem theProblem;


    /**
     * Contains the logic to solve and print a solution to a knapsack-problem.
     * @param aKnapsackProblem the problem to solve
     */
    public void solve(KnapsackProblem aKnapsackProblem){
       theProblem = aKnapsackProblem;
       ArrayList<Integer> selectedItems = new ArrayList<>();
       handleKnapsack(aKnapsackProblem.getItems().size()-1,aKnapsackProblem.getCapacity(), selectedItems);
       optimizeWeights(selectedItems);
       printItems(selectedItems);
    }

    /**
     * Determine the maximum achievable value for a given item for a remaining weight while keeping track of the items that belong to the solution
     * @param anIndex          the index of the item to consider
     * @param aRemainingWeight the remaining weight of the bag
     * @param someChosenItems  the items which are part of the solution so far
     * @return
     */
    private BigDecimal handleKnapsack(int anIndex, BigDecimal aRemainingWeight, ArrayList<Integer> someChosenItems){

        BigDecimal maxValue = BigDecimal.ZERO;

        /* If the end of the array or the weight limit is reached stop */
        if(anIndex == -1 || BigDecimal.ZERO.compareTo(aRemainingWeight)==0){
            return maxValue;
        }

        Item currentItem = theProblem.getItems().get(anIndex);
        if(currentItem.getWeight().compareTo(aRemainingWeight) == 1){
            maxValue = handleKnapsack(anIndex-1,aRemainingWeight,someChosenItems);
        } else{

            // choice: put the item in the bag
            int sizeBeforeChosen = someChosenItems.size();
            BigDecimal valueIfChosen = currentItem.getValue().add(handleKnapsack(anIndex-1,aRemainingWeight.subtract(currentItem.getWeight()),someChosenItems));


            // choice: don't put the item in the bag
            int sizeBeforeNotChosen = someChosenItems.size();
            BigDecimal valueIfNotChosen = handleKnapsack(anIndex-1,aRemainingWeight,someChosenItems);



            /* determine which choice is more promising. In case of a tie don't add the item to the bag */
            int comparison = valueIfChosen.compareTo(valueIfNotChosen);
            if (comparison == 1) {
                if(someChosenItems.size()>sizeBeforeNotChosen){
                    removeUnwantedItems(someChosenItems,sizeBeforeNotChosen,someChosenItems.size());
                }
                someChosenItems.add(currentItem.getId());
                maxValue = valueIfChosen;
            } else{
                if(sizeBeforeNotChosen > sizeBeforeChosen){
                    removeUnwantedItems(someChosenItems,sizeBeforeChosen,sizeBeforeNotChosen);
                }
                maxValue = valueIfNotChosen;
            }
        }
        return maxValue;
    }

    /**
     * Removes elements from the passed list in the given range.
     * This is a reimplementation of functionality of <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#removeRange-int-int-">ArrayList#removeRange(int, int)</a>
     *
     * @param aListToRemoveFrom list that is to be augmented
     * @param aStartIndex first index from which to remove
     * @param anEndIndex index up to which to remove
     */
    private void removeUnwantedItems(List<Integer> aListToRemoveFrom, int aStartIndex, int anEndIndex){
        int elementsToRemove = anEndIndex-aStartIndex;
        while(elementsToRemove > 0){
            aListToRemoveFrom.remove(aStartIndex);
            elementsToRemove--;
        }
    }

    /**
     * Takes a list containing the indices for a possible solution and replaces items of equal values for lighter ones
     * @param someItemIndices List with a proposed solution for the given problem
     */
    private void optimizeWeights(List<Integer> someItemIndices){
        List<Item> allItems = theProblem.getItems();

        // to easily track if an item is already part of the solution
        Map<Integer,Item> solutionParts = new HashMap<>();
        for (Integer index: someItemIndices) {
            solutionParts.put(index,allItems.get(index-1));
        }
        // check if a part of the solution can be substituted by a better fit
        for(int indexSelected = 0; indexSelected < someItemIndices.size(); indexSelected++){
            Item itemToOptimize = allItems.get(someItemIndices.get(indexSelected)-1);
            BigDecimal minWeight = itemToOptimize.getWeight();

            for (Item currentItem: allItems) {
                // substitute items for equally valuable ones which aren't already part of the solution
                if(!solutionParts.containsKey(currentItem.getId()) && currentItem.getValue().compareTo(itemToOptimize.getValue()) == 0  && currentItem.getWeight().compareTo(minWeight) == -1 ){
                    someItemIndices.set(indexSelected,currentItem.getId());
                    minWeight = currentItem.getWeight();
                }
            }
        }

    }

    /**
     * Print the found solution to the console.
     * @param someSolutionIndices a list with the indices of the chosen items
     */
    private void printItems(List<Integer> someSolutionIndices){
        if(someSolutionIndices.isEmpty()){
            System.out.println("-");
        }else{
            StringJoiner joiner = new StringJoiner(",");
            for (Integer itemIndex: someSolutionIndices) {
                joiner.add(itemIndex.toString());
            }
            joiner.toString();
            System.out.println(joiner.toString());
        }
        System.out.println("");
    }
}
