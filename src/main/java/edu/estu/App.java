package edu.estu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
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

        // Create the knapsack and the solver
        Knapsack knapsack = new Knapsack(capacity, items);
        KnapsackSolver solver = new KnapsackSolver();

        // Check for the trivial case
        if (knapsack.isTrivial()) {
            System.out.println("The problem instance is trivial. All items can be put into the knapsack.");
        } else {
            System.out.println("The problem instance is not trivial. Not all items can be put into the knapsack.");
        }

        // Solve the problem using different methods and print the results
        long startTime = System.nanoTime();
        long profit = solver.solveGreedy(knapsack);
        long endTime = System.nanoTime();
        System.out.println("The maximum profit using the greedy algorithm is " + profit);
        System.out.println("Running time: " + (endTime - startTime) / 1_000_000 + " milliseconds");

        startTime = System.nanoTime();
        profit = solver.solveRandom(knapsack);
        endTime = System.nanoTime();
        System.out.println("The maximum profit using a random solution is " + profit);
        System.out.println("Running time: " + (endTime - startTime) / 1_000_000 + " milliseconds");

        /*
        startTime = System.nanoTime();
        profit = solver.solveDynamic(knapsack);
        endTime = System.nanoTime();
        System.out.println("The maximum profit using dynamic programming is " + profit);
        System.out.println("Running time: " + (endTime - startTime) / 1_000_000 + " milliseconds");
      */
    }

}