package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private PartyRegister partyRegister;
    private PartyRemover partyRemover;
    private Parent waitlist;
    private Parent tables;
    private Parent kitchen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));
        StackPane screens = (StackPane) root.lookup("#contentStack");

        waitlist = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("WaitlistScreen.fxml")));
        VBox partyListVBox = (VBox) waitlist.lookup("#partyListVBox");

        tables = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Tables.fxml")));
        kitchen = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Kitchen.fxml")));

        screens.getChildren().addAll(waitlist, tables, kitchen);
        showWaitlist();

        partyRegister = new PartyRegister(partyListVBox);
        partyRemover = new PartyRemover(partyListVBox);

        setupAddGuestButton(waitlist);
        setupRemoveGuestButton(waitlist);
        waitListListener(root);
        tablesListener(root);
        kitchenListener(root);

        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);
        mainStage.show();

        setupTables();
    }

    private void setupAddGuestButton(Parent waitlist) {
        Button addGuestButton = (Button) waitlist.lookup("#addGuestButton");
        addGuestButton.setOnAction(e -> partyRegister.showAddPartyScreen());
    }

    private void setupRemoveGuestButton(Parent waitlist) {
        Button removeGuestButton = (Button) waitlist.lookup("#removeGuestButton");
        removeGuestButton.setOnAction(e -> partyRemover.removeParty());
    }

    private void waitListListener(Parent root) {
        Button waitlistTab = (Button) root.lookup("#waitlistTab");
        waitlistTab.setOnAction(e -> showWaitlist());
    }

    private void tablesListener(Parent root) {
        Button hostTab = (Button) root.lookup("#hostTab");
        hostTab.setOnAction(e -> showTables());
    }

    private void kitchenListener(Parent root) {
        Button kitchenTab = (Button) root.lookup("#kitchenTab");
        kitchenTab.setOnAction(e -> showKitchen());
    }

    private void showWaitlist() {
        waitlist.setVisible(true);
        tables.setVisible(false);
        kitchen.setVisible(false);
    }

    private void showTables() {
        waitlist.setVisible(false);
        tables.setVisible(true);
        kitchen.setVisible(false);
    }

    private void showKitchen() {
        waitlist.setVisible(false);
        tables.setVisible(false);
        kitchen.setVisible(true);
    }

    private void setupTables() {
        setupTableButton(tables.lookup("#t1"), 1);
        setupTableButton(tables.lookup("#t2"), 2);
        setupTableButton(tables.lookup("#t3"), 3);
        setupTableButton(tables.lookup("#t4"), 4);
        setupTableButton(tables.lookup("#t5"), 5);
        setupTableButton(tables.lookup("#t6"), 6);
        setupTableButton(tables.lookup("#t7"), 7);
        setupTableButton(tables.lookup("#t8"), 8);
        setupTableButton(tables.lookup("#t9"), 9);
        setupTableButton(tables.lookup("#t10"), 10);
        setupTableButton(tables.lookup("#t11"), 11);
        setupTableButton(tables.lookup("#t12"), 12);
    }

    private void setupTableButton(Object tableButton, int tableNumber) {
        Button button = (Button) tableButton;
        button.setOnAction(e -> TablesLogic.ChangeState(tableNumber, button));
    }
}
