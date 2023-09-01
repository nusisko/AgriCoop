package customer;

import logistics.delivery.models.Address;
import logistics.delivery.models.Provider;
import logistics.delivery.models.Tariff;
import production.models.Product;

public interface ICustomer {
    void purchase(Product product, float quantity, Provider transportProvider, Tariff transportTariff);

    CustomerType getCustomerType();

    String getName();

    void setName(String name);

    Address getAddress();

    void setAddress(Address newAddress);
}


