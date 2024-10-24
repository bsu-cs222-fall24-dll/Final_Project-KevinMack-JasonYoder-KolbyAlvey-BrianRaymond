package edu.bsu.cs;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVHandler {
    public void addIfNew(String name, String phoneNumber){
        InputStream dataStream = CSVHandler.class.getClassLoader().getResourceAsStream("restaurantData.csv");

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