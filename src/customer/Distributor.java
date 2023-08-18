package customer;

import logistics.Order;
import production.models.Product;

public class Distributor implements ICustomer {
    private String name;
    private String city;

    public Distributor(String name, String city) {
        this.name = name;
        this.city = city;
    }
    @Override
    public void purchase(Product product, float quantity) {
        Order newOrder = new Order(product, quantity, this);
    }
}
