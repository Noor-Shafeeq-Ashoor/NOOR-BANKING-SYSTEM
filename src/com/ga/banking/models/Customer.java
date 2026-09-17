package com.ga.banking.models;

import com.ga.banking.Service.Authentication;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private List<Account> accounts;

    public Customer(int id, String username, String email, String password) {
        super(id, username, email, "", "");

        String salt = Authentication.generateSalt();
        String hash = Authentication.hashPassword(password, salt);
        setPassSalt(salt);
        setPassHash(hash);
        accounts = new ArrayList<>();
    }

    // loading an existing customer from file
    public Customer(int id, String username, String email, String passHash, String passSalt) {
        super(id, username, email, passHash, passSalt);
        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}