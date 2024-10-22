package com.currency.converter.controller;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class ComboxInitializerControllerTest {
    private ComboxInitializerController controller;
    private Map<String, Image> flags;

    @BeforeEach
    public void setUp() {
        // Initialize JavaFX environment
        new JFXPanel();

        // Initialize flags map
        flags = new HashMap<>();
        flags.put("USA", new Image("file:src/test/resources/usa.png"));
        flags.put("Canada", new Image("file:src/test/resources/canada.png"));

        controller = new ComboxInitializerController(flags);
    }

    @Test
    public void testInitComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("USA", "Canada");

        controller.initComboBox(comboBox);

        comboBox.getSelectionModel().select("USA");
        assertNotNull(comboBox.getButtonCell().getGraphic());
        assertEquals("USA", comboBox.getButtonCell().getText());

        comboBox.getSelectionModel().select("Canada");
        assertNotNull(comboBox.getButtonCell().getGraphic());
        assertEquals("Canada", comboBox.getButtonCell().getText());
    }
}