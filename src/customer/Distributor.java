package customer;

import logistics.delivery.models.Provider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.models.Order;
import production.models.Product;

public class Distributor implements ICustomer {
    private String name;
    private String city;
    private CustomerType customerType;


    public Distributor(String name, String city) {
        this.name = name;
        this.city = city;
    }
    @Override
    public void purchase(Product product, float quantity, Provider transportProvider, Tariff transportTariff){
            Order newOrder = new Order(product, quantity,transportProvider,transportTariff, this);
    }
}
