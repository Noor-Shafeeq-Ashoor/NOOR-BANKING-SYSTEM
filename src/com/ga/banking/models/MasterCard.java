package com.ga.banking.models;

import com.ga.banking.enums.MasterCardType;

public class MasterCard {

    private int cardNumber;
    private MasterCardType masterCardType;

    public MasterCard(int cardNumber, MasterCardType masterCardType) {
        this.masterCardType = masterCardType;
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() { return cardNumber;}

    public void setCardNumber(int cardNumber) { this.cardNumber = cardNumber; }

    public MasterCardType getMasterCardType() { return masterCardType;}

    public void setMasterCardType(MasterCardType masterCardType) { this.masterCardType = masterCardType; }



    public double getDailyDepositLimit() {
        switch (masterCardType) {
            case STANDARD:
                return 100000;

            case TITANIUM:
                return 100000;

            case PLATINUM:
                return 100000;

            default:
                throw new IllegalStateException("Unknown Mastercard type.");
        }
    }

    public double getDailyOwnAccountDepositLimit() {
        switch (masterCardType) {
            case STANDARD:
                return 200000;

            case TITANIUM:
                return 200000;

            case PLATINUM:
                return 200000;

            default:
                throw new IllegalStateException("Unknown Mastercard type.");
        }
    }

    public double getDailyWithdrawalLimit() {
        switch (masterCardType) {
            case STANDARD:
                return 5000;

            case TITANIUM:
                return 10000;

            case PLATINUM:
                return 20000;

            default:
                throw new IllegalStateException("Unknown Mastercard type.");
        }
    }

    public double getDailyTransferLimit() {
        switch (masterCardType) {
            case STANDARD:
                return 10000;

            case TITANIUM:
                return 20000;

            case PLATINUM:
                return 40000;

            default:
                throw new IllegalStateException("Unknown Mastercard type.");
        }
    }

    public double getDailyOwnAccountTransferLimit() {
        switch (masterCardType) {
            case STANDARD:
                return 20000;

            case TITANIUM:
                return 40000;

            case PLATINUM:
                return 80000;

            default:
                throw new IllegalStateException("Unknown Mastercard type.");
        }
    }


}