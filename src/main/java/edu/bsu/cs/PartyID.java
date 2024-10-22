package edu.bsu.cs;

public class PartyID {
    private static int idCounter = 0; // Static counter to generate unique IDs
    private final int id;
    private final String size;
    private final String name;
    private final String phoneNumber;
    private final String waitTime;

    public PartyID(String size, String name, String phoneNumber, String waitTime) {
        this.id = ++idCounter; // Increment counter and assign ID
        this.size = size;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.waitTime = waitTime;
    }

    public int getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWaitTime() {
        return waitTime;
    }
}
