package edu.estu;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class KnapsackSolver {
    /* TODO: Dynamic programming approach
     * This method solves the 0-1 knapsack problem using dynamic programming.
     * It iterates over each item and for each item, it iterates over all possible weights from the knapsack's capacity down to the item's weight.
     * For each weight, it calculates the maximum profit that can be obtained by either not including the current item or including it.
     * The profit of including the current item is calculated as the profit of the current item plus the profit of the remaining weight from the previous items.
     * The maximum profit is stored in a dynamic programming table (dp array) and the maximum profit that can be obtained with the knapsack's capacity is returned.
     *
     * @param knapsack The knapsack problem instance.
     * @return The maximum profit that can be obtained.
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
     */
    /**
     * This helper method calculates the maximum profit that can be obtained with the given items
     * and the capacity of the knapsack.
     * It iterates over the items and for each item, if the item's weight is less than or equal to the
     * remaining capacity of the knapsack, it includes the item in the knapsack and
     * decreases the remaining capacity by the item's weight.
     * The method is used by the solveGreedy and solveRandom methods to avoid code duplication.
     *
     * @param items The items to be included in the knapsack.
     * @param capacity The capacity of the knapsack.
     * @return The maximum profit that can be obtained.
     */
    private List<Item> capacitatedSubset(List<Item> items, long capacity) {
        List<Item> usedItems = new ArrayList<>();
        for (Item item : items) {
            if (item.weight() <= capacity) {
                capacity -= item.weight();
                usedItems.add(item);
            }
        }
        return usedItems;
    }

    private List<Item> capacitatedSubsetUsingStream(List<Item> items, long capacity) {
        AtomicLong atomicCapacity = new AtomicLong(capacity);
        List<Item> usedItems = new ArrayList<>();

                 items.stream()
                        .sorted(Comparator.comparingDouble(Item::unitPrice).reversed())
                .filter(item -> item.weight() <= atomicCapacity.get())
                .forEachOrdered(item -> {
                    atomicCapacity.addAndGet(-item.weight());
                    usedItems.add(item);
                });
                       // .peek(i -> atomicCapacity.addAndGet(-i.weight())).collect(Collectors.toList());


       return usedItems;
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
        return capacitatedSubset(items, knapsack.capacity()).stream().mapToLong(Item::profit).sum();
    }

    public long solveGreedy2(Knapsack knapsack) {
        List<Item> items = new ArrayList<>(knapsack.items());
        items.sort(Comparator.comparingDouble(item -> -item.profit() / (double) item.weight()));
        return capacitatedSubsetUsingStream(items, knapsack.capacity()).stream().mapToLong(Item::profit).sum();
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
        return capacitatedSubset(items, knapsack.capacity()).stream().mapToLong(Item::profit).sum();
    }
    public long solveRandom2(Knapsack knapsack) {
        List<Item> items = new ArrayList<>(knapsack.items());
        Collections.shuffle(items);
        return capacitatedSubsetUsingStream(items, knapsack.capacity()).stream().mapToLong(Item::profit).sum();
    }
    public long solveRandomWithMultipleSolutions(Knapsack knapsack, long numOfSolutions) {
        long maxProfit = 0;
        for (long i = 0; i < numOfSolutions; i++) {
            long profit = solveRandom(knapsack);
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
    public long solveRandomWithMultipleSolutionsParallel(Knapsack knapsack, long numOfSolutions) {
        return LongStream.range(0, numOfSolutions)
                .parallel()
                .map(i -> solveRandom(knapsack))
                .max()
                .orElse(0);
    }
}