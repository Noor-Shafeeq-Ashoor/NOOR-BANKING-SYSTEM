package com.ga.banking.models;

import com.ga.banking.Exception.AccountInactiveEx;
import com.ga.banking.Exception.InsufficientFundsEx;
import com.ga.banking.Exception.InvalidAmountEx;
import com.ga.banking.enums.MasterCardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private MasterCard standardCard() {
        return new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );
    }

    private CheckingAccount createAccount(double balance) {
        return new CheckingAccount(
                1001,
                balance,
                standardCard()
        );
    }


    // =========================
    // DEPOSIT
    // =========================

    @Test
    void testDeposit() {

        Account account = createAccount(1000.0);

        account.deposit(500.0);

        assertEquals(1500.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
        assertEquals(500.0,
                account.getTransactions().get(0).getAmount());
    }


    @Test
    void testDepositInvalidAmount() {

        Account account = createAccount(1000.0);

        assertThrows(
                InvalidAmountEx.class,
                () -> account.deposit(0)
        );

        assertThrows(
                InvalidAmountEx.class,
                () -> account.deposit(-100)
        );
    }


    @Test
    void testDepositCreatesTransaction() {

        Account account = createAccount(1000.0);

        account.deposit(500.0);

        Transaction transaction =
                account.getTransactions().get(0);

        assertEquals(1, transaction.getTransactionId());
        assertEquals(500.0, transaction.getAmount());
        assertEquals(1500.0,
                transaction.getBalanceAfterTransaction());
    }


    // =========================
    // WITHDRAW
    // =========================

    @Test
    void testWithdraw() {

        Account account = createAccount(1000.0);

        account.withdraw(300.0);

        assertEquals(700.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }


    @Test
    void testWithdrawInvalidAmount() {

        Account account = createAccount(1000.0);

        assertThrows(
                InvalidAmountEx.class,
                () -> account.withdraw(0)
        );

        assertThrows(
                InvalidAmountEx.class,
                () -> account.withdraw(-50)
        );
    }


    @Test
    void testOverdraft() {

        Account account = createAccount(100.0);

        account.withdraw(150.0);

        // 100 - 150 - 35 fee = -85
        assertEquals(-85.0, account.getBalance());
        assertEquals(1, account.getOverdraftCount());
    }


    @Test
    void testOverdraftLimitExceeded() {

        Account account = createAccount(100.0);

        assertThrows(
                InsufficientFundsEx.class,
                () -> account.withdraw(201.0)
        );

        assertEquals(100.0, account.getBalance());
    }


    @Test
    void testAccountDeactivatesAfterTwoOverdrafts() {

        Account account = createAccount(100.0);

        account.withdraw(150.0);

        // Bring balance back up so we can overdraft again
        account.deposit(200.0);

        account.withdraw(150.0);

        assertEquals(2, account.getOverdraftCount());
        assertFalse(account.isActive());
    }


    // =========================
    // TRANSFER
    // =========================

    @Test
    void testTransfer() {

        Account source = createAccount(1000.0);
        Account target = new CheckingAccount(
                2002,
                500.0,
                standardCard()
        );

        source.transfer(target, 300.0);

        assertEquals(700.0, source.getBalance());
        assertEquals(800.0, target.getBalance());
    }


    @Test
    void testTransferCreatesTransactions() {

        Account source = createAccount(1000.0);
        Account target = new CheckingAccount(
                2002,
                500.0,
                standardCard()
        );

        source.transfer(target, 300.0);

        assertEquals(1, source.getTransactions().size());
        assertEquals(1, target.getTransactions().size());

        assertEquals(
                300.0,
                source.getTransactions().get(0).getAmount()
        );

        assertEquals(
                300.0,
                target.getTransactions().get(0).getAmount()
        );
    }


    @Test
    void testTransferInvalidAmount() {

        Account source = createAccount(1000.0);
        Account target = createAccount(500.0);

        assertThrows(
                InvalidAmountEx.class,
                () -> source.transfer(target, 0)
        );

        assertThrows(
                InvalidAmountEx.class,
                () -> source.transfer(target, -100)
        );
    }


    @Test
    void testTransferNullAccount() {

        Account source = createAccount(1000.0);

        assertThrows(
                InvalidAmountEx.class,
                () -> source.transfer(null, 100)
        );
    }


    @Test
    void testTransferInsufficientFunds() {

        Account source = createAccount(100.0);
        Account target = createAccount(500.0);

        assertThrows(
                InsufficientFundsEx.class,
                () -> source.transfer(target, 200)
        );
    }


    // =========================
    // ACCOUNT ACTIVE / INACTIVE
    // =========================

    @Test
    void testDeactivateAccount() {

        Account account = createAccount(1000.0);

        account.deactivate();

        assertFalse(account.isActive());
    }


    @Test
    void testInactiveAccountCannotDeposit() {

        Account account = createAccount(1000.0);

        account.deactivate();

        assertThrows(
                AccountInactiveEx.class,
                () -> account.deposit(100)
        );
    }


    @Test
    void testInactiveAccountCannotWithdraw() {

        Account account = createAccount(1000.0);

        account.deactivate();

        assertThrows(
                AccountInactiveEx.class,
                () -> account.withdraw(100)
        );
    }


    @Test
    void testInactiveAccountCannotTransfer() {

        Account source = createAccount(1000.0);
        Account target = createAccount(500.0);

        source.deactivate();

        assertThrows(
                AccountInactiveEx.class,
                () -> source.transfer(target, 100)
        );
    }


    // =========================
    // PASSWORD
    // =========================

    @Test
    void testSetAndVerifyAccountPassword() {

        Account account = createAccount(1000.0);

        account.setAccountPassword("123456");

        assertNotNull(account.getAccountPasswordHash());
        assertNotNull(account.getAccountPasswordSalt());

        assertTrue(
                account.verifyAccountPassword("123456")
        );

        assertFalse(
                account.verifyAccountPassword("654321")
        );
    }


    @Test
    void testInvalidAccountPassword() {

        Account account = createAccount(1000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.setAccountPassword("12345")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> account.setAccountPassword("12345a")
        );
    }


    // =========================
    // OWNER ID
    // =========================

    @Test
    void testOwnerId() {

        Account account = createAccount(1000.0);

        account.setOwnerId(50);

        assertEquals(50, account.getOwnerId());
    }
}
