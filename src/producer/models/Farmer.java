package producer.models;

import logistics.QuantityOwnerPair;
import logistics.Stock;
import production.models.Crop;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents a farmer
 *
 * @version 1.0.0
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

    //TODO protected ArrayList<SaleRequest> sales;

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
        //TODO this.sales = new ArrayList<>();
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
        //TODO this.sales = farmerToCopy.getSales();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a deep copy of the production
     *
     * @return production of the farmer
     */

     /*
    public ArrayList<Crop> getProduction() {
        ArrayList<Crop> productionCopy = new ArrayList<>();
        if (production != null && !production.isEmpty()) {
            for (Crop crop : production) {
                productionCopy.add(new Crop(crop));
            }
        }
        return productionCopy;
    }
    */

    public List<Crop> getProduction() {
        return production;
    }

    public abstract void setProduction(ArrayList<Crop> production);

    public void setTotalExtension() {
        float setTotalExtension = 0;
        if (production != null) {
            for (Crop crop : production) {
                setTotalExtension += crop.getExtension();
            }
        }
        this.totalExtension = setTotalExtension;
    }

    public float getTotalExtension() {
        setTotalExtension();
        return totalExtension;
    }
    public void removeCrop(Crop crop){
        production.remove(crop);
        setTotalExtension();
    }

    public abstract void addCrop(Crop crop);

    public void harvestToStock(Crop crop){
        QuantityOwnerPair quantityOwnerPair = new QuantityOwnerPair(this, crop.getExtension());
        Stock.addQuantity(crop.getProduct(), quantityOwnerPair);
        removeCrop(crop);
    }

}
