package logistics.warehouse.validations;

import logistics.delivery.models.Address;
import logistics.delivery.validations.AddressValidation;
import logistics.warehouse.models.Order;
import regulation.LogisticsStatics;

public class OrderLogic {
    /**
     * Private constructor to prevent instantiation
     */
    private OrderLogic() {
        throw new AssertionError("OrderLogic class should not be instantiated.");
    }

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
