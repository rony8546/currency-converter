package com.currency.converter.controller;

import java.io.IOException;

import com.currency.converter.App;
import javafx.fxml.FXML;

public class TertiaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}