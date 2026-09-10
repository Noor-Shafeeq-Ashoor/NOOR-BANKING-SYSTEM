package com.ga.banking;

import com.ga.banking.Service.Authentication;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("===== BANKING SYSTEM =====");
//        System.out.println("1. Customer");
//        System.out.println("2. Banker");
//        System.out.println("3. Exit");
//
//        System.out.print("Choose: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        if (choice == 1) {
//            System.out.println("===== CUSTOMER =====");
//            System.out.println("1. Login");
//            System.out.println("2. Create New Account");
//            System.out.println("3. Back");
//
//        } else if (choice == 2) {
//            System.out.println("===== BANKER LOGIN =====");
//
//        } else if (choice == 3) {
//            System.out.println("Goodbye!");
//
//        }
//
//        System.out.print("Username: ");
//        String username = scanner.nextLine();
//
//        System.out.print("Password: ");
//        String password = scanner.nextLine();
//
//        Authentication auth = new Authentication(users);
//
//        User loggedInUser = auth.login(username, password);
//
//        if (loggedInUser != null) {
//
//            if (loggedInUser instanceof Customer) {
//                System.out.println("Welcome Customer "
//                        + loggedInUser.getName());
//            }
//
//        } else {
//            System.out.println("Invalid username or password.");
//        }
//










//        List<User> users = new ArrayList<>();
//
//        Customer customer =
//                new Customer(1, "Noor", "noor", "1234");
//
//        Banker banker =
//                new Banker(2, "Ahmed", "ahmed", "5678");
//
//        users.add(customer);
//        users.add(banker);
//
//        Authentication auth = new Authentication(users);
//
//        User loggedIn = auth.login("noor", "99999934");
//
//        if (loggedIn != null) {
//            System.out.println("Login successful!");
//            System.out.println("Welcome " + loggedIn.getName());
//        } else {
//            System.out.println("Invalid username or password.");
//        }
//        MasterCard standardCard = new MasterCard(11111, MasterCardType.STANDARD);
//
//        CheckingAccount checkingAccount = new CheckingAccount(1001, 10000, standardCard);
//
//        System.out.println("Withdrawal limit: " + standardCard.getDailyWithdrawalLimit());
//
//        try {
//            checkingAccount.withdraw(70000000);
//
//            System.out.println("Withdrawal successful!");
//            System.out.println("New balance: " + checkingAccount.getBalance());
//
//        }
//        catch (Exception e) {
//            System.out.println("Withdrawal failed: " + e.getMessage());
//        }


//        MasterCard standardCard =
//                new MasterCard(11111, MasterCardType.STANDARD);
//
//        System.out.println("Deposit limit: " + standardCard.getDailyDepositLimit());
//
//        System.out.println("Own deposit limit: " + standardCard.getDailyOwnAccountDepositLimit());
//
//        System.out.println("Withdrawal limit: " + standardCard.getDailyWithdrawalLimit());
//
//        System.out.println("Transfer limit: " + standardCard.getDailyTransferLimit());
//
//        System.out.println("Own transfer limit: " + standardCard.getDailyOwnAccountTransferLimit());



//        MasterCard mastercard =
//                new MasterCard(11111, MasterCardType.STANDARD);
//
//        CheckingAccount checkingAccount =
//                new CheckingAccount(1001, 1000.0, mastercard);
//
//        SavingsAccount savingsAccount =
//                new SavingsAccount(2001, 500.0, mastercard);
//
//        // Before transfer
//        System.out.println("===== BEFORE TRANSFER =====");
//        System.out.println("Checking balance: "
//                + checkingAccount.getBalance());
//        System.out.println("Savings balance: "
//                + savingsAccount.getBalance());
//
//        // Transfer $300
//        checkingAccount.transfer(savingsAccount, 300);
//
//        // After transfer
//        System.out.println("\n===== AFTER TRANSFER =====");
//        System.out.println("Checking balance: "
//                + checkingAccount.getBalance());
//        System.out.println("Savings balance: "
//                + savingsAccount.getBalance());

        // Transaction history
//        System.out.println("\n===== CHECKING TRANSACTIONS =====");
//
//        for (var transaction : checkingAccount.getTransactions()) {
//            System.out.println(
//                    transaction.getType()
//                            + " | Amount: "
//                            + transaction.getAmount()
//                            + " | Balance: "
//                            + transaction.getBalanceAfterTransaction()
//            );
//        }
//
//        System.out.println("\n=====  TRANSACTIONS =====");
//
//        for (var transaction : savingsAccount.getTransactions()) {
//            System.out.println(
//                    transaction.getType()
//                            + " | Amount: "
//                            + transaction.getAmount()
//                            + " | Balance: "
//                            + transaction.getBalanceAfterTransaction()
//            );
//        }
//    }
//}
//package com.ga.banking;
//
//import com.ga.banking.enums.MasterCardType;
//import com.ga.banking.models.CheckingAccount;
//import com.ga.banking.models.MasterCard;
//import com.ga.banking.models.SavingsAccount;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        MasterCard mastercard = new MasterCard(11111, MasterCardType.STANDARD);
//
////        CheckingAccount checkingAccount = new CheckingAccount(1001, 1000.0, mastercard);
//
////        System.out.println("CA Before: " + checkingAccount.getBalance());
////
////        checkingAccount.deposit(700);
////        System.out.println("Transactions: " + checkingAccount.getTransactions().size());
////
////
////        System.out.println("CA After: " + checkingAccount.getBalance());
//
//        MasterCard master = new MasterCard(2011, MasterCardType.STANDARD);
//        CheckingAccount checkingA = new CheckingAccount(1001, 1000.0, mastercard);
//
//        SavingsAccount savingsAccount = new SavingsAccount(2001, 500.0, mastercard);
//
//        checkingA.transfer(savingsAccount, 300);
//
//        System.out.println("Checking balance: " + checkingA.getBalance());
//
//        System.out.println("Savings balance: " + savingsAccount.getBalance());
//
////        SavingsAccount saveAccount = new SavingsAccount(1001, 1000.0, mastercard);
//
////        System.out.println("sA Before: " + checkingA.getBalance());
////
////        checkingAccount.deposit(8000000);
////        System.out.println(
////                "Transactions: " + checkingAccount.getTransactions().size()
////        );
////
////        System.out.println("sA After: " + checkingAccount.getBalance());
////
////        CheckingAccount checkingAccou = new CheckingAccount(1001, 100.0, mastercard);
////
////        checkingAccou.withdraw(150);
////
////        System.out.println("Balance: " + checkingAccount.getBalance());
////
////        System.out.println("Overdraft count: " + checkingAccount.getOverdraftCount());
////
////        System.out.println("Active: " + checkingAccount.isActive());
////
////        checkingAccou.withdraw(8);
////        System.out.println("Balance: " + checkingAccount.getBalance());
////
////        System.out.println("Overdraft count: " + checkingAccount.getOverdraftCount());
////
////        System.out.println("Active: " + checkingAccount.isActive());
////
////        CheckingAccount checkingA =
////                new CheckingAccount(1001, 1000.0, mastercard);
////
////        SavingsAccount savingsAccount =
////                new SavingsAccount(2001, 500.0, mastercard);
////
////        checkingA.transfer(savingsAccount, 300);
////
////        System.out.println("Checking balance: "
////                + checkingAccount.getBalance());
////
////        System.out.println("Savings balance: "
////                + savingsAccount.getBalance());
//
//
//
    }
}