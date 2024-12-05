package edu.bsu.cs.Application.Kitchen;

import edu.bsu.cs.Order;
import edu.bsu.cs.SingletonDataStore;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class RemoveOrder {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Order> orderList = data.getOrderList();

    public void removeOrder(int index, Parent kitchen) {
        if (index < 0 || index >= orderList.size()) {
            return;
        }


        orderList.remove(index);


        Order.decreaseCounter();


        reassignOrderIds();


        KitchenUpdate manager = new KitchenUpdate();
        manager.updateOrderScreen(kitchen);


        resetOrderUI(index, kitchen);
    }

    private void reassignOrderIds() {
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);

            order.setId(i + 1);
        }
    }

    private void resetOrderUI(int index, Parent kitchen) {
        VBox order = (VBox) kitchen.lookup("#order" + (index + 1) + "VBox");
        if (order != null) {
            TextField timerField = (TextField) order.lookup("#order" + (index + 1) + "Timer");


            if (timerField != null) {
                timerField.setText("0:00");
            }


            TextField IDField = (TextField) order.lookup("#order" + (index + 1) + "ID");
            if (IDField != null) {
                IDField.clear();
            }


            TextArea detailsField = (TextArea) order.lookup("#order" + (index + 1) + "Details");
            if (detailsField != null) {
                detailsField.clear();
            }


            order.setVisible(false);
        }
    }
}
