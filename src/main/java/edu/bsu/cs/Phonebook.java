package edu.bsu.cs;

import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Phonebook {
    private final HashMap<String, String> phonebook = new HashMap<>();
    private final String csvFilePath;

    public Phonebook(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        loadPhoneBook(csvFilePath);
    }

    private void loadPhoneBook(String csvFilePath) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 2) continue; // Skip invalid lines
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
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath, true))) {
            String[] newEntry = {phoneNumber, name};
            writer.writeNext(newEntry);
        } catch (IOException e) {
            System.err.println("There was an error saving to the CSV file: " + e.getMessage());
        }
    }
}

