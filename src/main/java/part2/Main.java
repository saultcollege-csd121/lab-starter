package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Main {

    static void main(String[] args) {
        var bigMacs = loadData();


        // TODO: Find the BigMac entry for Canada in the year 2022 - something bigmac.country == canada && bigmac.year == 2022
        List<BigMac> burger = bigMacs.stream().filter(b -> b.year()==2022).filter(b -> Objects.equals(b.country(), "Canada")).toList(); // DONE
        // comment time! List<BigMac> to declare it being a list, .stream.filter to sort out ONLY the values that contain the year 2022, and ANOTHER filter to fing the one for canada, then finally toList to convert from a stream

        // TODO: Create a list containing only the data for Canada - KEYWORD BEING LIST!! but in one instruction?? ugh
        List<BigMac> CanadianBurgers = bigMacs.stream().filter(b -> Objects.equals(b.country(), "Canada")).toList(); // DONE
// its a list again, and thankfully its more or less the same process, but even simpler! this time I just have to filter it once because the only restriction is canada

        // TODO: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically
        List<String> CountryMoney = bigMacs.stream().filter(b -> b.year()==2000).distinct().sorted((b1, b2) -> (b1.country()).compareTo(b2.country())).map(b -> b.country() + ": " + b.localPrice()).toList(); // <---- FINISH THIs

        // HOLY THATS A LONG LINE
// a little more complicated this time, still a list and still filter (this might negate the distinct function? <-- IT DOESN'T, i needed this.)
// but then an added distinct function to remove duplicates, sorted function to alphabetise it, and formatted with a mapping function ^-^

        // TODO: Print the most recent 5 years of data for Canada
        System.out.println(bigMacs.stream().filter(b -> b.year()>=2017).filter(b -> Objects.equals(b.country(), "Canada")).toList()); // DONE
// a little different to start because I just need to print it, but otherwise still filtering.
// I wasn't sure if you meant five most recent years in the list, or five recent from TODAY, so i just went with the list.
// the list ends at 2022, so i made it filter for the years that are more than 2017, though i'm almost certain that's not the most efficient.
// it being hard coded makes it a bit more prone to breaking in the future. I'll fix it if i have time after everything else!

        // TODO: Print the data for countries with a 2022 BigMac price less than $2 USD
        System.out.println(bigMacs.stream().filter(b -> b.year()==2022).filter(b -> b.usdPrice()<2).toList()); // DONE
// back to the basics, declaring a list, filtering for the year 2022, and then filtering the usd price. helps that i didn't have to calculate that :3

        // TODO: Calculate the average USD price of BigMacs in 2022 over all countries
        double Price2022 = bigMacs.stream().filter(b -> b.year()==2022).mapToDouble(BigMac::usdPrice).sum()/(bigMacs.stream().filter(b -> b.year()==2022).count()); // how to do this in one line??? -> OMFG I THINK I GOT IT
        // WOW this was a little more of a pain. I made the variable a double because I figured that was the most reasonable for an average.
        // filter to make sure we're ONLY looking at 2022 prices (i almost forgot that!!!)
        // map to double with a method reference to take out only the usd price, because that's all i care about, with a sum function because you have to add to get the average
        // in order to get the divisor, I almost made it more complicated than it needed to be, but i simply took the stream again, filtered to find the year i wanted, then simply counted how many there were

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
