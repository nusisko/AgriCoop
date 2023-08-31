package logistics.warehouse.models;

import producer.models.interfaces.IHarvester;

public class QuantityOwnerPair {
    private IHarvester owner;
    private float quantity;

    public QuantityOwnerPair(IHarvester owner, float quantity) {
        this.owner = owner;
        this.quantity = quantity;
    }

    public IHarvester getOwner() {
        return owner;
    }

    public void setOwner(IHarvester owner) {
        this.owner = owner;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    public void addQuantity(float quantity) {
        this.quantity += quantity;
    }
    public void substractQuantity(float quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "QuantityOwnerPair{" +
                "owner=" + owner +
                ", quantity=" + quantity +
                '}';
    }
}
