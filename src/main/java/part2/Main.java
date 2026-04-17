package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        // TODO: Find the BigMac entry for Canada in the year 2022
        var cad2022 = bigMacs.stream().filter(a -> a.country().equals("Canada")&& a.year()==2022);
        //System.out.println(cad2022);

        // TODO: Create a list containing only the data for Canada
        var cad = (bigMacs.stream().filter(a->a.country().equals("Canada"))
                .toList());//Makes List
        //System.out.println(cad);


        // TODO: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically
        var currency = (bigMacs.stream().map(a->a.country()+" : "+a.currency()).distinct()
                .sorted()//Sorts alphabetically
                .toList());//Makes List
        //System.out.println(currency);

        // TODO: Print the most recent 5 years of data for Canada
        bigMacs.stream().filter(a->(a.year()>(bigMacs.stream().map(BigMac::year)//Maps big mac to it's year
                        .mapToInt(Integer::intValue).max()//Ints the Ints
                        .getAsInt()-5)//Gets the last 5 years from the ints
                        && a.country().equals("Canada")))// Makes sure it ALSO(&) is Canada
                .forEach(System.out::println);//Prints
       //System.out.println(cad5);

        // TODO: Print the data for countries with a 2022 BigMac price less than $2 USD
        bigMacs.stream().filter(a1->(bigMacs.stream().filter(a->a.usdPrice()<2 && a.year()==2022)// Gets burger price less than $2 and is in 2022
                .map(BigMac::country)// Maps to country
                        .distinct()//distinct
                        .toList()//Makes List
                ).contains(a1.country())).forEach(System.out::println);//Prints
       // System.out.println(data2022);


        // TODO: Calculate the average USD price of BigMacs in 2022 over all countries
        Double usdAVG2022 = bigMacs.stream().filter(a->a.year()==2022).map(BigMac::usdPrice)
                .mapToDouble(Double::doubleValue)//turns into a Double stream
                .average()//AVERAGE
                .getAsDouble();
        //System.out.println(usdAVG2022);


    }

    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
            return ((lines.map(Main::parseCsvLine).toList()));

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
