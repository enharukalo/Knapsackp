package edu.estu;

import java.util.*;

public class Solver {

    public static Solution solveGreedy(Knapsack knapsackProblem) {
        List<Item> items = knapsackProblem.items().stream()
                .sorted(Comparator.comparing(Item::unitPrice).reversed())
                .toList();

        List<Item> solutionItems = new ArrayList<>();
        long totalWeight = 0;
        for (Item item : items) {
            if (totalWeight + item.weight() <= knapsackProblem.capacity()) {
                solutionItems.add(item);
                totalWeight += item.weight();
            }
        }

        return new Solution(solutionItems, knapsackProblem);
    }

    public static Solution solveRandom(Knapsack knapsackProblem) {
        Random random = new Random();
        List<Item> items = new ArrayList<>(knapsackProblem.items());
        Collections.shuffle(items, random);

        List<Item> solutionItems = new ArrayList<>();
        long totalWeight = 0;
        for (Item item : items) {
            if (totalWeight + item.weight() <= knapsackProblem.capacity()) {
                solutionItems.add(item);
                totalWeight += item.weight();
            }
        }

        return new Solution(solutionItems, knapsackProblem);
    }

    public static Solution solveRandomWithMultipleSolutions(Knapsack knapsackProblem, int numSolutions) {
        Solution bestSolution = null;
        for (int i = 0; i < numSolutions; i++) {
            Solution solution = solveRandom(knapsackProblem);
            if (bestSolution == null || solution.totalProfit() > bestSolution.totalProfit()) {
                bestSolution = solution;
            }
        }

        return bestSolution;
    }
}
