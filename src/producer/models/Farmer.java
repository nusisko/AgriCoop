package producer.models;

import billing.Bill;
import logistics.stock.QuantityOwnerPair;
import logistics.stock.Stock;
import producer.models.interfaces.IHarvester;
import production.models.Crop;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents a farmer
 *
 * @version 1.0.0
 * @see IHarvester
 * @see Crop
 */
public abstract class Farmer implements IHarvester {

    /**
     * Farmer name
     */
    protected String name;
    /**
     * Total extension of the farmer's production (in hectares)
     */
    protected float totalExtension;
    /**
     * Collection of the farmer's crops
     */
    protected List<Crop> production;
    /**
     * Collection of the farmer's bills
     */
    protected final List<Bill> sales;

    /**
     * Creates a new Farmer object with the specified name and production
     *
     * @param name       the name of the farmer
     * @param production the production of the farmer as collection of crops
     */
    public Farmer(String name, List<Crop> production) {
        this.name = name;
        this.production = production;
        this.totalExtension = getTotalExtension();
        this.sales = new ArrayList<>();
    }

    /**
     * Creates a deep copy of the farmer passed as parameter
     *
     * @param farmerToCopy the farmer to copy
     */
    public Farmer(@NotNull Farmer farmerToCopy) {
        this.name = farmerToCopy.getName();
        this.production = farmerToCopy.getProduction();
        this.totalExtension = farmerToCopy.getTotalExtension();
        this.sales = farmerToCopy.getSales();
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
     * @param name the name of the farmer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the production as collection of crops
     *
     * @return production of the farmer
     */
    public List<Crop> getProduction() {
        return production;
    }

    /**
     * Sets the production of the farmer
     *
     * @param production the production of the farmer as collection of crops
     */
    public abstract void setProduction(ArrayList<Crop> production);

    /**
     * Sets the total extension of the farmer's production
     */
    public void setTotalExtension() {
        float setTotalExtension = 0;
        if (production != null) {
            for (Crop crop : production) {
                setTotalExtension += crop.getExtension();
            }
        }
        this.totalExtension = setTotalExtension;
    }

    /**
     * Returns the total extension of the farmer's production
     *
     * @return total extension of the farmer's production
     */
    public float getTotalExtension() {
        setTotalExtension();
        return totalExtension;
    }

    /**
     * Removes the crop passed as parameter from the farmer's production
     * @param crop the crop to remove
     */
    public void removeCrop(Crop crop){
        production.remove(crop);
        setTotalExtension();
    }

    /**
     * Adds the crop passed as parameter to the farmer's production
     * @param crop the crop to add
     */
    public abstract void addCrop(Crop crop);

    /**
     * Adds the crop passed as parameter to the cooperative's stock
     * Then removes the crop from the farmer's production
     * @param crop the crop to harvest
     */
    public void harvestToStock(Crop crop){
        QuantityOwnerPair quantityOwnerPair = new QuantityOwnerPair(this, crop.getExtension());
        Stock.addQuantity(crop.getProduct(), quantityOwnerPair);
        removeCrop(crop);
    }

    /**
     * Returns the collection of the farmer's bills
     *
     * @return collection of the farmer's bills
     */
    public List<Bill> getSales() {
        return sales;
    }

    /**
     * Adds the bill passed as parameter to the farmer's bills
     * @param bill the bill to add
     */
    public void addSale(Bill bill){
        sales.add(bill);
    }
}
