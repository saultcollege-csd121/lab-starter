package part2;

public record BigMac(
        int year,
        String currency,
        String country,
        double localPrice,
        double exchangeRate,
        double usdPrice) {
    public static int getYear(BigMac burger){return burger.year;}
    public String getCurrency(BigMac burger){
        return burger.currency;
    }
    public String getCountry(BigMac burger){
        return burger.country;
    }
    public double getLocalPrice(BigMac burger){
        return burger.localPrice;
    }
    public double getExchangeRate(BigMac burger){
        return burger.exchangeRate;
    }
    public double getUSD(BigMac burger){
        return burger.usdPrice;
    }
}