package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var bigMacs = loadData();

        // Find the BigMac entry for Canada in the year 2022
        var canada2022 = bigMacs.stream()
                .filter(x -> x.country().equals("Canada"))
                .filter(x -> x.year() == 2022)
                .findFirst();

        System.out.println("Canada in 2022:");
        System.out.println(canada2022);

        // Create a list containing only the data for Canada
        var canadaOnly = bigMacs.stream()
                .filter(x -> x.country().equals("Canada"))
                .toList();

        System.out.println("\nOnly Canada:");
        canadaOnly.forEach(System.out::println);

        // Create a list containing strings of the format "<country>: <currency>"
        // no duplicates and sorted
        var countryCurrency = bigMacs.stream()
                .map(x -> x.country() + ": " + x.currency())
                .distinct()
                .sorted()
                .toList();

        System.out.println("\nCountry and currency:");
        countryCurrency.forEach(System.out::println);

        // Print the most recent 5 years of data for Canada
        var recentCanada = bigMacs.stream()
                .filter(x -> x.country().equals("Canada"))
                .sorted(Comparator.comparing(BigMac::year).reversed())
                .limit(5)
                .toList();

        System.out.println("\nRecent 5 years for Canada:");
        recentCanada.forEach(System.out::println);

        // Print the data for countries with a 2022 BigMac price less than $2 USD
        var lessThan2 = bigMacs.stream()
                .filter(x -> x.year() == 2022)
                .filter(x -> x.usdPrice() < 2)
                .toList();

        System.out.println("\n2022 countries with BigMac under $2 USD:");
        lessThan2.forEach(System.out::println);

        // Calculate the average USD price of BigMacs in 2022 over all countries
        var avg2022 = bigMacs.stream()
                .filter(x -> x.year() == 2022)
                .mapToDouble(BigMac::usdPrice)
                .average()
                .orElse(0);

        System.out.println("\nAverage 2022 USD price: " + avg2022);
    }

    public static List<BigMac> loadData() {
        try (var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            return lines
                    .map(Main::parseCsvLine)
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