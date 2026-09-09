package com.ga.banking.Exception;

public class AccountInactiveEx extends RuntimeException{
    public AccountInactiveEx(String message) {
        super(message);
    }
}
