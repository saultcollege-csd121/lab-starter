package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        // TODO: Find the BigMac entry for Canada in the year 2022
        var canada2022 = bigMacs.stream()
                .filter(b -> b.country().equals("Canada") && b.year() == 2022) // filters for canada(country) and for 2022(year)
                .findFirst()
                .orElse(null);

        System.out.println(canada2022);

        // TODO: Create a list containing only the data for Canada
        var canadaBigMacs = bigMacs.stream()
                .filter(b -> b.country().equals("Canada")) //filters for canada(country)
                .toList(); // puts data to list
        System.out.println(canadaBigMacs);



        // TODO: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically
        var countryCurrency = bigMacs.stream()
                .map(b -> b.country() + " " + b.currency()) // formats data as {country currency}
                .distinct() // removes duplicates
                .sorted() // sorts country/currency alphabetically
                .toList(); // puts data into list
        System.out.println(countryCurrency);


        // TODO: Print the most recent 5 years of data for Canada
        var canadaRecentData = bigMacs.stream()
                .filter( b -> b.country().equals("Canada") && (b.year() >= 2017)); // filters for data containing Canada past the year 2017
        System.out.println(canadaRecentData);


        // TODO: Print the data for countries with a 2022 BigMac price less than $2 USD
        var bigMac2022 = bigMacs.stream()
                .filter(b -> b.usdPrice() < 2 && (b.year() == 2022));// filters for only
        System.out.println(bigMac2022);

        // TODO: Calculate the average USD price of BigMacs in 2022 over all countries
        var averageUSDPrice = bigMacs.stream()
                .filter(b ->b.year() == 2022) // filters for 2022
                .mapToDouble(b -> b.usdPrice()) //
                .average()
                .orElse(0);
            System.out.println(averageUSDPrice);
    }

    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            return lines
                    .map( Main :: parseCsvLine )
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
