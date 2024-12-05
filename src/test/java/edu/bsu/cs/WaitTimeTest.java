package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WaitTimeTest {

    private WaitTime waitTime;
    private SingletonDataStore dataStore;

    @BeforeEach
    void setUp() {
        dataStore = SingletonDataStore.getInstance();
        dataStore.getElapsedTime().clear();
        dataStore.getPartyList().clear();

        waitTime = new WaitTime();
    }

    @Test
    void waitTimeAlgorithm_noOccupiedTables_noWaitlist() {
        assertEquals(2, waitTime.waitTimeAlgorithm());
    }

    @Test
    void waitTimeAlgorithm_someOccupiedTables_noWaitlist() {
        dataStore.getElapsedTime().put(1, 360);
        dataStore.getElapsedTime().put(2, 480);
        dataStore.getElapsedTime().put(3, 600);

        assertEquals(10, waitTime.waitTimeAlgorithm());
    }

    @Test
    void waitTimeAlgorithm_moreThanSixOccupiedTables_withWaitlist() {
        for (int i = 1; i <= 8; i++) {
            dataStore.getElapsedTime().put(i, 360);
        }

        for (int i = 0; i < 3; i++) {
            dataStore.getPartyList().add(new Party(3, "Party ", "132-462-7890", waitTime.waitTimeAlgorithm()));
        }

        assertEquals(17, waitTime.waitTimeAlgorithm());
    }

    @Test
    void waitTimeAlgorithm_noOccupiedTables_withWaitlist() {
        for (int i = 0; i < 5; i++) {
            dataStore.getPartyList().add(new Party(3, "Party ", "132-462-7890", waitTime.waitTimeAlgorithm()));
        }

        assertEquals(7, waitTime.waitTimeAlgorithm());
    }

    @Test
    void calculateAverageSeatTime_noTables() {
        dataStore.getElapsedTime().clear();

        assertEquals(2, waitTime.waitTimeAlgorithm());
    }

    @Test
    void calculateAverageSeatTime_withTables() {
        dataStore.getElapsedTime().put(1, 600);
        dataStore.getElapsedTime().put(2, 1200);

        assertEquals(17, waitTime.waitTimeAlgorithm());
    }
}
