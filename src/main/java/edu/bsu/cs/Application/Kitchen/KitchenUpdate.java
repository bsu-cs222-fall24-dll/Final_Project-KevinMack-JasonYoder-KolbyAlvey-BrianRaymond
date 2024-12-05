package edu.bsu.cs.Application.Kitchen;

import edu.bsu.cs.Order;
import edu.bsu.cs.SingletonDataStore;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

public class KitchenUpdate {
    SingletonDataStore data = SingletonDataStore.getInstance();
    List<Order> orderList = data.getOrderList();

    private Timeline timeline;

    public void updateOrderScreen(Parent kitchen) {
        fillOrders(kitchen);
        makeOrdersVisible(kitchen);
        showMoreOrders(kitchen);
        startOrderTimers(kitchen);
    }

    private void fillOrders(Parent kitchen) {
        for (int i = 0; i < 6; i++) {
            VBox order = (VBox) kitchen.lookup("#order" + (i + 1) + "VBox");
            TextField ID = (TextField) order.lookup("#order" + (i + 1) + "ID");
            TextArea details = (TextArea) order.lookup("#order" + (i + 1) + "Details");

            if (i < orderList.size()) {
                Order currentOrder = orderList.get(i);
                if (i == 0 || isOrderVisible(kitchen, i)) {
                    ID.setText(String.valueOf(currentOrder.getId()));
                    details.setText(currentOrder.getDetails());
                } else {
                    ID.clear();
                    details.clear();
                }
            } else {
                ID.clear();
                details.clear();
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

    private boolean isOrderVisible(Parent kitchen, int index) {
        VBox previousOrder = (VBox) kitchen.lookup("#order" + (index) + "VBox");
        TextField previousID = (TextField) previousOrder.lookup("#order" + (index) + "ID");
        return !previousID.getText().isEmpty() && previousOrder.isVisible();
    }

    private void showMoreOrders(Parent kitchen) {
        Button indicator = (Button) kitchen.lookup("#indicator");
        indicator.setVisible(orderList.size() > 6);
    }

    // Start the timer for each order
    private void startOrderTimers(Parent kitchen) {
        if (timeline != null) {
            timeline.stop();
            timeline.getKeyFrames().removeAll();
            timeline.playFromStart();
        } else {
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);

        }

        for (Order order : orderList) {
            VBox orderBox = (VBox) kitchen.lookup("#order" + (orderList.indexOf(order) + 1) + "VBox");
            TextField timerField = (TextField) orderBox.lookup("#order" + (orderList.indexOf(order) + 1) + "Timer");

            if (orderBox.isVisible() && order.getElapsedTimeInSeconds() == 0) {
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
                    updateTimerDisplay(order, timerField);
                }));
            }
        }

        timeline.play();
    }

    private void updateTimerDisplay(Order order, TextField timerField) {
        double currentTime = System.currentTimeMillis();
        System.out.println("current time: " + currentTime + "| order time: " + order.getMilliTime());
        String timeString = String.format("%.2f", currentTime - order.getMilliTime());
        timerField.setText(timeString);
    }
}
