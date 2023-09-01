package logistics.warehouse.validations;

import customer.CustomerType;
import logistics.delivery.models.TransportProvider;
import logistics.delivery.models.Tariff;
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

    /**
     * Validates that the selected Tariff is provided by the selected Transport Provider
     * Then, validates that the selected Tariff is provided to the selected Customer Type
     * @param customerType
     * @param selectedTariff
     * @param selectedTransportProvider
     * @throws IllegalArgumentException if the selected Transport Provider does not provide the selected Tariff
     * @throws IllegalArgumentException if the selected Tariff is not provided to the selected Customer Type
     * @throws IllegalArgumentException if the selected Transport Provider does not provide any service
     */
    public static void validateTransportOrder(CustomerType customerType, Tariff selectedTariff, TransportProvider selectedTransportProvider) {
        List<Tariff> servicesOfSelectedCompany = selectedTransportProvider.getCustomerServices();
        if (servicesOfSelectedCompany.isEmpty()) {
            throw new IllegalArgumentException("This transport company does not provide any service.\n Select another transport company.");
        } else {
            validateTransportCompanyTariff(selectedTransportProvider, selectedTariff);
            validateCustomerTypeOrder(customerType, selectedTransportProvider, selectedTariff);
        }
    }

    /**
     * Validates that the selected Tariff is provided by the selected Transport Provider
     * @param selectedTransportProvider the selected Transport Provider
     * @param selectedTariff the selected Tariff
     * @throws IllegalArgumentException if the selected Transport Provider does not provide the selected Tariff
     */
    private static void validateTransportCompanyTariff(TransportProvider selectedTransportProvider, Tariff selectedTariff) {
        List<Tariff> transportCompanyServices = selectedTransportProvider.getCustomerServices();
        if (!transportCompanyServices.contains(selectedTariff)) {
            throw new IllegalArgumentException("This transport company does not provide this tariff.");
        }
    }

    /**
     * Validates that the selected Tariff is provided to the selected Customer Type
     * @param customerType the selected Customer Type
     * @param selectedTransportProvider the selected Transport Provider
     * @param selectedTariff the selected Tariff
     * @throws IllegalArgumentException if the selected Tariff is not provided to the selected Customer Type
     */
    private static void validateCustomerTypeOrder(CustomerType customerType, TransportProvider selectedTransportProvider, Tariff selectedTariff) {
        if (customerType != selectedTariff.getCustomerType()) {
            StringBuilder helperMessage = new StringBuilder();
            List<Tariff> posibleServices = new ArrayList<>();
            for (Tariff tariff : selectedTransportProvider.getCustomerServices()) {
                if (tariff.getCustomerType() == customerType) {
                    posibleServices.add(tariff);
                }
            }
            if (posibleServices.isEmpty()) {
                helperMessage.append("This transport company does not provide service to ").append(customerType).append(".\n Select other company");
            } else {
                helperMessage.append("This transport company does not provide this service to ").append(customerType).append(".\n Services of ").append(selectedTransportProvider).append("for ").append(customerType).append(" are:\n");
                for (Tariff tariff : posibleServices) {
                    helperMessage.append("- ").append(tariff.getName()).append("\n");
                }
            }
            throw new IllegalArgumentException(helperMessage.toString());
        }
    }

}
