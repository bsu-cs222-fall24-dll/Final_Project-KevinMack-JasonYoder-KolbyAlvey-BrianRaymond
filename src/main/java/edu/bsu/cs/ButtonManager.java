package edu.bsu.cs;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

        if (addOrderButton != null) {
            addOrderButton.setOnAction(e -> AddOrder.showAddOrder());
        } else {
            System.out.println("addOrderButton not found in Kitchen.fxml");
        }
    }

    public void setupFoodButtons(Parent addOrder) {

        TextArea orderBox = (TextArea) addOrder.lookup("#orderBox");
        TextField specialInstructionsField = (TextField) addOrder.lookup("#specialInstructions");

        Button cheeseburgerButton = (Button) addOrder.lookup("#cheeseburger");
        Button baconBurgerButton = (Button) addOrder.lookup("#baconBurger");
        Button hamAndCheeseButton = (Button) addOrder.lookup("#hamAndCheese");
        Button chickenSandwichButton = (Button) addOrder.lookup("#chickenSandwich");
        Button cobbSaladButton = (Button) addOrder.lookup("#cobbSalad");
        Button chickenTendersButton = (Button) addOrder.lookup("#chickenTenders");
        Button straightFriesButton = (Button) addOrder.lookup("#straightFries");
        Button curlyFriesButton = (Button) addOrder.lookup("#curlyFries");
        Button macNCheeseButton = (Button) addOrder.lookup("#macNCheese");
        Button sideSaladButton = (Button) addOrder.lookup("#sideSalad");
        Button fruitPlateButton = (Button) addOrder.lookup("#fruitPlate");
        Button applesauceButton = (Button) addOrder.lookup("#applesauce");
        Button fountainDrinkButton = (Button) addOrder.lookup("#fountainDrink");
        Button lemonadeButton = (Button) addOrder.lookup("#lemonade");
        Button waterButton = (Button) addOrder.lookup("#water");

        cheeseburgerButton.setOnAction(e -> appendToOrderBox("Cheeseburger", specialInstructionsField, orderBox));
        baconBurgerButton.setOnAction(e -> appendToOrderBox("Bacon Burger", specialInstructionsField, orderBox));
        hamAndCheeseButton.setOnAction(e -> appendToOrderBox("Ham and Cheese", specialInstructionsField, orderBox));
        chickenSandwichButton.setOnAction(e -> appendToOrderBox("Chicken Sandwich", specialInstructionsField, orderBox));
        cobbSaladButton.setOnAction(e -> appendToOrderBox("Cobb Salad", specialInstructionsField, orderBox));
        chickenTendersButton.setOnAction(e -> appendToOrderBox("Chicken Tenders", specialInstructionsField, orderBox));
        straightFriesButton.setOnAction(e -> appendToOrderBox("Straight Fries", specialInstructionsField, orderBox));
        curlyFriesButton.setOnAction(e -> appendToOrderBox("Curly Fries", specialInstructionsField, orderBox));
        macNCheeseButton.setOnAction(e -> appendToOrderBox("Mac n Cheese", specialInstructionsField, orderBox));
        sideSaladButton.setOnAction(e -> appendToOrderBox("Side Salad", specialInstructionsField, orderBox));
        fruitPlateButton.setOnAction(e -> appendToOrderBox("Fruit Plate", specialInstructionsField, orderBox));
        applesauceButton.setOnAction(e -> appendToOrderBox("Applesauce", specialInstructionsField, orderBox));
        fountainDrinkButton.setOnAction(e -> appendToOrderBox("Fountain Drink", specialInstructionsField, orderBox));
        lemonadeButton.setOnAction(e -> appendToOrderBox("Lemonade", specialInstructionsField, orderBox));
        waterButton.setOnAction(e -> appendToOrderBox("Water", specialInstructionsField, orderBox));
    }

    private void appendToOrderBox(String foodItem, TextField specialInstructionsField, TextArea orderBox) {
        orderBox.appendText(foodItem + "\n");
        String specialInstructions = specialInstructionsField.getText().trim();
        if (!specialInstructions.isEmpty()) {
            orderBox.appendText("Special Instructions: " + specialInstructions + "\n");
        }
        specialInstructionsField.clear();
    }
}
