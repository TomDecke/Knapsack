package de.knapsack.controller;

import de.knapsack.model.Item;
import de.knapsack.model.KnapsackProblem;

import java.math.BigDecimal;
import java.util.*;

/**
 * Class to find a solution for an instance of the knapsack problem.
 */
public class KnapsackSolver {

    KnapsackProblem theProblem;


    /**
     * Contains the logic to solve and print a solution to a knapsack-problem.
     * @param aKnapsackProblem the problem to solve
     */
    public void solve(KnapsackProblem aKnapsackProblem){
       theProblem = aKnapsackProblem;
       ArrayList<Integer> selectedItems = new ArrayList<>();
       printSolution(aKnapsackProblem);
       BigDecimal maxValue = determineMaxValue(aKnapsackProblem.getItems().size()-1,aKnapsackProblem.getBag().getMaxWeight(),selectedItems);
       optimizeWeights(selectedItems);

       System.out.println(maxValue);
        for (Integer i: selectedItems
             ) {
            System.out.println(i);
        }
    }

    /**
     * Determine the maximum achievable value for a given item for a remaining weight.
     * @param anIndex           the index of the item to consider
     * @param aRemainingWeight the remaining weight of the bag
     * @return
     */
    private BigDecimal determineMaxValue(int anIndex, BigDecimal aRemainingWeight, ArrayList<Integer> someChosenItems){

        BigDecimal maxValue = BigDecimal.ZERO;

        /* If the end of the array or the weight limit is reached stop */
        if(anIndex == -1 || BigDecimal.ZERO.compareTo(aRemainingWeight)==0){
            return maxValue;
        }

        Item currentItem = theProblem.getItems().get(anIndex);
        if(currentItem.getWeight().compareTo(aRemainingWeight) == 1){
            maxValue = determineMaxValue(anIndex-1,aRemainingWeight,someChosenItems);
        } else{

            // put the item in the bag
            int sizeBeforeChosen = someChosenItems.size();
            BigDecimal valueIfChosen = currentItem.getCost().add(determineMaxValue(anIndex-1,aRemainingWeight.subtract(currentItem.getWeight()),someChosenItems));


            // don't put the item in the bag
            int sizeBeforeNotChosen = someChosenItems.size();
            BigDecimal valueIfNotChosen = determineMaxValue(anIndex-1,aRemainingWeight,someChosenItems);



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
     * @param aListToRemoveFrom
     * @param aStartIndex
     * @param anEndIndex
     */
    private void removeUnwantedItems(List<Integer> aListToRemoveFrom, int aStartIndex, int anEndIndex){
        int elementsToRemove = anEndIndex-aStartIndex;
        while(elementsToRemove > 0){
            aListToRemoveFrom.remove(aStartIndex);
            elementsToRemove--;
        }
    }

    private void optimizeWeights(List<Integer> someItemIndices){
        List<Item> allItems = theProblem.getItems();
        Map<Integer,Item> solutionParts = new HashMap<>();
        for (Integer index: someItemIndices) {
            solutionParts.put(index,allItems.get(index-1));
        }
        for(int indexSelected = 0; indexSelected < someItemIndices.size(); indexSelected++){
            Item itemToOptimize = allItems.get(someItemIndices.get(indexSelected)-1);
            BigDecimal minWeight = itemToOptimize.getWeight();

            for (Item currentItem: allItems) {
                if(!solutionParts.containsKey(currentItem.getId()) && currentItem.getCost().compareTo(itemToOptimize.getCost()) == 0  && currentItem.getWeight().compareTo(minWeight) == -1 ){
                    someItemIndices.set(indexSelected,currentItem.getId());
                    minWeight = currentItem.getWeight();
                }
            }
        }

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
