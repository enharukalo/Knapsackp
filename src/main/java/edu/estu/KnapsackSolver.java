package edu.estu;

import java.util.List;

public class KnapsackSolver {
    public int solve(Knapsack knapsack) {
        List<Item> items = knapsack.items();
        int n = items.size();
        int capacity = knapsack.capacity();
        int[] dp = new int[capacity + 1];

        for (Item item : items) {
            for (int w = capacity; w >= item.weight(); w--) {
                dp[w] = Math.max(dp[w], item.profit() + dp[w - item.weight()]);
            }
        }

        return dp[capacity];
    }
}