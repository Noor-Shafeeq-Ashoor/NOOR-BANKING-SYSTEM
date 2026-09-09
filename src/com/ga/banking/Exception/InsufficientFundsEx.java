package com.ga.banking.Exception;

public class InsufficientFundsEx extends RuntimeException {
    public InsufficientFundsEx(String message) {
        super(message);
    }
}
