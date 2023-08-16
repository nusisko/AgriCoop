package Producers.Models;

import Producers.Validations.SmallFarmerValidation;
import Production.Models.Crop;

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
        SmallFarmerValidation.validateProduction(production);
    }

    /**
     * Creates a deep copy of the small farmer passed as parameter
     *
     * @param smallFarmerToCopy the farmer to copy
     */
    public SmallFarmer(SmallFarmer smallFarmerToCopy) {
        super(smallFarmerToCopy);
    }

    @Override
    public void setProduction(ArrayList<Crop> newProduction) {
        SmallFarmerValidation.validateProduction(newProduction);
        ArrayList<Crop> productionCopy = new ArrayList<>();
        if (newProduction != null && !newProduction.isEmpty()) {
            for (Crop crop : newProduction) {
                productionCopy.add(new Crop(crop));
            }
        }
        this.production = productionCopy;
    }

    @Override
    public void addCrop(Crop crop) {
        float expectedExtension = crop.getExtension() + this.getTotalExtension();
        SmallFarmerValidation.validateExtension(expectedExtension);
        production.add(new Crop(crop));
        setTotalExtension();
    }

    @Override
    public String toString() {
        return "SmallFarmer{" + "\n\tname='" + name + '\'' + "\n\ttotalExtension=" + totalExtension + "\n\tproduction=" + production + "\n}";
    }
}
