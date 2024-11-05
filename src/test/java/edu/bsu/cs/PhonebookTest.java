package edu.bsu.cs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhonebookTest {
    private Phonebook phonebook;
    private final String csvFilePath = "testPhonebook.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath))) {
            writer.write("3177375487,kevin\n");
            writer.write("3177245960,Brian\n");
        }

        phonebook = new Phonebook(csvFilePath);
    }

    @Test
    void testLoadPhoneBook() {
        assertEquals("kevin ", phonebook.getNameByPhoneNumber("3177375487"));
        assertEquals("Brian", phonebook.getNameByPhoneNumber("3177245960"));
    }

    @Test
    void testAddNewEntry() {
        String newPhoneNumber = "5554443345";
        String newName = "new Guest";

        phonebook.addNewEntry(newPhoneNumber, newName);
        assertEquals(newName, phonebook.getNameByPhoneNumber(newPhoneNumber));
    }

    @Test
    void testGetNameByPhoneNumber() {
        assertEquals("kevin", phonebook.getNameByPhoneNumber("3177375487"));
        assertNull(phonebook.getNameByPhoneNumber("0000000000"));
    }

    @Test
    void testSaveEntryToCSV() throws IOException {
        String newPhoneNumber = "3177375554";
        String newName = "newest guest";
        phonebook.addNewEntry(newPhoneNumber, newName);

        List<String> lines = Files.readAllLines(Paths.get(csvFilePath));
        assertTrue(lines.contains(newPhoneNumber + "," + newName));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(csvFilePath));
    }
}