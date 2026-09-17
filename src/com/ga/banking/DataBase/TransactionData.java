package com.ga.banking.DataBase;

import com.ga.banking.models.Account;
import com.ga.banking.models.Customer;
import com.ga.banking.models.Transaction;
import com.ga.banking.enums.TransctionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionData {
    private static final Path FILE =
            Path.of("DataBase", "transactions.txt");

    public static void saveTransactions(List<Customer> customers) {
        try {
            Files.createDirectories(FILE.getParent());
            List<String> lines = new ArrayList<>();
            lines.add("AccountNumber,TransactionID,Type,Amount,BalanceAfterTransaction,DateTime");

            for (Customer customer : customers) {
                for (Account account : customer.getAccounts()) {
                    for (Transaction transaction :
                            account.getTransactions()) {
                        String line = account.getAccountNumber() + "," + transaction.getTransactionId() + "," + transaction.getType()
                                        + "," + transaction.getAmount() + "," + transaction.getBalanceAfterTransaction() + "," + transaction.getDateTime();
                        lines.add(line);
                    }
                }
            }

            Files.write(FILE, lines);
        }
        catch (IOException e) {

            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static void loadTransactions(List<Customer> customers) {

        try {
            if (!Files.exists(FILE)) {return;}
            List<String> lines = Files.readAllLines(FILE);

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.isBlank()) {continue;}
                String[] data = line.split(",");
                if (data.length < 6) {
                    System.out.println("Skipping invalid transaction data.");
                    continue;
                }


                int accountNumber = Integer.parseInt(data[0]);
                int transactionId = Integer.parseInt(data[1]);
                TransctionType type = TransctionType.valueOf(data[2]);
                double amount = Double.parseDouble(data[3]);
                double balanceAfterTransaction = Double.parseDouble(data[4]);
                LocalDateTime dateTime = LocalDateTime.parse(data[5]);
                Account account = findAccount(customers, accountNumber);
                if (account == null) {
                    System.out.println("Account not found for transaction: " + transactionId);
                    continue;
                }

                Transaction transaction = new Transaction(transactionId, type, amount, balanceAfterTransaction, dateTime);
                account.addTransaction(transaction);
                account.updateTransactionCounter(transactionId);
            }

        }
        catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
    private static Account findAccount(List<Customer> customers, int accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber() == accountNumber) {
                    return account;
                }
            }
        }
        return null;
    }

    public static List<String> readTransactions() {

        try {
            if (!Files.exists(FILE)) {return new ArrayList<>();}
            return Files.readAllLines(FILE);

        } catch (IOException e) {

            System.out.println(
                    "Error reading transactions: "
                            + e.getMessage()
            );

            return new ArrayList<>();
        }
    }
}