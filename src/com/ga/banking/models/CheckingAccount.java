package com.ga.banking.models;

import com.ga.banking.Exception.AccountInactiveEx;
import com.ga.banking.Exception.InvalidAmountEx;
import com.ga.banking.models.Account;

public class CheckingAccount extends Account {

    public CheckingAccount (int accountNumber, double balance, MasterCard mastercard) {
        super(accountNumber, balance, mastercard);
    }

//    @Override
//    public void deposit(double amount) {
////            if (!isActive()) {
////                throw new AccountInactiveEx("Account is inactive.");
////            }
////            if (amount <= 0) {
////                throw new InvalidAmountEx("Deposit amount must be greater than zero.");
////            }
////
////            increaseBalance(amount);
//        }
//
//    @Override
//    public void withdraw(double amount) {
//
//    }
//
//    @Override
//    public void transfer(Account targetAccount, double amount) {
//
//    }
}
