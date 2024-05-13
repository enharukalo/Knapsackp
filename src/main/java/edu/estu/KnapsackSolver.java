package edu.estu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KnapsackSolver {
    /**
     * This method solves the 0-1 knapsack problem using dynamic programming.
     * It iterates over each item and for each item, it iterates over all possible weights from the knapsack's capacity down to the item's weight.
     * For each weight, it calculates the maximum profit that can be obtained by either not including the current item or including it.
     * The profit of including the current item is calculated as the profit of the current item plus the profit of the remaining weight from the previous items.
     * The maximum profit is stored in a dynamic programming table (dp array) and the maximum profit that can be obtained with the knapsack's capacity is returned.
     *
     * @param knapsack The knapsack problem instance.
     * @return The maximum profit that can be obtained.
     */
    public long solveDynamic(Knapsack knapsack) {
        List<Item> items = knapsack.items();
        long capacity = knapsack.capacity();
        long[] dp = new long[(int) capacity + 1];

        for (Item item : items) {
            for (long w = capacity; w >= item.weight(); w--) {
                dp[(int) w] = Math.max(dp[(int) w], item.profit() + dp[(int)(w - item.weight())]);
            }
        }

        return dp[(int) capacity];
    }
    /**
     * This helper method calculates the maximum profit that can be obtained with the given items and the capacity of the knapsack.
     * It iterates over the items and for each item, if the item's weight is less than or equal to the remaining capacity of the knapsack, it includes the item in the knapsack and decreases the remaining capacity by the item's weight.
     * The method is used by the solveGreedy and solveRandom methods to avoid code duplication.
     *
     * @param items The items to be included in the knapsack.
     * @param capacity The capacity of the knapsack.
     * @return The maximum profit that can be obtained.
     */
    private long solveWithGivenItems(List<Item> items, long capacity) {
        long profit = 0;
        for (Item item : items) {
            if (item.weight() <= capacity) {
                profit += item.profit();
                capacity -= item.weight();
            }
        }
        return profit;
    }
    /**
     * This method solves the 0-1 knapsack problem using a greedy algorithm.
     * It sorts the items by their profit-to-weight ratio in descending order.
     * Then, it iterates over the sorted items and for each item, if the item's weight is less than or equal to the remaining capacity of the knapsack, it includes the item in the knapsack and decreases the remaining capacity by the item's weight.
     * The method uses the solveWithGivenItems helper method to calculate the maximum profit.
     *
     * @param knapsack The knapsack problem instance.
     * @return The maximum profit that can be obtained.
     */
    public long solveGreedy(Knapsack knapsack) {
        List<Item> items = new ArrayList<>(knapsack.items());
        items.sort(Comparator.comparingDouble(item -> -item.profit() / (double) item.weight()));
        return solveWithGivenItems(items, knapsack.capacity());
    }
    /**
     * This method solves the 0-1 knapsack problem using a random algorithm.
     * It shuffles the items and then iterates over the shuffled items and for each item, if the item's weight is less than or equal to the remaining capacity of the knapsack, it includes the item in the knapsack and decreases the remaining capacity by the item's weight.
     * The method uses the solveWithGivenItems helper method to calculate the maximum profit.
     *
     * @param knapsack The knapsack problem instance.
     * @return The maximum profit that can be obtained.
     */
    public long solveRandom(Knapsack knapsack) {
        List<Item> items = new ArrayList<>(knapsack.items());
        Collections.shuffle(items);
        return solveWithGivenItems(items, knapsack.capacity());
    }
}