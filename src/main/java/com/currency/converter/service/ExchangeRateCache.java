package com.currency.converter.model;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateCache {
    private Map<String, Map<String, Double>> cache = new HashMap<>();

    public Map<String, Double> getRate(String baseCurrency) {
        return cache.get(baseCurrency);
    }

    public void addRates(String baseCurrency, Map<String, Double> exchangeRates) {
        cache.put(baseCurrency, exchangeRates);
    }

    public boolean hasRate(String baseCurrency) {
        return cache.containsKey(baseCurrency);
    }
}
