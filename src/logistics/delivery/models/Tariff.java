package logistics.delivery.models;

import customer.CustomerType;
import regulation.LogisticsStatics;

public class Tariff {
    private String name;
    private double priceFixed;
    private double pricePerKilometerSmallDistance;
    private CustomerType customerType;

    public Tariff(String name, double pricePerKilometerSmallDistance, CustomerType customerType) {
        this.name = name;
        this.priceFixed = LogisticsStatics.getFixedPriceLongDistance();
        this.pricePerKilometerSmallDistance = pricePerKilometerSmallDistance;
        this.customerType = customerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceFixed() {
        return priceFixed;
    }

    public void setPriceFixed(double priceFixed) {
        this.priceFixed = priceFixed;
    }

    public double getPricePerKilometerSmallDistance() {
        return pricePerKilometerSmallDistance;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    double calculatePriceLongDistanceDelivery(float productPrice, float productWeight) {
        return priceFixed * productPrice * productWeight;
    }

    double calculatePriceShortDistanceDelivery(float distance, float productWeight) {
        return pricePerKilometerSmallDistance * productWeight * distance;
    }
}
