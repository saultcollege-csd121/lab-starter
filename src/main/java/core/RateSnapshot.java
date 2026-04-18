package core;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * Stores a snapshot of exchange rates fetched from the API.
 * Base currency is whatever the user selected.
 */
public class RateSnapshot {

    private final String baseCurrency;
    private final Map<String, Double> rates;  // exm: {"USD": 0.73, "COP": 2633.0}
    private final LocalDateTime fetchedAt;

    public RateSnapshot(String baseCurrency, Map<String, Double> rates, LocalDateTime fetchedAt) {
        this.baseCurrency = baseCurrency;
        this.rates        = Collections.unmodifiableMap(rates);  // read-only so nothing can break it
        this.fetchedAt    = fetchedAt;
    }

    public String getBaseCurrency()       { return baseCurrency; }
    public Map<String, Double> getRates() { return rates; }
    public LocalDateTime getFetchedAt()   { return fetchedAt; }

    /** Returns the rate for a target currency, or -1 if not found. */
    public double getRate(String target) {
        return rates.getOrDefault(target.toUpperCase(), -1.0);
    }

    /** Converts an amount from base currency to the target currency. */
    public double convert(double amount, String target) {
        double rate = getRate(target);
        if (rate < 0) throw new IllegalArgumentException("Unknown currency: " + target);
        return amount * rate;
    }
}