package logistics;

import java.sql.SQLOutput;
import java.util.Stack;

public final class OrderStack {
    private static final Stack<Order> stack = new Stack<>();

    public OrderStack() {
        throw new AssertionError("ProductValidator class should not be instantiated.");
    }
    public static void printStack() {
        System.out.println("Order Stack:");
        for (Order order : stack) {
            System.out.println(order);
        }
    }
    public static void addOrdertoStack(Order order) {
        stack.push(order);
    }
    public static Order popOrderfromStack() {
        return stack.pop();
    }

    public static Order peekOrderfromStack() {
        return stack.peek();
    }


}
