package edu.bsu.cs.Application.Kitchen;

import edu.bsu.cs.Order;
import edu.bsu.cs.SingletonDataStore;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class KitchenUpdate {
    SingletonDataStore data = SingletonDataStore.getInstance();
    List<Order> orderList = data.getOrderList();

    public void updateOrderScreen(Parent kitchen) {
        fillOrders(kitchen);
        makeOrdersVisible(kitchen);
        showMoreOrders(kitchen);
    }

    private void fillOrders(Parent kitchen) {
        int size = Math.min(orderList.size(), 6); // Determine how many orders to display
        for (int i = 0; i < 6; i++) {
            VBox order = (VBox) kitchen.lookup("#order" + (i + 1) + "VBox");
            TextField ID = (TextField) order.lookup("#order" + (i + 1) + "ID");
            TextArea details = (TextArea) order.lookup("#order" + (i + 1) + "Details");
            TextField clock = (TextField) order.lookup("#order" + (i + 1) + "Clock");

            if (i < size) {
                // Populate the slot with the order's details
                Order currentOrder = orderList.get(i);
                ID.setText(String.valueOf(currentOrder.getId()));
                details.setText(currentOrder.getDetails());
                clock.setText(currentOrder.getCreationTime());
            } else {
                // Clear the slot if no order corresponds to it
                ID.clear();
                details.clear();
                clock.clear();
            }
        }
    }

    private void makeOrdersVisible(Parent kitchen) {
        for (int i = 0; i < 6; i++) {
            VBox order = (VBox) kitchen.lookup("#order" + (i + 1) + "VBox");
            TextField ID = (TextField) order.lookup("#order" + (i + 1) + "ID");

            boolean isVisible = !ID.getText().isEmpty();
            order.setVisible(isVisible);
        }
    }


    private void showMoreOrders(Parent kitchen) {
        Button indicator = (Button) kitchen.lookup("#indicator");
        indicator.setVisible(orderList.size() > 6);
    }

}
