package edu.bsu.cs;

public class Order {
    private final String orderInfo;
    private static int orderIdCounter = 0;
    private final int orderId;
    private int elapsedTimeInSeconds;

    public Order(String orderInfo) {
        this.orderInfo = orderInfo;
        this.orderId = ++orderIdCounter;
        this.elapsedTimeInSeconds = 0;
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


}
