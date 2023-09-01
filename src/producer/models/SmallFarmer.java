package producer.models;

import producer.validations.SmallFarmerValidation;
import production.models.Crop;
import java.util.ArrayList;

/**
 * Class that represents a small farmer
 *
 * @version 1.0.0
 * @see Farmer
 */
public class SmallFarmer extends Farmer{

    /**
     * Creates a new SmallFarmer object with the specified name and production
     *
     * @param name       the name of the farmer
     * @param production the production of the farmer as collection of crops
     */
    public SmallFarmer(String name, ArrayList<Crop> production) {
        super(name, production);
        SmallFarmerValidation.validateProductionExtension(production);
    }

    /**
     * Creates a deep copy of the small farmer passed as parameter
     *
     * @param smallFarmerToCopy the farmer to copy
     */
    public SmallFarmer(SmallFarmer smallFarmerToCopy) {
        super(smallFarmerToCopy);
    }

    /**
     * Sets the production of the farmer
     *
     * @param newProduction the new production of the farmer as collection of crops
     */
    @Override
    public void setProduction(ArrayList<Crop> newProduction) {
        SmallFarmerValidation.validateProductionExtension(newProduction);
        ArrayList<Crop> productionCopy = new ArrayList<>();
        if (!newProduction.isEmpty()) {
            for (Crop crop : newProduction) {
                productionCopy.add(new Crop(crop));
            }
        }
        this.production = productionCopy;
    }

    /**
     * Adds a crop to the production of the farmer
     *
     * @param crop the crop to add
     */
    @Override
    public void addCrop(Crop crop) {
        float expectedExtension = crop.getExtension() + this.getTotalExtension();
        SmallFarmerValidation.validateExtension(expectedExtension);
        production.add(new Crop(crop));
        setTotalExtension();
    }

    /**
     * Returns a string representation of the small farmer
     *
     * @return string representation of the small farmer
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmallFarmer {" + "\n\tname = '").append(name).append('\'').append("\n\ttotalExtension = ").append(totalExtension).append("\n\tproduction=\n");

        for (Crop crop : production) {
            sb.append("\t\t" + crop + "\n");
        }

        sb.append("}");
        return sb.toString();
    }
}
