package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents one entry in the search history.
 *  Every time the user refreshes rates, we save a SearchRecord for each currency.
 */
public class SearchRecord {

    private static final DateTimeFormatter FMT =      // format we use when saving from the file
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String baseCurrency;
    private final String targetCurrency;
    private final double rate;
    private final LocalDateTime timestamp;

    public SearchRecord(String base, String target, double rate, LocalDateTime timestamp) {
        this.baseCurrency   = base;
        this.targetCurrency = target;
        this.rate           = rate;
        this.timestamp      = timestamp;
    }

    public String getBaseCurrency()    { return baseCurrency; }
    public String getTargetCurrency()  { return targetCurrency; }
    public double getRate()            { return rate; }
    public LocalDateTime getTimestamp(){ return timestamp; }

    /** Converts this record to one CSV line for file storage. */
    public String toCsvLine() {
        return baseCurrency + "," + targetCurrency + "," + rate + "," + timestamp.format(FMT);
    }

    /** Builds a SearchRecord from a CSV line produced by toCsvLine(). */
    public static SearchRecord fromCsvLine(String line) {  // reads a line from the file and turns it back into a "SearchRecord"
        String[] p = line.split(",");
        if (p.length != 4) throw new IllegalArgumentException("Bad CSV line: " + line);
        return new SearchRecord(
                p[0].trim(), p[1].trim(),
                Double.parseDouble(p[2].trim()),
                LocalDateTime.parse(p[3].trim(), FMT)
        );
    }

    @Override
    public String toString() {       // what shows up in the history list
        return timestamp.format(FMT) + "  |  " + baseCurrency + " to " + targetCurrency
                + "  =  " + String.format("%.4f", rate);
    }
}