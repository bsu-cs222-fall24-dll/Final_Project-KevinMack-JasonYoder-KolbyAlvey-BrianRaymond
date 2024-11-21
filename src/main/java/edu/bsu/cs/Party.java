package edu.bsu.cs;

public class Party {
    private static int idCounter = 0; // Static counter to generate unique IDs
    private final int id;
    private final int size;
    private final String name;
    private final String phoneNumber;
    private final int waitTime;

    public Party(int size, String name, String phoneNumber, int waitTime) {
        this.id = ++idCounter; // Increment counter and assign ID
        this.size = size;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.waitTime = waitTime;
    }

    public static void resetIdCounter() {
        idCounter = 0;
    }

    public int getId() {
        return id;
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
}
