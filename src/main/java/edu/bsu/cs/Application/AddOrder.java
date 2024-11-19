package edu.bsu.cs.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddOrder {

    public static void showAddOrder() {
        Stage orderStage = new Stage();
        Parent addOrder = fetchFXMLFile();
        Scene orderScreen = new Scene(addOrder);
        orderScreen.getRoot().requestFocus();
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
}
