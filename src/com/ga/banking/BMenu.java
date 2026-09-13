package com.ga.banking;

import com.ga.banking.enums.MasterCardType;
import com.ga.banking.models.AccountRequest;
import com.ga.banking.Service.BankerService;
import com.ga.banking.models.Account;
import com.ga.banking.models.MasterCard;
import com.ga.banking.  Service.AccountService;
import com.ga.banking.Generation.NumberGenerator;

import java.util.List;
import java.util.Scanner;

public class BMenu {

    private Scanner scanner;
    private BankerService bankerService;
    private AccountService accountService;

    public BMenu(Scanner scanner, List<AccountRequest> requests) {
        this.scanner = scanner;
        this.bankerService = new BankerService(requests);
        this.accountService = new AccountService();

    }

    public void show() {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("===== BANKER MENU =====");
            System.out.println("1. View Account Requests");
            System.out.println("2. Approve Request");
            System.out.println("3. Reject Request");
            System.out.println("4. Logout");

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    bankerService.viewRequests();
                    break;

                case 2:
                    approveRequest();
                    break;

                case 3:
                    rejectRequest();
                    break;

                case 4:
                    running = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void approveRequest() {

        System.out.print("Enter Request ID: ");
        int requestId = scanner.nextInt();
        scanner.nextLine();

        System.out.println();
        System.out.println("Choose Mastercard to give:");
        System.out.println("1. Standard");
        System.out.println("2. Titanium");
        System.out.println("3. Platinum");

        System.out.print("Choose: ");
        int cardChoice = scanner.nextInt();
        scanner.nextLine();

        MasterCardType approvedMC;

        if (cardChoice == 1) {
            approvedMC = MasterCardType.STANDARD;

        } else if (cardChoice == 2) {
            approvedMC = MasterCardType.TITANIUM;

        } else if (cardChoice == 3) {
            approvedMC = MasterCardType.PLATINUM;

        } else {
            System.out.println("Invalid Mastercard type.");
            return;
        }

        // Banker approves the request
        AccountRequest approvedRequest =
                bankerService.approveRequest(
                        requestId,
                        approvedMC
                );

        // Stop if request was not found
        if (approvedRequest == null) {
            return;
        }

        // Create Mastercard using the type chosen by Banker
        MasterCard mastercard = new MasterCard(NumberGenerator.generateCardNumber(), approvedRequest.getApprovedMC());

        // Create the actual bank account
        Account account =
                accountService.createAccount(approvedRequest, NumberGenerator.generateAccountNumber(), 0.0, mastercard);

        System.out.println();
        System.out.println("===== ACCOUNT CREATED =====");
        System.out.println(
                "Account Number: " + account.getAccountNumber()
        );
        System.out.println(
                "Account Type: " + approvedRequest.getAccountType()
        );
        System.out.println(
                "Mastercard: " + approvedRequest.getApprovedMC()
        );
    }

    private void rejectRequest() {

        System.out.print("Enter Request ID: ");
        int requestId = scanner.nextInt();
        scanner.nextLine();

        bankerService.rejectRequest(requestId);
    }
}