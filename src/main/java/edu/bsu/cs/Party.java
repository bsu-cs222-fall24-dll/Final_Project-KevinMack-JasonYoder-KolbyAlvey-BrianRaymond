package edu.bsu.cs;

public class Party {
    private static int partyIdCounter = 0;
    private final int partyId;
    private final int size;
    private final String name;
    private final String phoneNumber;
    private final int waitTime;

    public Party(int size, String name, String phoneNumber, int waitTime) {
        this.partyId = ++partyIdCounter;
        this.size = size;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.waitTime = waitTime;
    }

    public int getPartyId() {
        return partyId;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public static void resetCounter() {
        partyIdCounter = 0;
    }
}
