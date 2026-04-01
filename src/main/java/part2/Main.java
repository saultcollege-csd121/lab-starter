package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var bigMacs = loadData();

        // 1. Find the BigMac entry for Canada in 2022
        var canada2022 = bigMacs.stream()
                .filter(b -> b.country().equals("Canada") && b.year() == 2022)
                .findFirst();
        System.out.println("Canada 2022: " + canada2022);

        // 2. Create a list containing only the data for Canada
        var canadaList = bigMacs.stream()
                .filter(b -> b.country().equals("Canada"))
                .toList();
        System.out.println("Canada entries: " + canadaList);

        // 3. Create a list containing strings of the format "<country>: <currency>". No duplicates.
        var countryCurrency = bigMacs.stream()
                .map(b -> b.country() + ": " + b.currency())
                .distinct()
                .sorted()
                .toList();
        System.out.println("Country/Currency: " + countryCurrency);

        // 4. Print the most recent 5 years of data for Canada
        bigMacs.stream()
                .filter(b -> b.country().equals("Canada"))
                .sorted(Comparator.comparingInt(BigMac::year).reversed())
                .limit(5)
                .forEach(System.out::println);

        // 5. Print the data for countries with a 2022 BigMac price less than $2 USD
        bigMacs.stream()
                .filter(b -> b.year() == 2022 && b.usdPrice() < 2.0)
                .forEach(System.out::println);

        // 6. Calculate the average USD price of BigMacs in 2022 over all countries
        var average = bigMacs.stream()
                .filter(b -> b.year() == 2022)
                .mapToDouble(BigMac::usdPrice)
                .average();
        System.out.println("Average 2022 USD price: " + average);
    }

    /**
     * Loads BigMac price data into a List of BigMac records.
     * @return a list of BigMac records
     */
    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            return lines.map(Main::parseCsvLine)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parses a CSV line into a BigMac record.
     * @param line the CSV line to parse
     * @return a BigMac record containing the parsed data
     */
    public static BigMac parseCsvLine(String line) {
        var values = line.split(",");
        return new BigMac(
                Integer.parseInt(values[0].substring(0, 4)),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                Double.parseDouble(values[4]),
                Double.parseDouble(values[5]));
    }
}
