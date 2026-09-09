package com.ga.banking.models;

public class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber, double balance, MasterCard mastercard) {
        super(accountNumber, balance, mastercard);
    }

//    @Override
//    public void deposit(double amount) {
//
//    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public void transfer(Account targetAccount, double amount) {

    }
}