package edu.estu;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("test.in"));
             PrintWriter timeWriter = new PrintWriter(new FileWriter("time.out"))) {

            int n = Integer.parseInt(reader.readLine());
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] line = reader.readLine().split(" ");
                int id = Integer.parseInt(line[0]);
                int profit = Integer.parseInt(line[1]);
                int weight = Integer.parseInt(line[2]);
                items.add(new Item(id, profit, weight));
            }
            int capacity = Integer.parseInt(reader.readLine());

            // solve problem
            Knapsack knapsack = new Knapsack(capacity, items);
            KnapsackSolver solver = new KnapsackSolver();

            long startTime = System.nanoTime();
            int maxProfit = solver.solve(knapsack);
            long endTime = System.nanoTime();

            // write output
            try (PrintWriter writer = new PrintWriter(new FileWriter("outp.out"))) {
                writer.println(maxProfit);
            }

            // write time to time.out
            long elapsedTime = endTime - startTime;
            double seconds = (double) elapsedTime / 1_000_000_000.0;
            timeWriter.println(seconds);

        } catch (IOException e) {
            try (PrintWriter timeWriter = new PrintWriter(new FileWriter("time.out"))) {
                timeWriter.println("-1");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}