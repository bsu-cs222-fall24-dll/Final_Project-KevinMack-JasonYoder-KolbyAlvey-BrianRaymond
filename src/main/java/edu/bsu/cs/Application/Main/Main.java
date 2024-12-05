package edu.bsu.cs.Application.Main;

import edu.bsu.cs.Application.Kitchen.KitchenUpdate;
import edu.bsu.cs.Application.Waitlist.PartyRegister;
import edu.bsu.cs.Application.Waitlist.PartyRemover;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainScene.fxml")));
        Parent tables = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Tables.fxml")));
        Parent kitchen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Kitchen.fxml")));
        Parent waitlist = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WaitlistScreen.fxml")));

        StackPane screens = (StackPane) Objects.requireNonNull(root).lookup("#contentStack");
        VBox partyListVBox = (VBox) Objects.requireNonNull(waitlist).lookup("#partyListVBox");

        screens.getChildren().addAll(waitlist, tables, kitchen);

        ScreenManager screenManager = new ScreenManager(waitlist, tables, kitchen);
        PartyRegister partyRegister = new PartyRegister(partyListVBox);
        PartyRemover partyRemover = new PartyRemover(partyListVBox);
        ButtonManager buttonManager = new ButtonManager(partyRegister, partyRemover);
        TabListener tabListener = new TabListener(screenManager);
        KitchenUpdate update = new KitchenUpdate();

        buttonManager.setupAddGuestButton(waitlist);
        buttonManager.setupRemoveGuestButton(waitlist);
        buttonManager.setupTableButtons(tables);
        buttonManager.setupAddOrderButton(Objects.requireNonNull(kitchen));
        buttonManager.setupClearOrderButtons(Objects.requireNonNull(kitchen));

        tabListener.setupWaitlistListener(root);
        tabListener.setupTablesListener(root);
        tabListener.setupKitchenListener(root);

        update.updateOrderScreen(kitchen);

        screenManager.showWaitlist();

        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);
        mainStage.show();
    }


}
