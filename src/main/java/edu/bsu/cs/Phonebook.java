package edu.bsu.cs;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Phonebook {
    private final HashMap<String, String> phonebook = new HashMap<>();
    private final String csvFilePath;

    public Phonebook(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        loadPhoneBook(csvFilePath);
    }

    private void loadPhoneBook(String filePath) {

        try {
            readToHashMap(filePath);
        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void readToHashMap(String filePath) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(filePath));
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            String[] nameNumberArray = line.split(",");
            phonebook.put(nameNumberArray[0], nameNumberArray[1]);
        }
        fileReader.close();
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
