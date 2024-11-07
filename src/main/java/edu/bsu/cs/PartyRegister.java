package edu.bsu.cs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;
import java.util.Objects;

public class PartyRegister extends PartyHBoxBuilder {
    PartyRegisterLogic registerLogic = new PartyRegisterLogic();
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Party> partyList = data.getPartyList();
    private final Phonebook phonebook = data.getPhonebook();
    private final VBox partyListVBOX;

    public PartyRegister(VBox partyListVBOX) {
        this.partyListVBOX = partyListVBOX;
    }

    public void showAddPartyScreen() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Party");

        TextField sizeField = new TextField();
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField waitTimeField = new TextField();

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> phoneField.setText(registerLogic.formatPhoneNumber(newValue)));
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            String suggestedName = phonebook.getNameByPhoneNumber(newValue);
            nameField.setText(Objects.requireNonNullElse(suggestedName, ""));
        });

        VBox dialogVbox = new VBox(10,
                new Label("Phone Number:"), phoneField,
                new Label("Name:"), nameField,
                new Label("Size:"), sizeField,
                new Label("Wait Time:"), waitTimeField
        );

        dialogVbox.setPadding(new Insets(20));

        Button addButton = new Button("Add");

        addButton.setOnAction(e -> {
            if(!registerLogic.isValidPhoneNumber(phoneField.getText())) {
                showPhoneNumberAlert();
            } else if (registerLogic.isNotRealNumber(sizeField.getText()) || registerLogic.isNotRealNumber(waitTimeField.getText())) {
                showInvalidCharacterAlert();
            } else if(registerLogic.isNotInboundInteger(Integer.parseInt(sizeField.getText())) || registerLogic.isNotInboundInteger(Integer.parseInt(waitTimeField.getText()))) {
                showOutOfBoundsIntegerAlert();
            } else {
                addParty(
                        Integer.parseInt(sizeField.getText()),
                        nameField.getText(), phoneField.getText(),
                        Integer.parseInt(waitTimeField.getText())
                );
                dialog.close();
            }
        });


        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private void addParty(int size, String name, String phoneNumber, int waitTime) {
        registerLogic.addPartyToData(size, name, phoneNumber, waitTime);
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : partyList) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }

    private void showPhoneNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Phone Number");
        alert.setHeaderText(null);
        alert.setContentText("Phone number must be left blank or 10 digits long.");
        alert.showAndWait();
    }

    private void showOutOfBoundsIntegerAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Number entered too large");
        alert.setHeaderText(null);
        alert.setContentText("No numbers larger than 75 are allowed.");
        alert.showAndWait();
    }

    private void showInvalidCharacterAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Character");
        alert.setHeaderText(null);
        alert.setContentText("Only digits are allowed.");
        alert.showAndWait();
    }

}
