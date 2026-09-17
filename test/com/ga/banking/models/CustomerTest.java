package com.ga.banking.models;

import com.ga.banking.enums.MasterCardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerConstructor() {

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "123456"
        );

        assertEquals(1, customer.getId());
        assertEquals("noor", customer.getUsername());
        assertEquals("noor@gmail.com", customer.getEmail());

        assertNotNull(customer.getPassHash());
        assertNotNull(customer.getPassSalt());

        assertNotNull(customer.getAccounts());
        assertTrue(customer.getAccounts().isEmpty());
    }




    @Test
    void testAddAccount() {

        Customer customer = new Customer(
                3,
                "sara",
                "sara@gmail.com",
                "123456"
        );

        MasterCard mastercard = new MasterCard(
                12345678,
                MasterCardType.STANDARD
        );

        Account account = new CheckingAccount(
                1001,
                500.0,
                mastercard
        );

        customer.addAccount(account);

        assertEquals(1, customer.getAccounts().size());
        assertSame(account, customer.getAccounts().get(0));
    }


    @Test
    void testAddMultipleAccounts() {

        Customer customer = new Customer(
                4,
                "test",
                "test@gmail.com",
                "123456"
        );

        MasterCard standardCard = new MasterCard(
                11111111,
                MasterCardType.STANDARD
        );

        MasterCard titaniumCard = new MasterCard(
                22222222,
                MasterCardType.TITANIUM
        );

        Account checking = new CheckingAccount(
                1001,
                500.0,
                standardCard
        );

        Account savings = new SavingsAccount(
                1002,
                1000.0,
                titaniumCard
        );

        customer.addAccount(checking);
        customer.addAccount(savings);

        assertEquals(2, customer.getAccounts().size());

        assertSame(
                checking,
                customer.getAccounts().get(0)
        );

        assertSame(
                savings,
                customer.getAccounts().get(1)
        );
    }


    @Test
    void testLoadedCustomerConstructor() {

        Customer customer = new Customer(
                5,
                "loadedUser",
                "loaded@gmail.com",
                "storedHash",
                "storedSalt"
        );

        assertEquals(5, customer.getId());
        assertEquals("loadedUser", customer.getUsername());
        assertEquals("loaded@gmail.com", customer.getEmail());

        assertEquals(
                "storedHash",
                customer.getPassHash()
        );

        assertEquals(
                "storedSalt",
                customer.getPassSalt()
        );

        assertNotNull(customer.getAccounts());
        assertTrue(customer.getAccounts().isEmpty());
    }
}
