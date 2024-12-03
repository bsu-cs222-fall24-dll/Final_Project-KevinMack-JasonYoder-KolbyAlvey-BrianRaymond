package edu.bsu.cs;


import javafx.scene.Parent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FetchFXMLTest {
    @Test
    public void testFetchFXML_Valid(){
        Parent result = FetchFXML.loadFXML("MainScene.fxml");
        assertNotEquals(null, result);
    }
    @Test
    public void testFetchFXML_Invalid(){
        Parent result = FetchFXML.loadFXML("TempLogScene.fxml");
        assertNull(result);
    }
}