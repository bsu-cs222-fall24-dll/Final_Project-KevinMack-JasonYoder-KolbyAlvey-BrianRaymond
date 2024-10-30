package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private PartyRegister partyRegister;
    private PartyRemover partyRemover;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));
        VBox partyListVBox = (VBox) root.lookup("#partyListVBox");

        partyRegister = new PartyRegister(partyListVBox);
        partyRemover = new PartyRemover(partyListVBox);

        setupAddGuestButton(root);
        setupRemoveGuestButton(root);

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    private void setupAddGuestButton(Parent root) {
        Button addGuestButton = (Button) root.lookup("#addGuestButton");
        addGuestButton.setOnAction(e -> partyRegister.showAddPartyScreen());
    }

    private void setupRemoveGuestButton(Parent root) {
        Button removeGuestButton = (Button) root.lookup("#removeGuestButton");
        removeGuestButton.setOnAction(e -> partyRemover.removeParty());
    }


}

