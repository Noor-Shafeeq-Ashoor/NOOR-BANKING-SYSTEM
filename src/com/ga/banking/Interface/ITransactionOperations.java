package com.ga.banking.Interface;

import com.ga.banking.models.Account;

public interface ITransactionOperations {
    void deposit(double amount);

    void withdraw(double amount);

    void transfer(Account targetAccount, double amount);
}
