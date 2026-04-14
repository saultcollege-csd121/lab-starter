package core;

import java.util.List;

/**
 * Represents the available categories of unit conversions: Length, Temperature, and Weight
 *
 * Each constant holds a display label and a list of Conversion records
 * that belong to that category. For example LENGTH holds conversions
 * like: km → miles and feet → metres
 *
 * It belongs to the core package, so it has no knowledge of UI or IO
 * It just organizes and provides conversion data
 * @see Conversion
 */
public enum ConversionCategory {

    LENGTH("Length", List.of(
        new Conversion("km → miles",x -> x * 0.621371),
        new Conversion("miles → km",x -> x * 1.60934),
        new Conversion("metres → feet",x -> x * 3.28084),
        new Conversion("feet → metres",x -> x / 3.28084),
        new Conversion("cm → inches",x -> x / 2.54),
        new Conversion("inches → cm",x -> x * 2.54)
    )),

    TEMPERATURE("Temperature", List.of(
        new Conversion("°C → °F",x -> x * 9.0 / 5.0 + 32),
        new Conversion("°F → °C",x -> (x - 32) * 5.0 / 9.0),
        new Conversion("°C → Kelvin",x -> x + 273.15),
        new Conversion("Kelvin → °C",x -> x - 273.15)
    )),

    WEIGHT("Weight", List.of(
        new Conversion("kg → pounds",x -> x * 2.20462),
        new Conversion("pounds → kg",x -> x / 2.20462),
        new Conversion("grams → ounces",x -> x * 0.035274),
        new Conversion("ounces → grams",x -> x / 0.035274)
    ));

    //The human-readable label shown in the category selector
    private final String label;

    // The list of conversions belonging to this category
    private final List<Conversion> conversions;

    /**
     * Constructs a category with its label and conversions
     * This constructor is called automatically when the enum constants are created
     */
    ConversionCategory(String label, List<Conversion> conversions) {
        this.label = label;
        this.conversions = conversions;
    }

    /** @return the display label for this category */
    public String getLabel() {
        return label;
    }

    /** @return the list of conversions in this category */
    public List<Conversion> getConversions() {
        return conversions;
    }

    /**
     * Overrides toString() so that the ComboBox displays the label
     * instead of the default enum constant name (e.g. "LENGTH").
     */
    @Override
    public String toString() {
        return label;
    }
}