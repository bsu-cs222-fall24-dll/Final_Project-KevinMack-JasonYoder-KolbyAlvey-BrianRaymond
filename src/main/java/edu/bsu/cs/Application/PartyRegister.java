package edu.bsu.cs.Application;

import edu.bsu.cs.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
    PartyRegisterInputValidation registerLogic = new PartyRegisterInputValidation();
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

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            String formattedNumber = registerLogic.formatPhoneNumber(newValue);
            if (!newValue.equals(formattedNumber)) {
                phoneField.setText(formattedNumber);
            }
            if (nameField.getText().isEmpty()) {
                String suggestedName = phonebook.getNameByPhoneNumber(formattedNumber);
                nameField.setText(Objects.requireNonNullElse(suggestedName, ""));
            }
        });

        VBox dialogVbox = new VBox(10,
                new Label("Phone Number:"), phoneField,
                new Label("Name:"), nameField,
                new Label("Size:"), sizeField
        );

        dialogVbox.setPadding(new Insets(20));

        Button addButton = new Button("Add");

        addButton.setOnAction(e -> {
            if(checkAddParty(phoneField.getText(), sizeField.getText())) {
                addParty(
                        Integer.parseInt(sizeField.getText()),
                        nameField.getText(), phoneField.getText()
                );
                dialog.close();
            }
        });


        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private boolean checkAddParty(String phoneNumber, String partySize) {
        Alerts alert = new Alerts();
        if(!registerLogic.isValidPhoneNumber(phoneNumber)) {
            alert.showPhoneNumberAlert();
            return false;
        }
        if (registerLogic.isNotRealNumber(partySize)) {
            alert.showInvalidCharacterAlert();
            return false;
        }
        if(registerLogic.isNotInboundInteger(Integer.parseInt(partySize))) {
            alert.showOutOfBoundsIntegerAlert();
            return false;
        }
        return true;
    }

    private void addParty(int size, String name, String phoneNumber) {
        WaitTime calcWaitTime = new WaitTime();
        registerLogic.addPartyToData(size, name, phoneNumber, calcWaitTime.waitTimeAlgorithm());
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : partyList) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }
}
