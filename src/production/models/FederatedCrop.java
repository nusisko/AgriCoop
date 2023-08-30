package production.models;

import org.jetbrains.annotations.NotNull;
import producer.models.SmallFarmer;

public class FederatedCrop{

    SmallFarmer proprietary;
    Crop crop;
    public FederatedCrop(Crop crop, SmallFarmer propietary) {
        this.proprietary = propietary;
        this.crop = crop;
    }
    /*
    public SmallFarmer getProprietary() {
        return new SmallFarmer(proprietary);
    }
    */

    public SmallFarmer getProprietary() {
        return proprietary;

    }

    /*
    public Crop getCrop() {
        return Crop(crop);
    }
    */

    public Crop getCrop() {
        return crop;
    }
}
