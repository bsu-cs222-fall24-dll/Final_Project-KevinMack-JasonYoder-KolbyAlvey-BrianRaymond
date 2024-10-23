package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));
        VBox partyListVBOX = (VBox) root.lookup("#partyListVBox");
        partyManager = new PartyManager(partyListVBOX);

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

        VBox dialogVbox = new VBox(10, new Label("Size:"), sizeField, new Label("Name:"), nameField,
                new Label("Phone Number:"), phoneField, new Label("Wait Time:"), waitTimeField);
        dialogVbox.setPadding(new Insets(20));

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            partyManager.addParty(sizeField.getText(), nameField.getText(), phoneField.getText(), waitTimeField.getText());
            dialog.close();
        });

        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}

