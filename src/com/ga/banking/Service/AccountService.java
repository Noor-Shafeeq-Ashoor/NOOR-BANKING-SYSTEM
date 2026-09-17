
package com.ga.banking.Service;

import com.ga.banking.enums.AccountType;
import com.ga.banking.models.Account;
import com.ga.banking.models.AccountRequest;
import com.ga.banking.models.CheckingAccount;
import com.ga.banking.models.MasterCard;
import com.ga.banking.models.SavingsAccount;

public class AccountService {

    public Account createAccount(
            AccountRequest request,
            int accountNumber,
            double initialBalance,
            MasterCard mastercard) {

        Account account;

        // =========================
        // CREATE ACCOUNT
        // =========================

        if (request.getAccountType() == AccountType.CHECKING) {

            account = new CheckingAccount(
                    accountNumber,
                    initialBalance,
                    mastercard
            );

        } else {

            account = new SavingsAccount(
                    accountNumber,
                    initialBalance,
                    mastercard
            );
        }


        // =========================
        // ACCOUNT PASSWORD
        // =========================

        account.setAccountPasswordHash(
                request.getAccountPasswordHash()
        );

        account.setAccountPasswordSalt(
                request.getAccountPasswordSalt()
        );


        // =========================
        // ADD TO CUSTOMER
        // =========================

        request.getCustomer().addAccount(account);

        return account;
    }
}