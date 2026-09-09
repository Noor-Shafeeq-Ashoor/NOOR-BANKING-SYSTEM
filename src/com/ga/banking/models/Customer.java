package com.ga.banking.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{

    private List<Account> accounts;

    public Customer(int id, String name, String username, String password) {
        super(id, name, username, password);
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
}
