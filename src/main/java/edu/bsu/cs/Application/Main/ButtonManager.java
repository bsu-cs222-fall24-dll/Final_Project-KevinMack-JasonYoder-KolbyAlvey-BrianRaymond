package edu.bsu.cs.Application.Main;


import edu.bsu.cs.Application.Host.TablesListener;
import edu.bsu.cs.Application.Kitchen.AddOrder;
import edu.bsu.cs.Application.Kitchen.KitchenUpdate;
import edu.bsu.cs.Application.Kitchen.RemoveOrder;
import edu.bsu.cs.Application.Waitlist.PartyRegister;
import edu.bsu.cs.Application.Waitlist.PartyRemover;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.util.HashMap;
import java.util.Map;

public class ButtonManager {
    private final PartyRegister partyRegister;
    private final PartyRemover partyRemover;

    private static final Map<Integer, String> TABLE_TYPES = new HashMap<>();

    static {
        TABLE_TYPES.put(1, "round");
        TABLE_TYPES.put(2, "round");
        TABLE_TYPES.put(3, "round");
        TABLE_TYPES.put(4, "square");
        TABLE_TYPES.put(5, "square");
        TABLE_TYPES.put(6, "square");
        TABLE_TYPES.put(7, "square");
        TABLE_TYPES.put(8, "square");
        TABLE_TYPES.put(9, "booth");
        TABLE_TYPES.put(10, "booth");
        TABLE_TYPES.put(11, "booth");
        TABLE_TYPES.put(12, "long");
    }

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

        TABLE_TYPES.forEach((tableId, tableType) -> {
            Button tableButton = (Button) tables.lookup("#t" + tableId);
            if (tableButton != null) {
                tableButton.setOnAction(e -> listener.toggleTableState(tableId, tableType, tableButton, tables));
                tableButton.setFocusTraversable(false);
            }
        });
    }

    public void setupAddOrderButton(Parent kitchen) {
        Button addOrderButton = (Button) kitchen.lookup("#addOrderButton");
        AddOrder orderScreen = new AddOrder();
        KitchenUpdate manager = new KitchenUpdate();
        addOrderButton.setOnAction(e -> {
            orderScreen.showAddOrder();
            manager.updateOrderScreen(kitchen);
        });
    }

    public void setupClearOrderButtons(Parent kitchen) {
        RemoveOrder remover = new RemoveOrder();
        KitchenUpdate manager = new KitchenUpdate();

        for (int i = 1; i < 7; i++) {
            Button clearOrderButton = (Button) kitchen.lookup("#order" + i + "Clear");
            int index = i - 1;
            clearOrderButton.setOnAction(e -> {
                remover.removeOrder(index, kitchen);
                manager.updateOrderScreen(kitchen);
            });
        }
    }

}
