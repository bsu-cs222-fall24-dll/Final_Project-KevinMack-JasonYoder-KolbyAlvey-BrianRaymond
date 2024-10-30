package edu.bsu.cs;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CSVTest {
    private static final String TEST_CSV_FILE = "src/test/resources/testPhonebook.csv";
    private Phonebook phonebook;

    @BeforeEach
    public void setUp() throws IOException {

        try (CSVWriter writer = new CSVWriter(new FileWriter(TEST_CSV_FILE))) {
            String[] entries = {"1234567890", "John Doe"};
            writer.writeNext(entries);
            entries = new String[]{"0987654321", "Jane Smith"};
            writer.writeNext(entries);
        }
        phonebook = new Phonebook("testPhonebook.csv");
    }

    @Test
    public void testLoadPhoneBook() {
        assertEquals("tommy", phonebook.getNameByPhoneNumber("1234567890"));
        assertEquals("james ", phonebook.getNameByPhoneNumber("0987654321"));
        assertNull(phonebook.getNameByPhoneNumber("0000000000"));
    }

    @Test
    public void testAddNewEntry() {
        phonebook.addNewEntry("3177375487", "Persona");


        assertEquals("Persona", phonebook.getNameByPhoneNumber("3177375487"));

        boolean entryExists = false;
        try {
            entryExists = Files.lines(Paths.get(TEST_CSV_FILE)).anyMatch(line -> line.contains("3177375487"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(entryExists);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }
}
