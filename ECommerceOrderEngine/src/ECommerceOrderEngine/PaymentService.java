package ECommerceOrderEngine;

import java.util.Random;

public class PaymentService {
	
	    private Random random = new Random();

	    public boolean processPayment() {
	        return random.nextBoolean();
	    }
}
