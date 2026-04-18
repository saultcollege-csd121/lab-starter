package controllers;

import core.RateSnapshot;
import core.SearchRecord;
import io.HistoryFileService;
import io.MarketApiService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller sits between the UI and the data/IO classes.
 * The view calls methods here, this class does the work and returns results.
 */
public class DashboardController {

    private MarketApiService apiService = new MarketApiService();
    private HistoryFileService fileService = new HistoryFileService();

    private RateSnapshot latestSnapshot = null;     // we keep the last snapshot so the converter can use it without re-fetching

    public RateSnapshot refreshRates(String baseCurrency) throws IOException, InterruptedException {      // calls the API, saves all rates to history, returns the snapshot
        latestSnapshot = apiService.fetchRates(baseCurrency);

        for (var entry : latestSnapshot.getRates().entrySet()) {          // save each currency pair as its own line in the history
            SearchRecord record = new SearchRecord(
                    latestSnapshot.getBaseCurrency(),
                    entry.getKey(),
                    entry.getValue(),
                    LocalDateTime.now()
            );
            fileService.save(record);
        }

        return latestSnapshot;
    }

    public double convert(double amount, String targetCurrency) {      // converts an amount using the last fetched rates, returns -1 if not loaded yet
        if (latestSnapshot == null) {
            return -1;
        }
        try {
            return latestSnapshot.convert(amount, targetCurrency);
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }


    public List<SearchRecord> loadHistory() throws IOException {      // this loads all records from the history
        return fileService.loadAll();
    }

    public void clearHistory() throws IOException {      // deletes history
        fileService.clear();
    }
}