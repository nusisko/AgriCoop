package logistics.warehouse.validations;

import logistics.delivery.models.Address;
import logistics.delivery.validations.AddressValidation;
import logistics.warehouse.models.Order;
import regulation.LogisticsStatics;

import java.util.Stack;

/**
 * Utility class that contains the logic of the orders
 * @version 1.0.0
 * @see Order
 * @see AddressValidation
 * @see Address
 * @see LogisticsStatics
 */
public class OrderLogic {

    /**
     * Stack that stores the orders to be processed
     */
    private static final Stack<Order> stack = new Stack<>();

    /**
     * Private constructor to prevent instantiation
     */
    private OrderLogic() {
        throw new AssertionError("OrderLogic class should not be instantiated.");
    }

    /**
     * Prints the stack of orders
     */
    public static void printStack() {
        System.out.println("Order Stack:");
        for (Order order : stack) {
            System.out.println(order);
        }
    }

    /**
     * Adds an order to the stack
     * @param order the order to add
     */
    public static void addOrderToStack(Order order) {
        stack.push(order);
    }

    /**
     * Removes and returns the last order from the stack
     * @return the last order from the stack
     */
    public static Order popOrderFromStack() {
        return stack.pop();
    }

    /**
     * Returns the last order from the stack without removing it from the stack
     * @return the last order from the stack
     */
    public static Order peekOrderFromStack() {
        return stack.peek();
    }
}
