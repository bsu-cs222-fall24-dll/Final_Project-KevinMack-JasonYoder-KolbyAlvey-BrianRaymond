package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class WaitlistGUI extends Application {
    private PartyManager partyManager;
    private Phonebook phonebook;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));
        VBox partyListVBOX = (VBox) root.lookup("#partyListVBox");

        phonebook = new Phonebook("restaurantData.csv");
        partyManager = new PartyManager(partyListVBOX, phonebook);

        setupAddGuestButton(root);
        setupRemoveGuestButton(root);

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    private void setupAddGuestButton(Parent root) {
        Button addGuestButton = (Button) root.lookup("#addGuestButton");
        addGuestButton.setOnAction(e -> showAddPartyScreen());
    }

    private void setupRemoveGuestButton(Parent root) {
        Button removeGuestButton = (Button) root.lookup("#removeGuestButton");
        removeGuestButton.setOnAction(e -> partyManager.removeParty());
    }

    private void showAddPartyScreen() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Party");

        TextField sizeField = new TextField();
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField waitTimeField = new TextField();

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> phoneField.setText(formatPhoneNumber(newValue)));
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            String suggestedName = phonebook.getNameByPhoneNumber(newValue);
            nameField.setText(Objects.requireNonNullElse(suggestedName, ""));
        });

        VBox dialogVbox = new VBox(10,
                new Label("Phone Number:"), phoneField,
                new Label("Name:"), nameField,
                new Label("Size:"), sizeField,
                new Label("Wait Time:"), waitTimeField
        );

        dialogVbox.setPadding(new Insets(20));

        Button addButton = new Button("Add");

        addButton.setOnAction(e -> {
            if(isValidPhoneNumber(phoneField.getText())) {
                partyManager.addParty(
                        Integer.parseInt(sizeField.getText()),
                        nameField.getText(), phoneField.getText(),
                        Integer.parseInt(waitTimeField.getText())
                );
                dialog.close();
            } else {
                showPhoneNumberAlert();
            }
        });


        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private String formatPhoneNumber(String input) {
        String cleaned = input.replaceAll("\\D", "");

        if (cleaned.length() > 6) {
            return cleaned.substring(0, 3) + "-" + cleaned.substring(3, 6) + "-" + cleaned.substring(6, Math.min(10, cleaned.length()));
        } else if (cleaned.length() > 3) {
            return cleaned.substring(0, 3) + "-" + cleaned.substring(3);
        } else {
            return cleaned;
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String cleaned = phoneNumber.replaceAll("\\D", "");

        return cleaned.isEmpty() || cleaned.length() == 10;
    }

    private void showPhoneNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Phone Number");
        alert.setHeaderText(null);
        alert.setContentText("Phone number must be left blank or 10 digits long");
        alert.showAndWait();
    }
}

