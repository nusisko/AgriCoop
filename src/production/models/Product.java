package production.models;

import production.validations.ProductValidator;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a product
 *
 * @version 1.0.0
 */
public class Product{

    /**
     * Product name
     */
    private final String name;

    /**
     * Product price in euros per kilogram without taxes
     */
    private float price;

    /**
     * Product performance in tonnes per hectare
     */
    private float performance;

    /**
     * Defines the perishable property of a product
     */
    private boolean isPerishable;

    /**
     * Creates a new Product object with the specified name and price.
     *
     * @param name         the name of the product
     * @param price        the price of the product
     * @param performance  the performance of the product
     * @param isPerishable determines if the product is perishable or not
     * @throws IllegalArgumentException if the price is negative or if the performance is negative
     * @throws NullPointerException     if the name is null
     * @throws IllegalArgumentException if the product already exists in the product collection
     * @see ProductValidator
     */
    public Product(String name, float price, float performance, boolean isPerishable) {
        ProductValidator.validatePositiveValue(price);
        ProductValidator.validatePositiveValue(performance);
        ProductValidator.validateNewProduct(name);
        this.name = name;
        this.price = price;
        this.performance = performance;
        this.isPerishable = isPerishable;
        ProductValidator.registerProduct(this);
    }

    /**
     * Creates a deep copy of the product passed as parameter
     *
     * @param productToCopy the product to copy
     * @throws NullPointerException if the product is null
     */
    public Product(@NotNull Product productToCopy) {
        this.name = productToCopy.getName();
        this.price = productToCopy.getPrice();
        this.performance = productToCopy.getPerformance();
        this.isPerishable = productToCopy.isPerishable();
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    /**
     * Sets the price of the product
     * @param price the price of the product
     * @throws IllegalArgumentException if the price is negative
     */
    public void setPrice(float price) {
        ProductValidator.validatePositiveValue(price);
        this.price = price;
    }

    public float getPerformance() {
        return performance;
    }

    /**
     * Sets the performance of the product
     * @param performance the performance of the product
     * @throws IllegalArgumentException if the performance is negative
     */
    public void setPerformance(float performance) {
        ProductValidator.validatePositiveValue(performance);
        this.performance = performance;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public void setPerishable(boolean perishable) {
        isPerishable = perishable;
    }

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", price=" + price + ", performance=" + performance + ", isPerishable=" + isPerishable + '}';
    }
}
