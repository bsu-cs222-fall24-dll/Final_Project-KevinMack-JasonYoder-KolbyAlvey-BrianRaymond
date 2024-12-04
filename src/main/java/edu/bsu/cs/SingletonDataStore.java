package edu.bsu.cs;

import java.util.*;

public class SingletonDataStore {
    private static SingletonDataStore instance;
    private final List<Party> partyList;
    private final Phonebook phonebook;
    private final Map<Integer, TimerTask> timerTasks;
    private final Map<Integer, Integer> elapsedTime;
    List<Order> orderList;


    private SingletonDataStore() {
        partyList = new ArrayList<>();
        phonebook = new Phonebook("src/main/resources/restaurantData.csv");
        timerTasks = new HashMap<>();
        elapsedTime = new HashMap<>();
        orderList = new ArrayList<>();
    }

    public static SingletonDataStore getInstance() {
        if (instance == null) {
            instance = new SingletonDataStore();
        }
        return instance;
    }

    public List<Party> getPartyList() {
        return partyList;
    }

    public Phonebook getPhonebook() {
        return phonebook;
    }

    public Map<Integer, TimerTask> getTimerTasks() {
        return timerTasks;
    }

    public Map<Integer, Integer> getElapsedTime() {
        return elapsedTime;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
