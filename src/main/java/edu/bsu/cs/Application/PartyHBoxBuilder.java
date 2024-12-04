package edu.bsu.cs.Application;

import edu.bsu.cs.Party;
import edu.bsu.cs.SingletonDataStore;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PartyHBoxBuilder {
    private final VBox partyListVBOX;
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Party> partyList = data.getPartyList();

    public PartyHBoxBuilder(VBox partyListVBOX) {
        this.partyListVBOX = partyListVBOX;
    }

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

    protected void sortPartyList() {
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : partyList) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }

}
