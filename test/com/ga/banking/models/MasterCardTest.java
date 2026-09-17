package com.ga.banking.models;

import com.ga.banking.enums.MasterCardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasterCardTest {

    @Test
    void testConstructorAndGetters() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        assertEquals(12345678, card.getCardNumber());
        assertEquals(MasterCardType.STANDARD, card.getMasterCardType());
    }

    @Test
    void testSetCardNumber() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        card.setCardNumber(87654321);

        assertEquals(87654321, card.getCardNumber());
    }

    @Test
    void testSetMasterCardType() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        card.setMasterCardType(MasterCardType.PLATINUM);

        assertEquals(MasterCardType.PLATINUM, card.getMasterCardType());
    }

    @Test
    void testStandardLimits() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        assertEquals(100000, card.getDailyDepositLimit());
        assertEquals(200000, card.getDailyOwnAccountDepositLimit());
        assertEquals(5000, card.getDailyWithdrawalLimit());
        assertEquals(10000, card.getDailyTransferLimit());
        assertEquals(20000, card.getDailyOwnAccountTransferLimit());
    }

    @Test
    void testTitaniumLimits() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.TITANIUM
        );

        assertEquals(100000, card.getDailyDepositLimit());
        assertEquals(200000, card.getDailyOwnAccountDepositLimit());
        assertEquals(10000, card.getDailyWithdrawalLimit());
        assertEquals(20000, card.getDailyTransferLimit());
        assertEquals(40000, card.getDailyOwnAccountTransferLimit());
    }

    @Test
    void testPlatinumLimits() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.PLATINUM
        );

        assertEquals(100000, card.getDailyDepositLimit());
        assertEquals(200000, card.getDailyOwnAccountDepositLimit());
        assertEquals(20000, card.getDailyWithdrawalLimit());
        assertEquals(40000, card.getDailyTransferLimit());
        assertEquals(80000, card.getDailyOwnAccountTransferLimit());
    }

    @Test
    void testChangeFromStandardToPlatinum() {

        MasterCard card = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        assertEquals(5000, card.getDailyWithdrawalLimit());

        card.setMasterCardType(MasterCardType.PLATINUM);

        assertEquals(20000, card.getDailyWithdrawalLimit());
        assertEquals(40000, card.getDailyTransferLimit());
        assertEquals(80000, card.getDailyOwnAccountTransferLimit());
    }
}
