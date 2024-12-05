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

        resetOrderUI(index, kitchen);
        orderList.remove(index);

        KitchenUpdate manager = new KitchenUpdate();
        manager.updateOrderScreen(kitchen);
    }


    private void resetOrderUI(int index, Parent kitchen) {
        VBox order = (VBox) kitchen.lookup("#order" + (index + 1) + "VBox");

        TextField timerField = (TextField) order.lookup("#order" + (index + 1) + "Timer");
        TextField IDField = (TextField) order.lookup("#order" + (index + 1) + "ID");
        TextArea detailsField = (TextArea) order.lookup("#order" + (index + 1) + "Details");

        timerField.setText("0:00");

        IDField.clear();
        detailsField.clear();


        order.setVisible(false);

    }
}
