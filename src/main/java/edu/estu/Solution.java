package edu.estu;

import java.util.List;

public class Solution {


    List<Item> items;
    private final long totalProfit;
    private final long totalWeight;
    Knapsack knapsackProblem;

    public Solution(List<Item> items, Knapsack knapsackProblem) {
        this.items = items;
        this.totalProfit = items.stream().mapToLong(Item::profit).sum();
        this.totalWeight = items.stream().mapToLong(Item::weight).sum();
        this.knapsackProblem = knapsackProblem;
    }
    public long totalProfit() {
        return totalProfit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Total Profit: ").append(totalProfit).append("\n");
        sb.append("Total Weight: ").append(totalWeight).append("\n");
        return sb.toString();
    }
}
