package Production;

/**
 * Class that represents a crop
 * Used to link a product with its extension
 *
 * @version 1.0.0
 * @see Product
 */
public class Crop {

    /**
     * Product used in the crop
     */
    private Product product;

    /**
     * Total extension of the crop (in hectares)
     */
    private float extension;

    /**
     * Creates a new crop with the specified product and extension
     *
     * @param product   Product used in the crop
     * @param extension Total extension of the crop (in hectares)
     * @throws IllegalArgumentException if the extension is negative
     * @see ProductValidator
     */
    public Crop(Product product, float extension) {
        this.product = product;
        ProductValidator.validatePositiveValue(extension);
        this.extension = extension;

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getExtension() {
        return extension;
    }

    public void setExtension(float extension) {
        this.extension = extension;
    }

    public void addExtension(float extension) {
        this.extension += extension;
    }

    @Override
    public String toString() {
        return "Crop{" + "product=" + product + ", extension=" + extension + '}';
    }
}
