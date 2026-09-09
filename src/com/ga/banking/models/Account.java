package com.ga.banking.models;

import com.ga.banking.Exception.AccountInactiveEx;
import com.ga.banking.Exception.InsufficientFundsEx;
import com.ga.banking.Exception.InvalidAmountEx;
import com.ga.banking.Interface.ITransactionOperations;
import com.ga.banking.enums.TransctionType;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements ITransactionOperations {

    private int accountNumber;
    private double balance;
    private boolean active;
    private int overdraftCount;
    private List<Transaction> transactions;
    // Connect the account with a MasterCard
    private MasterCard mastercard;
    private int transactionCounter = 0;


    public Account(int accountNumber, double balance, MasterCard mastercard) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.active = true;
        this.overdraftCount = 0;
        this.transactions = new ArrayList<>();
        this.mastercard = mastercard;
    }


    @Override
    public void deposit(double amount) {
            if (!isActive()) {
                throw new AccountInactiveEx("Account is inactive.");
            }
            if (amount <= 0) {
                throw new InvalidAmountEx("Deposit amount must be greater than zero.");
            }

            balance+=amount;

        Transaction transaction = new Transaction(++transactionCounter, com.ga.banking.enums.TransctionType.DEPOSIT, amount, balance);

        transactions.add(transaction);
        }

    @Override
    public void withdraw(double amount) {

        if (!active) {
            throw new AccountInactiveEx("Account is inactive.");
        }

        if (amount <= 0) {
            throw new InvalidAmountEx( "Withdrawal amount must be greater than zero.");
        }

        if (amount > balance) {

            double overdraftAmount = amount - balance;

            if (overdraftAmount > 100) {throw new InsufficientFundsEx("Overdraft limit exceeded.");}
            balance -= amount;
            balance -= 35;
            overdraftCount++;

            if (overdraftCount >= 2) {
                active = false;
            }

        } else {
            balance -= amount;
        }

        Transaction transaction = new Transaction( ++transactionCounter, TransctionType.WITHDRAW, amount, balance);

        transactions.add(transaction);
    }
    @Override
    public void transfer(Account targetAccount, double amount) {

        if (!active) {
            throw new AccountInactiveEx("Account is inactive.");
        }

        if (targetAccount == null) {
            throw new InvalidAmountEx("Target account cannot be null.");
        }

        if (amount <= 0) {
            throw new InvalidAmountEx("Transfer amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new InsufficientFundsEx("Insufficient funds for transfer.");
        }

        balance -= amount;
        targetAccount.increaseBalance(amount);

        Transaction targetTransaction = new Transaction(
                ++targetAccount.transactionCounter,
                TransctionType.TRANSFER_IN,
                amount,
                targetAccount.balance
        );
        targetAccount.transactions.add(targetTransaction);

        Transaction sourceTransaction = new Transaction(
                ++transactionCounter,
                TransctionType.TRANSFER_OUT,
                amount,
                balance
        );

        targetAccount.transactions.add(sourceTransaction);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
    public void activate() {
        active = true;
    }

    public int getOverdraftCount() {
        return overdraftCount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public MasterCard getMastercard() {
        return mastercard;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void increaseBalance(double amount) {
        balance += amount;
    }
}