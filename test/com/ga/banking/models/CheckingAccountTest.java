package com.ga.banking.models;

import com.ga.banking.enums.MasterCardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    @Test
    void testCheckingAccountConstructor() {

        MasterCard mastercard = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        CheckingAccount account = new CheckingAccount(
                1001,
                1000.0,
                mastercard
        );

        assertEquals(1001, account.getAccountNumber());
        assertEquals(1000.0, account.getBalance());
        assertEquals(mastercard, account.getMastercard());

        assertTrue(account.isActive());
        assertEquals(0, account.getOverdraftCount());

        assertNotNull(account.getTransactions());
        assertTrue(account.getTransactions().isEmpty());
    }


    @Test
    void testCheckingAccountDeposit() {

        MasterCard mastercard = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        CheckingAccount account = new CheckingAccount(
                1001,
                1000.0,
                mastercard
        );

        account.deposit(500.0);

        assertEquals(1500.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }


    @Test
    void testCheckingAccountWithdraw() {

        MasterCard mastercard = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        CheckingAccount account = new CheckingAccount(
                1001,
                1000.0,
                mastercard
        );

        account.withdraw(300.0);

        assertEquals(700.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }
}
