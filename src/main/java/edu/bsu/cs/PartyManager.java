package edu.bsu.cs;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PartyManager {
    private final List<Party> parties = new ArrayList<>();
    private final VBox partyListVBOX;

    public PartyManager(VBox partyListVBOX) {
        this.partyListVBOX = partyListVBOX;
    }

    public void addParty(int size, String name, String phoneNumber, int waitTime) {
        Party party = new Party(size, name, phoneNumber, waitTime);
        parties.add(party);
        parties.sort(Comparator.comparingInt(Party::getWaitTime));
        partyListVBOX.getChildren().clear();
        for (Party sortedParty : parties) {
            HBox partyHBox = createPartyHBox(sortedParty);
            partyHBox.setUserData(sortedParty.getName());
            partyListVBOX.getChildren().add(partyHBox);
        }
    }

    public void removeParty() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>();
        for (Party party : parties) {
            dialog.getItems().add(party.getName());
        }
        dialog.setTitle("Remove Party");
        dialog.setHeaderText("Select a party to remove:");
        dialog.showAndWait().ifPresent(selectedName -> {
            parties.removeIf(party -> party.getName().equals(selectedName));
            removePartyFromList(selectedName);
        });
    }

    private void removePartyFromList(String selectedName) {
        ObservableList<Node> children = partyListVBOX.getChildren();
        children.removeIf(node -> {
            if (node instanceof HBox hbox) {
                Object userData = hbox.getUserData();
                return userData != null && userData.equals(selectedName);
            }
            return false;
        });
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
