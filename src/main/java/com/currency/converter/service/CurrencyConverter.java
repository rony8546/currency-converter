package com.currency.converter.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    public static final String API_URL = "https://v6.exchangerate-api.com/v6/c1b2c5bae8b5bc8815246402/latest/";
    private ExchangeRateCache cache = new ExchangeRateCache();

    public Map<String, Double> getExchangeRates(String baseCurrency) {

        if (cache.hasRate(baseCurrency)) {
            return cache.getRate(baseCurrency);
        } else {
            Map<String, Double> exchangeRates = new HashMap<>();

            try {
                String urlString = API_URL + baseCurrency;

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                int status = connection.getResponseCode();

                if (status == 200) {
                    JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()));
                    JsonParser parser = new JsonParser();
                    JsonElement rootElement = parser.parse(reader);
                    JsonObject rootObject = rootElement.getAsJsonObject();
                    JsonObject rates = rootObject.getAsJsonObject("conversion_rates");

                    for (Map.Entry<String, JsonElement> entry : rates.entrySet()) {
                        exchangeRates.put(entry.getKey(), entry.getValue().getAsDouble());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            cache.addRates(baseCurrency,exchangeRates);
            return exchangeRates;
        }
    }
}