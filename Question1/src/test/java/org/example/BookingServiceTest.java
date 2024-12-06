package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Mock
    private PaymentService paymentService; // Mock PaymentService

    @InjectMocks
    private BookingService bookingService; // Inject mock into BookingService

    public BookingServiceTest() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testBookServiceCallsProcessPayment() {
        // Arrange
        String serviceId = "SERVICE123";
        double amount = 100.0;
        when(paymentService.processPayment(serviceId, amount)).thenReturn(true); // Mock behavior

        // Act
        boolean result = bookingService.bookService(serviceId, amount);

        // Assert
        assertTrue(result, "Booking should be successful");
        verify(paymentService, times(1)).processPayment(serviceId, amount); // Verify method call
        verifyNoMoreInteractions(paymentService); // Ensure no other methods are called
    }
}
