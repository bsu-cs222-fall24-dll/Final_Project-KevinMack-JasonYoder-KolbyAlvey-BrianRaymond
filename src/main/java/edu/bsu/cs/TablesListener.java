package edu.bsu.cs;

import javafx.scene.control.Button;

public class TablesListener {

    public void setButtonListener(String tableType, Button referenceButton) {
        referenceButton.setOnAction(e-> {findChangeMethod(tableType, referenceButton);
            referenceButton.getParent().requestFocus();});
    }

    private void findChangeMethod(String tableType, Button referenceButton) {
        switch (tableType) {
            case "round" -> changeRoundTable(referenceButton);
            case "square" -> changeSquareTable(referenceButton);
            case "booth" -> changeBooth(referenceButton);
            default -> changeLongTable(referenceButton);
        }

    }

    private void changeRoundTable(Button referenceButton) {
        if (referenceButton.getStyle().contains("green")) {
            referenceButton.setStyle("""
                        -fx-background-radius: 65;
                        -fx-pref-width: 130;
                        -fx-pref-height: 130;
                        -fx-background-color: #CF0000;
                        -fx-border-radius: 65;
                        -fx-border-width: 13;
                        -fx-border-color: #000000;
                        """);
        } else {
            referenceButton.setStyle("""
                        -fx-background-radius: 65;
                        -fx-pref-width: 130;
                        -fx-pref-height: 130;
                        -fx-background-color: green;
                        -fx-border-radius: 65;
                        -fx-border-width: 13;
                        -fx-border-color: #000000;
                        """);
        }
    }

    private void changeSquareTable(Button referenceButton) {
        if (referenceButton.getStyle().contains("green")) {
            referenceButton.setStyle("""
                        -fx-pref-width: 130;
                        -fx-pref-height: 130;
                        -fx-background-color: #CF0000;
                        -fx-border-width: 13;
                        -fx-border-color: #000000;
                        """);
        } else {
            referenceButton.setStyle("""
                        -fx-pref-width: 130;
                        -fx-pref-height: 130;
                        -fx-background-color: green;
                        -fx-border-width: 13;
                        -fx-border-color: #000000;
                        """);
        }
    }
    private void changeBooth(Button referenceButton) {
        if (referenceButton.getStyle().contains("green")) {
            referenceButton.setStyle("""
                    -fx-pref-width: 130;
                    -fx-pref-height: 142;
                    -fx-background-color: #CF0000;
                    -fx-border-width: 13;
                    -fx-border-color: #000000;
                    """);
        } else {
            referenceButton.setStyle("""
                    -fx-pref-width: 130;
                    -fx-pref-height: 142;
                    -fx-background-color: green;
                    -fx-border-width: 13;
                    -fx-border-color: #000000;
                    """);
        }
    }

    private void changeLongTable(Button referenceButton) {
        if (referenceButton.getStyle().contains("green")) {
            referenceButton.setStyle("""
                       -fx-pref-width: 320;
                       -fx-pref-height: 130;
                       -fx-background-color: #CF0000;
                       -fx-border-width: 13;
                       -fx-border-color: #000000;
                       """);
        } else {
            referenceButton.setStyle("""
                            -fx-pref-width: 320;
                            -fx-pref-height: 130;
                            -fx-background-color: green;
                            -fx-border-width: 13;
                            -fx-border-color: #000000;
                            """);
        }
    }
}