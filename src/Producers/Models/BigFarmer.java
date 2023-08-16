package Producers.Models;

import Production.Models.Crop;

import java.util.ArrayList;
/**
 * Class that represents a big farmer
 *
 * @version 1.0.0
 * @see Farmer
 */
public class BigFarmer extends Farmer {
    /**
     * Creates a new BigFarmer object with the specified name and production
     *
     * @param name       the name of the farmer
     * @param production the production of the farmer as collection of crops
     */
    public BigFarmer(String name, ArrayList<Crop> production) {
        super(name, production);
    }

    /**
     * Creates a deep copy of the big farmer passed as parameter
     *
     * @param bigFarmerToCopy the farmer to copy
     */
    public BigFarmer(BigFarmer bigFarmerToCopy) {
        super(bigFarmerToCopy);
    }
    @Override
    public void setProduction(ArrayList<Crop> production) {
        ArrayList<Crop> productionCopy = new ArrayList<>();
        if (production != null && !production.isEmpty()) {
            for (Crop crop : production) {
                productionCopy.add(new Crop(crop));
            }
        }
        this.production = productionCopy;
    }

    @Override
    public void addCrop(Crop crop) {
        Crop copyCrop = new Crop(crop);
        production.add(crop);
        setTotalExtension();

    }

    @Override
    public String toString() {
        return "BigFarmer{" + "\n\tname='" + name + '\'' + "\n\ttotalExtension=" + totalExtension + "\n\tproduction=" + production + "\n}";
    }
}
