package edu.bsu.cs;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private static int nextOrderId = 1000;

    private final int orderId;
    private final int tableNumber;
    private final List<String> items;
    private final String specialInstructions;
    private final LocalDateTime timestamp;

    public Order(int tableNumber, List<String> items, String specialInstructions) {
        this.orderId = nextOrderId++;
        this.tableNumber = tableNumber;
        this.items = items;
        this.specialInstructions = specialInstructions;
        this.timestamp = LocalDateTime.now();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public List<String> getItems() {
        return items;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
