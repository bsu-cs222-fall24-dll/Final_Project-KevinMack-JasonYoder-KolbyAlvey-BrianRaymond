package edu.bsu.cs;

import javafx.scene.Parent;

public class ScreenManager {
    private final Parent waitlist;
    private final Parent tables;
    private final Parent kitchen;

    public ScreenManager(Parent waitlist, Parent tables, Parent kitchen) {
        this.waitlist = waitlist;
        this.tables = tables;
        this.kitchen = kitchen;
    }

    public void showWaitlist() {
        waitlist.setVisible(true);
        tables.setVisible(false);
        kitchen.setVisible(false);
    }

    public void showTables() {
        waitlist.setVisible(false);
        tables.setVisible(true);
        kitchen.setVisible(false);
    }

    public void showKitchen() {
        waitlist.setVisible(false);
        tables.setVisible(false);
        kitchen.setVisible(true);
    }
}
