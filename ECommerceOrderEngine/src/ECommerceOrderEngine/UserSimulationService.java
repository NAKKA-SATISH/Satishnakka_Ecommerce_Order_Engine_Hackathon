package ECommerceOrderEngine;

public class UserSimulationService {

    public void simulateUsers(ProductService productService,
                              CartService cartService,
                              OrderService orderService) {

        Runnable user1 = () -> {
            Product p = productService.getProduct(1);
            if (p != null) {
                cartService.addToCart("USER_A", p, 1);
                orderService.placeOrder("USER_A", cartService, "FLAT10");
            }
        };

        Runnable user2 = () -> {
            Product p = productService.getProduct(1);
            if (p != null) {
                cartService.addToCart("USER_B", p, 2);
                orderService.placeOrder("USER_B", cartService, "SAVE100");
            }
        };

        Thread t1 = new Thread(user1);
        Thread t2 = new Thread(user2);

        t1.start();
        t2.start();

        System.out.println("Concurrent users simulation started");
        AuditLogger.log("Concurrent users simulation executed");
    }
}