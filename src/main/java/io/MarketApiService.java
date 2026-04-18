package io;

import core.RateSnapshot;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fetches live exchange rates from ExchangeRate-API.
 */
public class MarketApiService {

    private static final String API_KEY = "49ba20bee044c350d495c52a";

    private static final String BASE_URL =
            "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    /** Currencies displayed in the app. */
    public static final List<String> CURRENCIES =
            List.of("USD", "COP", "EUR", "GBP", "MXN", "BRL", "JPY", "AUD");

    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    /**
     * Calls the API and returns a RateSnapshot for the given base currency.
     *
     * @throws IOException          on network or HTTP error
     * @throws InterruptedException if the thread is interrupted
     */
    public RateSnapshot fetchRates(String baseCurrency) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()     // build the request URL
                .uri(URI.create(BASE_URL + baseCurrency.toUpperCase()))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(java.nio.charset.StandardCharsets.UTF_8));

        if (response.statusCode() != 200) {
            throw new IOException("HTTP error: " + response.statusCode());
        }

        JSONObject json = new JSONObject(response.body());           // parse the JSON response

        if (!"success".equals(json.getString("result"))) {
            throw new IOException("API error: " + json.optString("error-type", "unknown"));
        }

        JSONObject allRates = json.getJSONObject("conversion_rates");    // pull out only the currencies we care about
        Map<String, Double> rates = new HashMap<>();

        for (String code : CURRENCIES) {
            if (allRates.has(code)) {
                rates.put(code, allRates.getDouble(code));
            }
        }
        return new RateSnapshot(baseCurrency.toUpperCase(), rates, LocalDateTime.now());
    }
}