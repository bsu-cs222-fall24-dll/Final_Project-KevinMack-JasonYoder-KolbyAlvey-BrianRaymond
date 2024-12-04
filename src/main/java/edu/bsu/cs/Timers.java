package edu.bsu.cs;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Timers {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final Map<Integer, TimerTask> timerTasks = data.getTableTimerTasks();
    private final Map<Integer, Integer> elapsedTime = data.getElapsedTime();
    private final Timer timer = new Timer(true);

    public void startTimer(int tableId, TextField timerField) {
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

    public void stopAndResetTimer(int tableId, TextField timerField) {
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
