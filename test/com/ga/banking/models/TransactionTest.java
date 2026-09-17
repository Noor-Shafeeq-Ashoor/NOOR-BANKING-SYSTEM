package com.ga.banking.models;

import com.ga.banking.enums.TransctionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransactionConstructorAndGetters() {

        Transaction transaction = new Transaction(
                1,
                TransctionType.DEPOSIT,
                500.0,
                1500.0
        );

        assertEquals(1, transaction.getTransactionId());
        assertEquals(TransctionType.DEPOSIT, transaction.getType());
        assertEquals(500.0, transaction.getAmount());
        assertEquals(1500.0, transaction.getBalanceAfterTransaction());

        assertNotNull(transaction.getDateTime());
    }

    @Test
    void testTransactionConstructorWithDateTime() {

        LocalDateTime dateTime = LocalDateTime.of(2026, 9, 17, 8, 30);

        Transaction transaction = new Transaction(
                2,
                TransctionType.WITHDRAW,
                200.0,
                1300.0,
                dateTime
        );

        assertEquals(2, transaction.getTransactionId());
        assertEquals(TransctionType.WITHDRAW, transaction.getType());
        assertEquals(200.0, transaction.getAmount());
        assertEquals(1300.0, transaction.getBalanceAfterTransaction());
        assertEquals(dateTime, transaction.getDateTime());
    }

    @Test
    void testTransactionId() {

        Transaction transaction = new Transaction(
                10,
                TransctionType.DEPOSIT,
                100.0,
                600.0
        );

        assertEquals(10, transaction.getTransactionId());
    }

    @Test
    void testTransactionType() {

        Transaction transaction = new Transaction(
                11,
                TransctionType.WITHDRAW,
                100.0,
                400.0
        );

        assertEquals(TransctionType.WITHDRAW, transaction.getType());
    }

    @Test
    void testTransactionAmount() {

        Transaction transaction = new Transaction(
                12,
                TransctionType.DEPOSIT,
                250.0,
                750.0
        );

        assertEquals(250.0, transaction.getAmount());
    }

    @Test
    void testBalanceAfterTransaction() {

        Transaction transaction = new Transaction(
                13,
                TransctionType.DEPOSIT,
                300.0,
                1300.0
        );

        assertEquals(1300.0, transaction.getBalanceAfterTransaction());
    }

    @Test
    void testDateTimeIsCreatedAutomatically() {

        Transaction transaction = new Transaction(
                14,
                TransctionType.DEPOSIT,
                100.0,
                500.0
        );

        assertNotNull(transaction.getDateTime());
    }
}
