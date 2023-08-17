package producer.models;

import logistics.QuantityOwnerPair;
import logistics.Stock;
import producer.validations.FederatedFarmerValidation;
import producer.validations.SmallFarmerValidation;
import production.models.Crop;
import production.models.FederatedCrop;
import production.models.Product;

import java.util.*;

public class FederatedFarmer implements IHarvester{

    private String name;
    private final Product productType;
    private final HashSet<SmallFarmer> associates;
    private float totalExtension;
    private final List<FederatedCrop> production;

    public FederatedFarmer(String name, Product productType, HashSet<SmallFarmer> associates) {
        FederatedFarmerValidation.validateProductType(productType);
        this.name = name;
        this.productType = productType;
        this.associates = associates;
        this.totalExtension = getTotalExtension();
        this.production = new ArrayList<>();
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
    public List<FederatedCrop> getProduction() {
        return production;
    }
    public void addCrop(Crop crop , SmallFarmer smallFarmer) {
        FederatedFarmerValidation.validateAsociate(smallFarmer, associates);
        FederatedFarmerValidation.validateCropOwnership(crop, smallFarmer);
        float extensionToAdd = crop.getExtension();
        SmallFarmerValidation.validateExtension(this.getTotalExtension() + extensionToAdd);
        FederatedCrop federatedCrop = new FederatedCrop(crop, smallFarmer);
        production.add(federatedCrop);
        //federationCropMap.put(smallFarmer, federationCropMap.get(smallFarmer) + extensionToAdd);
        smallFarmer.removeCrop(crop);
        setTotalExtension();
    }

    public void removeCrop(Crop crop, SmallFarmer smallFarmer) {
        FederatedCrop federatedCropToCompare = new FederatedCrop(crop, smallFarmer);
        production.removeIf(federatedCrop -> federatedCrop.equals(federatedCropToCompare));
    }
    public SmallFarmer findCropOwner(Crop crop) {
        SmallFarmer cropOwner = null;
        for (FederatedCrop federatedCrop : production) {
            if (federatedCrop.getCrop().equals(crop)) {
                cropOwner = federatedCrop.getProprietary();
                break;
            }
        }
        if (cropOwner==null){
            throw new NullPointerException("Crop has no Owner");
        }
        return cropOwner;
    }

    public float getTotalExtension() {
        setTotalExtension();
        return totalExtension;
    }

    public void setTotalExtension() {
        float extension = .0f;
        for (Crop crop : production) {
            extension += crop.getExtension();
        }
        this.totalExtension = extension;
    }

    @Override
    public void harvestToStock(Crop crop) {
        QuantityOwnerPair quantityOwnerPair = new QuantityOwnerPair(this, crop.getExtension());
        SmallFarmer cropOwner = findCropOwner(crop);
        Stock.addQuantity(crop.getProduct(), quantityOwnerPair);
        removeCrop(crop, cropOwner);
    }
}
