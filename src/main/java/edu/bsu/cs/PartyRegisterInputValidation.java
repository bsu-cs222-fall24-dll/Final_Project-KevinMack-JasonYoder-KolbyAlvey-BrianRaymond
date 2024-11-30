package edu.bsu.cs;

import java.util.Comparator;
import java.util.List;

public class PartyRegisterInputValidation {
    SingletonDataStore data = SingletonDataStore.getInstance();
    private final List<Party> partyList = data.getPartyList();
    private final Phonebook phonebook = data.getPhonebook();

    public String formatPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\D", "");
        if (cleanPhoneNumber.isEmpty()) return "";
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < cleanPhoneNumber.length(); i++) {
            if (i == 3 || i == 6) {
                formatted.append("-");
            }
            formatted.append(cleanPhoneNumber.charAt(i));
        }
        return formatted.toString();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\D", "");

        return cleanPhoneNumber.isEmpty() || cleanPhoneNumber.length() == 10;
    }

    public boolean isNotInboundInteger(int number) {
        return number > 60;
    }

    public boolean isNotRealNumber(String textBoxValue) {
        try {
            Integer.parseInt(textBoxValue);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public void addPartyToData(int size, String name, String phoneNumber, int waitTime) {
        Party party = new Party(size, name, phoneNumber, waitTime);
        partyList.add(party);
        phonebook.addNewEntry(party.getPhoneNumber(), party.getName());
        partyList.sort(Comparator.comparingInt(Party::getWaitTime));
    }
}
