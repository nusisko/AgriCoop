package producer.models;

import production.models.Crop;
import production.models.FederatedCrop;

public interface IHarvester {
    public void harvestToStock(Crop crop);
}
