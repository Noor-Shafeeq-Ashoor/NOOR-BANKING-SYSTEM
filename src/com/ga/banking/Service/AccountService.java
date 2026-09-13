package com.ga.banking.Service;

import com.ga.banking.enums.AccountType;
import com.ga.banking.models.*;

public class AccountService {

    public Account createAccount(
            AccountRequest request,
            int accountNumber,
            double initialBalance,
            MasterCard mastercard) {

        Account account;

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

        request.getCustomer().addAccount(account);

        return account;
    }
}