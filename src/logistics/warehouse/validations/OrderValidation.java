package logistics.warehouse.validations;

import customer.CustomerType;
import logistics.delivery.models.Provider;
import logistics.delivery.models.Tariff;
import logistics.warehouse.models.Stock;
import production.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderValidation {


    /**
     * Validates that the value is positive
     *
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is negative
     */
    public static void validatePositiveValue(float value) {
        if (value < 0) {
            throw new IllegalArgumentException("Invalid value: " + value + "\n Enter a positive value.");
        }
    }


    public static void validateTransportOrder(CustomerType customerType, Tariff selectedTariff, Provider selectedProvider) {
        List<Tariff> servicesOfSelectedCompany = selectedProvider.getCustomerServices();
        if (servicesOfSelectedCompany.isEmpty()) {
            throw new IllegalArgumentException("This transport company does not provide any service.\n Select another transport company.");
        } else {
            validateTransportCompanyTariff(selectedProvider, selectedTariff);
            validateCustomerTypeOrder(customerType, selectedProvider, selectedTariff);
        }
    }

    private static void validateTransportCompanyTariff(Provider selectedProvider, Tariff selectedTariff) {
        List<Tariff> transportCompanyServices = selectedProvider.getCustomerServices();
        if (!transportCompanyServices.contains(selectedTariff)) {
            throw new IllegalArgumentException("This transport company does not provide this tariff.");
        }
    }

    private static void validateCustomerTypeOrder(CustomerType customerType, Provider selectedProvider, Tariff selectedTariff) {
        if (customerType != selectedTariff.getCustomerType()) {
            StringBuilder helperMessage = new StringBuilder();
            List<Tariff> posibleServices = new ArrayList<>();
            for (Tariff tariff : selectedProvider.getCustomerServices()) {
                if (tariff.getCustomerType() == customerType) {
                    posibleServices.add(tariff);
                }
            }
            if (posibleServices.isEmpty()) {
                helperMessage.append("This transport company does not provide service to ").append(customerType).append(".\n Select other company");
            } else {
                helperMessage.append("This transport company does not provide this service to ").append(customerType).append(".\n Services of ").append(selectedProvider).append("for ").append(customerType).append(" are:\n");
                for (Tariff tariff : posibleServices) {
                    helperMessage.append("- ").append(tariff.getName()).append("\n");
                }
            }
            throw new IllegalArgumentException(helperMessage.toString());
        }
    }

}
