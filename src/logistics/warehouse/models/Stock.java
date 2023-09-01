package logistics.warehouse.models;

import billing.Bill;
import logistics.warehouse.validations.OrderLogic;
import production.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Stock {
    private static final Map<Product, List<QuantityOwnerPair>> stock = new HashMap<>();

    public Stock() {
        throw new AssertionError("Stock class should not be instantiated.");
    }

    public static Map<Product, List<QuantityOwnerPair>> getStock() {
        return stock;
    }

    public static void registerProduct(Product product) {
        if (!stock.containsKey(product)) {
            List<QuantityOwnerPair> quantityOwnerPairs = new ArrayList<>();
            stock.put(product, quantityOwnerPairs);
        } else {
            throw new IllegalArgumentException("Product key already exists in the stock.");
        }
    }

    public static void addQuantity(Product product, QuantityOwnerPair quantityOwnerPair) {
        if (stock.containsKey(product)) {
            stock.get(product).add(quantityOwnerPair);
        } else {
            throw new IllegalArgumentException("Product key does not exist in the stock.");
        }
    }

    public static float getTotalProductQuantity(Product product) {
        float totalQuantity = 0.0f;
        List<QuantityOwnerPair> quantityOwnerPairs = stock.get(product);
        for (QuantityOwnerPair quantityOwnerPair : quantityOwnerPairs) {
            totalQuantity += quantityOwnerPair.getQuantity();
        }
        return totalQuantity;
    }

    public static void manageLastOrder() {
        Order lastOrderStack = OrderStack.peekOrderfromStack();
        Product orderProduct = lastOrderStack.getProduct();
        float availableProductStock = getTotalProductQuantity(orderProduct);
        if (availableProductStock >= lastOrderStack.getQuantity()) {
            handleStockOrder(lastOrderStack);
            OrderStack.popOrderfromStack();
        }
    }

    public static void handleStockOrder(Order order) {
        Product product = order.getProduct();
        float quantity = order.getQuantity();
        float quantityTons = quantity / 1000;
        List<QuantityOwnerPair> productStock = stock.get(product);
        float totalProductQuantityStock = getTotalProductQuantity(product);

        if (totalProductQuantityStock * 1000 < quantityTons) {
            throw new IllegalArgumentException("Not enough product in stock.");
        } else {

            for (QuantityOwnerPair quantityOwnerPair : productStock) {
                float ratio = quantityOwnerPair.getQuantity() / totalProductQuantityStock;
                quantityOwnerPair.substractQuantity(quantityTons * ratio);

                //TODO SEND TO LOGISTICS TRANSPORTER
                double deliveryCosts = OrderLogic.getDeliveryCosts(order);
                double productCosts = product.getPrice() * quantity;
                double pricePerKilo = (deliveryCosts + productCosts) / quantity;
                System.out.println("############\nPrice Per Kilo: " + pricePerKilo);

                String nameOwner = quantityOwnerPair.getOwner().getName();
                Bill bill = new Bill(nameOwner, product, quantity, "factura prueba 1");
                System.out.println("### Bill of " + (quantity * ratio) + " Kg of " + product.getName() + " generated to " + nameOwner + " ###");
                quantityOwnerPair.getOwner().addSale(bill);
            }

        }
    }

    public static String logStock() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stock {\n");

        for (Map.Entry<Product, List<QuantityOwnerPair>> entry : stock.entrySet()) {
            sb.append("\t").append(entry.getKey()).append(" = \n");

            for (QuantityOwnerPair pair : entry.getValue()) {
                sb.append("\t\t").append(pair.getOwner()).append(" (").append(pair.getQuantity()).append(")\n");
            }
        }

        sb.append("}");
        return sb.toString();

    }
}