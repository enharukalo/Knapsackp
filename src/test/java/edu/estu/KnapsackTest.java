package edu.estu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnapsackTest {
    @Test
    public void testIsTrivial() {
        List<Item> items = Arrays.asList(new Item(1, 3, 8), new Item(2, 2, 8), new Item(3, 9, 1));
        Knapsack knapsack = new Knapsack(10, items);
        assertFalse(knapsack.isTrivial()); // The total weight of all items is greater than the capacity

        knapsack = new Knapsack(20, items);
        assertTrue(knapsack.isTrivial()); // The total weight of all items is less than the capacity
    }
}