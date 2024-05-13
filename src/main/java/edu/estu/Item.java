package edu.estu;

public record Item(long id, long profit, long weight) {
    double unitPrice() {
        return (double) profit/weight;
    }
}