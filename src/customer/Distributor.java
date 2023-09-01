package customer;

import logistics.delivery.models.Address;
import logistics.delivery.models.Provider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.models.Order;
import production.models.Product;

public class Distributor implements ICustomer {
    private String name;
    private Address address;
    private CustomerType customerType;


    public Distributor(String name, Address address, CustomerType customerType) {
        this.name = name;
        this.address = address;
        this.customerType = customerType;

    }

    @Override
    public void purchase(Product product, float quantity, Provider transportProvider, Tariff transportTariff) {
        Order newOrder = new Order(product, quantity, transportProvider, transportTariff, this);
    }

    @Override
    public CustomerType getCustomerType() {
        return customerType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
