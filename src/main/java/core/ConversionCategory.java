package core;

import java.util.List;

/**
 * An enum representing the categories of unit conversions available
 * TODO
 */
public enum ConversionCategory {

    LENGTH("Length", List.of(
            new Conversion("km → miles",       x -> x * 0.621371),
            new Conversion("miles → km",       x -> x * 1.60934),
            new Conversion("metres → feet",    x -> x * 3.28084),
            new Conversion("feet → metres",    x -> x / 3.28084),
            new Conversion("cm → inches",      x -> x / 2.54),
            new Conversion("inches → cm",      x -> x * 2.54)
    )),

    TEMPERATURE("Temperature", List.of(
            new Conversion("°C → °F",          x -> x * 9.0 / 5.0 + 32),
            new Conversion("°F → °C",          x -> (x - 32) * 5.0 / 9.0),
            new Conversion("°C → Kelvin",      x -> x + 273.15),
            new Conversion("Kelvin → °C",      x -> x - 273.15)
    )),

    WEIGHT("Weight", List.of(
            new Conversion("kg → pounds",      x -> x * 2.20462),
            new Conversion("pounds → kg",      x -> x / 2.20462),
            new Conversion("grams → ounces",   x -> x * 0.035274),
            new Conversion("ounces → grams",   x -> x / 0.035274)
    ));

    //The human-readable label shown in the category selector
    private final String label;

    // The list of conversions belonging to this category
    private final List<Conversion> conversions;

    /**
     * Constructs a ConversionCategory with a display label and its conversions
     * TODO
     */
    ConversionCategory(String label, List<Conversion> conversions) {
        this.label = label;
        this.conversions = conversions;
    }

    // @return the display label for this category
    public String getLabel() { return label; }

    // @return the list of records in this category
    public List<Conversion> getConversions() { return conversions; }

    // Returns the label when this enum constant is displayed as a string
    @Override
    public String toString() { return label; }
}