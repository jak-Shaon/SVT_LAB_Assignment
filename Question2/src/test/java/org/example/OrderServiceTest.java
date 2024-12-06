package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private ShippingService shippingService;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(shippingService);
    }

    @Test
    void testPlaceOrder_ValidOrder() {
        String item = "Laptop";
        int quantity = 1;
        when(shippingService.ship(item, quantity)).thenReturn(true);

        boolean result = orderService.placeOrder(item, quantity);

        assertTrue(result, "Order placed successfully");
        verify(shippingService, times(1)).ship(item, quantity);
    }

    @Test
    void testPlaceOrder_InvalidQuantity() {
        String item = "Phone";
        int quantity = 0;

        boolean result = orderService.placeOrder(item, quantity);

        assertFalse(result, "Order can not be placed for quantity <= 0");
        verify(shippingService, never()).ship(anyString(), anyInt());
    }

    @Test
    void testPlaceOrder_InvalidShipment() {
        String item = "Tablet";
        int quantity = 2;
        when(shippingService.ship(item, quantity)).thenReturn(false);

        boolean result = orderService.placeOrder(item, quantity);

        assertFalse(result, "Order fail if shipment cannot be completed");
        verify(shippingService, times(1)).ship(item, quantity);
    }
}
