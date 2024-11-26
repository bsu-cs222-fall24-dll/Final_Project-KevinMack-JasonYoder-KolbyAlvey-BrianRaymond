package edu.bsu.cs;


public class Order {
    private final String orderInfo;
    private static int orderIdCounter = 0;
    private final int orderId;

    public Order(String orderInfo){
        this.orderInfo = orderInfo;
        this.orderId = ++orderIdCounter;
    }

    public String getDetails() {
        return orderInfo;
    }

    public static void resetCounter() {
        orderIdCounter = 0;
    }
}