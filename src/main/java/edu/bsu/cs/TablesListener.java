package edu.bsu.cs;

import javafx.scene.control.Button;

public class TablesListener {

    public void toggleTableState(String tableType, Button tableButton) {
        String openClass = "open-" + tableType + "-table";
        String occupiedClass = "occupied-" + tableType + "-table";

        if (tableButton.getStyleClass().contains(openClass)) {
            tableButton.getStyleClass().remove(openClass);
            tableButton.getStyleClass().add(occupiedClass);
        } else {
            tableButton.getStyleClass().remove(occupiedClass);
            tableButton.getStyleClass().add(openClass);
        }
    }
}