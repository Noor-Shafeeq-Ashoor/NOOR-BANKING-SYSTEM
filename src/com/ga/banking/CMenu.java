package com.ga.banking;

import com.ga.banking.models.Customer;
import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.models.AccountRequest;

import java.util.List;

import java.util.Scanner;

public class CMenu {
    private Customer customer;
    private Scanner scanner;
    private List<AccountRequest> requests;

    public CMenu(
            Customer customer,
            Scanner scanner,
            List<AccountRequest> requests) {

        this.customer = customer;
        this.scanner = scanner;
        this.requests = requests;
    }

    public void show() {

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println(" CUSTOMER MENU ");
            System.out.println("Welcome " + customer.getName());
            System.out.println("1. View Accounts");
            System.out.println("2. Request New Account");
            System.out.println("3. Logout");

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAccounts();
                    break;

                case 2:
                    requestNewAccount();
                    break;

                case 3:
                    running = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
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

            System.out.println( (i + 1) + ". Account #" + customer.getAccounts().get(i).getAccountNumber());
        }
    }
    private void requestNewAccount() {

        System.out.println();
        System.out.println("-- REQUEST NEW ACCOUNT --");

        System.out.println("1. Checking");
        System.out.println("2. Savings");

        System.out.print("Choose account type: ");
        int accountChoice = scanner.nextInt();
        scanner.nextLine();

        AccountType accountType;

        if (accountChoice == 1) {

            accountType = AccountType.CHECKING;

        }
        else if (accountChoice == 2) {

            accountType = AccountType.SAVINGS;

        }
        else {

            System.out.println("Invalid account type.");
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

        if (cardChoice == 1) {

            requestedMC = MasterCardType.STANDARD;

        } else if (cardChoice == 2) {

            requestedMC = MasterCardType.TITANIUM;

        } else if (cardChoice == 3) {

            requestedMC = MasterCardType.PLATINUM;

        } else {

            System.out.println("Invalid Mastercard type.");
            return;
        }


        int requestId = requests.size() + 1;

        AccountRequest request =
                new AccountRequest(requestId, requestedMC, accountType, customer);

        requests.add(request);

        System.out.println();
        System.out.println("Account request submitted successfully!");
        System.out.println("Account Type: " + accountType);
        System.out.println("Requested Mastercard: " + requestedMC);
        System.out.println("Status: " + request.getStatus());
    }
}

