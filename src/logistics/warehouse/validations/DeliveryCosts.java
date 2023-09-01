package logistics.warehouse.validations;

import customer.CustomerType;
import customer.ICustomer;
import logistics.delivery.models.Address;
import logistics.delivery.models.TransportProvider;
import logistics.delivery.validations.AddressValidation;
import logistics.warehouse.models.Order;
import regulation.LogisticsStatics;
import regulation.TaxesStatics;

/**
 * Utility class that contains the methods to perform the calculations of the delivery costs
 * @version 1.0.0
 * @see Order
 * @see AddressValidation
 * @see Address
 * @see LogisticsStatics
 */
public class DeliveryCosts {
    public static double getDeliveryCosts(Order order) {
        Address cooperativeAddress = LogisticsStatics.getCooperativeAddress();
        Address customerAddress = order.getCustomer().getAddress();
        double distanceBetween = AddressValidation.getDistanceBetweenAddresses(cooperativeAddress, customerAddress);

        boolean isProductPerishable = order.getProduct().isPerishable();
        float priceProduct = order.getProduct().getPrice();
        float quantityProduct = order.getQuantity();
        double fixedPrice = order.getTariff().getPriceFixedLongDistance();
        double longDistanceKilometerPrice = order.getTariff().getPricePerKilometerLongDistance();
        double shortDistanceKilometerPrice = order.getTariff().getPricePerKilometerSmallDistance();


        double maxTravelDistance = getMaxTravelDistance(distanceBetween, isProductPerishable);

        int numberLongTravels = (int) (distanceBetween / maxTravelDistance);
        double distanceShortTravel = distanceBetween % maxTravelDistance;

        double totalDeliveryCosts = 0;
        if (numberLongTravels > 0) {
            totalDeliveryCosts += calculateLongDistanceCosts(fixedPrice, priceProduct, quantityProduct, maxTravelDistance, numberLongTravels, shortDistanceKilometerPrice);
        }
        totalDeliveryCosts += calculateShortDistanceCosts(quantityProduct, distanceShortTravel, shortDistanceKilometerPrice);
        return totalDeliveryCosts;
    }
    public static double getProductCostsWithMargin(Order order) {
        ICustomer customer = order.getCustomer();
        TransportProvider transportProvider = order.getProvider();

        float benefitMargin;
        if (customer.getCustomerType() == CustomerType.DISTRIBUTOR) {
            benefitMargin = TaxesStatics.getTaxDistributors();
        } else {
            benefitMargin = TaxesStatics.getTaxFinalCustomer();
        }

        return order.getProduct().getPrice() * order.getQuantity() * (1 + benefitMargin);
    }

    private static double calculateLongDistanceCosts(double fixedPriceLongDistance, float priceProduct, float quantityProduct, double maxTravelDistance, int numberTravels, double pricePerKilometer) {
        double costFixed = (fixedPriceLongDistance * priceProduct * quantityProduct) * numberTravels;
        double costLongDistance = (maxTravelDistance * numberTravels) * pricePerKilometer;
        return costFixed + costLongDistance;
    }

    private static double calculateShortDistanceCosts(double quantityProduct, double distanceShortTravel, double pricePerKilometer) {
        return quantityProduct * distanceShortTravel * pricePerKilometer;
    }

    private static double getMaxTravelDistance(double distance, boolean isProductPerishable) {
        int maxTravelDistance;
        if (isProductPerishable) {
            maxTravelDistance = LogisticsStatics.getMaxDistanceTravelPerishable();
        } else {
            maxTravelDistance = LogisticsStatics.getMaxDistanceTravelNonPerishable();
        }
        return maxTravelDistance;
    }
}
