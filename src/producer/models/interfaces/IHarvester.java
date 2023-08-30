package producer.models.interfaces;

import billing.Bill;
import producer.models.Farmer;
import producer.models.FederatedFarmer;
import production.models.Crop;
import java.util.List;

/**
 * Interface that represents a harvester
 *
 * @version 1.0.0
 * @see Farmer
 * @see FederatedFarmer
 */
public interface IHarvester {

    /**
     * Adds the crop passed as parameter to the cooperative's stock
     * Then removes the crop from the farmer's production
     * @param crop the crop to harvest
     */
    public void harvestToStock(Crop crop);

    /**
     * Gets the collection of the farmer's bills
     * @return the collection of the farmer's bills
     */
    public List<Bill> getSales();

    /**
     * Adds a bill to the farmer's sales
     * @param bill the bill to add
     */
    public void addSale(Bill bill);

    String getName();
}
