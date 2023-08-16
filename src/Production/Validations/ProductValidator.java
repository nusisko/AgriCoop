package Production.Validations;

import Production.Models.Product;

import java.util.ArrayList;

/**
 * Utility class that validates the product properties
 *
 * @version 1.0.0
 * @see Product
 */
/* Declared as package-private to prevent access from outside the package */
/* Declared as final to prevent inheritance */

public final class ProductValidator {

    /**
     * Collection of the products created
     */
    /* Declared as final to prevent reassignment of the object reference; still mutable */
    private static final ArrayList<Product> productDirectory = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation
     */
    private ProductValidator() {
        throw new AssertionError("ProductValidator class should not be instantiated.");
    }

    /**
     * Validates that the value is positive
     *
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is negative
     */
    public static void validatePositiveValue(float value) {
        if (value < 0) {
            throw new IllegalArgumentException("Invalid value: " + value + "\n Enter a positive value.");
        }
    }

    /**
     * Validates that the product name is not null and that it does not already exist in the product collection
     *
     * @param productName the name of the product to validate
     * @throws NullPointerException     if the product name is null
     * @throws IllegalArgumentException if the product already exists in the product collection
     */
    public static void validateNewProduct(String productName) {
        if (productName == null) {
            throw new NullPointerException("Product name cannot be null.");
        }
        for (Product product : productDirectory) {
            if (product.getName().equals(productName)) {
                throw new IllegalArgumentException("Product already exists in the product collection.");
            }
        }
    }

    /**
     * Registers a product in the product collection
     *
     * @param product the product to register
     */
    public static void registerProduct(Product product) {
        productDirectory.add(product);
    }
}
