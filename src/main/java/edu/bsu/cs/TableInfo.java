package edu.bsu.cs;

import javafx.scene.control.ChoiceDialog;

import java.util.HashMap;

public class TableInfo {

    static String[] TableState = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};

    public static void ChangeState(int TableNumber){
        ChoiceDialog<String> dialog = new ChoiceDialog<>();

        dialog.getItems().add("Empty");
        dialog.getItems().add("Seated");
        dialog.getItems().add("Ordered");
        dialog.getItems().add("Eating");
        dialog.getItems().add("Dirty");

        dialog.setTitle("Table State");
        dialog.setHeaderText("Select the new state of the table:");
        dialog.showAndWait().ifPresent(selected -> {
            TableState[TableNumber - 1] = selected;

            System.out.println("Table Number " + TableNumber + " is now " + TableState[TableNumber - 1]);
        });
    }
}