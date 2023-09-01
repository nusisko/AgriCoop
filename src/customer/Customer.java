package customer;

import billing.Bill;
import logistics.delivery.models.Address;
import logistics.delivery.models.TransportProvider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.models.Order;
import production.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer implements ICustomer {
    private String name;
    private Address address;
    private CustomerType customerType;
    protected final List<Bill> purchases;

    public Customer(String name, Address address, CustomerType customerType) {
        this.name = name;
        this.address = address;
        this.customerType = customerType;
        this.purchases = new ArrayList<>();
    }

    @Override
    public void purchase(Product product, float quantity, TransportProvider transportProvider, Tariff transportTariff) {
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

    @Override
    public List<Bill> getSales() {
        return purchases;
    }

    @Override
    public void addSale(Bill bill) {
        purchases.add(bill);
    }
}
