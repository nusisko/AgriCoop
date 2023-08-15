package Production;

/**
 * Class that represents a product
 *
 * @version 1.0.0
 */
public class Product {

    /**
     * Product name
     */
    private String name;

    /**
     * Product price in euros per kilogram without taxes
     */
    private float price;

    /**
     * Product performance in tonnes per hectare
     */
    private double performance;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
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
