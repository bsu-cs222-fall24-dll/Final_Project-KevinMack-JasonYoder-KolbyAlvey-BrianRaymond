package edu.bsu.cs.Application.Kitchen;

import edu.bsu.cs.Order;
import edu.bsu.cs.SingletonDataStore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AddOrder {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Order> orderList = data.getOrderList();

    private static final String[] MENUITEMS = {"cheeseburger", "bacon-burger", "ham-and-cheese",
            "chicken-sandwich", "cobb-salad", "chicken-tenders", "straight-fries",
            "curly-fries", "mac-n-cheese", "side-salad", "fruit-plate", "applesauce",
            "fountain-drink", "lemonade", "water"};


    public void showAddOrder() {
        try {
            Stage orderStage = new Stage();
            Parent addOrderFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddOrder.fxml")));
            Scene orderScreen = new Scene(addOrderFXML);
            orderScreen.getRoot().requestFocus();
            setButtonActions(Objects.requireNonNull(addOrderFXML), orderStage);
            orderStage.setScene(orderScreen);
            orderStage.setResizable(false);
            orderStage.showAndWait();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void setButtonActions(Parent orderScreen, Stage orderStage) {
        TextArea orderBox = (TextArea) orderScreen.lookup("#orderBox");
        TextField specialInstructionsField = (TextField) orderScreen.lookup("#specialInstructions");
        Button submitOrderButton = (Button) orderScreen.lookup("#submitOrderButton");

        for (String item : MENUITEMS) {
            Button itemButton = (Button) orderScreen.lookup("#" + item);
            itemButton.setOnAction(e -> appendToOrderBox(itemButton.getText(), specialInstructionsField, orderBox));
        }

        submitOrderButton.setOnAction(e -> {
            Order newOrder = new Order(orderBox.getText());
            orderList.add(newOrder);
            orderStage.close();
        });
    }

    private void appendToOrderBox(String foodItem, TextField specialInstructionsField, TextArea orderBox) {
        orderBox.appendText(foodItem + "\n");
        String specialInstructions = specialInstructionsField.getText().trim();
        if (!specialInstructions.isEmpty()) {
            orderBox.appendText("--" + specialInstructions + "\n");
        }
        specialInstructionsField.clear();
    }

}
