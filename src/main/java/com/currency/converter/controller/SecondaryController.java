package com.currency.converter.controller;

import java.io.IOException;

import com.currency.converter.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}