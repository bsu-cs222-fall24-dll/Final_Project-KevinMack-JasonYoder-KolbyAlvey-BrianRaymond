package edu.bsu.cs.Application;

import edu.bsu.cs.FetchFXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Objects;

public class AddOrder {

    private static final String[] MENUITEMS = {"cheeseburger", "bacon-burger", "ham-and-cheese",
            "chicken-sandwich", "cobb-salad", "chicken-tenders", "straight-fries",
            "curly-fries", "mac-n-cheese", "side-salad", "fruit-plate", "applesauce",
            "fountain-drink", "lemonade", "water"};

    public void showAddOrder() {
        Stage orderStage = new Stage();
        Parent addOrderFXML = FetchFXML.loadFXML("AddOrder.fxml");
        Scene orderScreen = new Scene(addOrderFXML);
        orderScreen.getRoot().requestFocus();
        setButtonActions(Objects.requireNonNull(addOrderFXML));
        orderStage.setScene(orderScreen);
        orderStage.setResizable(false);
        orderStage.show();
    }

    private void setButtonActions(Parent orderScreen) {
        TextArea orderBox = (TextArea) orderScreen.lookup("#orderBox");
        TextField specialInstructionsField = (TextField) orderScreen.lookup("#specialInstructions");

        for (String item : MENUITEMS) {
            Button itemButton = (Button) orderScreen.lookup("#" + item);
            itemButton.setOnAction(e -> appendToOrderBox(itemButton.getText(), specialInstructionsField, orderBox));
        }

        Button submitButton = (Button) orderScreen.lookup("#submitOrderButton");
        submitButton.setOnAction(e -> submitOrder(orderBox));
    }

    private void appendToOrderBox(String foodItem, TextField specialInstructionsField, TextArea orderBox) {
        orderBox.appendText(foodItem + "\n");
        String specialInstructions = specialInstructionsField.getText().trim();
        if (!specialInstructions.isEmpty()) {
            orderBox.appendText("--" + specialInstructions + "\n");
        }
        specialInstructionsField.clear();
    }

    private void submitOrder(TextArea orderBox) {
        String order = orderBox.getText();
        Parent newOrder = FetchFXML.loadFXML("BlankOrder.fxml");
    }



}
