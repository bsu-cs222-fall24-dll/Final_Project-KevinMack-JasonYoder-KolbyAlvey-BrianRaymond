package edu.bsu.cs;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PartyManager {
    private final List<Party> parties = new ArrayList<>();
    private final VBox partyListVBOX;
    private final Phonebook phonebook;

    public PartyManager(VBox partyListVBOX, Phonebook phonebook) {
        this.partyListVBOX = partyListVBOX;
        this.phonebook = phonebook;
    }

    public void addParty(int size, String name, String phoneNumber, int waitTime) {
        CSVHandler handler = new CSVHandler();
        handler.addIfNew(name, phoneNumber);
        Party party = new Party(size, name, phoneNumber, waitTime);
        parties.add(party);
        phonebook.addNewEntry(party.getPhoneNumber(), party.getName());
        parties.sort(Comparator.comparingInt(Party::getWaitTime));
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : parties) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }

    public void removeParty() {
        HashMap<String, Integer> nameToIdMap = new HashMap<>();
        ChoiceDialog<String> dialog = new ChoiceDialog<>();

        for (Party party : parties) {
            String name = party.getName();
            int id = party.getId();
            dialog.getItems().add(name);
            nameToIdMap.put(name, id);
        }

        dialog.setTitle("Remove Party");
        dialog.setHeaderText("Select a party to remove:");
        dialog.showAndWait().ifPresent(selected -> {
            int idToRemove = nameToIdMap.get(selected);
            parties.removeIf(party -> party.getId() == idToRemove);
            updatePartyListDisplay();
        });
    }

    private void updatePartyListDisplay() {
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : parties) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }

    private HBox createPartyHBox(Party party) {
        HBox partyHBox = new HBox(10);
        partyHBox.setPrefHeight(45);
        partyHBox.setUserData(party.getId());

        TextField sizeField = createNonEditableField(String.valueOf(party.getSize()));
        TextField nameField = createNonEditableField(party.getName());
        TextField phoneField = createNonEditableField(party.getPhoneNumber());
        TextField waitTimeField = createNonEditableField(String.valueOf(party.getWaitTime()));

        partyHBox.getChildren().addAll(sizeField, nameField, phoneField, waitTimeField);
        return partyHBox;
    }

    private TextField createNonEditableField(String text) {
        TextField field = new TextField(text);
        field.setEditable(false);
        field.setAlignment(Pos.CENTER);
        field.setPrefHeight(45);
        field.setStyle("-fx-font-size: 21px");
        return field;
    }
}
