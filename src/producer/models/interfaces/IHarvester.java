package producer.models.interfaces;

import billing.Bill;
import billing.IBillable;
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
public interface IHarvester extends IBillable {

    /**
     * Adds the crop passed as parameter to the cooperative's stock
     * Then removes the crop from the farmer's production
     * @param crop the crop to harvest
     */
    public void harvestToStock(Crop crop);

    String getName();
}
