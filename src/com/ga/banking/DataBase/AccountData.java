package com.ga.banking.DataBase;

import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.models.Account;
import com.ga.banking.models.CheckingAccount;
import com.ga.banking.models.Customer;
import com.ga.banking.models.MasterCard;
import com.ga.banking.models.SavingsAccount;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccountData {
    private static final Path FILE = Path.of("DataBase", "accounts.txt");

    public static void saveCustomerAccounts(Customer customer) {
        try {
            Files.createDirectories(FILE.getParent());
            List<String> lines = new ArrayList<>();
            if (Files.exists(FILE)) {lines.addAll(Files.readAllLines(FILE));
            }

            lines.removeIf(line -> {if (line.isBlank()) {return false;}
                String[] data = line.split(",");
                if (data.length < 10) {return false;}
                int ownerId = Integer.parseInt(data[0]);
                return ownerId == customer.getId();
            });

            for (Account account : customer.getAccounts()) {

                String line = customer.getId() + "," + account.getAccountNumber() + "," + account.getBalance() + "," +
                                account.isActive() + "," + account.getOverdraftCount() + "," + account.getMastercard().getCardNumber() + "," +
                                account.getMastercard().getMasterCardType() + "," + account.getClass().getSimpleName() + "," +
                                account.getAccountPasswordHash() + "," + account.getAccountPasswordSalt();
                lines.add(line);
            }

            Files.write(FILE, lines);
        }
        catch (IOException | NumberFormatException e)
        {System.out.println("Error saving accounts: " + e.getMessage());}
    }

    public static void loadAccounts(List<Customer> customers) {
        try {
            if (!Files.exists(FILE)) {return;}
            List<String> lines = Files.readAllLines(FILE);
            for (String line : lines) {
                if (line.isBlank()) {continue;}

                String[] data = line.split(",");

                if (data.length < 10) {
                    System.out.println("Skipping invalid account data.");
                    continue;
                }

                int ownerId = Integer.parseInt(data[0]);
                int accountNumber = Integer.parseInt(data[1]);
                double balance = Double.parseDouble(data[2]);
                boolean active = Boolean.parseBoolean(data[3]);
                int overdraftCount = Integer.parseInt(data[4]);
                int cardNumber = Integer.parseInt(data[5]);
                MasterCardType cardType = MasterCardType.valueOf(data[6]);
                String accountType = data[7];
                String passwordHash = data[8];
                String passwordSalt = data[9];

                Customer owner = null;

                for (Customer customer : customers) {
                    if (customer.getId() == ownerId) {
                        owner = customer;
                        break;
                    }
                }

                if (owner == null) {
                    System.out.println("Customer not found for account: " + accountNumber);
                    continue;
                }

                MasterCard mastercard = new MasterCard(cardNumber, cardType);
                Account account;

                if (accountType.equals("CheckingAccount")) {
                    account = new CheckingAccount(accountNumber, balance, mastercard);
                }
                else if (accountType.equals("SavingsAccount")) {
                    account = new SavingsAccount(accountNumber, balance, mastercard);

                }
                else {
                    System.out.println("Unknown account type: " + accountType);
                    continue;
                }

                account.setOwnerId(ownerId);
                account.setActive(active);
                account.setOverdraftCount(overdraftCount);
                account.setAccountPasswordHash(passwordHash);
                account.setAccountPasswordSalt(passwordSalt);
                owner.addAccount(account);
            }


        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public static List<String> readAccounts() {

        try {
            if (!Files.exists(FILE)) {
                return new ArrayList<>();
            }
            return Files.readAllLines(FILE);
        } catch (IOException e) {
            System.out.println("Error reading accounts: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
