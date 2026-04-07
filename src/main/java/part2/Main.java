package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        Optional<BigMac> canada2022 = bigMacs.stream()
                .filter(b -> Objects.equals(b.country(), "Canada") && b.year() == 2022) // filters by the country record "Canada" AND the year 2022
                .findFirst();// finds the first element of the stream using the filter above - this returns an Optional in the case there could be zero matches.
        System.out.println(canada2022);

        List<BigMac> canadaData = bigMacs.stream()
                .filter(b -> Objects.equals(b.country(), "Canada")) // filters by the country "Canada"
                .toList(); // takes all relevant data with the filter above and puts it in a list
        System.out.println(canadaData);

        List<String> moneys = bigMacs.stream()
                .map(b -> b.country() + ": " + b.currency()) // map string to display "country:currency"
                .distinct() // no duplicates
                .sorted()// sorts the data in alphabetical order
                .toList(); // and puts it into a list
        System.out.println(moneys);

        bigMacs.stream()
                .filter(b -> Objects.equals(b.country(), "Canada")) // sort country by "Canada"
                .sorted(Comparator.comparingInt(BigMac::year).reversed())// create a comparator which allows precise control of sorting, in this case the years are compared and are sorted in descending order
                .limit(5) // this only gets the first 5 results of the above "filter/sort" operations
                .forEach(System.out::println); // this runs the action for each element in the stream, which is set to 5 with the limit above

        bigMacs.stream()
                .filter(b -> b.year() == 2022 && b.usdPrice() < 2.0) // filter for year 2022 and price being less than 2$
                .forEach(System.out::println); // for each element with the matching filter above. print that data to the console

        OptionalDouble avg = bigMacs.stream()
                .filter(b -> b.year() == 2022) // filter by the year 2022
                .mapToDouble(BigMac::usdPrice) // get all BigMac prices from 2022 from all countries
                .average(); // returns the average as an OptionalDouble
        System.out.println(avg);

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
