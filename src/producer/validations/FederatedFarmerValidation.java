package producer.validations;

import producer.models.FederatedFarmer;
import producer.models.SmallFarmer;
import production.models.Crop;
import production.models.Product;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Utility class that has the collection of validations for Federated Farmers class
 *
 * @version 1.0.0
 * @see FederatedFarmer
 * @see SmallFarmer
 * @see Crop
 * @see Product
 **/
public final class FederatedFarmerValidation {

    /**
     * The collection of federated farmers
     */
    private static final ArrayList<FederatedFarmer> federatedFarmersDirectory = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation
     *
     * @throws AssertionError when called
     */
    private FederatedFarmerValidation() {
        throw new AssertionError("FederatedFarmersValidation class should not be instantiated.");
    }

    /**
     * Validates that the product type is not already registered in the federated farmers directory
     *
     * @param tipoProducto the product type to validate
     * @throws IllegalArgumentException if the product type is already registered
     */
    public static void validateProductType(Product tipoProducto) {
        if (tipoProducto == null) {
            throw new NullPointerException("The product type cannot be null");
        }
        if (!federatedFarmersDirectory.isEmpty()) {
            for (FederatedFarmer federatedFarmer : federatedFarmersDirectory) {
                if (federatedFarmer.getProductType().equals(tipoProducto)) {
                    throw new IllegalArgumentException("Already exists a federated farmer with the same product type");
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

    /**
     * Validates that the given farmer is an associate of the federated farmer
     *
     * @param smallFarmer the farmer to validate
     * @param associates  the collection of associates of the federated farmer
     * @throws IllegalArgumentException if the farmer is not an associate
     */
    public static void validateAsociate(SmallFarmer smallFarmer, HashSet<SmallFarmer> associates) {
        if (!associates.contains(smallFarmer)) {
            throw new IllegalArgumentException("The farmer is not an associate");
        }
    }

    /**
     * Validates that the given crop belongs to the given farmer
     *
     * @param crop        the crop to validate
     * @param smallFarmer the farmer to validate
     * @throws IllegalArgumentException if the crop does not belong to the farmer
     */
    public static void validateCropOwnership(Crop crop, SmallFarmer smallFarmer) {
        if (!smallFarmer.getProduction().contains(crop)) {
            throw new IllegalArgumentException("The crop does not belong to the given farmer");
        }
    }
}

