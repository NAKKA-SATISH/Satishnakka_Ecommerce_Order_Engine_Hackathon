package ECommerceOrderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private Map<String, List<CartItem>> carts = new HashMap<>();

    // Add to Cart
    public void addToCart(String userId, Product product, int qty) {

        if (!product.reserveStock(qty)) {
            System.out.println("Insufficient stock");
            return;
        }

        carts.putIfAbsent(userId, new ArrayList<>());
        List<CartItem> cart = carts.get(userId);

        // If product already in cart, increase quantity
        for (CartItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                int newQty = item.getQuantity() + qty;
                cart.remove(item);
                cart.add(new CartItem(product, newQty));

                AuditLogger.log(userId + " updated qty for " + product.getName());
                return;
            }
        }

        cart.add(new CartItem(product, qty));
        AuditLogger.log(userId + " added " + product.getName() + " qty=" + qty);
    }

    // Remove From Cart
    public void removeFromCart(String userId, int productId) {

        List<CartItem> cart = carts.get(userId);

        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        CartItem removeItem = null;

        for (CartItem item : cart) {
            if (item.getProduct().getId() == productId) {
                item.getProduct().releaseStock(item.getQuantity());
                removeItem = item;
                break;
            }
        }

        if (removeItem != null) {
            cart.remove(removeItem);
            System.out.println("Item removed from cart");
            AuditLogger.log(userId + " removed product " + productId + " from cart");
        } else {
            System.out.println("Product not found in cart");
        }
    }

    // View Cart
    public void viewCart(String userId) {

        List<CartItem> cart = carts.get(userId);

        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double total = 0;

        System.out.println("---- Cart Items ----");
        for (CartItem item : cart) {
            double itemTotal = item.getTotal();
            total += itemTotal;

            System.out.println(
                    item.getProduct().getId() + " | " +
                    item.getProduct().getName() + " | Qty: " +
                    item.getQuantity() + " | Total: ₹" + itemTotal);
        }

        System.out.println("Cart Total = ₹" + total);
    }

    // Get Cart
    public List<CartItem> getCart(String userId) {
        return carts.getOrDefault(userId, new ArrayList<>());
    }

    // Clear Cart
    public void clearCart(String userId) {
        carts.remove(userId);
        AuditLogger.log(userId + " cart cleared");
    }

    // Cart Total Calculation
    public double getCartTotal(String userId) {
        List<CartItem> cart = getCart(userId);

        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotal();
        }

        return total;
    }
}