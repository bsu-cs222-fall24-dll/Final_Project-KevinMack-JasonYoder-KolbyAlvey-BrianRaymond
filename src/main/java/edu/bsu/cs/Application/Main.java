package edu.bsu.cs.Application;

import edu.bsu.cs.FetchFXML;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage){
        Parent root = FetchFXML.loadFXML("MainScene.fxml");
        StackPane screens = (StackPane) Objects.requireNonNull(root).lookup("#contentStack");

        Parent waitlist = FetchFXML.loadFXML("WaitlistScreen.fxml");
        VBox partyListVBox = (VBox) Objects.requireNonNull(waitlist).lookup("#partyListVBox");

        Parent tables = FetchFXML.loadFXML("Tables.fxml");
        Parent kitchen = FetchFXML.loadFXML("Kitchen.fxml");

        screens.getChildren().addAll(waitlist, tables, kitchen);

        ScreenManager screenManager = new ScreenManager(waitlist, tables, kitchen);
        PartyRegister partyRegister = new PartyRegister(partyListVBox);
        PartyRemover partyRemover = new PartyRemover(partyListVBox);

        ButtonManager buttonManager = new ButtonManager(partyRegister, partyRemover);
        buttonManager.setupAddGuestButton(waitlist);
        buttonManager.setupRemoveGuestButton(waitlist);
        buttonManager.setupTableButtons(tables);
        buttonManager.setupAddOrderButton(Objects.requireNonNull(kitchen));

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
