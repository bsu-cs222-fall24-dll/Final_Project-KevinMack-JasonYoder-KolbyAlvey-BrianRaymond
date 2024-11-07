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
        Button TableOne = (Button) tables.lookup("#t1");
        TableOne.setOnAction(e -> TableInfo.ChangeState(1));
        Button TableTwo = (Button) tables.lookup("#t2");
        TableTwo.setOnAction(e -> TableInfo.ChangeState(2));
        Button TableThree = (Button) tables.lookup("#t3");
        TableThree.setOnAction(e -> TableInfo.ChangeState(3));
        Button TableFour = (Button) tables.lookup("#t4");
        TableFour.setOnAction(e -> TableInfo.ChangeState(4));
        Button TableFive = (Button) tables.lookup("#t5");
        TableFive.setOnAction(e -> TableInfo.ChangeState(5));
        Button TableSix = (Button) tables.lookup("#t6");
        TableSix.setOnAction(e -> TableInfo.ChangeState(6));
        Button TableSeven = (Button) tables.lookup("#t7");
        TableSeven.setOnAction(e -> TableInfo.ChangeState(7));
        Button TableEight = (Button) tables.lookup("#t8");
        TableEight.setOnAction(e -> TableInfo.ChangeState(8));
        Button TableNine = (Button) tables.lookup("#t9");
        TableNine.setOnAction(e -> TableInfo.ChangeState(9));
        Button TableTen = (Button) tables.lookup("#t10");
        TableTen.setOnAction(e -> TableInfo.ChangeState(10));
        Button TableEleven = (Button) tables.lookup("#t11");
        TableEleven.setOnAction(e -> TableInfo.ChangeState(11));
        Button TableTwelve = (Button) tables.lookup("#t12");
        TableTwelve.setOnAction(e -> TableInfo.ChangeState(12));
    }

}

