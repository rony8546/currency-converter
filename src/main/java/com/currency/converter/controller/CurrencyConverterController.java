package com.currency.converter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterController {

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> fromCurrencyCombox;

    @FXML
    private ComboBox<String> toCurrencyCombox;

    @FXML
    private Label exchangeRateLabel;

    @FXML
    private Label convertedAmountLabel;

    private Map<String, Double> exchangeRates = new HashMap<>();
    private Map<String, Image> flags = new HashMap<>();

    @FXML
    public void initialize() {
        initFlags();
        initComboBox(fromCurrencyCombox);
        initComboBox(toCurrencyCombox);
    }

    private void initFlags() {
        flags.put("EUR", new Image(getClass().getResourceAsStream("/css/img/european-union.png")));
        flags.put("CAD", new Image(getClass().getResourceAsStream("/css/img/canada.png")));
        flags.put("COP", new Image(getClass().getResourceAsStream("/css/img/colombia.png")));
        flags.put("USD", new Image(getClass().getResourceAsStream("/css/img/united-states.png")));
        flags.put("MXM", new Image(getClass().getResourceAsStream("/css/img/mxflag.png")));
    }

    private void initComboBox(ComboBox<String> comboBox) {
        comboBox.setCellFactory(combox -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    imageView.setImage(flags.get(item));
                    imageView.setFitHeight(20);  // Tamaño de la bandera
                    imageView.setFitWidth(30);
                    setGraphic(imageView);
                    setText(item);  // Mostrar el texto junto a la bandera
                }
            }
        });
    }

    @FXML
    public void handleConvertButtonAction(MouseEvent event) {
        String from = fromCurrencyCombox.getValue();
        String to = toCurrencyCombox.getValue();
        String amountText = amountField.getText();

        if (from == null || to == null || amountText.isEmpty()) {
            convertedAmountLabel.setText("Invalid input");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            double rateFrom = exchangeRates.get(from);
            double rateTo = exchangeRates.get(to);
            double convertedAmount = (amount / rateFrom) * rateTo;

            convertedAmountLabel.setText(String.format("$ %.2f", convertedAmount));

            // Actualizar tasa de cambio en la etiqueta
            exchangeRateLabel.setText(String.format("1 %s = %.4f %s | 1 %s = %.4f %s",
                    from, rateFrom, "USD", to, rateTo, "NGN"));
        } catch (NumberFormatException e) {
            convertedAmountLabel.setText("Invalid amount");
        }
    }
}
