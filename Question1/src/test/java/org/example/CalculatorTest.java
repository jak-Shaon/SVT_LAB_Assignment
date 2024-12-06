package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void setupAll() {
        System.out.println("BeforeAll: Setting up resources.");
    }

    @BeforeEach
    void setupEach() {
        System.out.println("BeforeEach: Initializing test environment.");
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        int result = calculator.add(5, 3);
        assertEquals(8, result);
        assertNotEquals(10, result);
    }

    @Test
    void testDivideByZeroThrowsException() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0),
                "Division by zero should throw ArithmeticException"
        );
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testDivideDoesNotThrowException() {
        assertDoesNotThrow(() -> calculator.divide(10, 2),
                "Division should not throw an exception");
    }

    @Test
    void testNullAndNotNull() {
        Calculator nullCalculator = null;
        assertNull(nullCalculator, "Calculator instance should be null");
        assertNotNull(calculator, "Calculator instance should not be null");
    }

    @Test
    void testAssertArrayEquals() {
        int[] expectedArray = {1, 2, 3, 4};
        int[] actualArray = calculator.generateArray();
        assertArrayEquals(expectedArray, actualArray, "Arrays should be identical");
    }

    @Test
    void testAssertSame() {
        String str1 = "Hello";
        String str2 = str1;
        assertSame(str1, str2, "str1 and str2 should refer to the same instance");
    }

    @Test
    void testAssertLinesMatch() {
        List<String> expectedLines = List.of("Line 1", "Line 2", "Line 3");
        List<String> actualLines = List.of("Line 1", "Line 2", "Line 3");
        assertLinesMatch(expectedLines, actualLines, "The lines should match");
    }

    @Test
    void testExecutionTime() {
        assertTimeout(Duration.ofMillis(10), () -> {
            calculator.add(10, 20); // Simulated operation
        }, "Method should complete within the time limit");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "5, 5, 10",
            "10, 20, 30"
    })
    void testAddWithParameters(int a, int b, int expected) {
        int result = calculator.add(a, b);
        assertEquals(expected, result);
    }

    @RepeatedTest(5)
    void testGenerateRandomNumberWithinRange() {
        int randomNumber = calculator.generateRandomNumber();
        assertTrue(randomNumber >= 0 && randomNumber < 100,
                "Generated number should be between 0 and 99");
    }

    @AfterEach
    void tearDownEach() {
        System.out.println("AfterEach: Cleaning up");
        calculator = null;
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll: Cleaning up resources.");
    }
}
