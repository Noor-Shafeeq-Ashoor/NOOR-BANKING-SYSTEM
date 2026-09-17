package com.ga.banking.models;

import com.ga.banking.Exception.AccountInactiveEx;
import com.ga.banking.Exception.InsufficientFundsEx;
import com.ga.banking.Exception.InvalidAmountEx;
import com.ga.banking.Interface.ITransactionOperations;
import com.ga.banking.Service.Authentication;
import com.ga.banking.enums.TransctionType;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements ITransactionOperations {

    private int accountNumber;
    private double balance;
    private boolean active;
    private int overdraftCount;

    private List<Transaction> transactions;

    private MasterCard mastercard;

    private double dailyWithdrawalAmount;
    private int transactionCounter = 0;
    private double dailyDepositAmount;
    private double dailyTransferAmount;

    // NEW
    private int ownerId;
    private String accountHash;
    private String accountSalt;


    public Account(
            int accountNumber,
            double balance,
            MasterCard mastercard) {

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.active = true;
        this.overdraftCount = 0;
        this.transactions = new ArrayList<>();
        this.mastercard = mastercard;
        this.dailyWithdrawalAmount = 0;
        this.dailyDepositAmount = 0;
        this.dailyTransferAmount = 0;
    }


    // =========================
    // DEPOSIT
    // =========================

   @Override
    public void deposit(double amount) {

        if (!active) {
            throw new AccountInactiveEx(
                    "Account is inactive."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountEx(
                    "Deposit amount must be greater than zero."
            );
        }

        double depositLimit =
                mastercard.getDailyDepositLimit();

        if (dailyDepositAmount + amount > depositLimit) {
            throw new InsufficientFundsEx(
                    "Daily deposit limit exceeded."
            );
        }

        balance += amount;

        dailyDepositAmount += amount;

        Transaction transaction =
                new Transaction(
                        ++transactionCounter,
                        TransctionType.DEPOSIT,
                        amount,
                        balance
                );

        transactions.add(transaction);
    }



    // =========================
    // WITHDRAW
    // =========================

    @Override
    public void withdraw(double amount) {

        if (!active) {
            throw new AccountInactiveEx(
                    "Account is inactive."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountEx(
                    "Withdrawal amount must be greater than zero."
            );
        }

        double withdrawalLimit =
                mastercard.getDailyWithdrawalLimit();

        if (dailyWithdrawalAmount + amount >
                withdrawalLimit) {

            throw new InsufficientFundsEx(
                    "Daily withdrawal limit exceeded."
            );
        }

        if (amount > balance) {

            double overdraftAmount =
                    amount - balance;

            if (overdraftAmount > 100) {

                throw new InsufficientFundsEx(
                        "Overdraft limit exceeded."
                );
            }

            balance -= amount;

            // ACME overdraft fee
            balance -= 35;

            overdraftCount++;

            if (overdraftCount >= 2) {
                active = false;
            }

        } else {

            balance -= amount;
        }

        dailyWithdrawalAmount += amount;

        Transaction transaction =
                new Transaction(
                        ++transactionCounter,
                        TransctionType.WITHDRAW,
                        amount,
                        balance
                );

        transactions.add(transaction);
    }


    // =========================
    // TRANSFER
    // =========================

    @Override
    public void transfer(
            Account targetAccount,
            double amount) {

        if (!active) {
            throw new AccountInactiveEx(
                    "Account is inactive."
            );
        }

        if (targetAccount == null) {
            throw new InvalidAmountEx(
                    "Target account cannot be null."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountEx(
                    "Transfer amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsEx(
                    "Insufficient funds for transfer."
            );
        }

        double transferLimit;

        if (ownerId == targetAccount.getOwnerId()) {

            transferLimit =
                    mastercard.getDailyOwnAccountTransferLimit();

        } else {

            transferLimit =
                    mastercard.getDailyTransferLimit();
        }

        if (dailyTransferAmount + amount > transferLimit) {

            throw new InsufficientFundsEx(
                    "Daily transfer limit exceeded."
            );
        }

        balance -= amount;
        dailyTransferAmount += amount;

        targetAccount.increaseBalance(amount);


        // TARGET transaction
        Transaction targetTransaction =
                new Transaction(
                        ++targetAccount.transactionCounter,
                        TransctionType.TRANSFER_IN,
                        amount,
                        targetAccount.balance
                );

        targetAccount.transactions.add(
                targetTransaction
        );


        // SOURCE transaction
        Transaction sourceTransaction =
                new Transaction(
                        ++transactionCounter,
                        TransctionType.TRANSFER_OUT,
                        amount,
                        balance
                );

        // FIXED: add to SOURCE account
        transactions.add(sourceTransaction);
    }


    // =========================
    // GETTERS
    // =========================

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
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

    public int getOwnerId() {
        return ownerId;
    }

    public String getAccountPasswordHash() {
        return accountHash;
    }

    public String getAccountPasswordSalt() {
        return accountSalt;
    }


    // =========================
    // SETTERS
    // =========================

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setAccountPasswordHash(String accountPasswordHash) {
        this.accountHash = accountPasswordHash;
    }

    public void setAccountPasswordSalt(String accountPasswordSalt) {
        this.accountSalt = accountPasswordSalt;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOverdraftCount(int overdraftCount) {
        this.overdraftCount = overdraftCount;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTransactions(
            List<Transaction> transactions) {

        this.transactions = transactions;
    }


    public void updateTransactionCounter(int transactionId) {
        if (transactionId > transactionCounter) {
            transactionCounter = transactionId;
        }
    }




    // =========================
    // OTHER
    // =========================

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

    public void addTransaction(
            Transaction transaction) {

        transactions.add(transaction);
    }

    public void increaseBalance(double amount) {
        balance += amount;
    }
    public void setAccountPassword(String password) {

        if (password == null || !password.matches("\\d{6}")) {
            throw new IllegalArgumentException(
                    "Account password must be exactly 6 digits."
            );
        }

        String salt = Authentication.generateSalt();

        String hash = Authentication.hashPassword(password, salt);

        this.accountSalt = salt;
        this.accountHash = hash;
    }


    public boolean verifyAccountPassword(String password) {

        if (password == null || accountHash == null || accountSalt == null) {
            return false;
        }

        return Authentication.verifyPassword(password, accountHash, accountSalt);
    }
}