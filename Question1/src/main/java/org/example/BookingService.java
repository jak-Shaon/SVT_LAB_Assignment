package org.example;

public class BookingService {

    private final PaymentService paymentService;

    public BookingService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean bookService(String serviceId, double amount) {
        boolean paymentSuccess = paymentService.processPayment(serviceId, amount);
        if (paymentSuccess) {
            System.out.println("Service booked successfully!");
            return true;
        } else {
            System.out.println("Service booking failed due to payment issues.");
            return false;
        }
    }
}
