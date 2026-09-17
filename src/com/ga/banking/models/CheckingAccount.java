package com.ga.banking.models;

public class CheckingAccount extends Account {

    public CheckingAccount(
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