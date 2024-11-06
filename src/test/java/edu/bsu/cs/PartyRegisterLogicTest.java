package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartyRegisterLogicTest {
    private PartyRegisterLogic partyRegisterLogic;

    @BeforeEach
    public void setUp() {
        partyRegisterLogic = new PartyRegisterLogic();
    }

    @Test
    public void testFormatPhoneNumber_ValidLongNumber() {
        String formattedNumber = partyRegisterLogic.formatPhoneNumber("1234567890");
        assertEquals("123-456-7890", formattedNumber);
    }

    @Test
    public void testFormatPhoneNumber_ValidShortNumber() {
        String formattedNumber = partyRegisterLogic.formatPhoneNumber("12345");
        assertEquals("123-45", formattedNumber);
    }

    @Test
    public void testFormatPhoneNumber_InvalidNumber() {
        String formattedNumber = partyRegisterLogic.formatPhoneNumber("abc123");
        assertEquals("123", formattedNumber);
    }

    @Test
    public void testIsValidPhoneNumber_ValidNumber() {
        assertTrue(partyRegisterLogic.isValidPhoneNumber("1234567890"));
    }

    @Test
    public void testIsValidPhoneNumber_InvalidNumber() {
        assertFalse(partyRegisterLogic.isValidPhoneNumber("1234"));
    }

    @Test
    public void testAddPartyToData() {
        SingletonDataStore dataStore = SingletonDataStore.getInstance();
        assertEquals(0, dataStore.getPartyList().size());

        partyRegisterLogic.addPartyToData(4, "Jason", "1234567890", 30);
        assertEquals(1, dataStore.getPartyList().size());
        Party party = dataStore.getPartyList().getFirst();
        assertEquals("Jason", party.getName());
        assertEquals("1234567890", party.getPhoneNumber());
        assertEquals(30, party.getWaitTime());
    }

    @Test
    public void testAddPartyToData_SortsByWaitTime() {
        partyRegisterLogic.addPartyToData(2, "Alice", "3177245960", 20);
        partyRegisterLogic.addPartyToData(3, "Kolby", "3177375487", 10);

        SingletonDataStore dataStore = SingletonDataStore.getInstance();
        Party firstParty = dataStore.getPartyList().getFirst();
        assertEquals("Kolby", firstParty.getName());
    }
}