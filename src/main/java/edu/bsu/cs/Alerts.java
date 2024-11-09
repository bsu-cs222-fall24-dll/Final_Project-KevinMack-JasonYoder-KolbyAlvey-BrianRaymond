package edu.bsu.cs;

import javafx.scene.control.Alert;

public class Alerts {
    public void showPhoneNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Phone Number");
        alert.setHeaderText(null);
        alert.setContentText("Phone number must be left blank or 10 digits long.");
        alert.showAndWait();
    }

    public void showOutOfBoundsIntegerAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Number entered too large");
        alert.setHeaderText(null);
        alert.setContentText("No numbers larger than 75 are allowed.");
        alert.showAndWait();
    }

    public void showInvalidCharacterAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Character");
        alert.setHeaderText(null);
        alert.setContentText("Only digits are allowed.");
        alert.showAndWait();
    }
}
