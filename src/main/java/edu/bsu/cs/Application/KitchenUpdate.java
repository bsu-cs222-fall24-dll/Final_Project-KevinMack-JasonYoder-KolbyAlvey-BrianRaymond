package edu.bsu.cs.Application;

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

    private void fillOrders (Parent kitchen) {
        for (int i = 0; i < Math.min(6, orderList.size()); i++) {
            VBox order = (VBox) kitchen.lookup("#order" + (i + 1) + "VBox");
            TextField ID = (TextField) order.lookup("#order" + (i + 1) + "ID");
            TextArea details = (TextArea) order.lookup("#order" + (i + 1) + "Details");
            ID.setText(String.valueOf(orderList.get(i).getId()));
            details.setText(orderList.get(i).getDetails());
        }
    }

    private void makeOrdersVisible(Parent kitchen) {
        for (int i = 0; i < 6; i++) {
            VBox order = (VBox) kitchen.lookup("#order" + (i + 1) + "VBox");
            TextField ID = (TextField) order.lookup("#order" + (i + 1) + "ID");
            order.setVisible(!ID.getText().isEmpty());
        }
    }

    private void showMoreOrders(Parent kitchen) {
        Button indicator = (Button) kitchen.lookup("#indicator");
        indicator.setVisible(orderList.size() > 6);
    }

}
