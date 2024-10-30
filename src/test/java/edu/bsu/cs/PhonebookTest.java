package edu.bsu.cs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PhonebookTest {
    private Phonebook phonebook;
    private static final String TEST_CSV_FILE = "src/test/resources/testPhonebook.csv";

    @BeforeEach
    public void setUp() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_CSV_FILE)) {
            writer.write("1234567890, John Doe\n");
            writer.write("0987654321, Jane Smith\n");
        }


        phonebook = new Phonebook("testPhonebook.csv");
    }

    @Test
    public void testGetNameByPhoneNumber_ExistingEntry() {
        String name = phonebook.getNameByPhoneNumber("1234567890");
        assertEquals("John Doe", name);
    }

    @Test
    public void testGetNameByPhoneNumber_NonExistingEntry() {
        String name = phonebook.getNameByPhoneNumber("0000000000");
        assertNull(name);
    }

    @Test
    public void testAddNewEntry() {
        phonebook.addNewEntry("1111111111", "Lebron james");

        String name = phonebook.getNameByPhoneNumber("1111111111");
        assertEquals("Lebron james", name);


        boolean entryExists = false;
        try {
            entryExists = Files.lines(Paths.get(TEST_CSV_FILE)).anyMatch(line -> line.contains("5555555555"));
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