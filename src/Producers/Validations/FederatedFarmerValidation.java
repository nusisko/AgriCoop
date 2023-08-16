package Producers.Validations;

import Producers.Models.FederatedFarmer;
import Producers.Models.SmallFarmer;
import Production.Models.Crop;
import Production.Models.Product;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class FederatedFarmerValidation {
    private static final ArrayList<FederatedFarmer> federatedFarmersDirectory = new ArrayList<>();


    private FederatedFarmerValidation() {
        throw new AssertionError("FederatedFarmersValidation class should not be instantiated.");
    }

    public static void validateProductType(Product tipoProducto) {
        if (!federatedFarmersDirectory.isEmpty()) {
            for (FederatedFarmer federatedFarmer : federatedFarmersDirectory) {
                if (federatedFarmer.getProductType().equals(tipoProducto)) {
                    throw new IllegalArgumentException("Ya existe un productor federado con el producto: " + tipoProducto);
                }
            }
        }
    }

    /**
     * Registers a federated farmer in the federated farmer directory
     *
     * @param federatedFarmer the federated farmer to register
     */
    public static void registerFederatedFarmer(FederatedFarmer federatedFarmer) {
        federatedFarmersDirectory.add(federatedFarmer);
    }

    public static @NotNull HashMap<SmallFarmer, Float> initializeFederationCropMap(@NotNull HashSet<SmallFarmer> associates) {
        HashMap<SmallFarmer, Float> map = new HashMap<>();
        for (SmallFarmer associate : associates) {
            map.put(associate, 0.0f);
        }
        return map;
    }

    public static void validateAsociate(SmallFarmer smallFarmer, HashSet<SmallFarmer> associates) {
        if (!associates.contains(smallFarmer)) {
            throw new IllegalArgumentException("The farmer is not an associate");
        }
    }

    public static void validateCropOwnership(Crop crop, SmallFarmer smallFarmer) {
        if (!smallFarmer.getProduction().contains(crop)) {
            throw new IllegalArgumentException("The crop does not belong to the given farmer");
        }
    }
}
