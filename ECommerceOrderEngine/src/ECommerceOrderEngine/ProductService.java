package ECommerceOrderEngine;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            System.out.println("Duplicate Product ID not allowed");
            return;
        }
        products.put(product.getId(), product);
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public void viewProducts() {
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }

    public void lowStockAlert() {
        for (Product p : products.values()) {
            if (p.getStock() < 3) {
                System.out.println("Low Stock Alert: " + p);
            }
        }
    }
}
