package production.models;

import producer.models.SmallFarmer;

/**
 * Class that represents a federated crop as a pair of a crop and a small farmer
 * @see Crop
 * @see SmallFarmer
 * @version 1.0.0
 */
public class FederatedCrop{
    /**
     * The small farmer that owns the crop
     */
    SmallFarmer proprietary;
    /**
     * The crop
     */
    Crop crop;
    public FederatedCrop(Crop crop, SmallFarmer proprietary) {
        this.proprietary = proprietary;
        this.crop = crop;
    }

    /**
     * @return the small farmer that owns the crop
     */
    public SmallFarmer getProprietary() {
        return proprietary;
    }
    /**
     * @return the crop
     */
    public Crop getCrop() {
        return crop;
    }
}
