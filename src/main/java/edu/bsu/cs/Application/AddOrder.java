package edu.bsu.cs.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddOrder {

    private static final String[] MENUITEMS = {"Cheeseburger", "Bacon Burger", "Ham and Cheese",
            "Chicken Sandwich", "Cobb Salad", "Chicken Tenders", "Straight Fries",
            "Curly Fries", "Mac n Cheese", "Side Salad", "Fruit Plate", "Applesauce",
            "Fountain Drink", "Lemonade", "Water"};

    public static void showAddOrder() {
        Stage orderStage = new Stage();
        Parent addOrderFXML = fetchFXMLFile();
        Scene orderScreen = new Scene(addOrderFXML);
        orderScreen.getRoot().requestFocus();
        setButtonActions(Objects.requireNonNull(addOrderFXML));
        orderStage.setScene(orderScreen);
        orderStage.setResizable(false);
        orderStage.show();
    }

    private static Parent fetchFXMLFile() {
        try {
            return FXMLLoader.load(Objects.requireNonNull(AddOrder.class.getClassLoader().getResource("AddOrder.fxml")));
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static void setButtonActions(Parent orderScreen) {
        TextArea orderBox = (TextArea) orderScreen.lookup("#orderBox");
        TextField specialInstructionsField = (TextField) orderScreen.lookup("#specialInstructions");

        Button cheeseburgerButton = (Button) orderScreen.lookup("#cheeseburger");
        Button baconBurgerButton = (Button) orderScreen.lookup("#baconBurger");
        Button hamAndCheeseButton = (Button) orderScreen.lookup("#hamAndCheese");
        Button chickenSandwichButton = (Button) orderScreen.lookup("#chickenSandwich");
        Button cobbSaladButton = (Button) orderScreen.lookup("#cobbSalad");
        Button chickenTendersButton = (Button) orderScreen.lookup("#chickenTenders");
        Button straightFriesButton = (Button) orderScreen.lookup("#straightFries");
        Button curlyFriesButton = (Button) orderScreen.lookup("#curlyFries");
        Button macNCheeseButton = (Button) orderScreen.lookup("#macNCheese");
        Button sideSaladButton = (Button) orderScreen.lookup("#sideSalad");
        Button fruitPlateButton = (Button) orderScreen.lookup("#fruitPlate");
        Button applesauceButton = (Button) orderScreen.lookup("#applesauce");
        Button fountainDrinkButton = (Button) orderScreen.lookup("#fountainDrink");
        Button lemonadeButton = (Button) orderScreen.lookup("#lemonade");
        Button waterButton = (Button) orderScreen.lookup("#water");

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

    private static void appendToOrderBox(String foodItem, TextField specialInstructionsField, TextArea orderBox) {
        orderBox.appendText(foodItem + "\n");
        String specialInstructions = specialInstructionsField.getText().trim();
        if (!specialInstructions.isEmpty()) {
            orderBox.appendText("--" + specialInstructions + "\n");
        }
        specialInstructionsField.clear();
    }



}
