package edu.bsu.cs.Application;

import edu.bsu.cs.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KitchenController {

    @FXML
    private VBox ordersContainer;

    @FXML
    private Button addOrderButton;

    private final List<Order> orders = new ArrayList<>();

    @FXML
    public void initialize() {
        addOrderButton.setOnAction(event -> openAddOrderPopup());
    }

    private void openAddOrderPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("AddOrder.fxml")));
            Parent addOrderRoot = loader.load();

            AddOrderController addOrderController = loader.getController();
            addOrderController.setKitchenController(this);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add Order");
            popupStage.setScene(new Scene(addOrderRoot));
            popupStage.show();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load AddOrder.fxml", e);
        }
    }

    public void addOrder(Order order) {
        orders.add(order);

        HBox orderBox = new HBox();
        orderBox.setSpacing(10);
        orderBox.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");

        Text orderIdText = new Text("Order ID: " + order.getOrderId());
        Text tableNumberText = new Text("Table: " + order.getTableNumber());
        Text itemsText = new Text("Items: " + String.join(", ", order.getItems()));
        Text instructionsText = new Text("Instructions: " + order.getSpecialInstructions());
        Text timerText = new Text("Timer: 0:00"); // Placeholder for timer logic


        Button completeButton = new Button("Complete");
        Button cancelButton = new Button("Cancel");


        completeButton.setOnAction(event -> removeOrder(order));
        cancelButton.setOnAction(event -> removeOrder(order));


        orderBox.getChildren().addAll(orderIdText, tableNumberText, itemsText, instructionsText, timerText, completeButton, cancelButton);


        ordersContainer.getChildren().add(orderBox);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        refreshOrders();
    }

    private void refreshOrders() {
        ordersContainer.getChildren().clear();
        for (Order order : orders) {
            addOrder(order);
        }
    }
}
