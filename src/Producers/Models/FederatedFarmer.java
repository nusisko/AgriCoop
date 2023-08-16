package Producers.Models;

import Producers.Validations.FederatedFarmerValidation;
import Producers.Validations.SmallFarmerValidation;
import Production.Models.Crop;
import Production.Models.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class FederatedFarmer {

    private String name;
    private final Product productType;
    private final HashSet<SmallFarmer> associates;
    private final HashMap<SmallFarmer, Float> federationCropMap;
    private float totalExtension;

    public FederatedFarmer(String name, Product productType, HashSet<SmallFarmer> associates) {
        FederatedFarmerValidation.validateProductType(productType);
        this.name = name;
        this.productType = productType;
        this.associates = associates;
        this.federationCropMap = FederatedFarmerValidation.initializeFederationCropMap(associates);
        this.totalExtension = getTotalExtension();
        FederatedFarmerValidation.registerFederatedFarmer(this);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProductType() {
        return new Product(productType);
    }

    public HashSet<SmallFarmer> getAssociates() {
        return this.associates;
    }
    public void addAssociate(SmallFarmer associate) {
        associates.add(associate);
    }
    public HashMap<SmallFarmer, Float> getFederationCropMap() {
        return federationCropMap;
    }
    public void addCrop(Crop crop , SmallFarmer smallFarmer) {
        FederatedFarmerValidation.validateAsociate(smallFarmer, associates);
        FederatedFarmerValidation.validateCropOwnership(crop, smallFarmer);
        float extensionToAdd = crop.getExtension();
        SmallFarmerValidation.validateExtension(this.getTotalExtension() + extensionToAdd);
        federationCropMap.put(smallFarmer, federationCropMap.get(smallFarmer) + extensionToAdd);
        smallFarmer.removeCrop(crop);
        setTotalExtension();
    }

    public float getTotalExtension() {
        setTotalExtension();
        return totalExtension;
    }

    public void setTotalExtension() {
        float extension = .0f;
        Collection<Float> values = federationCropMap.values();
        for (Float value : values) {
            extension += value;
        }
        this.totalExtension = extension;
    }
}
