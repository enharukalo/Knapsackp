package edu.estu;

import java.util.List;

public record Knapsack(long capacity, List<Item> items) {
    public boolean isTrivial() {
        long totalWeight = items.stream().mapToLong(Item::weight).sum();
        return totalWeight <= capacity;
    }
}