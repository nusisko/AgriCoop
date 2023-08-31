package logistics.warehouse.models;

import java.util.Stack;

public final class OrderStack {
    private static final Stack<Order> stack = new Stack<>();

    private OrderStack() {
        throw new AssertionError("ProductValidator class should not be instantiated.");
    }
    public static void printStack() {
        System.out.println("Order Stack:");
        for (Order order : stack) {
            System.out.println(order);
        }
    }
    static void addOrdertoStack(Order order) {
        stack.push(order);
    }
    static Order popOrderfromStack() {
        return stack.pop();
    }

    static Order peekOrderfromStack() {
        return stack.peek();
    }


}
