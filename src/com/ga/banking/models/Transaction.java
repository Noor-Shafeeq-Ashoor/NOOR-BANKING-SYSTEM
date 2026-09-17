package com.ga.banking.models;


import com.ga.banking.enums.TransctionType;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private TransctionType type;
    private double amount;
    private double balanceAfterTransaction;
    private LocalDateTime dateTime;

    public Transaction(int transactionId, TransctionType type, double amount, double balanceAfterTransaction) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.dateTime = LocalDateTime.now();
    }
    public Transaction( int transactionId, TransctionType type, double amount, double balanceAfterTransaction, LocalDateTime dateTime) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.dateTime = dateTime; }

    public int getTransactionId() {
        return transactionId;
    }

    public TransctionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

