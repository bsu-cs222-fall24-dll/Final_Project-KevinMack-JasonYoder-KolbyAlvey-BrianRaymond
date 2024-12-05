package edu.bsu.cs.Application.Host;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class TablesListener {
    Timers timerHandler = new Timers();

    public void toggleTableState(int tableId, String tableType, Button tableButton, Parent tables) {
        String openClass = "open-" + tableType + "-table";
        String occupiedClass = "occupied-" + tableType + "-table";

        TextField timerField = (TextField) tables.lookup("#t" + tableId + "-timer");

        if (tableButton.getStyleClass().contains(openClass)) {
            tableButton.getStyleClass().remove(openClass);
            tableButton.getStyleClass().add(occupiedClass);
            timerHandler.startTimer(tableId, timerField);
        } else {
            tableButton.getStyleClass().remove(occupiedClass);
            tableButton.getStyleClass().add(openClass);
            timerHandler.stopAndResetTimer(tableId, timerField);
        }
    }


}