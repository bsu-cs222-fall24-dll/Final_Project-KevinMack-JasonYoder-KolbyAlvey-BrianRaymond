package edu.bsu.cs;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PartyManagerTest {
    private PartyManager partyManager;
    private VBox testVBox;

    @BeforeEach
    public void setUp() {
        testVBox = new VBox();
        partyManager = new PartyManager(testVBox);
    }

    @AfterEach
    public void tearDown() {
        testVBox.getChildren().clear();
        Platform.runLater(() -> {});
    }

    @Test
    public void testAddParty() {
        partyManager.addParty(4, "kevin", "098-765-4321", 5);

        assertEquals(1, testVBox.getChildren().size(), "Party should be added to the VBox.");

        HBox partyHBox = (HBox) testVBox.getChildren().get(0);
        assertNotNull(partyHBox, "Party HBox should not be null.");
        assertEquals("4", ((TextField) partyHBox.getChildren().get(0)).getText(), "Size should match.");
        assertEquals("kevin", ((TextField) partyHBox.getChildren().get(1)).getText(), "Name should match.");
        assertEquals("098-765-4321", ((TextField) partyHBox.getChildren().get(2)).getText(), "Phone should match.");
        assertEquals("5", ((TextField) partyHBox.getChildren().get(3)).getText(), "Wait time should match.");
    }

    @Test
    public void testRemoveParty() {
        partyManager.addParty(4, "johnnyboy", "317-723-8759", 32);
        partyManager.addParty(12, "kevin", "098-765-4321", 5);


        partyManager.removeParty();

        assertEquals(1, testVBox.getChildren().size(), "One party should remain in the VBox.");
        HBox remainingPartyHBox = (HBox) testVBox.getChildren().get(0);
        assertEquals("kevin", ((TextField) remainingPartyHBox.getChildren().get(1)).getText(), "Remaining party's name should be 'kevin'.");
    }

    @Test
    public void testRemoveNonExistentParty() {
        partyManager.addParty(4, "johnnyboy", "317-723-8759", 32);
        partyManager.removeParty();

        assertEquals(0, testVBox.getChildren().size(), "VBox should be empty after removal.");
    }
}
