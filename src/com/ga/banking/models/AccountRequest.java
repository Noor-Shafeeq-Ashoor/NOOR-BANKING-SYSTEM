package com.ga.banking.models;

import com.ga.banking.Service.Authentication;
import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.enums.RequestStatus;

public class AccountRequest {

    private int requestId;
    private Customer customer;
    private AccountType accountType;
    private MasterCardType requestedMC;
    private MasterCardType approvedMC;
    private RequestStatus status;
    private String accountPasswordHash;
    private String accountPasswordSalt;

    public AccountRequest(
            int requestId,
            MasterCardType requestedMC,
            AccountType accountType,
            Customer customer,
            String accountPassword) {

        this.requestId = requestId;
        this.requestedMC = requestedMC;
        this.accountType = accountType;
        this.customer = customer;
        this.status = RequestStatus.PENDING;
        this.approvedMC = null;

        if (!accountPassword.matches("\\d{6}")) {
            throw new IllegalArgumentException(
                    "Account password must be exactly 6 digits."
            );
        }

        this.accountPasswordSalt = Authentication.generateSalt();

        this.accountPasswordHash = Authentication.hashPassword(
                        accountPassword,
                        accountPasswordSalt
                );
    }

    public AccountRequest(
            int requestId,
            MasterCardType requestedMC,
            AccountType accountType,
            Customer customer,
            String passwordHash,
            String passwordSalt,
            MasterCardType approvedMC,
            RequestStatus status) {

        this.requestId = requestId;
        this.requestedMC = requestedMC;
        this.accountType = accountType;
        this.customer = customer;
        this.accountPasswordHash = passwordHash;
        this.accountPasswordSalt = passwordSalt;
        this.approvedMC = approvedMC;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public MasterCardType getRequestedMC() {
        return requestedMC;
    }

    public MasterCardType getApprovedMC() {
        return approvedMC;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public String getAccountPasswordHash() {
        return accountPasswordHash;
    }

    public String getAccountPasswordSalt() {
        return accountPasswordSalt;
    }


    public boolean approve(MasterCardType approvedMC) {

        if (status != RequestStatus.PENDING) {
            return false;
        }

        this.status = RequestStatus.APPROVED;
        this.approvedMC = approvedMC;

        return true;
    }

    public boolean reject() {

        if (status != RequestStatus.PENDING) {
            return false;
        }

        this.status = RequestStatus.REJECTED;

        return true;
    }
}