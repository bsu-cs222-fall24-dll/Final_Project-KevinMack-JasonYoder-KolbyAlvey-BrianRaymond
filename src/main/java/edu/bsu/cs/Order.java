package edu.bsu.cs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private final String orderDetails;
    private static int orderIdCounter = 0;
    private final int orderId;
    private final long creationTime;

    public Order(String orderInfo) {
        this.orderDetails = orderInfo;
        this.orderId = ++orderIdCounter;
        this.creationTime = System.currentTimeMillis();
    }

    public String getDetails() {
        return orderDetails;
    }

    public int getId() {
        return orderId;
    }

    public String getCreationTime() {
        Date date = new Date(Long.parseLong(String.valueOf(creationTime)));
        DateFormat format = new SimpleDateFormat("hh:mm");
        return format.format(date);
    }

    public static void resetCounter() {
        orderIdCounter = 0;
    }

}
