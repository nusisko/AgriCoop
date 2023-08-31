package logistics.delivery.models;

import java.util.List;

public class Provider {
    private String name;
    private List<Tariff> customerServices;

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
}

