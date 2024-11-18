package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {


    @Test
    public void testOrderCreation() {
        Order order1 = new Order(5, List.of("Hamburger", "French Fries"), "No pickles");
        Order order2 = new Order(6, List.of("Cheeseburger", "Sprite"), "Extra cheese");

        assertEquals(5, order1.getTableNumber());
        assertEquals(2, order1.getItems().size());
        assertTrue(order1.getItems().contains("Hamburger"));
        assertEquals("No pickles", order1.getSpecialInstructions());
        assertNotNull(order1.getTimestamp());

        assertEquals(1000, order1.getOrderId());
        assertEquals(1001, order2.getOrderId());
    }
}
