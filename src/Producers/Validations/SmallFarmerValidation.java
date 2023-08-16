package Producers.Validations;

import Production.Models.Crop;
import Regulation.CooperativeStatics;

import java.util.ArrayList;

public final class SmallFarmerValidation {

    private SmallFarmerValidation() {
        throw new AssertionError("SmallFarmerValidation class should not be instantiated.");
    }

     public static void validateProduction(ArrayList<Crop> production) {
        float totalExtension = 0.f;
        if (!production.isEmpty()) {
            for (Crop crop : production) {
                totalExtension += crop.getExtension();
            }
            validateExtension(totalExtension);
        }
    }
    public static void validateExtension(float extension) {
        if (extension > CooperativeStatics.getSmallProducerMaxExtension()) {
            throw new IllegalArgumentException("Extension exceeds the limit of " + CooperativeStatics.getSmallProducerMaxExtension() + " hectares.");
        }
    }
}
