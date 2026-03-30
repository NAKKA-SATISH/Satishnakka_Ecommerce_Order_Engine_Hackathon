# Satishnakka_Ecommerce_Order_Engine_Hackathon

Project Overview:-
The Ecommerce Order Engine is a CLI-based backend simulation of an ecommerce order management system developed in Java. The application simulates real-world ecommerce operations such as product management, cart operations, coupon discounts, order placement, payment processing, order cancellation, returns, logging, failure handling, and concurrent users.

This project demonstrates backend system design concepts like inventory management, order lifecycle, service-layer architecture, logging, multithreading, and failure simulation.

The system is completely in-memory and runs through a Command Line Interface (CLI).

Features Implemented:-
 Product Management
  -Add Product
  -View Products
  -Low Stock Alert
  -Prevent duplicate product IDs
  -Stock management
Cart Management
  -Add to Cart
  -Remove from Cart
  -View Cart
  -Stock reservation when adding to cart
  -Stock release when removing from cart
Coupon System
  -Apply coupon to cart/order
  -Percentage discount coupons
  -Flat discount coupons
Order Management
  -Place Order
  -View Orders
  -Cancel Order
  -Return Product
Order status tracking:
  -CREATED
  -PAID
  -FAILED
  -CANCELLED
  -RETURNED
Payment Simulation
  -Random payment success/failure
  -Stock restored if payment fails
  
Logging System
Audit logs stored for:

  -Product added
  -Cart operations
  -Order placed
  -Payment failed
  -Order cancelled
  -Product returned
  -Failure mode triggered
  -Concurrent users simulation
Failure Mode Simulation
  -System failure mode can be triggered
  -Blocks new order placement
  -Used to simulate system outages
Concurrent Users Simulation
  -Multiple users placing orders simultaneously
  -Implemented using multithreading
CLI Menu Operations
1 Add Product
2 View Products
3 Add to Cart
4 Remove from Cart
5 View Cart
6 Apply Coupon
7 Place Order
8 Cancel Order
9 View Orders
10 Low Stock Alert
11 Return Product
12 Simulate Concurrent Users
13 View Logs
14 Trigger Failure Mode
0 Exit

Future Improvements
  -Add database integration (MySQL / PostgreSQL)
  -Convert CLI to REST API (Spring Boot)
  -Add user authentication
  -Add order tracking system
  -Add inventory locking for concurrency
  -Add payment gateway integration
  -Build web UI
  -Deploy as microservice

Design Approach
The project follows a Service-Based Layered Architecture.

Architecture Layers
DriverClass (CLI Layer)->Service Layer->Model Layer->Utility Layer (Logger, Payment, etc.)

Assumptions
 -Single admin adds products to the system.
 -Stock is reserved when items are added to the cart.
 -Stock is restored when:
   -Item removed from cart
   -Payment fails
   -Order cancelled
   -Product returned
 -Payment success/failure is randomly simulated.
 -Coupons are applied at order level.
 -Failure mode blocks new order placement.
 -Concurrent users are simulated using threads.
 -Data is stored in memory (no database).
 -CLI is used instead of UI.
 -One cart per user.
 
How to Run the Project
 Requirements
  -Java 8 or higher
  -Any IDE (IntelliJ / Eclipse / VS Code)
 Steps to Run in IDE
  -Clone the repository
  -Open the project in IDE
  -Navigate to:DriverClass.java
  -Run the main method
  -Use CLI menu to test features
  
Compile and Run Using Terminal
  -javac ECommerceOrderEngine/*.java
  -java ECommerceOrderEngine.DriverClass
  


