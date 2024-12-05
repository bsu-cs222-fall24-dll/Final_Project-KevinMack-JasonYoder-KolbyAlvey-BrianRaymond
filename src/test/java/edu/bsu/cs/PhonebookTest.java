package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhonebookTest {
    private Phonebook phonebook;
    private final String csvFilePath = "src/test/resources/testPhonebook.csv";

    @BeforeEach
    void setUp()  {
        phonebook = new Phonebook(csvFilePath);
        phonebook.addNewEntry("3177375487", "kevin");
        phonebook.addNewEntry("3177245960", "Brian");

    }

    @Test
    void testLoadPhoneBookKevin() {
        assertEquals("kevin", phonebook.getNameByPhoneNumber("3177375487"));
    }

    @Test
    void testLoadPhoneBookBrian() {
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
    void testGetNameByPhoneNumberKevin() {
        assertEquals("kevin", phonebook.getNameByPhoneNumber("3177375487"));
    }

    @Test
    void testGetNameByPhoneNumberNull() {
        assertNull(phonebook.getNameByPhoneNumber("0000000000"));
    }

    @Test
    void testSaveEntryToCSV() throws IOException {
        String newPhoneNumber = "3177375554";
        String newName = "newest guest";
        phonebook.addNewEntry(newPhoneNumber, newName);

        List<String> lines = Files.readAllLines(Paths.get(csvFilePath));

        assertTrue(lines.contains(newPhoneNumber + "," + newName), "The CSV file should contain the new entry.");
    }
}