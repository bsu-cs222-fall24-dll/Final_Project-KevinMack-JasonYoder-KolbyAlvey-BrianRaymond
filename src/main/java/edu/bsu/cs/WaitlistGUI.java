package edu.bsu.cs;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WaitlistGUI extends Application {
    private VBox partyListVBOX;
    private final List<PartyID> partyIDs = new ArrayList<>();

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
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>();
        for (PartyID party : partyIDs) {
            dialog.getItems().add(party.getId());
        }
        dialog.setTitle("Remove Party");
        dialog.setHeaderText("Select a party to remove:");
        dialog.showAndWait().ifPresent(selectedId -> {
            partyIDs.removeIf(party -> party.getId() == selectedId);
            ObservableList<Node> children = container.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Node node = children.get(i);
                if (node instanceof HBox hbox) {
                    Object userData = hbox.getUserData();
                    if (userData != null && userData.equals(selectedId)) {
                        children.remove(i);
                        break;
                    }
                }
            }
        });
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
        PartyID party = new PartyID(size, name, phoneNumber, waitTime);
        partyIDs.add(party);

        HBox partyHBox = createPartyHBox(party);
        partyListVBOX.getChildren().add(partyHBox);
    }

    private HBox createPartyHBox(PartyID party) {
        HBox partyHBox = new HBox(10);
        partyHBox.setPrefHeight(45);
        partyHBox.setUserData(party.getId());

        TextField sizeField = createNonEditableField(party.getSize());
        TextField nameField = createNonEditableField(party.getName());
        TextField phoneField = createNonEditableField(party.getPhoneNumber());
        TextField waitTimeField = createNonEditableField(party.getWaitTime());

        partyHBox.getChildren().addAll(sizeField, nameField, phoneField, waitTimeField);

        return partyHBox;
    }

    private TextField createNonEditableField(String text) {
        TextField field = new TextField(text);
        field.setEditable(false);
        field.setAlignment(Pos.CENTER);
        field.setPrefHeight(45);
        field.setStyle("-fx-font-size: 21px");
        return field;
    }
}
