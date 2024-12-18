package edu.bsu.cs.Application.Waitlist;

import edu.bsu.cs.Party;
import edu.bsu.cs.SingletonDataStore;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class PartyRemover extends PartyHBoxBuilder {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Party> partyList = data.getPartyList();

    public PartyRemover(VBox partyListVBOX) {
        super(partyListVBOX);
    }

    public void removeParty() {
        HashMap<String, Integer> nameToIdMap = new HashMap<>();
        ChoiceDialog<String> dialog = new ChoiceDialog<>();

        for (Party party : partyList) {
            String name = party.getName();
            int id = party.getPartyId();
            dialog.getItems().add(name);
            nameToIdMap.put(name, id);
        }

        dialog.setTitle("Remove Party");
        dialog.setHeaderText("Select a party to remove:");
        dialog.showAndWait().ifPresent(selected -> {
            int idToRemove = nameToIdMap.get(selected);
            partyList.removeIf(party -> party.getPartyId() == idToRemove);
            sortPartyList();
        });
    }

}
