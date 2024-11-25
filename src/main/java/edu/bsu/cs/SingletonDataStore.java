package edu.bsu.cs;

import java.util.ArrayList;
import java.util.List;

public class SingletonDataStore {
    private static SingletonDataStore instance;
    private final List<Party> partyList;
    private final Phonebook phonebook;

    private SingletonDataStore() {
        partyList = new ArrayList<>();
        phonebook = new Phonebook("src/main/resources/restaurantData.csv");
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
}
