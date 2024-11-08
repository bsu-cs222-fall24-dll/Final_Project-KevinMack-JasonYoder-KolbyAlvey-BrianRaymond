package edu.bsu.cs;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

public class TablesLogic {
    static String[] TableState = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};

    public static void ChangeState(int tableNumber, Button button) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>();
        dialog.getItems().add("Empty");
        dialog.getItems().add("In Use");

        dialog.setTitle("Table State");
        dialog.setHeaderText("Select the new state of the table:");
        dialog.showAndWait().ifPresent(selected -> {
            TableState[tableNumber - 1] = selected;

            if (selected.equals("Empty")) {
                button.setStyle("-fx-background-color: green; -fx-background-radius: 65; -fx-pref-width: 130; -fx-pref-height: 130;");
            } else if (selected.equals("In Use")) {
                button.setStyle("-fx-background-color: #CF0000; -fx-background-radius: 65; -fx-pref-width: 130; -fx-pref-height: 130;");
            }
        });
    }
}