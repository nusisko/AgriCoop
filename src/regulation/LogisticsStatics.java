package regulation;

import logistics.delivery.models.Address;
import logistics.delivery.models.Tariff;

public class LogisticsStatics {

    /**
     * Fixed price for long distance logistics per trip and per kilometer
     *
     * @see Tariff
     */
    private static double fixedPriceLongDistance = .5f;

    private static Address cooperativeAddress = new Address("Guadalajara", "Guadalajara", "Calle La Cooperativa", 1, "");

    private static int maxDistanceTravelPerishable = 100;

    private static int maxDistanceTravelNonPerishable = 50;

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

    public static void updateCooperativeAddress(Address newCooperativeAdress) {
        cooperativeAddress = newCooperativeAdress;
    }

    public static Address getCooperativeAddress() {
        return cooperativeAddress;
    }

    public static int getMaxDistanceTravelPerishable() {
        return maxDistanceTravelPerishable;
    }

    public static void updateMaxDistanceTravelPerishable(int newMaxDistanceTravelPerishable) {
        if (newMaxDistanceTravelPerishable > 0) {
            maxDistanceTravelPerishable = newMaxDistanceTravelPerishable;
        } else {
            throw new IllegalArgumentException("Invalid distance, enter a positive distance");
        }
    }

    public static int getMaxDistanceTravelNonPerishable() {
        return maxDistanceTravelNonPerishable;
    }

    public static void updateMaxDistanceTravelNonPerishable(int newMaxDistanceTravelNonPerishable) {
        if (newMaxDistanceTravelNonPerishable > 0) {
            maxDistanceTravelNonPerishable = newMaxDistanceTravelNonPerishable;
        } else {
            throw new IllegalArgumentException("Invalid distance, enter a positive distance");
        }
    }

}
