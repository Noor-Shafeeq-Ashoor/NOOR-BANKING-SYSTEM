package com.ga.banking.models;

public class SavingsAccount extends Account {

    public SavingsAccount(
            int accountNumber,
            double balance,
            MasterCard mastercard) {

        super(
                accountNumber,
                balance,
                mastercard
        );
    }
}