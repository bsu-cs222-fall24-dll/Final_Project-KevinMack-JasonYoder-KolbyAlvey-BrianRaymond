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

        TextField timerField = (TextField) order.lookup("#order" + (index + 1) + "Timer");
        TextField IDField = (TextField) order.lookup("#order" + (index + 1) + "ID");
        TextArea detailsField = (TextArea) order.lookup("#order" + (index + 1) + "Details");

        orderList.get(index).;
        timerField.setText("0:00");
        IDField.clear();
        detailsField.clear();


        order.setVisible(false);

    }
}
