package com.ga.banking.models;

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

    public AccountRequest(int requestId, MasterCardType requestedMC, AccountType accountType, Customer customer) {

        this.requestId = requestId;
        this.requestedMC = requestedMC;
        this.accountType = accountType;
        this.customer = customer;
        this.status = RequestStatus.PENDING;
        this.approvedMC = null;
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