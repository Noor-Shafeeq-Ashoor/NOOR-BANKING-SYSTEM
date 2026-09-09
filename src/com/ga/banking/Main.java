package com.ga.banking;

import com.ga.banking.enums.MasterCardType;
import com.ga.banking.models.CheckingAccount;
import com.ga.banking.models.MasterCard;
import com.ga.banking.models.SavingsAccount;

public class Main {

    public static void main(String[] args) {

        MasterCard mastercard =
                new MasterCard(11111, MasterCardType.STANDARD);

        CheckingAccount checkingAccount =
                new CheckingAccount(1001, 1000.0, mastercard);

        SavingsAccount savingsAccount =
                new SavingsAccount(2001, 500.0, mastercard);

        // Before transfer
        System.out.println("===== BEFORE TRANSFER =====");
        System.out.println("Checking balance: "
                + checkingAccount.getBalance());
        System.out.println("Savings balance: "
                + savingsAccount.getBalance());

        // Transfer $300
        checkingAccount.transfer(savingsAccount, 300);

        // After transfer
        System.out.println("\n===== AFTER TRANSFER =====");
        System.out.println("Checking balance: "
                + checkingAccount.getBalance());
        System.out.println("Savings balance: "
                + savingsAccount.getBalance());

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

        System.out.println("\n=====  TRANSACTIONS =====");

        for (var transaction : savingsAccount.getTransactions()) {
            System.out.println(
                    transaction.getType()
                            + " | Amount: "
                            + transaction.getAmount()
                            + " | Balance: "
                            + transaction.getBalanceAfterTransaction()
            );
        }
    }
}
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
//    }
//}