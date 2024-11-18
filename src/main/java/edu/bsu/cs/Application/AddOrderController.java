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

    private final List<String> menuItems = List.of(
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
        try {

            String tableInput = tableNumberField.getText().trim();
            if (tableInput.isEmpty()) {
                showAlert("Invalid Input", "Table number is required.");
                return;
            }

            int tableNumber = Integer.parseInt(tableInput);
            if (tableNumber <= 0) {
                showAlert("Invalid Input", "Table number must be greater than 0.");
                return;
            }


            List<String> selectedItems = new ArrayList<>(menuList.getSelectionModel().getSelectedItems());
            if (selectedItems.isEmpty()) {
                showAlert("Invalid Input", "You must select at least one menu item.");
                return;
            }


            String specialInstructions = specialInstructionsField.getText().trim();


            Order order = new Order(tableNumber, selectedItems, specialInstructions);
            kitchenController.addOrder(order);

            closePopup();

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Table number must be a valid number.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void closePopup() {
        Stage stage = (Stage) submitOrderButton.getScene().getWindow();
        stage.close();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}