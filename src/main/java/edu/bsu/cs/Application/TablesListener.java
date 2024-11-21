package edu.bsu.cs.Application;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TablesListener {
    private final Map<Integer, TimerTask> timerTasks = new HashMap<>();
    private final Map<Integer, Integer> elapsedTime = new HashMap<>();
    private final Timer timer = new Timer(true);

    public void toggleTableState(int tableId, String tableType, Button tableButton, Parent tables) {
        String openClass = "open-" + tableType + "-table";
        String occupiedClass = "occupied-" + tableType + "-table";

        TextField timerField = (TextField) tables.lookup("#t" + tableId + "-timer");

        if (tableButton.getStyleClass().contains(openClass)) {
            tableButton.getStyleClass().remove(openClass);
            tableButton.getStyleClass().add(occupiedClass);
            startTimer(tableId, timerField);
        } else {
            tableButton.getStyleClass().remove(occupiedClass);
            tableButton.getStyleClass().add(openClass);
            stopAndResetTimer(tableId, timerField);
        }
    }

    private void startTimer(int tableId, TextField timerField) {
        stopAndResetTimer(tableId, timerField);
        elapsedTime.put(tableId, 0);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int seconds = elapsedTime.get(tableId) + 1;
                elapsedTime.put(tableId, seconds);
                updateTimerField(timerField, seconds);
            }
        };

        timerTasks.put(tableId, task);
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void stopAndResetTimer(int tableId, TextField timerField) {
        TimerTask task = timerTasks.get(tableId);
        if (task != null) {
            task.cancel();
            timerTasks.remove(tableId);
        }
        elapsedTime.put(tableId, 0);
        updateTimerField(timerField, 0);
    }

    private void updateTimerField(TextField timerField, int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        String timeText = String.format("%d:%02d", minutes, remainingSeconds);

        Platform.runLater(() -> timerField.setText(timeText));
    }
}