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

import java.util.Comparator;
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
        Stage dialog = createDialog();
        VBox dialogVbox = createDialogVBox();

        TextField phoneField = new TextField();
        TextField nameField = new TextField();
        TextField sizeField = new TextField();
        TextField[] inputFields = {sizeField, nameField, phoneField};

        setupPhoneFieldListener(phoneField, nameField);

        Button addButton = createAddButton(inputFields, dialog);
        dialogVbox.getChildren().addAll(
                new Label("Phone Number:"), phoneField,
                new Label("Name:"), nameField,
                new Label("Size:"), sizeField,
                addButton
        );

        dialog.setScene(new Scene(dialogVbox));
        dialog.showAndWait();
    }


    private Stage createDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Party");
        dialog.setResizable(false);
        dialog.setWidth(300);
        dialog.setHeight(400);
        return dialog;
    }

    private VBox createDialogVBox() {
        VBox dialogVbox = new VBox(10);
        dialogVbox.setPadding(new Insets(20));
        return dialogVbox;
    }

    private void setupPhoneFieldListener(TextField phoneField, TextField nameField) {
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
    }

    private Button createAddButton(TextField[] inputFields, Stage dialog) {
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            if (checkAddParty(inputFields[2].getText(), inputFields[0].getText())) {
                WaitTime calcWaitTime = new WaitTime();
                Party newParty = new Party(
                        Integer.parseInt(inputFields[0].getText()),
                        inputFields[1].getText(),
                        inputFields[2].getText(),
                        calcWaitTime.waitTimeAlgorithm()
                );
                addParty(newParty);
                dialog.close();
            }
        });
        return addButton;
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

    private void addParty(Party party) {

        partyList.add(party);
        phonebook.addNewEntry(party.getPhoneNumber(), party.getName());
        partyList.sort(Comparator.comparingInt(Party::getWaitTime));
        sortPartyList();
    }
    public void sortPartyList() {
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : partyList) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }
}
