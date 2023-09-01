package logistics.warehouse.models;

import customer.ICustomer;
import logistics.delivery.models.Provider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.validations.OrderValidation;
import production.models.Product;

import java.util.Date;

public class Order {

    private static int count = 0;
    private final int orderNumber;
    private final Product product;
    private final ICustomer customer;
    private final Date date;
    private final Provider provider;
    private final Tariff tariff;
    private float quantity;


    public Order(Product product, float quantity, Provider transportProvider, Tariff transportTariff, ICustomer customer) {
        this.orderNumber = count++;
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.date = new Date();
        this.provider = transportProvider;
        this.tariff = transportTariff;
        OrderValidation.validateTransportOrder(customer.getCustomerType(), transportTariff, transportProvider);
        OrderStack.addOrdertoStack(this);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Product getProduct() {
        return product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public ICustomer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return date;
    }

    public Provider getProvider() {

        return provider;
    }

    public Tariff getTariff() {
        return tariff;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", product=" + product + ", quantity=" + quantity + ", customer=" + customer + ", date=" + date + '}';
    }
}
