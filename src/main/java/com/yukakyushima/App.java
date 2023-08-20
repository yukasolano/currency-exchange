package com.yukakyushima;

import com.yukakyushima.currency.CurrencyClient;
import com.yukakyushima.currency.CurrencyConverter;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        CurrencyClient client = new CurrencyClient();
        CurrencyConverter converter = new CurrencyConverter(client);
        BigDecimal rate = converter.convertCurrency("BRL", "GBP");
        System.out.println(rate);
    }
}
