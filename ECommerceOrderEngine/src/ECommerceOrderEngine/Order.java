package ECommerceOrderEngine;

import java.util.List;

public class Order {
    private String orderId;
    private List<CartItem> items;
    private double total;
    private String status;

    public Order(String orderId, List<CartItem> items, double total) {
        this.orderId = orderId;
        this.items = items;
        this.total = total;
        this.status = "CREATED";
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return orderId + " | ₹" + total + " | " + status;
    }
}