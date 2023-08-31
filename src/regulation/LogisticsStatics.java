package regulation;

import logistics.delivery.models.Tariff;

public class LogisticsStatics {

    /**
     * Fixed price for long distance logistics per trip and per kilometer
     *
     * @see Tariff
     */
    private static double fixedPriceLongDistance = .5f;

    /**
     * Private constructor to prevent instantiation
     *
     * @throws AssertionError when called
     */
    private LogisticsStatics() {
        throw new AssertionError("LogisticsStatics class should not be instantiated.");
    }

    /**
     * Updates the fixed price for long distance logistics per trip and per kilometer
     *
     * @param newFixedPriceLongDistance the new fixed price for long distance logistics per trip and per kilometer
     * @throws IllegalArgumentException if the new limit is negative
     */
    public static void updateFixedPriceBigLogistics(double newFixedPriceLongDistance) {
        if (newFixedPriceLongDistance < 0) {
            throw new IllegalArgumentException("Invalid value: " + newFixedPriceLongDistance + "\n Enter a positive value.");
        }
        fixedPriceLongDistance = newFixedPriceLongDistance;
    }

    /**
     * Returns the fixed price for long distance logistics per trip and per kilometer
     *
     * @return the fixed price for long distance logistics per trip and per kilometer
     */
    public static double getFixedPriceLongDistance() {
        return fixedPriceLongDistance;
    }


}
