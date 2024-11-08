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
        for (int i = 1; i <= 12; i++) {
            Button tableButton = (Button) tables.lookup("#t" + i);
            int tableNumber = i;
            tableButton.setOnAction(e -> TablesLogic.ChangeState(tableNumber, tableButton));
        }
    }
}
