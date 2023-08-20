package com.yukakyushima.currency;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.SECONDS;

public class CurrencyClient {

    public BigDecimal getRate(String from, String to) {

        try {
            URI uri = buildUri(from, to);
            HttpResponse<String> response = makeRequest(uri);
            if (response.statusCode() == 200) {
                return responseToRate(response.body());
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }

    private URI buildUri(String from, String to) throws MalformedURLException, URISyntaxException {
        return new URI("https://api.bcb.gov.br/dados/serie/bcdata.sgs.4389/dados?formato=json&dataInicial=01/01/2016&dataFinal=31/12/2016");
    }

    private HttpResponse<String> makeRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).timeout(Duration.of(10, SECONDS)).GET().build();
        return HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private BigDecimal responseToRate(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        List<Map<String, String>> resp = mapper.readValue(body, typeFactory.constructCollectionType(List.class, Map.class));
        System.out.println(resp);
        return BigDecimal.valueOf(100);
    }
}
