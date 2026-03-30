package ECommerceOrderEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private PaymentService paymentService = new PaymentService();
    private CouponService couponService = new CouponService();
    private boolean failureMode = false;

    // PLACE ORDER
    public void placeOrder(String userId, CartService cartService, String couponCode) {

        if (failureMode) {
            System.out.println("System Failure Mode Enabled. Cannot place order.");
            AuditLogger.log("ORDER FAILED DUE TO FAILURE MODE");
            return;
        }

        List<CartItem> cart = cartService.getCart(userId);

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotal();
        }

        if (couponCode != null && !couponCode.isEmpty()) {
            total = couponService.applyCoupon(couponCode, total);
        }

        Order order = new Order(UUID.randomUUID().toString(),
                new ArrayList<>(cart), total);

        boolean paymentSuccess = paymentService.processPayment();

        if (!paymentSuccess) {
            for (CartItem item : cart) {
                item.getProduct().releaseStock(item.getQuantity());
            }
            order.setStatus("FAILED");
            System.out.println("Payment failed. Stock restored.");
            AuditLogger.log("PAYMENT FAILED: " + order.getOrderId());
        } else {
            order.setStatus("PAID");
            cartService.clearCart(userId);
            System.out.println("Order placed successfully");
            AuditLogger.log("ORDER PLACED: " + order.getOrderId());
        }

        orders.add(order);
    }

    // VIEW ORDERS
    public void viewOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // CANCEL ORDER
    public void cancelOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {

                if (order.getStatus().equals("PAID")) {
                    order.setStatus("CANCELLED");

                    for (CartItem item : order.getItems()) {
                        item.getProduct().releaseStock(item.getQuantity());
                    }

                    System.out.println("Order cancelled");
                    AuditLogger.log("ORDER CANCELLED: " + orderId);
                } else {
                    System.out.println("Cannot cancel this order");
                }
                return;
            }
        }
        System.out.println("Order not found");
    }

    // RETURN PRODUCT
    public void returnOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {

                if (order.getStatus().equals("PAID")) {
                    order.setStatus("RETURNED");

                    for (CartItem item : order.getItems()) {
                        item.getProduct().releaseStock(item.getQuantity());
                    }

                    System.out.println("Product returned successfully");
                    AuditLogger.log("ORDER RETURNED: " + orderId);
                } else {
                    System.out.println("Return not allowed");
                }
                return;
            }
        }
        System.out.println("Order not found");
    }

    // FAILURE MODE
    public void triggerFailureMode() {
        failureMode = true;
        System.out.println("System Failure Mode Enabled");
        AuditLogger.log("SYSTEM FAILURE MODE ENABLED");
    }
}