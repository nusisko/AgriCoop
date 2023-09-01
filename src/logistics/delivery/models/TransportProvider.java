package logistics.delivery.models;

import billing.Bill;
import billing.IBillable;

import java.util.ArrayList;
import java.util.List;

public class TransportProvider implements IBillable {
    /**
     * Collection of the farmer's bills
     */
    protected final List<Bill> sales;
    private String name;
    private List<Tariff> customerServices;

    public TransportProvider(String name, List<Tariff> customerServices) {
        this.name = name;
        this.customerServices = customerServices;
        this.sales = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tariff> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(List<Tariff> customerServices) {
        this.customerServices = customerServices;
    }

    public void addCustomerService(Tariff customerService) {
        this.customerServices.add(customerService);
    }

    @Override
    public List<Bill> getSales() {
        return sales;
    }

    @Override
    public void addSale(Bill bill) {
        sales.add(bill);

    }
}

