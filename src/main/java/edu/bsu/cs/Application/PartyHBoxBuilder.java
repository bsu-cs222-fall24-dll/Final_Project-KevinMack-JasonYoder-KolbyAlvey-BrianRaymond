package edu.bsu.cs.Application;

import edu.bsu.cs.Party;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PartyHBoxBuilder {
    HBox createPartyHBox(Party party) {
        HBox partyHBox = new HBox(10);
        partyHBox.setPrefHeight(45);
        partyHBox.setUserData(party.getPartyId());

        TextField sizeField = createTextField(String.valueOf(party.getSize()));
        TextField nameField = createTextField(party.getName());
        TextField phoneField = createTextField(party.getPhoneNumber());
        TextField waitTimeField = createTextField(String.valueOf(party.getWaitTime()));

        sizeField.setEditable(false);
        nameField.setEditable(false);
        phoneField.setEditable(false);
        waitTimeField.setEditable(true);

        partyHBox.getChildren().addAll(sizeField, nameField, phoneField, waitTimeField);
        return partyHBox;
    }


    private TextField createTextField(String text) {
        TextField field = new TextField(text);
        field.setAlignment(Pos.CENTER);
        field.setPrefHeight(45);
        field.setStyle("-fx-font-size: 21px");
        return field;
    }

}
