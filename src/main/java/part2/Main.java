package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        // TODO: Find the BigMac entry for Canada in the year 2022// Start a stream so we can process all BigMac records
        // Keep only records where the country is Canada
        // Keep only records where the year is 2022
        // Take the first matching result
        // If no result is found, use null
        BigMac canada2022 = bigMacs.stream()
                .filter(bigMac -> bigMac.country().equals("Canada"))
                .filter(bigMac -> bigMac.year() == 2022)
                .findFirst()
                .orElse(null);


        // TODO: Create a list containing only the data for Canada
        // Start a stream to process all BigMac records
        // Keep only records where the country is Canada
        // Convert the filtered results back into a List
        List<BigMac> canadaData = bigMacs.stream()
                .filter(bigMac -> bigMac.country().equals("Canada"))
                .toList();

        // TODO: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically
        // Start a stream to process all BigMac records
        // Convert each BigMac into a String like "Canada: CAD"
        // Remove duplicate entries
        // Sort the list alphabetically
        // Convert the results into a List
        List<String> countryCurrencyList = bigMacs.stream()
                .map(bigMac -> bigMac.country() + ": " + bigMac.currency())
                .distinct()
                .sorted()
                .toList();


        // TODO: Print the most recent 5 years of data for Canada
        // Start a stream to process all BigMac records
        // Keep only records where the country is Canada
        // Sort records by year in descending order (newest first)
        // Take only the first 5 records
        // Print each record to the console
        bigMacs.stream()
                .filter(bigMac -> bigMac.country().equals("Canada"))
                .sorted((a, b) -> Integer.compare(b.year(), a.year()))
                .limit(5)
                .forEach(System.out::println);


        // TODO: Print the data for countries with a 2022 BigMac price less than $2 USD
        //Start a stream to process all BigMac records
        // Keep only records from the year 2022
        // Keep only records where the USD price is less than 2
        // Print each matching record
        bigMacs.stream()
                .filter(bigMac -> bigMac.year() == 2022)
                .filter(bigMac -> bigMac.usdPrice() < 2.0)
                .forEach(System.out::println);


        // TODO: Calculate the average USD price of BigMacs in 2022 over all countries
        // Start a stream to process all BigMac records
        // Keep only records from the year 2022
        // Convert each BigMac into its USD price (double value)
        // Calculate the average of those prices
        // If no data exists, return 0.0
        double averageUsdPrice = bigMacs.stream()
                .filter(bigMac -> bigMac.year() == 2022)
                .mapToDouble(BigMac::usdPrice)
                .average()
                .orElse(0.0);
    }

    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            return lines.map(Main::parseCsvLine)


                    .toList();

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
