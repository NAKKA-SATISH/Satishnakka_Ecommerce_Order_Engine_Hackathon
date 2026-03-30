package ECommerceOrderEngine;

public class CouponService {

    public double applyCoupon(String code, double total) {
        if (code.equalsIgnoreCase("FLAT10")) {
            return total * 0.9;
        } else if (code.equalsIgnoreCase("SAVE100")) {
            return total - 100;
        }
        return total;
    }
}