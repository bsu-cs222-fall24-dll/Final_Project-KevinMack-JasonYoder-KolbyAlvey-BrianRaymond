package edu.bsu.cs;

import java.util.ArrayList;
import java.util.List;

public class SingletonData {
    private static SingletonData instance;
    private final List<Party> partyList;
    private final Phonebook phonebook;

    private SingletonData() {
        partyList = new ArrayList<>();
        phonebook = new Phonebook("restaurantData.csv");
    }

    public static SingletonData getInstance() {
        if (instance == null) {
            instance = new SingletonData();
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
