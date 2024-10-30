package edu.bsu.cs;

import java.util.Comparator;
import java.util.List;

public class PartyRegisterLogic {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Party> partyList = data.getPartyList();
    private final Phonebook phonebook = data.getPhonebook();

    public String formatPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\D", "");

        if (cleanPhoneNumber.length() > 6) {
            return cleanPhoneNumber.substring(0, 3) + "-" + cleanPhoneNumber.substring(3, 6) + "-" + cleanPhoneNumber.substring(6, Math.min(10, cleanPhoneNumber.length()));
        } else if (cleanPhoneNumber.length() > 3) {
            return cleanPhoneNumber.substring(0, 3) + "-" + cleanPhoneNumber.substring(3);
        } else {
            return cleanPhoneNumber;
        }
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\D", "");

        return cleanPhoneNumber.isEmpty() || cleanPhoneNumber.length() == 10;
    }

    public void addPartyToData(int size, String name, String phoneNumber, int waitTime) {
        Party party = new Party(size, name, phoneNumber, waitTime);
        partyList.add(party);
        phonebook.addNewEntry(party.getPhoneNumber(), party.getName());
        partyList.sort(Comparator.comparingInt(Party::getWaitTime));
    }
}
