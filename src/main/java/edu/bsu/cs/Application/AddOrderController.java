package edu.bsu.cs.Application;

import edu.bsu.cs.Order;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddOrderController {
    @FXML
    private TextField tableNumberField;

    @FXML
    private TextArea specialInstructionsField;

    @FXML
    private ListView<String> menuList;

    @FXML
    private Button submitOrderButton;

    @FXML
    private Button cancelButton;

    private KitchenController kitchenController;

    private List<String> menuItems = List.of(
            "Hamburger", "Cheeseburger", "Hot Dog", "Chili Dog", "Ham and Cheese Sliders",
            "Nachos", "French Fries", "Cheese Curds", "Pretzel Bites", "Curly Fries",
            "Coke", "Sprite", "Water", "Root beer", "Lemonade"
    );

    @FXML
    public void initialize() {
        menuList.getItems().addAll(menuItems);
        menuList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        submitOrderButton.setOnAction(event -> submitOrder());
        cancelButton.setOnAction(event -> closePopup());
    }

    public void setKitchenController(KitchenController controller) {
        this.kitchenController = controller;
    }

    private void submitOrder() {
        int tableNumber = Integer.parseInt(tableNumberField.getText());
        List<String> selectedItems = new ArrayList<>(menuList.getSelectionModel().getSelectedItems());
        String specialInstructions = specialInstructionsField.getText();

        Order order = new Order(tableNumber, selectedItems, specialInstructions);
        kitchenController.addOrder(order);
        closePopup();
    }

    private void closePopup() {
        Stage stage = (Stage) submitOrderButton.getScene().getWindow();
        stage.close();
    }
}
