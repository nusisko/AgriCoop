package customer;

import production.models.Product;

public interface ICustomer {
    void purchase(Product product, float quantity);

}
