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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PartyRegister extends PartyHBoxBuilder {
    private final VBox partyListVBOX;
    private final List<Party> partyList;
    private final Phonebook phonebook;

    public PartyRegister(VBox partyListVBOX, Phonebook phonebook, List<Party> partyList) {
        this.partyListVBOX = partyListVBOX;
        this.phonebook = phonebook;
        this.partyList = partyList;
    }

    public void showAddPartyScreen() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Party");

        TextField sizeField = new TextField();
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField waitTimeField = new TextField();

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> phoneField.setText(formatPhoneNumber(newValue)));
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
            if(isValidPhoneNumber(phoneField.getText())) {
                addParty(
                        Integer.parseInt(sizeField.getText()),
                        nameField.getText(), phoneField.getText(),
                        Integer.parseInt(waitTimeField.getText())
                );

                dialog.close();
            } else {
                showPhoneNumberAlert();
            }
        });


        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private String formatPhoneNumber(String input) {
        String cleaned = input.replaceAll("\\D", "");

        if (cleaned.length() > 6) {
            return cleaned.substring(0, 3) + "-" + cleaned.substring(3, 6) + "-" + cleaned.substring(6, Math.min(10, cleaned.length()));
        } else if (cleaned.length() > 3) {
            return cleaned.substring(0, 3) + "-" + cleaned.substring(3);
        } else {
            return cleaned;
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String cleaned = phoneNumber.replaceAll("\\D", "");

        return cleaned.isEmpty() || cleaned.length() == 10;
    }

    private void showPhoneNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Phone Number");
        alert.setHeaderText(null);
        alert.setContentText("Phone number must be left blank or 10 digits long");
        alert.showAndWait();
    }

    public void addParty(int size, String name, String phoneNumber, int waitTime) {
        Party party = new Party(size, name, phoneNumber, waitTime);
        partyList.add(party);
        phonebook.addNewEntry(party.getPhoneNumber(), party.getName());
        partyList.sort(Comparator.comparingInt(Party::getWaitTime));
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : partyList) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }
}
