package edu.estu;

import java.util.List;

public record Knapsack(int capacity, List<Item> items) {
    public boolean isTrivial() {
        int totalWeight = items.stream().mapToInt(Item::weight).sum();
        return totalWeight <= capacity;
    }
}