package customer;

import billing.IBillable;
import logistics.delivery.models.Address;
import logistics.delivery.models.TransportProvider;
import logistics.delivery.models.Tariff;
import production.models.Product;

public interface ICustomer extends IBillable {
    void purchase(Product product, float quantity, TransportProvider transportProvider, Tariff transportTariff);

    CustomerType getCustomerType();

    String getName();

    void setName(String name);

    Address getAddress();

    void setAddress(Address newAddress);
}


