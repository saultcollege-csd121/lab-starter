package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        // Find the BigMac entry for Canada in the year 2022
        BigMac canada2022 = bigMacs.stream()
            .filter(b -> b.country().equals("Canada") && b.year() == 2022)  // filter() goes through every BigMac and keeps only the ones where
            .findFirst() // returns the first element that passed the filter (Optional)
            .orElseThrow(); // get the actual object, or throws an exception if nothing was found
        System.out.println(canada2022);

        // Create a list containing only the data for Canada
        List<BigMac> canadaData = bigMacs.stream()
            .filter(b -> b.country().equals("Canada")) // filter() keeps only entries where country is Canada
            .toList(); // accumulates all the remaining elements into a List
        System.out.println(canadaData);

        // Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically
        List<String> countryCurrency = bigMacs.stream()
            .map(b -> b.country() + ": " + b.currency()) // transforms each BigMac following the format e.g "Canada: CAD"
            .distinct() // removes duplicate strings
            .sorted() // sorts alphabetically
            .toList(); // accumulates all the remaining elements into a List
        System.out.println(countryCurrency);

        // Print the most recent 5 years of data for Canada
        bigMacs.stream()
            .filter(b -> b.country().equals("Canada")) // keeps only Canada entries
            .sorted(Comparator.comparingInt(BigMac::year).reversed()) // BigMac::year means: use the year() method of each BigMac object
                // same as writing: (b -> b.year())
                // comparingInt(BigMac::year) sorts by the year field numerically
                // reversed() flips it so the most recent comes first
            .limit(5) // keeps only the first 5 results
            .forEach(System.out::println); // print each item using println() (obs: same as: x -> System.out.println(x))

        // Print the data for countries with a 2022 BigMac price less than $2 USD

        bigMacs.stream()
            .filter(b -> b.year() == 2022 && b.usdPrice() < 2.0) // keeps only 2022 entries where the usd price is < 2
            .forEach(System.out::println); // print each item using println()

        // Calculate the average USD price of BigMacs in 2022 over all countries

        double average2022 = bigMacs.stream()
            .filter(b -> b.year() == 2022) // keeps only 2022 entries
            .mapToDouble(BigMac::usdPrice) // mapToDouble() extracts the usdPrice field from each BigMac and converts into a stream of primitives
            .average() // adds up all the usdPrice values and divides by the count (Obs: not accepted as a stream)
            .orElseThrow(); // get the actual value, or throws an exception if nothing was found
        System.out.println("Average 2022 USD price: " + average2022);
    }

    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            return lines.map(Main::parseCsvLine).toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse a CSV line into a BigMac record. The expected format is:
     * year,currency,country,localPrice,exchangeRate,usdPrice
     * @param line the CSV line to parse
     * @return a BigMac record containing the data from the CSV line
     */
    public static BigMac parseCsvLine(String line) {
        var values = line.split(",");
        return new BigMac(
                Integer.parseInt(values[0].substring(0,4)),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                Double.parseDouble(values[4]),
                Double.parseDouble(values[5]));
    }
}
