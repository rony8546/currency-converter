package com.currency.converter.controller;

import com.currency.converter.service.CurrencyConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import java.util.Map;

public class CurrencyConverterController {

    @FXML
    private ComboBox<String> fromCurrencyCombox;

    @FXML
    private ComboBox<String> toCurrencyCombox;

    @FXML
    private TextField amountField;

    @FXML
    private Label convertedAmountLabel;

    @FXML
    private Label exchangeRateLabel;

    private Map<String, Double> exchangeRates;
    private FlagInitializerController flagInitializerController;
    private ComboxInitializerController comboxInitializerController;
    private CurrencyConverter currencyConverter;

    @FXML
    public void initialize() {

        flagInitializerController = new FlagInitializerController();
        comboxInitializerController = new ComboxInitializerController(flagInitializerController.getFlags());
        currencyConverter = new CurrencyConverter();

        try {
            if (fromCurrencyCombox != null && toCurrencyCombox != null) {
                comboxInitializerController.initComboBox(fromCurrencyCombox);
                comboxInitializerController.initComboBox(toCurrencyCombox);
            } else {
                throw new NullPointerException("uno o ambos ComboBox(es) no están inicializados.");
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    public void handleConvertButtonAction(ActionEvent event) {
        String from = fromCurrencyCombox.getValue();
        String to = toCurrencyCombox.getValue();
        String amountText = amountField.getText();

        if (from == null || to == null || amountText.isEmpty()) {
            convertedAmountLabel.setText("Invalid input");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            //obtener tasa de cambio
            exchangeRates = currencyConverter.getExchangeRates(from);

            Double rateFrom = exchangeRates.get(from);
            Double rateTo = exchangeRates.get(to);

            if (rateFrom == null || rateTo == null) {
                convertedAmountLabel.setText("La tasa de cambio no se encuentra disponible");
                return;
            }

            double convertedAmount = (amount / rateFrom) * rateTo;

            convertedAmountLabel.setText(String.format("$ %.2f", convertedAmount));

            // Actualizar tasa de cambio en la etiqueta
            exchangeRateLabel.setText(String.format("1 %s = %.4f %s | 1 %s = %.4f %s",
                    from, rateFrom, to, to, rateTo, from));
        } catch (NumberFormatException e) {
            convertedAmountLabel.setText("Invalid amount");
        }
        catch (Exception e) {
            convertedAmountLabel.setText("Error during conversion");
            e.printStackTrace();
        }
    }
}