package com.currency.converter.controller;

import java.io.IOException;

import com.currency.converter.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToTertiary() throws IOException {
        App.setRoot("tertiary");
    }
}