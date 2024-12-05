package edu.bsu.cs;

public class Order {
    private final String orderInfo;
    private static int orderIdCounter = 0;
    private int orderId;
    private int elapsedTimeInSeconds;  // Timer in seconds

    public Order(String orderInfo) {
        this.orderInfo = orderInfo;
        this.orderId = ++orderIdCounter;
        this.elapsedTimeInSeconds = 0;  // Initialize timer to 0
    }

    public String getDetails() {
        return orderInfo;
    }

    public int getId() {
        return orderId;
    }

    public int getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public void incrementTime() {
        this.elapsedTimeInSeconds++;
    }

    public void resetTime() {
        this.elapsedTimeInSeconds = 0;
    }

    public void setId(int id) {
        this.orderId = id;
    }

    // Decrease the counter after an order is removed
    public static void decreaseCounter() {
        if (orderIdCounter > 0) {
            orderIdCounter--;
        }
    }
}
