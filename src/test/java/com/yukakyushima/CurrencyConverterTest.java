package com.yukakyushima;

import com.yukakyushima.currency.CurrencyClient;
import com.yukakyushima.currency.CurrencyConverter;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class CurrencyConverterTest {

    @Test
    public void testUsingApi() {
        CurrencyClient mockClient = mock(CurrencyClient.class);
        when(mockClient.getRate("BRL", "GBP")).thenReturn(BigDecimal.valueOf(10));
        when(mockClient.getRate("USD", "GBP")).thenReturn(BigDecimal.valueOf(11));

        CurrencyConverter converter = new CurrencyConverter(mockClient);

        BigDecimal rate1 = converter.convertCurrency("BRL", "GBP");
        Assert.assertEquals(BigDecimal.valueOf(10), rate1);

        BigDecimal rate2 = converter.convertCurrency("USD", "GBP");
        Assert.assertEquals(BigDecimal.valueOf(11), rate2);

        verify(mockClient, times(2)).getRate(anyString(), anyString());
    }

    @Test
    public void testUsingMap() {
        CurrencyClient mockClient = mock(CurrencyClient.class);
        when(mockClient.getRate("BRL", "GBP")).thenReturn(BigDecimal.valueOf(10));

        CurrencyConverter converter = new CurrencyConverter(mockClient);

        BigDecimal rate1 = converter.convertCurrency("BRL", "GBP");
        Assert.assertEquals(BigDecimal.valueOf(10), rate1);

        BigDecimal rate2 = converter.convertCurrency("BRL", "GBP");
        Assert.assertEquals(BigDecimal.valueOf(10), rate2);

        verify(mockClient, times(1)).getRate(anyString(), anyString());
    }
}
