package edu.bsu.cs.Application;

import edu.bsu.cs.Order;
import edu.bsu.cs.FetchFXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddOrder {

    private static final String[] MENUITEMS = {"cheeseburger", "bacon-burger", "ham-and-cheese",
            "chicken-sandwich", "cobb-salad", "chicken-tenders", "straight-fries",
            "curly-fries", "mac-n-cheese", "side-salad", "fruit-plate", "applesauce",
            "fountain-drink", "lemonade", "water"};

    List<Order> listOfOrders = new ArrayList<>();

    public void showAddOrder(Parent kitchenReference) {
        Stage orderStage = new Stage();
        Parent addOrderFXML = FetchFXML.loadFXML("AddOrder.fxml");
        Scene orderScreen = new Scene(addOrderFXML);
        orderScreen.getRoot().requestFocus();
        setButtonActions(Objects.requireNonNull(addOrderFXML));
        orderStage.setScene(orderScreen);
        orderStage.setResizable(false);
        orderStage.show();
        setUpSubmitOrderButton(Objects.requireNonNull(addOrderFXML), orderStage, kitchenReference);
    }

    private void setButtonActions(Parent orderScreen) {
        TextArea orderBox = (TextArea) orderScreen.lookup("#orderBox");
        TextField specialInstructionsField = (TextField) orderScreen.lookup("#specialInstructions");

        for (String item : MENUITEMS) {
            Button itemButton = (Button) orderScreen.lookup("#" + item);
            itemButton.setOnAction(e -> appendToOrderBox(itemButton.getText(), specialInstructionsField, orderBox));
        }
    }

    private void appendToOrderBox(String foodItem, TextField specialInstructionsField, TextArea orderBox) {
        orderBox.appendText(foodItem + "\n");
        String specialInstructions = specialInstructionsField.getText().trim();
        if (!specialInstructions.isEmpty()) {
            orderBox.appendText("--" + specialInstructions + "\n");
        }
        specialInstructionsField.clear();
    }

    private void setUpSubmitOrderButton(Parent orderScreen, Stage orderStage, Parent kitchenReference){
        Button submitOrderButton = (Button) orderScreen.lookup("#submitOrderButton");
        TextArea orderBox = (TextArea) orderScreen.lookup("#orderBox");
        submitOrderButton.setOnAction(e -> {
            Order newOrder = new Order(orderBox.getText());
            VBox orderVBox = setUpNewOrder(newOrder);
            addOrderToScreen(orderVBox, kitchenReference);
            listOfOrders.add(newOrder);
            orderStage.close();
        });
    }

    private VBox setUpNewOrder(Order order) {
        VBox orderBox = (VBox) FetchFXML.loadFXML("BlankOrder.fxml");
        TextField idField = (TextField) Objects.requireNonNull(orderBox).lookup("#id");
        TextArea detailsField = (TextArea) Objects.requireNonNull(orderBox).lookup("#orderDetails");
        idField.setText(String.valueOf(order.getId()));
        detailsField.setText(order.getDetails());
        return orderBox;
    }

    private void addOrderToScreen(VBox orderBox, Parent orderScreen) {
        int length = listOfOrders.size();
        int row, column;
        GridPane pane = (GridPane) orderScreen.lookup("#gridPane");

        if (length < 3) {
            row = 0;
        } else if (length < 6) {
            row = 1;
        } else {
            row = 2;
        }
        column = length % 3;
        if (listOfOrders.size() < 6) {
            pane.add(orderBox, column, row);
        }

    }

}
