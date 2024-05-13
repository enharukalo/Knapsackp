package edu.estu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Read the problem instance from the file
        Scanner scanner = new Scanner(new File("test.in"));
        int n = scanner.nextInt();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long id = scanner.nextLong();
            long profit = scanner.nextLong();
            long weight = scanner.nextLong();
            items.add(new Item(id, profit, weight));
        }
        long capacity = scanner.nextLong();
        scanner.close();

        Knapsack knapsackProblem = new Knapsack(capacity, items);

        Solution greedySolution = Solver.solveGreedy(knapsackProblem);
        System.out.println("Greedy solution: " + greedySolution.toString());

        Solution randomSolution = Solver.solveRandom(knapsackProblem);
        System.out.println("Random solution: " + randomSolution.toString());

        int numSolutions = 1000000;
        Solution randomMultipleSolution = Solver.solveRandomWithMultipleSolutions(knapsackProblem, numSolutions);
        System.out.println("Random multiple solutions (" + numSolutions + "): "+ randomMultipleSolution.toString());
    }
}
