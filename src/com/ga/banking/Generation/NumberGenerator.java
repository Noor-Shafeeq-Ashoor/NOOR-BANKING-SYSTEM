package com.ga.banking.Generation;

public class NumberGenerator {

    private static int nextAccountNumber = 1;
    private static int nextCardNumber = 1;

    public static int generateAccountNumber() {
        return nextAccountNumber++;
    }

    public static int generateCardNumber() {
        return nextCardNumber++;
    }
}