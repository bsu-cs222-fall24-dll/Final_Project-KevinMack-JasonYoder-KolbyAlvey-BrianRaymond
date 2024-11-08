package edu.bsu.cs;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public class ButtonManager {
    private final PartyRegister partyRegister;
    private final PartyRemover partyRemover;

    public ButtonManager(PartyRegister partyRegister, PartyRemover partyRemover) {
        this.partyRegister = partyRegister;
        this.partyRemover = partyRemover;
    }

    public void setupAddGuestButton(Parent waitlist) {
        Button addGuestButton = (Button) waitlist.lookup("#addGuestButton");
        addGuestButton.setOnAction(e -> partyRegister.showAddPartyScreen());
    }

    public void setupRemoveGuestButton(Parent waitlist) {
        Button removeGuestButton = (Button) waitlist.lookup("#removeGuestButton");
        removeGuestButton.setOnAction(e -> partyRemover.removeParty());
    }

    public void setupTableButtons(Parent tables) {
        TablesListener listener = new TablesListener();

        for (int i = 1; i < 4; i++) {
            Button tableButton = (Button) tables.lookup("#t" + i);
            String tableType = "round";
            tableButton.setOnAction(e -> listener.setButtonListener(tableType, tableButton));
            tableButton.setFocusTraversable(false);
        }

        for (int i = 4; i < 9; i++) {
            Button tableButton = (Button) tables.lookup("#t" + i);
            String tableType = "square";
            tableButton.setOnAction(e -> listener.setButtonListener(tableType, tableButton));
            tableButton.setFocusTraversable(false);
        }

        for (int i = 9; i < 12; i++) {
            Button tableButton = (Button) tables.lookup("#t" + i);
            String tableType = "booth";
            tableButton.setOnAction(e -> listener.setButtonListener(tableType, tableButton));
            tableButton.setFocusTraversable(false);
        }

        Button tableButton = (Button) tables.lookup("#t12");
        String tableType = "long";
        tableButton.setOnAction(e -> listener.setButtonListener(tableType, tableButton));
        tableButton.setFocusTraversable(false);
    }
}
