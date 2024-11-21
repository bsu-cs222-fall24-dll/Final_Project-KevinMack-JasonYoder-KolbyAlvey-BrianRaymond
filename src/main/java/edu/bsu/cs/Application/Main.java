package edu.bsu.cs.Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));
        StackPane screens = (StackPane) root.lookup("#contentStack");

        Parent waitlist = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("WaitlistScreen.fxml")));
        VBox partyListVBox = (VBox) waitlist.lookup("#partyListVBox");

        Parent tables = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Tables.fxml")));
        Parent kitchen = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Kitchen.fxml")));

        screens.getChildren().addAll(waitlist, tables, kitchen);

        ScreenManager screenManager = new ScreenManager(waitlist, tables, kitchen);
        PartyRegister partyRegister = new PartyRegister(partyListVBox);
        PartyRemover partyRemover = new PartyRemover(partyListVBox);

        ButtonManager buttonManager = new ButtonManager(partyRegister, partyRemover);
        buttonManager.setupAddGuestButton(waitlist);
        buttonManager.setupRemoveGuestButton(waitlist);
        buttonManager.setupTableButtons(tables);
        buttonManager.setupAddOrderButton(kitchen);

        TabListener tabListener = new TabListener(screenManager);
        tabListener.setupWaitlistListener(root);
        tabListener.setupTablesListener(root);
        tabListener.setupKitchenListener(root);

        screenManager.showWaitlist();

        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);
        mainStage.show();
    }
}
