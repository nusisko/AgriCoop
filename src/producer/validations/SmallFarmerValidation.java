package producer.validations;

import producer.models.SmallFarmer;
import production.models.Crop;
import regulation.CooperativeStatics;
import java.util.ArrayList;

/**
 * Utility class that has the collection of validations for Small Farmer class
 *
 * @version 1.0.0
 * @see SmallFarmer
 * @see Crop
 * @see CooperativeStatics
 **/
public final class SmallFarmerValidation {

    /**
     * Private constructor to prevent instantiation
     *
     * @throws AssertionError when called
     */
    private SmallFarmerValidation() {
        throw new AssertionError("SmallFarmerValidation class should not be instantiated.");
    }

    /**
     * Validates that the production of the farmer does not exceed the maximum extension allowed
     * @param production the production of the farmer
     */
     public static void validateProductionExtension(ArrayList<Crop> production) {
        float totalExtension = 0.f;
        if (!production.isEmpty()) {
            for (Crop crop : production) {
                totalExtension += crop.getExtension();
            }
            validateExtension(totalExtension);
        }
    }

    /**
     * Validates that the extension of the farmer does not exceed the maximum extension allowed
     * @param extension the extension of the farmer
     */
    public static void validateExtension(float extension) {
        if (extension > CooperativeStatics.getSmallProducerMaxExtension()) {
            throw new IllegalArgumentException("Extension exceeds the limit of " + CooperativeStatics.getSmallProducerMaxExtension() + " hectares.");
        }
    }
}
