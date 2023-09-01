package logistics.warehouse.models;

import customer.ICustomer;
import logistics.delivery.models.TransportProvider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.validations.OrderLogic;
import logistics.warehouse.validations.OrderValidation;
import production.models.Product;

import java.util.Date;

/**
 * Class that represents an order
 *
 * @version 1.0.0
 * @see Product
 * @see ICustomer
 * @see TransportProvider
 * @see Tariff
 */
public class Order {

    /**
     * Counter of orders
     */
    private static int count = 0;
    /**
     * Unique order number
     */
    private final int orderNumber;
    /**
     * Product of the order
     */
    private final Product product;
    /**
     * Customer of the order
     */
    private final ICustomer customer;
    /**
     * Date of the order
     */
    private final Date date;
    /**
     * Transport provider of the order
     */
    private final TransportProvider transportProvider;
    /**
     * Tariff of the order
     */
    private final Tariff tariff;
    /**
     * Quantity of the order
     */
    private float quantity;

    /**
     * Creates a new Order object with the specified product, quantity, transport provider and tariff
     *
     * @param product            the product of the order
     * @param quantity           the quantity of the order
     * @param transportProvider  the transport provider of the order
     * @param transportTariff    the tariff of the order
     * @param customer           the customer of the order
     * @throws IllegalArgumentException if the quantity is negative
     * @throws IllegalArgumentException if the transport provider does not provide the tariff
     * @throws IllegalArgumentException if the transport provider does not provide the tariff to the customer
     */
    public Order(Product product, float quantity, TransportProvider transportProvider, Tariff transportTariff, ICustomer customer) {
        this.orderNumber = count++;
        this.product = product;
        OrderValidation.validatePositiveValue(quantity);
        this.quantity = quantity;
        this.customer = customer;
        this.date = new Date();
        this.transportProvider = transportProvider;
        this.tariff = transportTariff;
        OrderValidation.validateTransportOrder(customer.getCustomerType(), transportTariff, transportProvider);
        OrderLogic.addOrderToStack(this);
    }

    /**
     * @return the number of orders
     */
    public static int getCount() {
        return count;
    }

    /**
     * @return the order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @return the product of the order
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return the quantity of the order
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the order
     *
     * @param quantity the new quantity of the order
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the customer of the order
     */
    public ICustomer getCustomer() {
        return customer;
    }

    /**
     * @return the date of the order
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the transport provider of the order
     */
    public TransportProvider getProvider() {

        return transportProvider;
    }

    /**
     * @return the tariff of the order
     */
    public Tariff getTariff() {
        return tariff;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", product=" + product + ", quantity=" + quantity + ", customer=" + customer + ", date=" + date + '}';
    }
}
