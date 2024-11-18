package edu.bsu.cs.Application;

import edu.bsu.cs.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class KitchenController {
    @FXML
    private VBox ordersContainer;

    @FXML
    private Button addOrderButton;

    private List<Order> orders = new ArrayList<>();

    @FXML
    public void initialize() {
        addOrderButton.setOnAction(event -> openAddOrderPopup());
    }

    private void openAddOrderPopup() {
        // Load the popup FXML (AddOrder.fxml) and show it.
        // Add order to the list and display it in the container.
    }

    public void addOrder(Order order) {
        orders.add(order);
        // Create a visual representation of the order and add to the `ordersContainer`
        // Include cancel and complete buttons
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        // Remove the visual representation from `ordersContainer`
    }
}
