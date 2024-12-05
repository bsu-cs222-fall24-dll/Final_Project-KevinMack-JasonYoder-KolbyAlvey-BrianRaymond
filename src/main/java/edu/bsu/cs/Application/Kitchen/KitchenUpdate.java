package edu.bsu.cs.Application.Kitchen;

import edu.bsu.cs.Order;
import edu.bsu.cs.SingletonDataStore;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;


public class KitchenUpdate {

    SingletonDataStore data = SingletonDataStore.getInstance();
    List<Order> orderList = data.getOrderList();

    public void updateOrderScreen(Parent kitchen) {
        fillOrders(kitchen);
        makeOrdersVisible(kitchen);
        showMoreOrders(kitchen);


        startRealTimeUpdates(kitchen);
    }

    private void fillOrders(Parent kitchen) {
        int size = Math.min(orderList.size(), 6);
        for (int i = 0; i < 6; i++) {
            VBox order = (VBox) kitchen.lookup("#order" + (i + 1) + "VBox");
            TextField ID = (TextField) order.lookup("#order" + (i + 1) + "ID");
            TextArea details = (TextArea) order.lookup("#order" + (i + 1) + "Details");
            TextField clock = (TextField) order.lookup("#order" + (i + 1) + "Clock");

            if (i < size) {
                Order currentOrder = orderList.get(i);
                ID.setText(String.valueOf(currentOrder.getId()));
                details.setText(currentOrder.getDetails());
                clock.setText(currentOrder.getCreationTime());
                checkOrderTimeout(order, currentOrder);
            } else {
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

    private void startRealTimeUpdates(Parent kitchen) {
        TextField realTimeField = (TextField) kitchen.lookup("#realTimeField");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateRealTime(realTimeField))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Run forever
        timeline.play();
    }

    private void updateRealTime(TextField realTimeField) {

        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("America/New_York"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        String timeString = currentTime.format(formatter);
        realTimeField.setText(timeString);
    }

    private void checkOrderTimeout(VBox order, Order currentOrder) {
        String orderCreationTimeString = currentOrder.getCreationTime();

        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("America/New_York"));

        String fullOrderCreationTimeString = currentTime.toLocalDate() + "T" + orderCreationTimeString + currentTime.getOffset();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        ZonedDateTime orderCreationTime = ZonedDateTime.parse(fullOrderCreationTimeString, formatter);

        long minutesDifference = ChronoUnit.MINUTES.between(orderCreationTime, currentTime);

        if (minutesDifference >= 10) {
            order.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, null)));
        } else {
            order.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
        }
    }
}
