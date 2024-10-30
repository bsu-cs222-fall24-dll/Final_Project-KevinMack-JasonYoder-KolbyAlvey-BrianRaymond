package edu.bsu.cs;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.HashMap;
import java.util.List;

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
        saveEntryToCSV(phoneNumber, name);
    }

    public String getNameByPhoneNumber(String phoneNumber) {
        return phonebook.get(phoneNumber);
    }

    private void saveEntryToCSV(String phoneNumber, String name) {
        InputStream dataStream = Phonebook.class.getClassLoader().getResourceAsStream("restaurantData.csv");

        try {
            assert dataStream != null;
            try (CSVReader reader = new CSVReader(new InputStreamReader(dataStream))) {
                List<String[]> allData = reader.readAll();
                boolean match = false;
                for (String[] row : allData) {
                    if (row[0].equals(phoneNumber)) {
                        match = true;
                        break;
                    }
                }
                if(!match) {
                    String data = "src/main/resources/restaurantData.csv";
                    CSVWriter writer = new CSVWriter(new FileWriter(data, true));
                    String[] newGuest = {phoneNumber, name};
                    writer.writeNext(newGuest);
                    writer.close();
                }
            }
        } catch (Exception exception) {
            System.err.println("There was an error reading the CSV file: " + exception.getMessage());
        }
    }
}
