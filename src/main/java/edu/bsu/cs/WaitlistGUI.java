package edu.bsu.cs;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class WaitlistGUI extends Application {
    private VBox partyListVBOX;

    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));

        partyListVBOX = (VBox) root.lookup("#partyListVBox");

        Button addGuestButton = (Button) root.lookup("#addGuestButton");
        addGuestButton.setOnAction(e -> showAddPartyScreen());
        Button removeGuestButton = (Button) root.lookup("#removeGuestButton");

        removeGuestButton.setOnAction(e -> removeSelectedParty(partyListVBOX));

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    private void removeSelectedParty(Pane container) {
        ObservableList<Node> children = container.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof HBox hbox && hbox.getStyle().contains("lightblue")) {
                children.remove(hbox);
                return;
            }
        }
        showAlert();
    }
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("No party selected to remove.");
        alert.showAndWait();
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
            addPartyToList(sizeField.getText(), nameField.getText(), phoneField.getText(), waitTimeField.getText());
            dialog.close();
        });

        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private void addPartyToList(String size, String name, String phoneNumber, String waitTime) {
        HBox partyHBox = new HBox(10);
        partyHBox.setPrefHeight(45);

        TextField sizeField = new TextField(size);
        sizeField.setAlignment(Pos.CENTER);
        sizeField.setPrefHeight(45);
        sizeField.setPrefWidth(120);
        sizeField.setStyle("-fx-font-size: 21px");
        sizeField.setEditable(false);

        TextField nameField = new TextField(name);
        nameField.setAlignment(Pos.CENTER);
        nameField.setPrefHeight(45);
        nameField.setPrefWidth(262);
        nameField.setStyle("-fx-font-size: 21px");
        nameField.setEditable(false);

        TextField phoneField = new TextField(phoneNumber);
        phoneField.setAlignment(Pos.CENTER);
        phoneField.setPrefHeight(45);
        phoneField.setPrefWidth(198);
        phoneField.setStyle("-fx-font-size: 21px");
        phoneField.setEditable(false);

        TextField waitTimeField = new TextField(waitTime);
        waitTimeField.setAlignment(Pos.CENTER);
        waitTimeField.setPrefHeight(45);
        waitTimeField.setPrefWidth(149);
        waitTimeField.setStyle("-fx-font-size: 21px");
        waitTimeField.setEditable(false);

        partyHBox.getChildren().addAll(sizeField, nameField, phoneField, waitTimeField);

        partyHBox.setStyle("-fx-background-color: transparent");

        sizeField.focusedProperty().addListener((obs, oldVal, newVal) -> updatedSelectionState(partyHBox, newVal));
        nameField.focusedProperty().addListener((obs, oldVal, newVal) -> updatedSelectionState(partyHBox, newVal));
        phoneField.focusedProperty().addListener((obs, oldVal, newVal) -> updatedSelectionState(partyHBox, newVal));
        waitTimeField.focusedProperty().addListener((obs, oldVal, newVal) -> updatedSelectionState(partyHBox, newVal));

        partyListVBOX.getChildren().add(partyHBox);
    }

    private void updatedSelectionState(HBox hbox, boolean isSelected) {
        if (isSelected) {
            hbox.setStyle("-fx-background-color: lightblue");
        } else {
            hbox.setStyle("-fx-background-color: transparent");
        }
    }
}
