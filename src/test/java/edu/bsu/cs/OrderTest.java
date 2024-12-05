package edu.bsu.cs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderTest {
    @AfterEach
    public void resetCounter() {
        Order.resetCounter();
    }


    @Test
    public void testGetDetails() {
        Order testOrder = new Order("Burger\nFries\nLemonade");

        Assertions.assertEquals("Burger\nFries\nLemonade", testOrder.getDetails());
    }

    @Test
    public void testGetId() {
        Order testOrder = new Order("Burger\nFries\nLemonade");

        Assertions.assertEquals(1, testOrder.getId());
    }

    @Test
    public void testGetSecondId() {
        /*
        Weak warning for testOrder1 never used is suppressed,
        it's created to test that the id incrementation works as planned.
         */
        Order testOrder1 = new Order("Burger\nFries\nLemonade");
        Order testOrder2 = new Order("Burger\nFries\nWater");

        Assertions.assertEquals(2, testOrder2.getId());
    }

    @Test
    public void testGetCreationTime() {
        Order testOrder = new Order("Burger\nFries\nLemonade");

        long creationTime = System.currentTimeMillis();
        Date date = new Date(Long.parseLong(String.valueOf(creationTime)));
        DateFormat format = new SimpleDateFormat("hh:mm");
        String time = format.format(date);

        Assertions.assertEquals(time, testOrder.getCreationTime());
    }
}
