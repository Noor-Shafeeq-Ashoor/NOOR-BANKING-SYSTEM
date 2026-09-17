package com.ga.banking.models;

import com.ga.banking.enums.AccountType;
import com.ga.banking.enums.MasterCardType;
import com.ga.banking.enums.RequestStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRequestTest {

    @Test
    void testConstructorAndGetters() {

        Customer customer = new Customer(
                1,
                "noor",
                "noor@gmail.com",
                "123456"
        );

        AccountRequest request = new AccountRequest(
                1,
                MasterCardType.STANDARD,
                AccountType.CHECKING,
                customer,
                "123456"
        );

        assertEquals(1, request.getRequestId());
        assertEquals(customer, request.getCustomer());
        assertEquals(AccountType.CHECKING, request.getAccountType());
        assertEquals(MasterCardType.STANDARD, request.getRequestedMC());

        assertNull(request.getApprovedMC());
        assertEquals(RequestStatus.PENDING, request.getStatus());

        assertNotNull(request.getAccountPasswordHash());
        assertNotNull(request.getAccountPasswordSalt());
    }


    @Test
    void testApproveRequest() {

        Customer customer = new Customer(
                2,
                "ali",
                "ali@gmail.com",
                "123456"
        );

        AccountRequest request = new AccountRequest(
                2,
                MasterCardType.STANDARD,
                AccountType.SAVINGS,
                customer,
                "123456"
        );

        boolean result = request.approve(MasterCardType.TITANIUM);

        assertTrue(result);
        assertEquals(RequestStatus.APPROVED, request.getStatus());
        assertEquals(
                MasterCardType.TITANIUM,
                request.getApprovedMC()
        );
    }


    @Test
    void testCannotApproveAlreadyApprovedRequest() {

        Customer customer = new Customer(
                3,
                "test",
                "test@gmail.com",
                "123456"
        );

        AccountRequest request = new AccountRequest(
                3,
                MasterCardType.STANDARD,
                AccountType.CHECKING,
                customer,
                "123456"
        );

        request.approve(MasterCardType.STANDARD);

        boolean result = request.approve(MasterCardType.PLATINUM);

        assertFalse(result);
        assertEquals(
                MasterCardType.STANDARD,
                request.getApprovedMC()
        );
    }


    @Test
    void testRejectRequest() {

        Customer customer = new Customer(
                4,
                "sara",
                "sara@gmail.com",
                "123456"
        );

        AccountRequest request = new AccountRequest(
                4,
                MasterCardType.PLATINUM,
                AccountType.SAVINGS,
                customer,
                "123456"
        );

        boolean result = request.reject();

        assertTrue(result);
        assertEquals(RequestStatus.REJECTED, request.getStatus());
    }


    @Test
    void testCannotRejectAlreadyRejectedRequest() {

        Customer customer = new Customer(
                5,
                "test2",
                "test2@gmail.com",
                "123456"
        );

        AccountRequest request = new AccountRequest(
                5,
                MasterCardType.STANDARD,
                AccountType.CHECKING,
                customer,
                "123456"
        );

        request.reject();

        boolean result = request.reject();

        assertFalse(result);
        assertEquals(RequestStatus.REJECTED, request.getStatus());
    }


    @Test
    void testCannotApproveRejectedRequest() {

        Customer customer = new Customer(
                6,
                "test3",
                "test3@gmail.com",
                "123456"
        );

        AccountRequest request = new AccountRequest(
                6,
                MasterCardType.STANDARD,
                AccountType.CHECKING,
                customer,
                "123456"
        );

        request.reject();

        boolean result = request.approve(MasterCardType.PLATINUM);

        assertFalse(result);
        assertEquals(RequestStatus.REJECTED, request.getStatus());
        assertNull(request.getApprovedMC());
    }


    @Test
    void testInvalidPasswordTooShort() {

        Customer customer = new Customer(
                7,
                "test4",
                "test4@gmail.com",
                "123456"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new AccountRequest(
                        7,
                        MasterCardType.STANDARD,
                        AccountType.CHECKING,
                        customer,
                        "12345"
                )
        );
    }


    @Test
    void testInvalidPasswordContainsLetters() {

        Customer customer = new Customer(
                8,
                "test5",
                "test5@gmail.com",
                "123456"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new AccountRequest(
                        8,
                        MasterCardType.STANDARD,
                        AccountType.CHECKING,
                        customer,
                        "12a456"
                )
        );
    }
}
