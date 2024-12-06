package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TemperatureConverterTest {

    @ParameterizedTest
    @CsvSource({"0, 32", "25, 77", "-40, -40"})
    void testCelsiusToFahrenheit(double celsius, double expectedFahrenheit) {

        double result = TemperatureConverter.celsiusToFahrenheit(celsius);

        assertEquals(expectedFahrenheit, result,
                "Celsius to Fahrenheit is incorrect");
    }

    @Test
    void testCelsiusToFahrenheit_BelowAbsoluteZero() {

        double invalidCelsius = -274;

        assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.celsiusToFahrenheit(invalidCelsius);
        }, "IllegalArgumentException for temperature below absolute zero");
    }
}
