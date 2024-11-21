package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartyTest {
    @BeforeEach
    public void setUp() {
        Party.resetIdCounter();
    }

    @Test
    public void testPartyCreation() {
        Party party = new Party(4, "kevin", "123-155-1234", 20);

        assertEquals(1, party.getId());
        assertEquals(4, party.getSize());
        assertEquals("kevin", party.getName());
        assertEquals("123-155-1234", party.getPhoneNumber());
        assertEquals(20, party.getWaitTime());
    }

    @Test
    public void testMultiplePartyCreation() {
        Party party1 = new Party(2, "Jason", "312-322-9999", 15);
        Party party2 = new Party(3, "Kolby", "317-737-5487", 10);

        assertEquals(1, party1.getId());
        assertEquals(2, party2.getId());
    }

    @Test
    public void testPartyProperties() {
        Party party = new Party(5, "brian", "317-234-4321", 30);

        assertAll("Verify party properties",
                () -> assertEquals(1, party.getId()),
                () -> assertEquals(5, party.getSize()),
                () -> assertEquals("brian", party.getName()),
                () -> assertEquals("317-234-4321", party.getPhoneNumber()),
                () -> assertEquals(30, party.getWaitTime())
        );
    }
}