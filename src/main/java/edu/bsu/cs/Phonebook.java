package edu.bsu.cs;

import java.io.*;
import java.util.HashMap;

public class Phonebook {
    private final HashMap<String, String> phonebook = new HashMap<>();

    public Phonebook(String csvFilePath) {
        loadPhoneBook(csvFilePath);
    }

    private void loadPhoneBook(String csvFilePath) {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFilePath)) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    String phoneNumber = fields[0].trim();
                    String name = fields[1].trim();
                    phonebook.put(phoneNumber, name);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred loading the phonebook: " + e.getMessage());
        }
    }

    public void addNewEntry(String phoneNumber, String name) {
        phonebook.put(phoneNumber, name);
    }

    public String getNameByPhoneNumber(String phoneNumber) {
        return phonebook.get(phoneNumber);
    }
}
