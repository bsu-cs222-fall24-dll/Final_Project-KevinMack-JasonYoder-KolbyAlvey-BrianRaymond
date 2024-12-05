package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartyRegisterLogicTest {
    private PartyRegisterInputValidation partyRegisterLogic;

    @BeforeEach
    public void setUp() {
        partyRegisterLogic = new PartyRegisterInputValidation();
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
    public void testIsInboundNumberInRange() {
        assertFalse(partyRegisterLogic.isNotInboundInteger(50));
    }

    @Test
    public void testIsInboundNumberOnBorder() {
        assertTrue(partyRegisterLogic.isNotInboundInteger(61));
    }

    @Test
    public void testIsInboundNumberOutOfRange() {
        assertTrue(partyRegisterLogic.isNotInboundInteger(100));
    }

    @Test
    public void testIsRealNumberNotRealNumber() {
        assertFalse(partyRegisterLogic.isNotRealNumber("45"));
    }

    @Test
    public void testIsNotRealNumberString() {
        assertTrue(partyRegisterLogic.isNotRealNumber("hello"));
    }

    @Test
    public void testIsNotRealNumberCharacters() {
        assertTrue(partyRegisterLogic.isNotRealNumber("!@$&%(#)_"));
    }

}