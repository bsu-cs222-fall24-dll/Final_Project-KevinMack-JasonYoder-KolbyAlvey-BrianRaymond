package edu.bsu.cs;

import edu.bsu.cs.Application.AddOrder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class FetchFXML {
    public static Parent loadFXML(String fxmlFile) {
        try {
            return FXMLLoader.load(Objects.requireNonNull(AddOrder.class.getClassLoader().getResource(fxmlFile)));
        } catch(IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
        return null;
    }
}

