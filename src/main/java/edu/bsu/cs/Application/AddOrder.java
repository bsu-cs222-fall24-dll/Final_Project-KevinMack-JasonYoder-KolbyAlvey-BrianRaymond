package edu.bsu.cs.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddOrder {

    private static final String[] MENUITEMS = {"Cheeseburger", "Bacon Burger", "Ham and Cheese",
            "Chicken Sandwich", "Cobb Salad", "Chicken Tenders", "Straight Fries",
            "Curly Fries", "Mac n Cheese", "Side Salad", "Fruit Plate", "Applesauce",
            "Fountain Drink", "Lemonade", "Water"};

    public static void showAddOrder() {
        Stage orderStage = new Stage();
        Parent addOrderFXML = fetchFXMLFile();
        Scene orderScreen = new Scene(addOrderFXML);
        orderScreen.getRoot().requestFocus();
        setButtonActions(Objects.requireNonNull(addOrderFXML));
        orderStage.setScene(orderScreen);
        orderStage.setResizable(false);
        orderStage.show();
    }

    private static Parent fetchFXMLFile() {
        try {
            return FXMLLoader.load(Objects.requireNonNull(AddOrder.class.getClassLoader().getResource("AddOrder.fxml")));
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static void setButtonActions(Parent orderScreen) {
        TextArea orderList = (TextArea) orderScreen.lookup("#orderBox");
        for (String item : MENUITEMS) {
            Button button = new Button(item);
            button.setOnAction(e -> orderList.appendText(item + "\n"));
        }
    }


}
