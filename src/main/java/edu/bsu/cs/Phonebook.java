package edu.bsu.cs;


import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Phonebook {
    private final HashMap<String, String> phonebook = new HashMap<>();
    private final String csvFilePath;

    public Phonebook(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        loadPhoneBook();
    }

    private void loadPhoneBook() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String phoneNumber = fields[0].trim();
                String name = fields[1].trim();
                phonebook.put(phoneNumber, name);
            }
        } catch (IOException e) {
            System.err.println("An error occurred loading the phonebook: " + e.getMessage());
        }
    }

    public void addNewEntry(String phoneNumber, String name) {
        phonebook.put(phoneNumber, name);
        saveEntryToCSV(phoneNumber, name);
    }

    public String getNameByPhoneNumber(String phoneNumber) {
        return phonebook.get(phoneNumber);
    }

    private void saveEntryToCSV(String phoneNumber, String name) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath))) {
            writer.write(phoneNumber + "," + name);
        } catch (IOException e) {
            System.err.println("There was an error writing to the CSV file: " + e.getMessage());
        }
    }
}
