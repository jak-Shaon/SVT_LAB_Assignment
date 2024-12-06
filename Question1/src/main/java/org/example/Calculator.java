package org.example;

import java.util.Random;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double) a / b;
    }

    public int[] generateArray() {
        return new int[]{1, 2, 3, 4};
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100); // Generates a number between 0 and 99
    }
}
