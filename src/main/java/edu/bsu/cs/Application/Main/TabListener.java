package edu.bsu.cs.Application.Main;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public class TabListener {
    private final ScreenManager screenManager;

    public TabListener(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public void setupWaitlistListener(Parent root) {
        Button waitlistTab = (Button) root.lookup("#waitlistTab");
        waitlistTab.setOnAction(e -> screenManager.showWaitlist());
    }

    public void setupTablesListener(Parent root) {
        Button hostTab = (Button) root.lookup("#hostTab");
        hostTab.setOnAction(e -> screenManager.showTables());
    }

    public void setupKitchenListener(Parent root) {
        Button kitchenTab = (Button) root.lookup("#kitchenTab");
        kitchenTab.setOnAction(e -> screenManager.showKitchen());
    }
}
