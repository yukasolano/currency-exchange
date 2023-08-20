package com.yukakyushima.currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private final CurrencyClient client;
    private final Map<String, BigDecimal> rates = new HashMap<>();

    public CurrencyConverter(CurrencyClient client) {
        this.client = client;
    }

    public BigDecimal convertCurrency(String from, String to) {
        String key = from + "_" + to;
        BigDecimal rate = rates.get(key);

        if (rate == null) {
            rate = client.getRate(from, to);
            rates.put(key, rate);
        }
        return rate;
    }
}
