package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartyTest {

    @Test
    public void testPartyGetId() {
        Party party = new Party(4, "kevin", "123-155-1234", 20);

        assertEquals(1, party.getPartyId());
    }

    @Test
    public void TestPartyGetSize() {
        Party party = new Party(4, "kevin", "123-155-1234", 20);
        assertEquals(4, party.getSize());
    }

    @Test
    public void TestPartyGetName() {
        Party party = new Party(4, "kevin", "123-155-1234", 20);
        assertEquals("kevin", party.getName());
    }

    @Test
    public void TestPartyGetPhoneNumber() {
        Party party = new Party(4, "kevin", "123-155-1234", 20);
        assertEquals("123-155-1234", party.getPhoneNumber());
    }

    @Test
    public void TestPartyGetWaitTime() {
        Party party = new Party(4, "kevin", "123-155-1234", 20);
        assertEquals(20, party.getWaitTime());
    }

    @Test
    public void testMultiplePartyCreation() {
        /*
        Weak warning for party1 never used is suppressed.
        It's created to test that the id incrementation works as planned.
         */
        Party party1 = new Party(2, "Jason", "312-322-9999", 15);
        Party party2 = new Party(3, "Kolby", "317-737-5487", 10);

        assertEquals(2, party2.getPartyId());
    }

    @BeforeEach
    void resetCount() {
        Party.resetCounter();
    }
}