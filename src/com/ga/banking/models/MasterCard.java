package com.ga.banking.models;

import com.ga.banking.enums.MasterCardType;

public class MasterCard {

    private int cardNumber;
    private MasterCardType masterCardType;

    public MasterCard(int cardNumber, MasterCardType masterCardType) {
        this.masterCardType = masterCardType;
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public MasterCardType getMasterCardType() {
        return masterCardType;
    }

    public void setMasterCardType(MasterCardType masterCardType) {
        this.masterCardType = masterCardType;
    }

    public double WithdrawalLimit() {

        switch (masterCardType) {
            case STANDARD:
                return 5000;

            case TITANIUM:
                return 10000;

            case PLATINUM:
                return 20000;

            default:
                throw new IllegalStateException(
                        "Unknown Mastercard type."
                );
        }
    }

    public double OwnAccTransferLimit() {

        switch (masterCardType) {
            case STANDARD:
                return 20000;

            case TITANIUM:
                return 40000;

            case PLATINUM:
                return 80000;

            default:
                throw new IllegalStateException( "Unknown Mastercard type.");
        }
    }

    public double TransferLimit() {

        switch (masterCardType) {
            case STANDARD:
                return 10000;

            case TITANIUM:
                return 20000;

            case PLATINUM:
                return 40000;

            default:
                throw new IllegalStateException( "Unknown Mastercard type.");
        }
    }
}