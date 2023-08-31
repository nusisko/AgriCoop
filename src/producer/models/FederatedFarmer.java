package producer.models;

import billing.Bill;
import logistics.warehouse.models.QuantityOwnerPair;
import logistics.warehouse.models.Stock;
import producer.models.interfaces.IHarvester;
import producer.validations.FederatedFarmerValidation;
import producer.validations.SmallFarmerValidation;
import production.models.Crop;
import production.models.FederatedCrop;
import production.models.Product;

import java.util.*;

/**
 * Class that represents a federated farmer that acts as group of small farmers that produce the same product
 *
 * @version 1.0.0
 * @see IHarvester
 * @see Farmer
 */
public class FederatedFarmer implements IHarvester{

    /**
     * Name of the federated farmer
     */
    private String name;
    /**
     * Unique product type of the federated farmer
     */
    private final Product productType;
    /**
     * Collection of the small farmers that are associates of the federated farmer
     */
    private final HashSet<SmallFarmer> associates;
    /**
     * Total extension of the federated farmer's production (in hectares)
     */
    private float totalExtension;
    /**
     * Collection of the federated farmer's crops
     */
    private final List<FederatedCrop> production;
    /**
     * Collection of the federated farmer's bills
     */
    private final List<Bill> sales = new ArrayList<>();

    /**
     * Creates a new FederatedFarmer object with the specified name, product type and associates
     *
     * @param name        the name of the federated farmer
     * @param productType the product type of the federated farmer
     * @param associates  the associates of the federated farmer
     * @throws NullPointerException if the product type is null
     * @throws IllegalArgumentException if the product type is already registered
     */
    public FederatedFarmer(String name, Product productType, HashSet<SmallFarmer> associates) {
        FederatedFarmerValidation.validateProductType(productType);
        this.name = name;
        this.productType = productType;
        this.associates = associates;
        this.totalExtension = .0f;
        this.production = new ArrayList<>();
        FederatedFarmerValidation.registerFederatedFarmer(this);
    }

    /**
     * Returns the name of the farmer
     *
     * @return name of the farmer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the farmer
     *
     * @param name the new name of the farmer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product type of the federated farmer
     *
     * @return product type of the federated farmer
     */
    public Product getProductType() {
        return new Product(productType);
    }

    /**
     * Returns the associates of the federated farmer
     *
     * @return associates of the federated farmer
     */
    public HashSet<SmallFarmer> getAssociates() {
        return this.associates;
    }

    /**
     * Adds a small farmer to the associates of the federated farmer
     *
     * @param associate the small farmer to add
     */
    public void addAssociate(SmallFarmer associate) {
        associates.add(associate);
    }

    /**
     * Returns the production of the federated farmer
     * @return production of the federated farmer
     */
    public List<FederatedCrop> getProduction() {
        return production;
    }

    /**
     * Adds a crop to the production of the federated farmer given the small farmer that owns the crop.
     * Then validates that the small farmer is an associate of the federated farmer and that the crop belongs to the small farmer.
     * Creates a new federated crop with the crop and the small farmer and adds it to the production of the federated farmer.
     * Finally, removes the crop from the production of the small farmer and updates the total extension of the federated farmer's production.
     * @param crop the crop to add
     * @param smallFarmer the small farmer that owns the crop
     * @throws IllegalArgumentException if the small farmer is not an associate of the federated farmer
     * @throws IllegalArgumentException if the crop does not belong to the small farmer
     * @throws IllegalArgumentException if the total extension of the federated farmer's production exceeds the maximum extension
     * @see FederatedCrop
     * @see SmallFarmerValidation
     */
    public void addCrop(Crop crop , SmallFarmer smallFarmer) {
        FederatedFarmerValidation.validateAsociate(smallFarmer, associates);
        FederatedFarmerValidation.validateCropOwnership(crop, smallFarmer);
        float extensionToAdd = crop.getExtension();
        SmallFarmerValidation.validateExtension(this.getTotalExtension() + extensionToAdd);
        FederatedCrop federatedCrop = new FederatedCrop(crop, smallFarmer);
        production.add(federatedCrop);
        smallFarmer.removeCrop(crop);
        setTotalExtension();
    }

    /**
     * Removes the crop passed as parameter from the production of the federated farmer
     * @param crop the crop to remove
     * @throws IllegalArgumentException if the small farmer is not an associate of the federated farmer
     * @throws IllegalArgumentException if the crop does not belong to the small farmer
     */
    public void removeCrop(Crop crop) {
        production.removeIf(federatedCrop -> federatedCrop.getCrop().equals(crop));
    }

    /**
     * Returns the small farmer that owns the crop passed as parameter
     * @param crop the crop to find its owner
     * @return the small farmer that owns the crop
     * @throws NullPointerException if the crop has no owner
     */
    public SmallFarmer findCropOwner(Crop crop) {
        SmallFarmer cropOwner = null;
        for (FederatedCrop federatedCrop : production) {
            if (federatedCrop.getCrop().equals(crop)) {
                cropOwner = federatedCrop.getProprietary();
                break;
            }
        }
        if (cropOwner==null){
            throw new NullPointerException("Crop has no Owner");
        }else{
            return cropOwner;
        }
    }

    /**
     * Returns the total extension of the federated farmer's production
     * @return total extension of the federated farmer's production
     */
    public float getTotalExtension() {
        setTotalExtension();
        return totalExtension;
    }

    /**
     * Calculates the total extension of the federated farmer's production
     */
    public void setTotalExtension() {
        float extension = .0f;
        if (!production.isEmpty()) {
            for (FederatedCrop fedaratedCrop : production) {
                extension += fedaratedCrop.getCrop().getExtension();
            }
        }
        this.totalExtension = extension;
    }

    /**
     * Creates a relation between the federated farmer and the extension of the crop harvested as a QuantityOwnerPair.
     * Then adds the QuantityOwnerPair to the stock of the product of the crop harvested.
     * Finally, removes the crop from the production of the federated farmer.
     * @param crop the crop to harvest
     * @see QuantityOwnerPair
     * @see Stock
     */
    @Override
    public void harvestToStock(Crop crop) {
        QuantityOwnerPair quantityOwnerPair = new QuantityOwnerPair(this, crop.getExtension());
        Stock.addQuantity(crop.getProduct(), quantityOwnerPair);
        removeCrop(crop);
    }

    /**
     * Returns the collection of the federated farmer's bills
     * @return the collection of the federated farmer's bills
     */
    public List<Bill> getSales() {
        return sales;
    }

    /**
     * Adds a bill to the federated farmer's sales
     * @param bill the bill to add
     */
    @Override
    public void addSale(Bill bill) {
        sales.add(bill);
    }

    /**
     * Returns a string representation of the federated farmer
     * @return string representation of the federated farmer
     */
    @Override
    public String toString() {
        return "FederatedFarmer{" +
                "name='" + name + '\'' +
                ", productType=" + productType +
                ", associates=" + associates +
                ", totalExtension=" + totalExtension +
                ", production=" + production +
                '}';
    }
}
