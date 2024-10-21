package edu.bsu.cs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WaitlistGUI extends Application {
    private WaitlistManager waitlistManager;
    private ListView<Party> listView;
    private TextField nameField;
    private TextField sizeField;
    private TextField phoneField;
    private TextField waitTimeField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        waitlistManager = new WaitlistManager();

        listView = new ListView<>();
        listView.getItems().addAll(waitlistManager.getWaitlist());

        nameField = new TextField();
        sizeField = new TextField();
        phoneField = new TextField();
        waitTimeField = new TextField();

        Button addButton = new Button("Add Party");
        addButton.setOnAction(e -> addParty());

        Button removeButton = new Button("Remove Party");
        removeButton.setOnAction(e -> removeParty());

        VBox inputFields = new VBox(10, new Label("Name:"), nameField,
                new Label("Size:"), sizeField,
                new Label("Phone Number:"), phoneField,
                new Label("Wait Time (min):"), waitTimeField,
                addButton, removeButton);

        inputFields.setPadding(new Insets(10));
        inputFields.setPrefWidth(250);

        HBox mainLayout = new HBox(10, listView, inputFields);
        Scene scene = new Scene(mainLayout, 600, 400);

        primaryStage.setTitle("Restaurant Waitlist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addParty() {
        String name = nameField.getText();
        int size = Integer.parseInt(sizeField.getText());
        String phone = phoneField.getText();
        long waitTime = Long.parseLong(waitTimeField.getText());

        Party newParty = new Party(name, size, phone, waitTime);
        waitlistManager.addParty(newParty);
        updateWaitlistDisplay();

        clearFields();
    }

    private void removeParty() {
        Party selectedParty = listView.getSelectionModel().getSelectedItem();
        if (selectedParty != null) {
            waitlistManager.removeParty(selectedParty);
            updateWaitlistDisplay();
        }
    }

    private void updateWaitlistDisplay() {
        listView.getItems().clear();
        listView.getItems().addAll(waitlistManager.getWaitlist());
    }

    private void clearFields() {
        nameField.clear();
        sizeField.clear();
        phoneField.clear();
        waitTimeField.clear();
    }
}
