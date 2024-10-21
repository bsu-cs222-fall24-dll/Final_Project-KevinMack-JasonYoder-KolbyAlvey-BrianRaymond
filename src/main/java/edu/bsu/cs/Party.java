package edu.bsu.cs;

public class Party {
    private String name;
    private int size;
    private String phoneNumber;
    private long waitTime;

    public Party (String name, int size, String phoneNumber, long waitTime) {
        this.name = name;
        this.size = size;
        this.phoneNumber = phoneNumber;
        this.waitTime = waitTime;
    }

    public long getWaitTime() {
        return this.waitTime;
    }
}
