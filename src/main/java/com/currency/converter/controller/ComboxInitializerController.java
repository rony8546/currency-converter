package com.currency.converter.controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class ComboxInitializerController {

    private Map<String, Image> flags;

    public ComboxInitializerController(Map<String, Image> flags) {
        this.flags = flags;
    }

    public void initComboBox(ComboBox<String> comboBox) {
        comboBox.setCellFactory(combox -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    imageView.setImage(flags.get(item));
                    imageView.setFitHeight(20); // Tamaño de la bandera
                    imageView.setFitWidth(30);
                    setGraphic(imageView);
                    setText(item); // Mostrar el texto junto a la bandera
                }
            }
        });
    }
}