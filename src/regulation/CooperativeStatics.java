package regulation;

import producer.models.SmallFarmer;

/**
 * Class that represents the cooperative statics values
 *
 * @version 1.0.0
 */
public class CooperativeStatics {

    /**
     * Maximum extension allowed for a small producer
     *
     * @see SmallFarmer
     */
    private static float smallProducerMaxExtension = 5.f;

    /**
     * Private constructor to prevent instantiation
     *
     * @throws AssertionError when called
     */
    private CooperativeStatics() {
        throw new AssertionError("CooperativeConstants class should not be instantiated.");
    }

    /**
     * Updates the maximum extension allowed for a small producer
     *
     * @param newLimit the new maximum extension allowed for a small producer
     * @throws IllegalArgumentException if the new limit is negative
     */
    public static void updateSmallProducerMaxExtension(float newLimit) {
        if (newLimit < 0) {
            throw new IllegalArgumentException("Invalid value: " + newLimit + "\n Enter a positive value.");
        }
        smallProducerMaxExtension = newLimit;
    }

    /**
     * Returns the maximum extension allowed for a small producer
     *
     * @return the maximum extension allowed for a small producer
     */
    public static float getSmallProducerMaxExtension() {
        return smallProducerMaxExtension;
    }


}

