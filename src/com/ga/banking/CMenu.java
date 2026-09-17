package com.ga.banking;

import com.ga.banking.DataBase.RequestData;
import com.ga.banking.DataBase.TransactionData;
import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.enums.RequestStatus;
import com.ga.banking.models.Account;
import com.ga.banking.models.AccountRequest;
import com.ga.banking.models.Customer;
import com.ga.banking.models.Transaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CMenu {

    private Customer customer;
    private Scanner scanner;
    private List<AccountRequest> requests;
    private List<Customer> customers;

    public CMenu(Customer customer, Scanner scanner, List<AccountRequest> requests, List<Customer> customers) {
        this.customer = customer;
        this.scanner = scanner;
        this.requests = requests;
        this.customers = customers;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== CUSTOMER MENU =====");
            System.out.println("Welcome " + customer.getUsername());
            System.out.println("1. View Accounts");
            System.out.println("2. Request New Account");
            System.out.println("3. Logout");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();scanner.nextLine();
            switch (choice) {
                case 1: viewAccounts();
                    break;

                case 2: requestNewAccount();
                    break;

                case 3: running = false;
                    System.out.println("Logged out.");
                    break;

                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void viewAccounts() {
        System.out.println();
        System.out.println("---- YOUR ACCOUNTS ----");
        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts.");
            return;
        }

        for (int i = 0; i < customer.getAccounts().size(); i++) {
            Account account = customer.getAccounts().get(i);
            System.out.println((i + 1) + ". " + account.getClass().getSimpleName());
        }
        System.out.println((customer.getAccounts().size() + 1) + ". Back");
        System.out.print("Choose account: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == customer.getAccounts().size() + 1) {
            return;
        }


        if (choice < 1 || choice > customer.getAccounts().size()) {
            System.out.println("Invalid account choice.");
            return;
        }

        Account selectedAccount = customer.getAccounts().get(choice - 1);
        System.out.print("Enter 6-digit account password: ");

        String password = scanner.nextLine();
        if (!password.matches("\\d{6}")) {
            System.out.println("Invalid account password.");
            return;
        }

        if (!selectedAccount.verifyAccountPassword(password)) {
            System.out.println("Incorrect account password.");
            return;
        }
        showAccountDetails(selectedAccount);
    }

    private void showAccountDetails(Account account) {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("===== ACCOUNT DETAILS =====");
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Status: " + (account.isActive() ? "Active" : "Inactive"));
            System.out.println("Mastercard: " + account.getMastercard().getMasterCardType());
            System.out.println();
            System.out.println("1. Card Details");
            System.out.println("2. Balance");
            System.out.println("3. Mastercard");
            System.out.println("4. Transactions");
            System.out.println("5. Account Statement");
            System.out.println("6. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println();
                    System.out.println("===== CARD DETAILS =====");
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println(
                            "Card Number: "
                                    + account.getMastercard().getCardNumber()
                    );
                    break;

                case 2:
                    System.out.println();
                    System.out.println("===== BALANCE =====");
                    System.out.println("Balance: " + account.getBalance());
                    break;

                case 3:
                    System.out.println();
                    System.out.println("===== MASTERCARD =====");
                    System.out.println(
                            "Card Number: "
                                    + account.getMastercard().getCardNumber()
                    );
                    System.out.println(
                            "Type: "
                                    + account.getMastercard().getMasterCardType()
                    );
                    break;

                case 4:
                    showTransactionsMenu(account);
                    break;

                case 5:
                    showAccountStatement(account);
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private void showAccountStatement(Account account) {

        System.out.println();
        System.out.println("===== ACCOUNT STATEMENT =====");

        System.out.println(
                "Account Number: "
                        + account.getAccountNumber()
        );

        System.out.println(
                "Account Type: "
                        + account.getClass().getSimpleName()
        );

        System.out.println(
                "Mastercard: "
                        + account.getMastercard().getMasterCardType()
        );

        System.out.println(
                "Status: "
                        + (account.isActive()
                        ? "Active"
                        : "Inactive")
        );

        System.out.println();

        double totalDeposits = 0;
        double totalWithdrawals = 0;
        double totalTransfersIn = 0;
        double totalTransfersOut = 0;

        for (Transaction transaction :
                account.getTransactions()) {

            switch (transaction.getType()) {

                case DEPOSIT:
                    totalDeposits += transaction.getAmount();
                    break;

                case WITHDRAW:
                    totalWithdrawals += transaction.getAmount();
                    break;

                case TRANSFER_IN:
                    totalTransfersIn += transaction.getAmount();
                    break;

                case TRANSFER_OUT:
                    totalTransfersOut += transaction.getAmount();
                    break;
            }
        }

        System.out.println("----- SUMMARY -----");

        System.out.println(
                "Total Deposits: "
                        + totalDeposits
        );

        System.out.println(
                "Total Withdrawals: "
                        + totalWithdrawals
        );

        System.out.println(
                "Total Transfers In: "
                        + totalTransfersIn
        );

        System.out.println(
                "Total Transfers Out: "
                        + totalTransfersOut
        );

        System.out.println(
                "Current Balance: "
                        + account.getBalance()
        );

        System.out.println();

        System.out.println("----- TRANSACTIONS -----");

        if (account.getTransactions().isEmpty()) {

            System.out.println(
                    "No transactions found."
            );

            return;
        }

        for (Transaction transaction :
                account.getTransactions()) {

            System.out.println(
                    "------------------------------"
            );

            System.out.println(
                    "Transaction ID: "
                            + transaction.getTransactionId()
            );

            System.out.println(
                    "Type: "
                            + transaction.getType()
            );

            System.out.println(
                    "Amount: "
                            + transaction.getAmount()
            );

            System.out.println(
                    "Balance After Transaction: "
                            + transaction
                            .getBalanceAfterTransaction()
            );

            System.out.println(
                    "Date & Time: "
                            + transaction.getDateTime()
            );
        }

        System.out.println(
                "------------------------------"
        );
    }

    private void showTransactionsMenu(Account account) {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("===== TRANSACTIONS =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    deposit(account);
                    break;

                case 2:
                    withdraw(account);
                    break;

                case 3:
                    transfer(account);
                    break;


                case 4:
                    showTransactionHistory(account);
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


//    private void showTransactionHistory(Account account) {
//
//        boolean running = true;
//
//        while (running) {
//
//            System.out.println();
//            System.out.println("===== TRANSACTION HISTORY =====");
//
//            System.out.println("1. Show All");
//            System.out.println("2. Today");
//            System.out.println("3. Yesterday");
//            System.out.println("4. Last 7 Days");
//            System.out.println("5. Last 30 Days");
//            System.out.println("6. Date/Time");
//            System.out.println("7. Back");
//
//            System.out.print("Choose: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//
//                case 1:
//                    displayTransactions(
//                            account,
//                            null,
//                            null
//                    );
//                    break;
//
//                case 2:
//                    showToday(account);
//                    break;
//
//                case 3:
//                    showYesterday(account);
//                    break;
//
//                case 4:
//                    showLast7Days(account);
//                    break;
//
//                case 5:
//                    showLast30Days(account);
//                    break;
//
//                case 6:
//                    showCustomDateTime(account);
//                    break;
//
//                case 7:
//                    running = false;
//                    break;
//
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        }
//    }


    private void showTransactionHistory(Account account) {

        System.out.println();
        System.out.println("===== TRANSACTION HISTORY =====");

        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        // Create a copy so we don't change the original transaction list
        List<Transaction> transactions =
                new ArrayList<>(account.getTransactions());

        // Lambda expression:
        // Sort newest transactions first
        transactions.sort(
                (t1, t2) ->
                        t2.getDateTime().compareTo(
                                t1.getDateTime()
                        )
        );

        for (Transaction transaction : transactions) {

            System.out.println(
                    "------------------------------"
            );

            System.out.println(
                    "Transaction ID: "
                            + transaction.getTransactionId()
            );

            System.out.println(
                    "Type: "
                            + transaction.getType()
            );

            System.out.println(
                    "Amount: "
                            + transaction.getAmount()
            );

            System.out.println(
                    "Balance After Transaction: "
                            + transaction.getBalanceAfterTransaction()
            );

            System.out.println(
                    "Date & Time: "
                            + transaction.getDateTime()
            );
        }

        System.out.println(
                "------------------------------"
        );
    }



    private void showToday(Account account) {

        LocalDate today = LocalDate.now();

        LocalDateTime start =
                today.atStartOfDay();

        LocalDateTime end =
                today.plusDays(1).atStartOfDay();

        displayTransactions(
                account,
                start,
                end
        );
    }
    private void showYesterday(Account account) {

        LocalDate yesterday =
                LocalDate.now().minusDays(1);

        LocalDateTime start =
                yesterday.atStartOfDay();

        LocalDateTime end =
                yesterday.plusDays(1).atStartOfDay();

        displayTransactions(
                account,
                start,
                end
        );
    }

    private void showLast7Days(Account account) {

        LocalDateTime end =
                LocalDateTime.now();

        LocalDateTime start =
                end.minusDays(7);

        displayTransactions(
                account,
                start,
                end
        );
    }

    private void showLast30Days(Account account) {

        LocalDateTime end =
                LocalDateTime.now();

        LocalDateTime start =
                end.minusDays(30);

        displayTransactions(
                account,
                start,
                end
        );
    }

    private void showCustomDateTime(Account account) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm"
                );

        System.out.println();
        System.out.println("===== DATE/TIME FILTER =====");
        System.out.println(
                "Format: yyyy-MM-dd HH:mm"
        );

        System.out.print("Enter start date/time: ");

        String startInput =
                scanner.nextLine();

        System.out.print("Enter end date/time: ");

        String endInput =
                scanner.nextLine();

        try {

            LocalDateTime start =
                    LocalDateTime.parse(
                            startInput,
                            formatter
                    );

            LocalDateTime end =
                    LocalDateTime.parse(
                            endInput,
                            formatter
                    );

            if (end.isBefore(start)) {

                System.out.println(
                        "End date/time cannot be before start date/time."
                );

                return;
            }

            displayTransactions(
                    account,
                    start,
                    end
            );

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Invalid date/time format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd HH:mm"
            );
        }
    }

    private void displayTransactions(
            Account account,
            LocalDateTime start,
            LocalDateTime end) {

        List<Transaction> transactions =
                account.getTransactions();

        System.out.println();

        if (start == null && end == null) {

            System.out.println(
                    "===== ALL TRANSACTIONS ====="
            );

        } else {

            System.out.println(
                    "===== FILTERED TRANSACTIONS ====="
            );
        }


        boolean found = false;


        for (Transaction transaction : transactions) {

            LocalDateTime transactionDate =
                    transaction.getDateTime();


            // If a date filter exists
            if (start != null && end != null) {

                if (transactionDate.isBefore(start)
                        || !transactionDate.isBefore(end)) {

                    continue;
                }
            }


            found = true;

            System.out.println(
                    "------------------------------"
            );

            System.out.println(
                    "Transaction ID: "
                            + transaction.getTransactionId()
            );

            System.out.println(
                    "Type: "
                            + transaction.getType()
            );

            System.out.println(
                    "Amount: "
                            + transaction.getAmount()
            );

            System.out.println(
                    "Balance After Transaction: "
                            + transaction.getBalanceAfterTransaction()
            );

            System.out.println(
                    "Date & Time: "
                            + transaction.getDateTime()
            );
        }


        if (!found) {

            System.out.println(
                    "No transactions found for this period."
            );
        }


        System.out.println(
                "------------------------------"
        );
    }






    private void transfer(Account sourceAccount) {

        System.out.println();
        System.out.println("===== TRANSFER =====");

        // Show all available accounts except the current account
        List<Account> availableAccounts = new java.util.ArrayList<>();

        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {

                if (account.getAccountNumber()
                        != sourceAccount.getAccountNumber()) {

                    availableAccounts.add(account);
                }
            }
        }

        if (availableAccounts.isEmpty()) {
            System.out.println("No other accounts are available.");
            return;
        }

        System.out.println("Choose target account:");

        for (int i = 0; i < availableAccounts.size(); i++) {

            Account account = availableAccounts.get(i);

            System.out.println(
                    (i + 1)
                            + ". Account "
                            + account.getAccountNumber()
            );
        }

        System.out.println(
                (availableAccounts.size() + 1) + ". Back"
        );

        System.out.print("Choose: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == availableAccounts.size() + 1) {
            return;
        }

        if (choice < 1 || choice > availableAccounts.size()) {
            System.out.println("Invalid account choice.");
            return;
        }

        Account targetAccount =
                availableAccounts.get(choice - 1);

        System.out.print("Enter transfer amount: ");

        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {

            sourceAccount.transfer(targetAccount, amount);
            TransactionData.saveTransactions(customers);

            System.out.println();
            System.out.println("Transfer successful!");
            System.out.println("Amount: " + amount);
            System.out.println(
                    "From Account: "
                            + sourceAccount.getAccountNumber()
            );
            System.out.println(
                    "To Account: "
                            + targetAccount.getAccountNumber()
            );
            System.out.println(
                    "New Balance: "
                            + sourceAccount.getBalance()
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "Transfer failed: "
                            + e.getMessage()
            );
        }
    }

    private void deposit(Account account) {

        System.out.println();
        System.out.println("===== DEPOSIT =====");
        System.out.print("Enter deposit amount: ");

        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {

            account.deposit(amount);
            TransactionData.saveTransactions(customers);

            System.out.println();
            System.out.println("Deposit successful!");
            System.out.println("Deposited: " + amount);
            System.out.println("New Balance: " + account.getBalance());

        } catch (Exception e) {

            System.out.println();
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }


    private void withdraw(Account account) {

        System.out.println();
        System.out.println("===== WITHDRAW =====");
        System.out.print("Enter withdrawal amount: ");

        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {

            account.withdraw(amount);
            TransactionData.saveTransactions(customers);

            System.out.println();
            System.out.println("Withdrawal successful!");
            System.out.println("Withdrawn: " + amount);
            System.out.println("New Balance: " + account.getBalance());

            if (!account.isActive()) {
                System.out.println();
                System.out.println("WARNING: Your account has been deactivated.");
            }

        } catch (Exception e) {

            System.out.println();
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private boolean canRequestAccount(AccountType accountType) {

        // Check if customer already owns this account type
        for (var account : customer.getAccounts()) {

            if (account.getClass()
                    .getSimpleName()
                    .equalsIgnoreCase(
                            accountType.name().replace("_", "")
                    )) {

                System.out.println();
                System.out.println(
                        "You already have a "
                                + accountType
                                + " account."
                );

                return false;
            }
        }

        for (AccountRequest request : requests) {
            if (request.getCustomer().getId() == customer.getId() && request.getAccountType() == accountType && request.getStatus() == RequestStatus.PENDING) {
                System.out.println();
                System.out.println("You already have a pending request " + "for a " + accountType + " account.");
                System.out.println("Please wait for the bank to approve " + "or reject your request.");
                return false;
            }
        }
        return true;
    }

    private void requestNewAccount() {
        System.out.println();
        System.out.println("===== REQUEST NEW ACCOUNT =====");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.print("Choose account type: ");

        int accountChoice = scanner.nextInt();
        scanner.nextLine();
        AccountType accountType;
        if (accountChoice == 1) {
            accountType = AccountType.CHECKING;
        } else if (accountChoice == 2) {
            accountType = AccountType.SAVINGS;
        }
        else {
            System.out.println("Invalid account type.");
            return;
        }
        if (!canRequestAccount(accountType)) {
            return;
        }

        System.out.println();
        System.out.println("Choose Mastercard type:");
        System.out.println("1. Standard");
        System.out.println("2. Titanium");
        System.out.println("3. Platinum");
        System.out.print("Choose Mastercard: ");
        int cardChoice = scanner.nextInt();
        scanner.nextLine();
        MasterCardType requestedMC;
        if (cardChoice == 1) {requestedMC = MasterCardType.STANDARD;}
        else if (cardChoice == 2) {requestedMC = MasterCardType.TITANIUM;}
        else if (cardChoice == 3) {requestedMC = MasterCardType.PLATINUM;}
        else {System.out.println("Invalid Mastercard type.");
            return;
        }

        System.out.print("Create 6-digit account password: ");
        String accountPassword = scanner.nextLine();
        if (!accountPassword.matches("\\d{6}")) {
            System.out.println("Account password must be exactly 6 digits.");
            return;
        }
        int requestId = requests.size() + 1;
        AccountRequest request = new AccountRequest(requestId, requestedMC, accountType, customer, accountPassword);
        requests.add(request);
        RequestData.saveRequests(requests);
        System.out.println();
        System.out.println("Account request submitted successfully!");
        System.out.println("Account Type: " + accountType);
        System.out.println("Requested Mastercard: " + requestedMC);
        System.out.println("Status: " + request.getStatus());
    }
}