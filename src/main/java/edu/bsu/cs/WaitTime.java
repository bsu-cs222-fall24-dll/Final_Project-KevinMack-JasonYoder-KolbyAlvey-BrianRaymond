package edu.bsu.cs;

import java.util.Map;

public class WaitTime {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final Map<Integer, Integer> elapsedTime = data.getElapsedTime();

    public int waitTimeAlgorithm() {
        int occupiedTables = 0;
        for (int time : elapsedTime.values()) {
            if (time > 0) {
                occupiedTables++;
            }
        }

        int averageSeatTime = calculateAverageSeatTime(occupiedTables);
        int baseWaitTime = Math.min(averageSeatTime, 20);
        int additionalWaitTime = (occupiedTables > 6) ? (occupiedTables - 6) * 3 : 0;
        return baseWaitTime + additionalWaitTime;
    }

    private int calculateAverageSeatTime(int numberOfTables) {
        if (numberOfTables == 0) {
            return 0;
        }

        int totalSeconds = 0;
        for (Map.Entry<Integer,Integer> time : elapsedTime.entrySet()) {
            totalSeconds += time.getValue();
        }
        return (totalSeconds / 60) / numberOfTables;
    }
}
