package logistics.delivery.models;

import customer.CustomerType;
import regulation.LogisticsStatics;

public class Tariff {
    private String name;
    private final double priceFixedLongDistance;
    private double pricePerKilometerLongDistance;
    private double pricePerKilometerSmallDistance;
    private CustomerType customerType;

    public Tariff(String name, double pricePerKilometerSmallDistance, double pricePerKilometerLongDistance, CustomerType customerType) {
        this.name = name;
        this.priceFixedLongDistance = LogisticsStatics.getFixedPriceLongDistance();
        this.pricePerKilometerSmallDistance = pricePerKilometerSmallDistance;
        this.pricePerKilometerLongDistance = pricePerKilometerLongDistance;
        this.customerType = customerType;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceFixedLongDistance() {
        return priceFixedLongDistance;
    }

    public double getPricePerKilometerSmallDistance() {
        return pricePerKilometerSmallDistance;
    }

    public void setPricePerKilometerSmallDistance(double pricePerKilometerSmallDistance) {
        this.pricePerKilometerSmallDistance = pricePerKilometerSmallDistance;
    }

    public double getPricePerKilometerLongDistance() {
        return pricePerKilometerLongDistance;
    }

    public void setPricePerKilometerLongDistance(double pricePerKilometerLongDistance) {
        this.pricePerKilometerLongDistance = pricePerKilometerLongDistance;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

}
