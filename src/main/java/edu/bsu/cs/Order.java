package edu.bsu.cs;

public class Order {
    private final String orderInfo;
    private static int orderIdCounter = 0;
    private int orderId;
    private int elapsedTimeInSeconds;  // Timer in seconds
    private double milliTime;

    public Order(String orderInfo) {
        this.milliTime = System.currentTimeMillis();
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

    public double getMilliTime(){
        return this.milliTime;
    }


}
